package com.example.administrator.myweather.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myweather.R;
import com.example.administrator.myweather.activity.ChooseAreaActivity;
import com.example.administrator.myweather.adapter.CityAdapter;
import com.example.administrator.myweather.adapter.CountyAdapter;
import com.example.administrator.myweather.adapter.ProvinceAdapter;
import com.example.administrator.myweather.db.CityEntity;
import com.example.administrator.myweather.db.CountyEntity;
import com.example.administrator.myweather.db.DBManager;
import com.example.administrator.myweather.db.ProvinceEntity;

import java.util.List;

/**
 * 选择地区的fragment
 */
public class ChooseAreaFragment extends Fragment {
    private static final String CURRENT_MODE = "current_mode";
    private static final String ID = "id";

    private int mCurrentMode;
    private String mID;
    private RecyclerView mRecyclerView;

    public ChooseAreaFragment() {
    }

    public static ChooseAreaFragment newInstance(int currentMode, String id) {
        ChooseAreaFragment fragment = new ChooseAreaFragment();
        Bundle args = new Bundle();
        args.putInt(CURRENT_MODE, currentMode);
        args.putString(ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCurrentMode= getArguments().getInt(CURRENT_MODE);
            mID = getArguments().getString(ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choose_area, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        initAdapter();
        return rootView;
    }

    private void initAdapter() {
        switch (mCurrentMode) {
            case ChooseAreaActivity.CHOOSE_TYPE_PROVINCE:
                List<ProvinceEntity> provinceEntityList = DBManager.getInstance(getContext()).queryProvinceList();
                ProvinceAdapter provinceAdapter = new ProvinceAdapter(getActivity(), provinceEntityList);
                mRecyclerView.setAdapter(provinceAdapter);
                break;
            case ChooseAreaActivity.CHOOSE_TYPE_CITY:
                List<CityEntity> cityEntityList = DBManager.getInstance(getContext()).queryCityListByProvinceId(mID);
                CityAdapter cityAdapter = new CityAdapter(getActivity(), cityEntityList);
                mRecyclerView.setAdapter(cityAdapter);
                break;
            case ChooseAreaActivity.CHOOSE_TYPE_COUNTY:
                List<CountyEntity> countyEntityList = DBManager.getInstance(getContext()).queryCountyListByCityId(mID);
                CountyAdapter countyAdapter = new CountyAdapter(getActivity(), countyEntityList);
                mRecyclerView.setAdapter(countyAdapter);
                break;
            default:
                Log.e("lalla", "no mode");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
