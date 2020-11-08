package com.example.shanshui.fragment;

import android.view.View;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shanshui.R;

public class TabFirstFragment extends Fragment {

    protected Context mContext; // 声明上下文对象
    protected View mView; // 声明视图对象

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity(); //获取页面上下文

        mView = inflater.inflate(R.layout.fragment_first_tab, container, false);
        String desc = String.format(
                "我是%s页面，来自%s", "首页", getArguments().getString("tag"));
        TextView tv_first = mView.findViewById(R.id.tv_first);
        tv_first.setText(desc);
        return mView;

    }

}
