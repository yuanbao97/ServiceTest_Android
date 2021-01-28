package com.example.servicetest.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servicetest.R;
import com.example.servicetest.service.MyService;
import com.example.servicetest.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.start_service_button)
    Button startServiceButton;
    @BindView(R.id.stop_service_button)
    Button stopServiceButton;
    @BindView(R.id.bind_service_button)
    Button bindServiceButton;
    @BindView(R.id.unbind_service_button)
    Button unbindServiceButton;

    private MyService.DownloadBinder mDownloadBinder;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (MyService.DownloadBinder) service;
            mDownloadBinder.startDownload();
            mDownloadBinder.getProgress();
            LogUtil.e(TAG, "onServiceConnected executed");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.e(TAG, "onServiceDisconnected executed");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.start_service_button, R.id.stop_service_button, R.id.bind_service_button, R.id.unbind_service_button})
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
            case R.id.bind_service_button:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, mConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service_button:
                unbindService(mConnection);
                break;
        }
    }

}