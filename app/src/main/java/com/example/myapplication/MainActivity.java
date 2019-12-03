package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.example_sql.DBOperation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity implements DBOperation, View.OnClickListener {

    private static final String TAG = "MainActivity";


    private DBOperation db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_operationDb).setOnClickListener(this);

        db = (DBOperation) Proxy.newProxyInstance(DBOperation.class.getClassLoader(), new Class[]{DBOperation.class}, new DBHandler(this));
    }

    /**
     * 操作数据库
     * @param view
     */
    public void databaseOperating(View view) {
        // oop 写法：
//        db.save();
//        db.insert();

        db.insert();
    }

    @Override
    public void insert() {
        Log.d(TAG, "数据库操作：insert()");
    }

    @Override
    public void delete() {
        Log.d(TAG, "数据库操作：delete()");
    }

    @Override
    public void update() {
        Log.d(TAG, "数据库操作：update()");
    }

    @Override
    public void save() {
        Log.d(TAG, "数据库操作：save()");
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.btn_operationDb:
                databaseOperating(v);
                break;
        }
    }

    /**
     * 动态代理
     */
    private class DBHandler implements InvocationHandler {
        private DBOperation db;

        public DBHandler(DBOperation dbOperation) {
            this.db = dbOperation;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(db != null) {
                if (!"save".equals(method.getName())) {
                    Log.d(TAG, "操作数据库之前，开始备份。。。");
                    save();
                    Log.d(TAG, "数据库备份完成。。。");
                }
                return method.invoke(db, args);
            }

            return null;
        }
    }
}
