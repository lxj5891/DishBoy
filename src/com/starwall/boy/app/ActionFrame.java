package com.starwall.boy.app;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.starwall.boy.R;

/**
 * Created by mac on 14-6-1.
 */
public class ActionFrame extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("ActionFrame","ActionFrame");
        return inflater.inflate(R.layout.action_fragment,container,false);
    }
}
