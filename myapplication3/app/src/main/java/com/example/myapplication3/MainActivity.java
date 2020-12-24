package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity   {
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.initialize(this);
        SQLiteDatabase db = Connector.getDatabase();

   /*     // 启动服务播放背景音乐
        intent = new Intent(MainActivity.this, MyIntentService.class);
        String action = MyIntentService.ACTION_MUSIC;
        // 设置action
        intent.setAction(action);
        startService(intent);*/



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (intent != null){
            // 对于intentService，这一步可能是多余的
            stopService(intent);
        }
    }



}
