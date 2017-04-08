package com.example.travisho.codebuilder;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Bluetooth bluetooth;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == Bluetooth.MESSAGE_READ) {
                byte[] buffer = (byte[]) msg.obj;

                for (int i = 0;i < msg.arg1;i++) {
                    System.out.println("COMMM " + (char) buffer[i]);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bluetooth = new Bluetooth(this, mHandler);

        try {
            connectService();
        } catch (Exception e){
            e.printStackTrace();
        }

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bluetooth.sendMessage("1");
                } catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void connectService(){
        try {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter.isEnabled()) {
                bluetooth.start();
                bluetooth.connectDevice("HC-06");
                Log.d("myTag", "Btservice started - listening");
            } else {
                Log.w("myTag", "Btservice started - bluetooth is not enabled");
            }
        } catch(Exception e){
            Log.e("myTag", "Unable to start bt ",e);
        }
    }
}
