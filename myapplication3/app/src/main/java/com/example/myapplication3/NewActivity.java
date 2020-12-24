package com.example.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.service.WeatherActivity;

import java.util.ArrayList;

public class NewActivity extends AppCompatActivity implements View.OnClickListener {
    public ArrayList list=new ArrayList();
    //获取editText文本内容


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


    }

    public void Click(View view){
  /*      EditText text=(EditText)findViewById(R.id.e);
        String s=text.getText().toString();
        list.add(s);
        ListView listView=(ListView)findViewById(R.id.show);
        ArrayAdapter<String> ad =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(ad);*/


        EditText editText=(EditText)findViewById(R.id.e);
        String str = editText.getText().toString();
        Intent intent = new Intent(NewActivity.this, WeatherActivity.class);
//使用putExtra(key,value)方法传递数据
        intent.putExtra("editTextValue",str);
//跳转到第2个Activity页面
        startActivity(intent);

    }


    @Override
    public void onClick(View v) {

            // 按钮监听绑定
            switch (v.getId()){
                case R.id.back:
                    // 跳转到添加页面
                    Intent intent = new Intent(NewActivity.this, WeatherActivity.class);
                    startActivity(intent);
                    // 结束当前页面
                    NewActivity.this.finish();
                    break;
                default:
                    break;
            }
        }

}