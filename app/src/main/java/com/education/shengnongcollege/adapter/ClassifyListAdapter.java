package com.education.shengnongcollege.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.education.shengnongcollege.R;
import com.education.shengnongcollege.model.GetCategoryListRespData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pjy
 */
public class ClassifyListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mInflater;
    private List<GetCategoryListRespData> dataList = new ArrayList<>();
    public ClassifyListAdapter(Context context, List<GetCategoryListRespData> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.mInflater = LayoutInflater.from(context);
    }


    public void setDataList(List<GetCategoryListRespData> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList == null ? 0:dataList.size();
    }

    @Override
    public GetCategoryListRespData getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolde holder;
        if(view == null){
            view = mInflater.inflate(R.layout.classify_listview_item, null);
            holder = new ViewHolde();
            holder.mItemName =view.findViewById(R.id.textView);
            view.setTag(holder);
        }else{
            holder = (ViewHolde) view.getTag();
        }
        holder.mItemName.setText(getItem(position).getName());
        if(getItem(position).ischoose()){
            holder.mItemName.setBackgroundResource(R.drawable.shape_classify_listitembg);
            holder.mItemName.setTextColor(Color.parseColor("#FF9800"));
        }else {
            holder.mItemName.setBackgroundResource(R.color.table_bg);
            holder.mItemName.setTextColor(Color.BLACK);
        }
        return view;
    }

    class ViewHolde {
        TextView mItemName;
    }
    public List<GetCategoryListRespData> getDataList(){
        return this.dataList;
    }

}
