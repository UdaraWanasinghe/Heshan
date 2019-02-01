package com.example.projectmanegmenttoolandroidx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        // get value of last login status
//        defltShrdPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        boolean isLogin = defltShrdPreferences.getBoolean("isLogin", false);
//
//        //if there is a user logged before then go go main activity
//        if (isLogin) {
//        goTo(Home.class);
//        }
    }

    public void createOne(View view) {
        TextView textView = (TextView) view;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            textView.setTextColor(getColor(R.color.orange_dark));
        }


        goTo(SignUpActivity.class);

    }

    private void goTo(Class classToGo) {
        Intent intent = new Intent(MainActivity.this, classToGo);
        startActivity(intent);
    }

    public void login(View view) {
        //todo:set login logics
        goTo(Home.class);
    }
}


