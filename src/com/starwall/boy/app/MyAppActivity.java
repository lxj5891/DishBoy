package com.starwall.boy.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.starwall.boy.AppContext;
import com.starwall.boy.AppException;
import com.starwall.boy.R;
import com.starwall.boy.api.ApiClient;
import com.starwall.boy.bean.User;

import java.util.logging.Handler;

public class MyAppActivity extends Activity {


    private final static int DATA_LOAD_ING = 0x001;
    private final static int DATA_LOAD_COMPLETE = 0x002;
    private final static int DATA_LOAD_FAIL = 0x003;

    /**
     * Called when the activity is first created.
     */
    Button loginBtn;
    EditText userText;
    EditText passwordText;
    private ProgressBar mProgressbar;

    Handler loginHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        loginBtn =  (Button)findViewById(R.id.loginBtn);
        userText = (EditText) findViewById(R.id.editText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        mProgressbar = (ProgressBar) findViewById(R.id.loginProgressBar);

        loginBtn.setOnClickListener(new LoginOnClick());

        statusSwitch(DATA_LOAD_COMPLETE);
    }

    private void statusSwitch(int type) {
        switch (type) {
            case DATA_LOAD_ING:
                loginBtn.setEnabled(false);
                mProgressbar.setVisibility(View.VISIBLE);
                break;
            case DATA_LOAD_COMPLETE:
                loginBtn.setEnabled(true);
                mProgressbar.setVisibility(View.GONE);
                break;
            case DATA_LOAD_FAIL:
                loginBtn.setEnabled(true);
                mProgressbar.setVisibility(View.GONE);
                break;
        }
    }

    public class LoginOnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            statusSwitch(DATA_LOAD_ING);

            final String username = userText.getText().toString();
            final String password = passwordText.getText().toString();

            final android.os.Handler handler = new android.os.Handler() {

                public void handleMessage(Message msg) {

                    if (msg.what == 1) {

                        Intent intent = new Intent();
                        intent.setClass(MyAppActivity.this, DeskActivity.class);
                        startActivity(intent);

                        finish();
                    } else {

                        Context context = getApplicationContext();
                        Toast toast = Toast.makeText(context, "密码错误", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    statusSwitch(DATA_LOAD_COMPLETE);
                }
            };

            new Thread(){
                public void run() {

                    Message msg =new Message();
                    try {
                        User user = ApiClient.login((AppContext) getApplicationContext(),username,password);
                        Log.i("MyAppActivity", user.toString());
                        msg.what = 1;

                    } catch (AppException e) {
                        msg.what = -1;
                        msg.obj = e;
                    }
                    handler.sendMessage(msg);
                }
            }.start();
        }
    }
}
