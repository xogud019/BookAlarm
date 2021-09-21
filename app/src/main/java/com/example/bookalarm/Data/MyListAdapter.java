package com.example.bookalarm.Data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookalarm.R;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter {
    Context context;
    ArrayList<ListItem> list_itemArrayList;
    ViewHolder viewHolder;

    class ViewHolder{
        TextView price_id,salesPrice_id,title_id,pub_id,aut_id;
        ImageView img_id;
    }

    public MyListAdapter(Context context, ArrayList<ListItem> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }

    @Override
    public int getCount() {
        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            Log.d("xogud","getView");
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
            viewHolder = new ViewHolder();
            viewHolder.title_id = (TextView)convertView.findViewById(R.id.title_id);
            viewHolder.pub_id = (TextView)convertView.findViewById(R.id.pub_id);
            viewHolder.price_id = (TextView)convertView.findViewById(R.id.price_id);
            viewHolder.salesPrice_id = (TextView)convertView.findViewById(R.id.salesPrice_id);
            viewHolder.aut_id = (TextView)convertView.findViewById(R.id.aut_id);
            viewHolder.img_id = (ImageView)convertView.findViewById(R.id.img_id);
            convertView.setTag(viewHolder);
        }
        else viewHolder = (ViewHolder)convertView.getTag();

        Log.d("xogud","outgetView");
        if(list_itemArrayList.get(position).getTitle()!=null)
        if(list_itemArrayList.get(position).getTitle().length()>10) viewHolder.title_id.setText(list_itemArrayList.get(position).getTitle().substring(0,10));
        else viewHolder.title_id.setText(list_itemArrayList.get(position).getTitle());
        viewHolder.price_id.setText(list_itemArrayList.get(position).getPriceStandard());
        viewHolder.salesPrice_id.setText(list_itemArrayList.get(position).getPriceSales());
        if(list_itemArrayList.get(position).getPubDate()!=null) viewHolder.pub_id.setText(list_itemArrayList.get(position).getPubDate());
        viewHolder.aut_id.setText(list_itemArrayList.get(position).getAuthor());
        Glide.with(context).load(list_itemArrayList.get(position).getCoverSmallUrl()).into(viewHolder.img_id);
        Log.d("xogud","endgetView");

        return convertView;
    }
}
