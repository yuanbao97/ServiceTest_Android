package com.example.servicetest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servicetest.R;
import com.example.servicetest.service.MyService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.start_service_button)
    Button startServiceButton;
    @BindView(R.id.stop_service_button)
    Button stopServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.start_service_button, R.id.stop_service_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_service_button:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service_button:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
        }
    }
}