package com.example.travisho.codebuilder;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BlockGameMap extends AppCompatActivity {
    private TextView level_select_text;
    private ImageView block1;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    public static int level = 1;
    public static int shownTransition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_game_map);

        Typeface hurme = Typeface.createFromAsset(getAssets(), "HurmeSemi.otf");
        level_select_text = (TextView) findViewById(R.id.level_select_text);
        level_select_text.setTypeface(hurme);

        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goLevel = new Intent(BlockGameMap.this, Level.class);
                startActivity(goLevel);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goLevel = new Intent(BlockGameMap.this, Level.class);
                startActivity(goLevel);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goLevel = new Intent(BlockGameMap.this, Level.class);
                startActivity(goLevel);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goLevel = new Intent(BlockGameMap.this, Level.class);
                startActivity(goLevel);
            }
        });




        block1 = (ImageView) findViewById(R.id.block1);
        moveViewToScreenCenter(block1);
        /*block1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });*/

    }

    private void moveViewToScreenCenter( View view )
    {
        RelativeLayout root = (RelativeLayout) findViewById( R.id.activity_block_game_map );
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics( dm );
        int statusBarOffset = dm.heightPixels - root.getMeasuredHeight();

        int originalPos[] = new int[2];
        view.getLocationOnScreen( originalPos );

        int xDest = 0;
       // xDest -= (view.getMeasuredWidth()/2);
        int yDest = dm.heightPixels - 1200;


        TranslateAnimation anim = new TranslateAnimation( 0, xDest - originalPos[0] , -1000, yDest - originalPos[1] );
        anim.setDuration(1000);
        anim.setFillAfter( true );
        view.startAnimation(anim);
        block1.setVisibility(View.VISIBLE);
    }


}
