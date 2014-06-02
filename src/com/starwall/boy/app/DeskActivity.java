package com.starwall.boy.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import com.starwall.boy.R;
import com.starwall.boy.adapter.GridDeskItemAdapter;
import com.starwall.boy.bean.Desk;
import com.starwall.boy.common.UIHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Antony on 14-5-29.
 */
public class DeskActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desk);
        initDeskGridView();
    }

    private void initDeskGridView() {

        ArrayList<Desk> listData = new ArrayList<Desk>();

        for (int i = 0; i < 120; i++) {
            Desk desk = new Desk();
            desk.setName("No." + i);
            desk.setStatus("2002");
            listData.add(desk);
        }

        GridView gridView = (GridView) findViewById(R.id.deskGridView);
        gridView.setAdapter(new GridDeskItemAdapter(this, listData, R.layout.desk_item));
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