package com.starwall.boy.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import com.starwall.boy.adapter.GridDeskItemAdapter;
import com.starwall.boy.bean.Desk;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Antony on 14-5-29.
 */
public class DeskActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDeskGridView();
        setContentView(R.layout.desk);
    }

    private void initDeskGridView() {

        ArrayList<Desk> listData = new ArrayList<Desk>();

        for (int i = 0; i < 20; i++) {
            Desk desk = new Desk();
            desk.setName("No." + i);
            desk.setStatus("2002");
            listData.add(desk);
        }


        GridView gridView = (GridView) findViewById(R.id.deskGridView);
        gridView.setAdapter(new GridDeskItemAdapter(getApplicationContext(), listData, R.layout.desk_item));
    }
}