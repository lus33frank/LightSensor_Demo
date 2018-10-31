package com.frankchang.lightsensor_demo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // 畫面元件
    private TextView show;
    // 物件
    private SensorManager managers;
    private Sensor sensor;
    private sensorListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = findViewById(R.id.tvShow);

        // 取得傳感器物件
        managers = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (managers != null) {
            sensor = managers.getDefaultSensor(Sensor.TYPE_LIGHT);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 建立監聽器
        if (sensor != null) {
            listener = new sensorListener();
            // 註冊
            managers.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 註銷
        managers.unregisterListener(listener);
    }


    // 監聽器
    private class sensorListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            String lux = "lux : " + values[0];
            show.setText(lux);  // 流明（亮度單位）
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // 未使用
        }
    }

}
