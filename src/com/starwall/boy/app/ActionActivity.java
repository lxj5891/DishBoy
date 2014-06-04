package com.starwall.boy.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabWidget;
import com.starwall.boy.AppContext;
import com.starwall.boy.AppException;
import com.starwall.boy.R;
import com.starwall.boy.api.ApiClient;
import com.starwall.boy.common.UIHelper;

/**
 * Created by mac on 14-6-1.
 */
public class ActionActivity extends Activity {

    private Button setupServiceBtn;
    private Button gotoMenuBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.action);



        initActionView();
    }

    public void initActionView() {

        setupServiceBtn.setOnClickListener(onSetupServiceBtnClickListener);
        gotoMenuBtn.setOnClickListener(onGotoMenuBtnClickListener);
    }

    private View.OnClickListener onSetupServiceBtnClickListener = new View.OnClickListener() {

        public void onClick(View v) {

        Log.i("ActionActivity", "onSetupServiceBtnClickListener");

        try {

            ApiClient.login((AppContext) getApplicationContext(),"sara","sara");
        } catch (AppException e) {

            e.printStackTrace();
        }
        }
    };

    private View.OnClickListener onGotoMenuBtnClickListener = new View.OnClickListener() {

        public void onClick(View v) {

        Log.i("ActionActivity", "onGotoMenuBtnClickListener");
        }
    };


}