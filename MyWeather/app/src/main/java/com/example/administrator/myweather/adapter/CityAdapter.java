package com.example.administrator.myweather.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myweather.R;
import com.example.administrator.myweather.activity.ChooseAreaActivity;
import com.example.administrator.myweather.db.CityEntity;
import com.example.administrator.myweather.fragment.ChooseAreaFragment;
import com.example.administrator.myweather.util.FragmentUtil;

import java.util.List;

/**
 * Created by zhengwuy on 2017/2/20.
 * Emali: 963460692@qq.com
 * description:
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private List<CityEntity> mCityEntityList;
    private Context mContext;

    public CityAdapter(Context context, List<CityEntity> list) {
        mContext = context;
        mCityEntityList = list;
    }

    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.province_item, parent, false);
        return new CityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityAdapter.ViewHolder holder, int position) {
        final CityEntity cityEntity = mCityEntityList.get(position);
        holder.mNameTv.setText(cityEntity.getMCityName());
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseAreaFragment chooseAreaFragment = ChooseAreaFragment.newInstance(ChooseAreaActivity.CHOOSE_TYPE_COUNTY,
                        cityEntity.getMCityId());
                FragmentUtil.replaceAreaFragment((AppCompatActivity) mContext, R.id.container, chooseAreaFragment, true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCityEntityList == null ? 0 : mCityEntityList.size();
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
