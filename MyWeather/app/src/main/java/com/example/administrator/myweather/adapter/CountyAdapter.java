package com.example.administrator.myweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myweather.R;
import com.example.administrator.myweather.constant.SharedPreferenceKeyConstant;
import com.example.administrator.myweather.db.CountyEntity;
import com.example.administrator.myweather.db.ProvinceEntity;
import com.example.administrator.myweather.util.ActivityUtil;
import com.example.administrator.myweather.util.SharedPreferenceHelper;

import java.util.List;

/**
 * Created by zhengwuy on 2017/2/20.
 * Emali: 963460692@qq.com
 * description:
 */

public class CountyAdapter extends RecyclerView.Adapter<CountyAdapter.ViewHolder> {
    private List<CountyEntity> mCountyEntityList;
    private Context mContext;

    public CountyAdapter(Context context, List<CountyEntity> list) {
        mCountyEntityList = list;
        mContext = context;
    }

    @Override
    public CountyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.province_item, parent, false);
        return new CountyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountyAdapter.ViewHolder holder, final int position) {
        holder.mNameTv.setText(mCountyEntityList.get(position).getMCountyName());
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceHelper.getInstance().putString(SharedPreferenceKeyConstant.KEY_CHOOSE_COUNTY_WEATHER_ID,
                        mCountyEntityList.get(position).getMWeatherId());
                ActivityUtil.goHomeActivity(mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCountyEntityList == null ? 0 : mCountyEntityList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLinearLayout;
        TextView mNameTv;

        ViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.province_name);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.linearlayout);
        }
    }
}
