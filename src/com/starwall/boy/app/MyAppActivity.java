package com.starwall.boy.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyAppActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    Button loginBtn;
    EditText userText;
    EditText passwordText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        loginBtn =  (Button)findViewById(R.id.loginBtn);
        userText = (EditText) findViewById(R.id.editText);
        passwordText = (EditText) findViewById(R.id.passwordText);

        loginBtn.setOnClickListener(new LoginOnClick());
    }

    public class LoginOnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            String username = userText.getText().toString();
            String password = passwordText.getText().toString();

            Log.i("MyAppActivity", "click username : " + username + " password : " + password);
            if(username.equals("1001") && password.equals("1001")) {

                Intent intent = new Intent();
                intent.setClass(MyAppActivity.this, DeskActivity.class);
                intent.putExtra("str", "come from first activity");
                startActivity(intent);//无返回值的调用,启动一个明确的activity

            } else {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "密码错误", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}
