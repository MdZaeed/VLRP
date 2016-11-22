package com.du.iit.zayed.vlrp_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.du.iit.zayed.vlrp_android.Utils.Tools;
import com.du.iit.zayed.vlrp_android.adapter.ApiAdapter;
import com.du.iit.zayed.vlrp_android.models.LoginPost;
import com.du.iit.zayed.vlrp_android.models.LoginResponse;
import com.du.iit.zayed.vlrp_android.models.RegisterPostModel;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Zayed on 05-Nov-16.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView loginTextView;
    EditText fullnameEditText;
    EditText universityEditText;
    EditText departmentEditText;
    EditText instiuteEditText;
    EditText emailEditText;
    EditText passwordEditText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        loginTextView=(TextView) findViewById(R.id.tv_redirect_to_login);

        loginTextView.setOnClickListener(this);
        fullnameEditText=(EditText) findViewById(R.id.et_full_name_reg);
        departmentEditText=(EditText) findViewById(R.id.et_department_reg);
        instiuteEditText=(EditText) findViewById(R.id.et_institute_reg);
        emailEditText=(EditText) findViewById(R.id.et_email_reg);
        passwordEditText=(EditText) findViewById(R.id.et_password_reg);
    }

    public void gotoActivity(AppCompatActivity appCompatActivity)
    {
        Intent intent=new Intent(this,appCompatActivity.getClass());
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tv_redirect_to_login:
                gotoActivity(new LoginActivity());
                break;

            case R.id.btn_register:
                register();
                break;
        }
    }

    private void register() {
        RegisterPostModel registerPostModel=new RegisterPostModel(fullnameEditText.getText().toString(),"",departmentEditText.getText().toString(),instiuteEditText.getText().toString(),"rohan",emailEditText.toString(),"",passwordEditText.getText().toString());

        ApiAdapter apiAdapter=new ApiAdapter();
        Call<JSONObject> call=apiAdapter.vlrpApi.register(registerPostModel);
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Response<JSONObject> response, Retrofit retrofit) {
                JSONObject jsonObject=response.body();
                gotoActivity(new LoginActivity());
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(RegisterActivity.this,"Failed! Try again",Toast.LENGTH_LONG).show();
            }
        });
    }
}
