package com.starwall.boy.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import com.starwall.boy.AppContext;
import com.starwall.boy.AppException;
import com.starwall.boy.R;
import com.starwall.boy.adapter.GridDeskItemAdapter;
import com.starwall.boy.api.ApiClient;
import com.starwall.boy.bean.Desk;
import com.starwall.boy.bean.DeskList;
import com.starwall.boy.common.UIHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Antony on 14-5-29.
 */
public class DeskActivity extends Activity {

//    ActionView actionView;

    private Handler mHandler;
    private Context mContext;
    private TabHost mTabHost;
    private ActionLayout mActionLayout;

    private static DeskList deskList;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.desk);
        mContext = this;


        initDeskGridView();
    }

    private void initDeskGridView() {

        initData();



        mTabHost = (TabHost) findViewById(R.id.tabHost);
        mTabHost.setup();

        mTabHost.addTab(mTabHost.newTabSpec("tab1")
                .setIndicator("常用")
                .setContent(R.id.tab1));

        mTabHost.addTab(mTabHost.newTabSpec("tab2")
                .setIndicator("更多")
                .setContent(R.id.tab2));

        mActionLayout = (ActionLayout) findViewById(R.id.actionLayout);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(100, 100);
//        mActionLayout.setLayoutParams(layoutParams);
    }

    private void initData() {
        mHandler = new Handler()
        {
            public void handleMessage(Message msg)
            {
                if(msg.what == 1)
                {
                    GridView gridView = (GridView) findViewById(R.id.deskGridView);
                    gridView.setAdapter(new GridDeskItemAdapter(mContext, deskList.getItems(), R.layout.desk_item));
                }
                else if(msg.what == 0)
                {

                }
                else if(msg.what == -1 && msg.obj != null)
                {

                }
            }
        };
        getDate();
    }

    private void getDate() {

        new Thread(){
            public void run() {

                Message msg = new Message();
                try {
                    deskList = ApiClient.getDeskList((AppContext) getApplicationContext());
                    msg.what = (deskList!=null) ? 1 : 0;
                } catch (AppException e) {
                    e.printStackTrace();
                    msg.what = -1;
                    msg.obj = e;
                }
                mHandler.sendMessage(msg);
            }
        }.start();
    }

    /*
     * 登录后 禁止回退
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            ExitDialog(DeskActivity.this).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 提示退出系统
     *
     * @param context
     * @return
     */
    private Dialog ExitDialog(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("系统信息");
        builder.setMessage("确定要退出程序吗?");
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
        return builder.create();
    }
}