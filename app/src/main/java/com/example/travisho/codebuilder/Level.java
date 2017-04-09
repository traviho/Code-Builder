package com.example.travisho.codebuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Level extends AppCompatActivity {

    /* Storing the number of items */
    private static final int NBR_ITEMS = 9;

    /* The Grid Layout */
    private GridLayout mGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        /* Setting up the grid */
        mGrid = (GridLayout) findViewById(R.id.grid_layout);
        mGrid.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                final View view = (View) event.getLocalState();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_LOCATION:
                        // do nothing if hovering above own position
                        if (view == v) return true;
                        // get the new list index
                        final int index = calculateNewIndex(event.getX(), event.getY());
                        Toast.makeText(getApplicationContext(), "THis is index: " + mGrid.indexOfChild(v), Toast.LENGTH_SHORT).show();


                        // remove the view from the old position
                        mGrid.removeView(view);
                        // and push to the new
                        mGrid.addView(view, index);
                        break;
                    case DragEvent.ACTION_DROP:
                        view.setVisibility(View.VISIBLE);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
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
            final TextView text = (TextView) itemView.findViewById(R.id.text);
            text.setText(String.valueOf(i + 1));
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
}
