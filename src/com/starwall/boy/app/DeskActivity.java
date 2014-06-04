package com.starwall.boy.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.*;
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
import widget.MyQuickAction;
import widget.QuickActionGrid;
import widget.QuickActionWidget;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Antony on 14-5-29.
 */
public class DeskActivity extends Activity {

//    ActionView actionView;

    public static final int QUICKACTION_LOGIN_OR_LOGOUT = 0;
    public static final int QUICKACTION_USERINFO = 1;
    public static final int QUICKACTION_SOFTWARE = 2;
    public static final int QUICKACTION_SEARCH = 3;
    public static final int QUICKACTION_SETTING = 4;
    public static final int QUICKACTION_EXIT = 5;

    private Handler mHandler;
    private Context mContext;
    private TabHost mTabHost;
    private ActionLayout mActionLayout;

    public QuickActionWidget mGrid;// 快捷栏控件

    private static DeskList deskList;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.desk);
        mContext = this;


        initDeskGridView();
        this.initQuickActionGrid();
    }

    private void initDeskGridView() {

        initData();
    }

    /**
     * 初始化快捷栏
     */
    private void initQuickActionGrid() {

        mGrid = new QuickActionGrid(this);

        mGrid.addQuickAction(new MyQuickAction(this, R.drawable.ic_menu_login,
                R.string.main_menu_setup));

        mGrid.addQuickAction(new MyQuickAction(this, R.drawable.ic_menu_myinfo,
                R.string.main_menu_dish));

        mGrid.addQuickAction(new MyQuickAction(this,
                R.drawable.ic_menu_software, R.string.main_menu_bill));

        mGrid.addQuickAction(new MyQuickAction(this, R.drawable.ic_menu_sweep,
                R.string.main_menu_setup));

        mGrid.addQuickAction(new MyQuickAction(this,
                R.drawable.ic_menu_setting, R.string.main_menu_setup));

        mGrid.addQuickAction(new MyQuickAction(this, R.drawable.ic_menu_exit,
                R.string.main_menu_setup));

        mGrid.addQuickAction(new MyQuickAction(this,
                R.drawable.ic_menu_setting, R.string.main_menu_setup));

        mGrid.addQuickAction(new MyQuickAction(this, R.drawable.ic_menu_exit,
                R.string.main_menu_setup));

        mGrid.setOnQuickActionClickListener(mActionListener);
    }
    /**
     * 快捷栏item点击事件
     */
    private QuickActionWidget.OnQuickActionClickListener mActionListener = new QuickActionWidget.OnQuickActionClickListener() {
        public void onQuickActionClicked(QuickActionWidget widget, int position) {
            switch (position) {

                case QUICKACTION_LOGIN_OR_LOGOUT:
                    Log.i("DeskActivity.java","开台");
                    break;
                case QUICKACTION_USERINFO:// 我的资料
                    Log.i("DeskActivity.java","点菜");
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), OrderActivity.class);
                    startActivity(intent);
                    break;
                case QUICKACTION_SOFTWARE:// 开源软件
                    break;
                case QUICKACTION_SEARCH://
                    break;
                case QUICKACTION_SETTING:// 设置
                    break;
                case QUICKACTION_EXIT:// 退出
                    break;
            }
        }
    };



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