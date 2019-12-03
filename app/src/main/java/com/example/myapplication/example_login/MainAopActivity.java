package com.example.myapplication.example_login;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.MainActivity;
import com.example.myapplication.example_login.aop.ClickBehavior;
import com.example.myapplication.example_login.aop.LoginCheck;
import com.example.myapplication.example_login.ui.login.LoginActivity;
import com.example.myapplication.example_login.util.SharedPreferencesUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class MainAopActivity extends AppCompatActivity {


    private static final String TAG = "ClickBehaviorAspect";
    private View btn_signOut;
    private TextView tv_username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_aop);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setupUserName();
    }

    private void initView() {
        tv_username = findViewById(R.id.tv_username);
        btn_signOut = findViewById(R.id.btn_signOut);

        // 点击退出登录
        btn_signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtil.clearLoginUser();

                tv_username.setText("");
                btn_signOut.setVisibility(View.GONE);
            }
        });
    }

    private void setupUserName() {
        String username = SharedPreferencesUtil.getLoginedUser();
        if (TextUtils.isEmpty(username)) {
            return;
        }

        tv_username.setText(username);
        btn_signOut.setVisibility(View.VISIBLE);
    }

    /**
     * 登录
     * @param view
     */
    @ClickBehavior("登录")
    public void login(View view) {
        Log.d(TAG, "跳转到登录界面");
        startActivity(new Intent(this, LoginActivity.class));
    }

    /**
     * 需要登录后操作，未登陆需要跳转登录界面
     * @param view
     */
    @LoginCheck
    @ClickBehavior("我的专区")
    public void area(View view) {
        Log.d(TAG, "跳转到我的专区界面");
        startActivity(new Intent(this, MainActivity.class));
    }

    /**
     * 需要登录后操作，未登陆需要跳转登录界面
     * @param view
     */
    @LoginCheck
    @ClickBehavior("我的优惠券")
    public void coupon(View view) {
        Log.d(TAG, "跳转到我的优惠券界面");
        startActivity(new Intent(this, MainActivity.class));
    }

    /**
     * 需要登录后操作，未登陆需要跳转登录界面
     * @param view
     */
    @LoginCheck
    @ClickBehavior("我的积分")
    public void score(View view) {
        Log.d(TAG, "跳转到我的积分界面");
        startActivity(new Intent(this, MainActivity.class));
    }
}
