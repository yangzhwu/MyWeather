package com.example.administrator.myweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myweather.R;
import com.example.administrator.myweather.db.ProvinceEntity;

import java.util.List;

/**
 * Created by zhengwuy on 2017/2/19.
 * Email: 963460692@qq.com
 * description:
 */

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.ViewHolder> {
    private List<ProvinceEntity> mProvinceEntityList;

    public ProvinceAdapter(List<ProvinceEntity> list) {
        mProvinceEntityList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.province_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mNameTv.setText(mProvinceEntityList.get(position).getMProvinceName());
    }

    @Override
    public int getItemCount() {
        return mProvinceEntityList == null ? 0 : mProvinceEntityList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNameTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.province_name);
        }
    }
}
