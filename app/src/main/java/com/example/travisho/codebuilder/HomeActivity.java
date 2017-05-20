package com.example.travisho.codebuilder;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private ImageView background;
    private TextView play_button;
    private ImageView fire;

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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Typeface hurme = Typeface.createFromAsset(getAssets(), "HurmeSemi.otf");
        play_button = (TextView) findViewById(R.id.play_button);
        play_button.setTypeface(hurme);
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameMap = new Intent(HomeActivity.this, BlockGameMap.class);
                startActivity(gameMap);
            }
        });



        /* Setting up Bluetooth */
       // bluetooth = new Bluetooth(this, mHandler);

        try {
         //   connectService();
        } catch (Exception e){
            e.printStackTrace();
        }

      /*  ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bluetooth.sendMessage("1");
                } catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        }); */
    }

   /* public void connectService(){
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
    }*/
}
