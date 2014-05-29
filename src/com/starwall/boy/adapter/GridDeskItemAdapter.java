package com.starwall.boy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.starwall.boy.app.R;
import com.starwall.boy.bean.Desk;

import java.util.List;

/**
 * Created by Antony on 14-5-30.
 */
public class GridDeskItemAdapter extends BaseAdapter {

    private Context context;//运行上下文
    private List<Desk> listItems;//数据集合
    private LayoutInflater listContainer;//视图容器
    private int itemViewResource;//自定义项视图源


    static class DeskItemView {                //自定义控件集合
        public TextView name;
        public TextView status;
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

    // 获取图片在库中的位置
    public Object getItem(int position) {
        return position;
    }

    // 获取图片ID
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
            convertView.setTag(deskItemView);
        } else {

            deskItemView = (DeskItemView) convertView.getTag();
        }
        return convertView;
    }
}
