package com.du.iit.zayed.vlrp_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Zayed on 05-Nov-16.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView loginTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        loginTextView=(TextView) findViewById(R.id.tv_redirect_to_login);

        loginTextView.setOnClickListener(this);
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
        }
    }
}
