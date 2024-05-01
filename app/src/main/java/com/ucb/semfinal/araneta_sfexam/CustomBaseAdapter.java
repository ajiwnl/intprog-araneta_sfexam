package com.ucb.semfinal.araneta_sfexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    String[] daylist;
    String[] timelist;
    String[] codelist;

    String[] desclist;

    String[] roomlist;


    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, String day[], String time[], String code[], String desc[], String room[]) {
        this.context = ctx;
        this.daylist = day;
        this.timelist = time;
        this.codelist = code;
        this.desclist = desc;
        this.roomlist = room;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return daylist.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.araneta_itemlist, parent, false);
        }

        TextView txtView1 = convertView.findViewById(R.id.dayView);
        TextView txtView2 = convertView.findViewById(R.id.timeView);
        TextView txtView3 = convertView.findViewById(R.id.codeView);
        TextView txtView4 = convertView.findViewById(R.id.descView);
        TextView txtView5 = convertView.findViewById(R.id.roomView);

        txtView1.setText(daylist[position]);
        txtView2.setText(timelist[position]);
        txtView3.setText(codelist[position]);
        txtView4.setText(desclist[position]);
        txtView5.setText(roomlist[position]);

        return convertView;
    }

    public void updateData(List<String> dayList, List<String> timeList, List<String> codeList, List<String> descList, List<String> roomList) {
        this.daylist = dayList.toArray(new String[0]);
        this.timelist = timeList.toArray(new String[0]);
        this.codelist = codeList.toArray(new String[0]);
        this.desclist = descList.toArray(new String[0]);
        this.roomlist = roomList.toArray(new String[0]);
        notifyDataSetChanged();
    }

}
