package com.example.travisho.codebuilder;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Level extends AppCompatActivity {

    /* Storing the number of items */
    private static final int NBR_ITEMS = 4;
    private ImageView firstCircle;
    private ImageView secondCircle;
    private ImageView thirdCircle;
    private ImageView fourthCircle;
    public static Bluetooth bluetooth;

    /* The Grid Layout */
    private GridLayout mGrid;
    int touchedPoint = -1;
    int dropped = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        bluetooth = new Bluetooth(this,mHandler);
        connectService();




        /* Setting up Markers */
        firstCircle = (ImageView) findViewById(R.id.firstCircle);
        secondCircle = (ImageView) findViewById(R.id.secondCircle);
        thirdCircle = (ImageView) findViewById(R.id.thirdCircle);
        fourthCircle = (ImageView) findViewById(R.id.fourthCircle);

        firstCircle.setVisibility(View.VISIBLE);
        secondCircle.setVisibility(View.VISIBLE);
        thirdCircle.setVisibility(View.GONE);
        fourthCircle.setVisibility(View.GONE);

        firstCircle.setImageDrawable(getResources().getDrawable(R.drawable.green_circle));
        secondCircle.setImageDrawable(getResources().getDrawable(R.drawable.blue_circle));

        /* Setting up the grid */
        mGrid = (GridLayout) findViewById(R.id.grid_layout);
        mGrid.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                final View view = (View) event.getLocalState();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        touchedPoint = calculateNewIndexs(event.getX());
                       System.out.println("Hello x: " + touchedPoint);
                    case DragEvent.ACTION_DRAG_LOCATION:
                        // do nothing if hovering above own position
                       // System.out.println("My X Value is: " + calculateNewIndex(event.getX(), event.getY()));

                        if (view == v) return true;
                        // get the new list index
                       // dropped = calculateNewIndex(event.getX(), event.getY());
                        dropped = calculateNewIndexs(event.getX());
                       // System.out.println("Hello");

                        //Toast.makeText(getApplicationContext(), "THis is index: " + mGrid.indexOfChild(v), Toast.LENGTH_SHORT).show();
                     //   System.out.println("My Index Value: " + index);


                        // remove the view from the old position
                        mGrid.removeView(view);
                        // and push to the new
                        mGrid.addView(view, dropped);
                        System.out.print("Touch: " + touchedPoint + " vs. Dropped " + dropped);
                        break;
                    case DragEvent.ACTION_DROP:
                        view.setVisibility(View.VISIBLE);
                        if (dropped == touchedPoint + 1 ) {
                            bluetooth.sendMessage(""+ touchedPoint);
                        } else {
                            System.out.println("Hello ? YOU DROPPED ME WRONG BITCH");
                            mGrid.removeView(view);
                            mGrid.addView(view, touchedPoint );
                        }
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        touchedPoint = -1;
                        dropped = -1;
                        if (!event.getResult()) {
                            view.setVisibility(View.VISIBLE);
                        }
                        break;
                }
                return true;

            }
        });

        /* Setting up layout inflater */
        final LayoutInflater inflater = LayoutInflater.from(this);

        /* Populating the Grid */
        for (int i = 0; i < NBR_ITEMS; i++) {
            final View itemView = inflater.inflate(R.layout.block, mGrid, false);
            itemView.setOnLongClickListener(new LongPressListener());
            itemView.setId(i);
            final ImageView text = (ImageView) itemView.findViewById(R.id.text);
            final RelativeLayout card = (RelativeLayout) itemView.findViewById(R.id.my_card);
            text.setImageDrawable(getResources().getDrawable(R.drawable.block1));
            if (i == 0) {
                text.setImageDrawable(getResources().getDrawable(R.drawable.block3));
               // text.setElevation(3f);
            } else if (i == 1) {
                text.setImageDrawable(getResources().getDrawable(R.drawable.block1));
              //  text.setElevation(5f);
            } else if (i == 2) {
                text.setImageDrawable(getResources().getDrawable(R.drawable.block2));
               // text.setElevation(8f);

            } else {
                text.setImageDrawable(getResources().getDrawable(R.drawable.block4));
              //  text.setElevation(10f);


            }
            mGrid.addView(itemView);
        }

    }
    private int calculateNewIndex(float x, float y) {
        // calculate which column to move to
        final float cellWidth = mGrid.getWidth() / mGrid.getColumnCount();
        final int column = (int)(x / cellWidth);

        // calculate which row to move to
        final float cellHeight = mGrid.getHeight() / mGrid.getRowCount();
        final int row = (int)Math.floor(y / cellHeight);

        // the items in the GridLayout is organized as a wrapping list
        // and not as an actual grid, so this is how to get the new index
        int index = row * mGrid.getColumnCount() + column;
        if (index >= mGrid.getChildCount()) {
            index = mGrid.getChildCount() - 1;
        }

        return index;
    }
    private int calculateNewIndexs(float y) {
       if (y >= 0 && y<=200) {
           return 0;
       } else if (y > 200 && y <=380) {
           return 1;
       } else if (y > 380 && y <= 570) {
           return 2;
       } else {
           return 3;
       }

    }
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Bluetooth.MESSAGE_STATE_CHANGE:
                    Log.d("myTag", "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    break;
                case Bluetooth.MESSAGE_WRITE:
                    Log.d("myTag", "MESSAGE_WRITE ");
                    break;
                case Bluetooth.MESSAGE_READ:
                    Log.d("myTag", "MESSAGE_READ ");
                    break;
                case Bluetooth.MESSAGE_DEVICE_NAME:
                    Log.d("myTag", "MESSAGE_DEVICE_NAME "+msg);
                    break;
                case Bluetooth.MESSAGE_TOAST:
                    Log.d("myTag", "MESSAGE_TOAST "+msg);
                    break;
            }
        }
    };
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
