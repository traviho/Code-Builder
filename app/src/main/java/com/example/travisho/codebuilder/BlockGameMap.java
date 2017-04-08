package com.example.travisho.codebuilder;

import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BlockGameMap extends AppCompatActivity {
    private TextView level_select_text;
    private ImageView block1;
    public static int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_game_map);

        Typeface hurme = Typeface.createFromAsset(getAssets(), "HurmeSemi.otf");
        level_select_text = (TextView) findViewById(R.id.level_select_text);
        level_select_text.setTypeface(hurme);

        block1 = (ImageView) findViewById(R.id.block1);
        if (level == 0) {
            moveViewToScreenCenter( block1 );
        }
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

        int xDest = dm.widthPixels/100;
       // xDest -= (view.getMeasuredWidth()/2);
        int yDest = dm.heightPixels - 550;


        TranslateAnimation anim = new TranslateAnimation( 0, xDest - originalPos[0] , 0, yDest - originalPos[1] );
        anim.setDuration(1000);
        anim.setFillAfter( true );
        view.startAnimation(anim);
    }
}
