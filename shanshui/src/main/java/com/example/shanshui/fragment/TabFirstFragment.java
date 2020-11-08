package com.example.shanshui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.text.TextUtils;
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
import com.example.shanshui.TakePictureActivity;
import com.example.shanshui.sensor.CameraView;
import com.example.shanshui.sensor.HttpRequestUtil;
import com.example.shanshui.util.HttpReqData;
import com.example.shanshui.util.HttpRespData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class TabFirstFragment extends Fragment implements OnClickListener  {

    private static final String TAG = "TabFragmentActivity";
    protected Context mContext; // 声明上下文对象
    protected View mView; // 声明视图对象

    //操作本页面的元素
    private FrameLayout fl_content; //声明一个FrameLayout对象
    private ImageView iv_photo; // 声明一个图像视图对象
    private GridView gv_shooting; // 声明一个网络视图

    // HTTP访问
//    private String mAddressUrl = "http://api.tianditu.gov.cn/geocoder?postStr={'lon':%f,'lat':%f,'ver':1}&type=geocode";
    private String mAddressUrl = "http://127.0.0.1:8888/time?name=%s&age=%s";


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

    // 处理Camera拍照页面的返回结果
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.d(TAG, "onActivityResult. requestCode=" + requestCode + ", resultCode=" + resultCode);
        Bundle resp = intent.getExtras(); // 获取返回的包裹
        String is_null = resp.getString("is_null");
        if (!TextUtils.isEmpty(is_null) && !is_null.equals("yes")) { // 有发生拍照动作
            int type = resp.getInt("type");
            Log.d(TAG, "type=" + type);
            if (type == 0) { // 单拍。一次只拍一张
                iv_photo.setVisibility(View.VISIBLE);
                gv_shooting.setVisibility(View.GONE);
                String path = resp.getString("path");
                fillBitmap(BitmapFactory.decodeFile(path, null));
                HashMap<String, String> param_ = new HashMap<String, String>(){
                    {
                        put("name", "wxk");
                        put("age", "18");
                    }
                };
                try{
                    GetServerInfo(param_);
                }catch (Exception e){
                    Log.e(TAG, Log.getStackTraceString(e));
                }
            } else if (type == 1) {
            }
        }
    }

    public Object GetServerInfo(HashMap<String, String> data){
        
        //构造URL参数
        Log.d(TAG, data.get("name"));
        String url = String.format(mAddressUrl, data.get("name"), data.get("age"));
        Log.d(TAG, url);
        // 创建一个HTTP请求对象
        HttpReqData req_data = new HttpReqData(url);
        // 发送HTTP请求信息，并获得HTTP应答对象
        HttpRespData resp_data = HttpRequestUtil.getData(req_data);
        Log.d(TAG, "return json = " + resp_data.content);
        String address = "未知";
        // 下面从json串中逐级解析formatted_address字段获得详细地址描述
        if (resp_data.err_msg.length() <= 0) {
            try {
                JSONObject obj = new JSONObject(resp_data.content);
//                JSONArray resultArray = obj.getJSONArray("results");
//                if (resultArray.length() > 0) {
//                    JSONObject resultObj = resultArray.getJSONObject(0);
//                    address = resultObj.getString("formatted_address");
//                }
                JSONObject result = obj.getJSONObject("result");
                address = result.getString("formatted_address");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "address = " + address);
        return address; // 返回HTTP应答内容中的详细地址
    }

    // 以合适比例显示照片
    private void fillBitmap(Bitmap bitmap) {
        Log.d(TAG, "fillBitmap width=" + bitmap.getWidth() + ",height=" + bitmap.getHeight());
        // 位图的高度大于框架布局的高度，则按比例调整图像视图的宽高
        if (bitmap.getHeight() > fl_content.getMeasuredHeight()) {
            ViewGroup.LayoutParams params = iv_photo.getLayoutParams();
            params.height = fl_content.getMeasuredHeight();
            params.width = bitmap.getWidth() * fl_content.getMeasuredHeight() / bitmap.getHeight();
            // 设置iv_photo的布局参数
            iv_photo.setLayoutParams(params);
        }
        // 设置iv_photo的拉伸类型为居中
        iv_photo.setScaleType(ImageView.ScaleType.FIT_CENTER);
        // 设置iv_photo的位图对象
        iv_photo.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v){
        Log.d(TAG, "getNumberOfCameras=" + Camera.getNumberOfCameras());
        if (v.getId() == R.id.btn_catch_behind){  //打开后置摄像图
            Camera mCamera = Camera.open();
            if(mCamera != null){
                mCamera.release();  //释放摄像头
                Intent intent = new Intent(mContext, TakePictureActivity.class); // 前往camera的拍照页面
                Log.d(TAG, "2222进到这里了");
                intent.putExtra("type", CameraView.CAMERA_BEHIND); //类型是后置摄像
                Log.d(TAG, "进到这里了3333");
                //处理拍照页面的返回值
                startActivityForResult(intent, 1);
                Log.d(TAG, "进到这里了4444");
            }else{
                Toast.makeText(mContext, "当前设备不支持后置摄像头", Toast.LENGTH_SHORT).show();
            }
        }else if(v.getId() == R.id.btn_catch_front){  //打开前置摄像头
            Camera mCamera = Camera.open();
            if(mCamera != null){
                mCamera.release();  //释放摄像头
                Intent intent = new Intent(mContext, TakePictureActivity.class); // 前往camera的拍照页面
                intent.putExtra("type", CameraView.CAMERA_FRONT); //类型是前置摄像
                //处理拍照页面的返回值
                startActivityForResult(intent, 1);
            }else{
                Toast.makeText(mContext, "当前设备不支持后置摄像头", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(mContext, "当前设备不支持摄像头", Toast.LENGTH_SHORT).show();
        }

    }

}
