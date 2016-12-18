package com.example.yujin.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sonaj on 2016-12-17.
 */
public class MoimCustomListviewAdapter extends BaseAdapter {


    private ArrayList<MoimItemData> MoimItemDatas = new ArrayList<MoimItemData>();

    public MoimCustomListviewAdapter(){

    }

    @Override
    public int getCount() {
        return MoimItemDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return MoimItemDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.moin_list_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView Title = (TextView) convertView.findViewById(R.id.Item_Title);
        TextView Date = (TextView)convertView.findViewById(R.id.Item_date);
        TextView Time = (TextView)convertView.findViewById(R.id.Item_time);
        TextView location = (TextView)convertView.findViewById(R.id.Item_location);
        ImageView lock = (ImageView)convertView.findViewById(R.id.image_lock);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
      //  MoimItemData listViewItem = new MoimItemData();
        MoimItemData listViewItem = MoimItemDatas.get(position);

        Title.setText(listViewItem.getTitle());
        Date.setText(listViewItem.getDate());
        Time.setText(listViewItem.getTime());
        location.setText(listViewItem.getLocation());
        lock.setImageDrawable(listViewItem.getLock());
//        listViewItem.setTitle(Title.toString());
//        listViewItem.setDate(Date.toString());
//        listViewItem.setTime(Time.toString());
//        listViewItem.setLocation(location.toString());
//        listViewItem.setIcom(lock.getDrawable());


        return convertView;
    }


    public void addItem(Drawable lock, String title, String date, String time, String location,String secret,String pwd){
        MoimItemData item = new MoimItemData();

        item.setIcom(lock);
        item.setTitle(title);
        item.setDate(date);
        item.setTime(time);
        item.setLocation(location);
        item.setSecret(secret);
        item.setPwd(pwd);

        MoimItemDatas.add(item);
    }

}
