package com.du.iit.zayed.vlrp_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.du.iit.zayed.vlrp_android.Utils.Tools;
import com.du.iit.zayed.vlrp_android.adapter.ApiAdapter;
import com.du.iit.zayed.vlrp_android.adapter.RecyclerViewListAdapter;
import com.du.iit.zayed.vlrp_android.models.LoginPost;
import com.du.iit.zayed.vlrp_android.models.LoginResponse;
import com.du.iit.zayed.vlrp_android.models.VehicleResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Zayed on 05-Nov-16.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameEditText;
    EditText passwordEditText;
    Button submitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        usernameEditText=(EditText) findViewById(R.id.et_username);
        passwordEditText=(EditText) findViewById(R.id.et_password);
        submitButton=(Button) findViewById(R.id.btn_login);

        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_login:
                LoginPost loginPost=new LoginPost(usernameEditText.getText().toString(),passwordEditText.getText().toString());

                ApiAdapter apiAdapter=new ApiAdapter();
                Call<LoginResponse> call=apiAdapter.vlrpApi.login(loginPost);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Response<LoginResponse> response, Retrofit retrofit) {
                        LoginResponse loginResponse=response.body();
                        if(loginResponse.getSuccess().equals("true"))
                        {
                            Tools.AuthToken=loginResponse.getMessage().toString();
                            Toast.makeText(LoginActivity.this,loginResponse.getMessage().toString(),Toast.LENGTH_LONG).show();

                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else
                        {
                            Toast.makeText(LoginActivity.this,"Username Or Password do not match",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                    }
                });
                break;
        }
    }
}
