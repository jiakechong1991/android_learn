package com.example.shanshui.fragment;

import android.content.Intent;
import android.hardware.Camera;
import android.util.Log;
import android.view.View;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;


import com.example.shanshui.R;


public class TabFirstFragment extends Fragment implements OnClickListener  {

    private static final String TAG = "TabFragmentActivity";
    protected Context mContext; // 声明上下文对象
    protected View mView; // 声明视图对象

    //操作本页面的元素
    private FrameLayout fl_content; //声明一个FrameLayout对象
    private ImageView iv_photo; // 声明一个图像视图对象
    private GridView gv_shooting; // 声明一个网络视图


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity(); //获取页面上下文

        mView = inflater.inflate(R.layout.fragment_first_tab, container, false);
        String desc = String.format("我是%s页面，来自%s", "首页", getArguments().getString("tag"));
        Log.d(TAG, desc);
//        TextView tv_first = mView.findViewById(R.id.tv_first);
//        tv_first.setText(desc);

        // 从布局文件中获取名叫fl_content的框架布局
        fl_content = mView.findViewById(R.id.fl_content);
        // 从布局文件中获取名叫iv_photo的图像视图
        iv_photo = mView.findViewById(R.id.iv_photo);
        // 从布局文件中获取名叫gv_shooting的网格视图
        gv_shooting = mView.findViewById(R.id.gv_shooting);

        //监控两个按钮的点击
        mView.findViewById(R.id.btn_catch_behind).setOnClickListener(this);
        mView.findViewById(R.id.btn_catch_front).setOnClickListener(this);

        return mView;

    }

    @Override
    public void onClick(View v){
        Log.d(TAG, "getNumberOfCameras=" + Camera.getNumberOfCameras());
        if (v.getId() == R.id.btn_catch_behind){  //打开后置摄像图
            Camera mCamera = Camera.open();
            if(mCamera != null){
                mCamera.release();  //释放摄像头
//                Intent intent = new Intent(mContext, null); // 前往camera的拍照页面
//                intent.putExtra("type", CameraView.);
                //处理拍照页面的返回值
//                startActivityForResult(intent, 1);
            }else{
                Toast.makeText(mContext, "当前设备不支持后置摄像头", Toast.LENGTH_SHORT).show();
            }
        }else if(v.getId() == R.id.btn_catch_front){  //打开前置摄像头
            Toast.makeText(mContext, "当前设备前摄像头", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext, "当前设备不支持摄像头", Toast.LENGTH_SHORT).show();
        }

    }

}
