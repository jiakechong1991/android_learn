package com.example.junior;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // 创建屏幕
        setContentView(R.layout.activity_main);  // 用这个布局初始化屏幕

        // 像素演示
        findViewById(R.id.btn_px).setOnClickListener(this);
        // 颜色演示
        findViewById(R.id.btn_color).setOnClickListener(this);
        // 分辨率演示
        findViewById(R.id.btn_screen).setOnClickListener(this);
        // 空白间隔演示 (通过ID找到view视图)
        findViewById(R.id.btn_margin).setOnClickListener(this);  //对这个视图设置"点击监听"
        // 对齐方式演示
        findViewById(R.id.btn_gravity).setOnClickListener(this);
        findViewById(R.id.btn_scroll).setOnClickListener(this);
        // 文字 跑马灯演示
        findViewById(R.id.btn_marquee).setOnClickListener(this);
        // 聊天室演示
        findViewById(R.id.btn_bbs).setOnClickListener(this);
        // 按钮点击
        findViewById(R.id.btn_click).setOnClickListener(this);
        // 图像控件
        findViewById(R.id.btn_scale).setOnClickListener(this);
        // 截图功能
        findViewById(R.id.btn_capture).setOnClickListener(this);
        // 使用 ImageButton
        findViewById(R.id.btn_icon).setOnClickListener(this);
        findViewById(R.id.btn_state).setOnClickListener(this);
        findViewById(R.id.btn_shape).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);
        findViewById(R.id.btn_calculator).setOnClickListener(this);
    }

    @Override  // 监听点击
    public void onClick(View v) {
        // 判定点击发生在哪个 view上,然后启动不动的调用函数
        if (v.getId() == R.id.btn_px) {
            Intent intent = new Intent(this, PxActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_color) {
            Intent intent = new Intent(this, ColorActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_screen) {
            Intent intent = new Intent(this, ScreenActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_margin) {
            // 用指定的视图 创建 屏幕视图
            Intent intent = new Intent(this, MarginActivity.class);
            startActivity(intent);  // 用视图填充屏幕
        } else if (v.getId() == R.id.btn_gravity) {
            Intent intent = new Intent(this, GravityActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_scroll) {
            // 滚动视图
            Intent intent = new Intent(this, ScrollActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_marquee) {
            Intent intent = new Intent(this, MarqueeActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_bbs) {
            Intent intent = new Intent(this, BbsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_click) {
            Intent intent = new Intent(this, ClickActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_scale) {
            Intent intent = new Intent(this, ScaleActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_capture) {
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_icon) {
            Intent intent = new Intent(this, IconActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_state) {
            Intent intent = new Intent(this, StateActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_shape) {
            Intent intent = new Intent(this, ShapeActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_nine) {
            Intent intent = new Intent(this, NineActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_calculator) {
            Intent intent = new Intent(this, CalculatorActivity.class);
            startActivity(intent);
        }
    }

}
