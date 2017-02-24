package com.example.administrator.myweather.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myweather.R;
import com.example.administrator.myweather.constant.Constants;
import com.example.administrator.myweather.db.CountyEntity;
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
    public void onBindViewHolder(CountyAdapter.ViewHolder holder, int position) {
        final CountyEntity countyEntity = mCountyEntityList.get(position);
        holder.mNameTv.setText(countyEntity.getMCountyName());
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceHelper.getInstance().putString(Constants.SharedPreferenceKeyConstant.KEY_CHOOSE_COUNTY_WEATHER_ID,
                        countyEntity.getMWeatherId());
                Intent intent = new Intent();
                intent.putExtra(Constants.SharedPreferenceKeyConstant.KEY_CHOOSE_COUNTY_WEATHER_ID, countyEntity.getMWeatherId());
                ((AppCompatActivity)mContext).setResult(Activity.RESULT_OK, intent);
                ((AppCompatActivity)mContext).finish();
                ActivityUtil.finishAnim((AppCompatActivity) mContext);
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
