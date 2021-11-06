package com.ryanchoi.darkthemesample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* View 설정 */
        TextView TV_description = findViewById(R.id.act_main_TV_description);
        Button BTN_dayMode = findViewById(R.id.act_main_BTN_day_mode);
        Button BTN_nightMode = findViewById(R.id.act_main_BTN_night_mode);
        Button BTN_followSystem = findViewById(R.id.act_main_BTN_follow_system);

        /* OnClickListener 설정 */
        BTN_dayMode.setOnClickListener(this);
        BTN_nightMode.setOnClickListener(this);
        BTN_followSystem.setOnClickListener(this);

        /* 기본 나이트 모드 값에 따라 Description TextView 텍스트 설정*/
        int currentMode = AppCompatDelegate.getDefaultNightMode();
        switch (currentMode) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                TV_description.setText("Current Mode: Night Mode");
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
                TV_description.setText("Current Mode: Day Mode");
                break;
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
            case AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY:
                TV_description.setText("Current Mode: Follow System");
                break;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.act_main_BTN_day_mode) {   // Day Mode 버튼 클릭 시
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (view.getId() == R.id.act_main_BTN_night_mode) {   // Night Mode 버튼 클릭 시
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else if (view.getId() == R.id.act_main_BTN_follow_system) {   // Follow System 버튼 클릭 시
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                /* Android Version Q (API 29) 이상인 경우 Follow System 상수를 사용 */
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            } else {
                /* Android Version Q (API 29) 미만인 경우 Auto Battery 상수를 사용 */
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
            }
        }
        recreate();     // Default Night Mode 변경 사항을 적용하기 위해 Activity 재생성
    }
}