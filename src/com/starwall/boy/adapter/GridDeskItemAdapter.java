package com.starwall.boy.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.starwall.boy.R;
import com.starwall.boy.bean.Desk;
import com.starwall.boy.common.UIHelper;

import java.util.List;

/**
 * Created by Antony on 14-5-30.
 */
public class GridDeskItemAdapter extends BaseAdapter {

    private Context context;//运行上下文
    private List<Desk> listItems;//数据集合
    private LayoutInflater listContainer;//视图容器
    private int itemViewResource;//自定义项视图源

    public boolean isDeskItemViewClick() {
        return isDeskItemViewClick;
    }

    public void setDeskItemViewClick(boolean isDeskItemViewClick) {
        this.isDeskItemViewClick = isDeskItemViewClick;
    }

    private boolean isDeskItemViewClick = false;




    static class DeskItemView {                //自定义控件集合
        public TextView name;
        public TextView status;
        public LinearLayout layout;
    }

    public GridDeskItemAdapter(Context context, List<Desk> data, int resource) {
        this.context = context;
        this.listContainer = LayoutInflater.from(context);    //创建视图容器并设置上下文
        this.itemViewResource = resource;
        this.listItems = data;
    }

    public int getCount() {
        return listItems.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        DeskItemView deskItemView = null;

        if (convertView == null) {

            convertView = listContainer.inflate(this.itemViewResource, null);

            deskItemView = new DeskItemView();
            deskItemView.name = (TextView) convertView.findViewById(R.id.deskItemText);
            deskItemView.status = (TextView) convertView.findViewById(R.id.deskStatusText);
            deskItemView.layout = (LinearLayout)convertView.findViewById(R.id.deskItemView);

            convertView.setTag(deskItemView);
        } else {

            deskItemView = (DeskItemView) convertView.getTag();
        }

        Desk desk = listItems.get(position);
        deskItemView.name.setText(desk.getName());

        if (desk.getService() != null) {
            deskItemView.status.setText("状态 " + desk.getService().getStatus());
            deskItemView.layout.setBackgroundColor(Color.rgb(102, 0, 0));

        } else {

            deskItemView.status.setText("空闲");
            deskItemView.layout.setBackgroundColor(Color.parseColor("#ffc18a5d"));
        }

        deskItemView.layout.setOnClickListener(deskItemClickListener);
        return convertView;
    }

    private View.OnClickListener deskItemClickListener = new View.OnClickListener() {

        public void onClick(View v) {

            if(!isDeskItemViewClick()){
                Log.i("GridDeskItemAdapter", "GridDeskItemAdapter Click");
                UIHelper.showDeskAction(context);
            }
            setDeskItemViewClick(false);
        }
    };
}
