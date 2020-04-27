package com.midlandstech.student.treywyates.libraryrentals;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

//Lists all of the rentals from the DB
public class ListActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    //Pulls everything from the DB and puts it into a numbered list
    private void updateView() {
        ArrayList<Rental> rentals = dbManager.selectAll();
        if (rentals.size() > 0) {
            //Creates ScrollView and GridLayout
            ScrollView scrollView = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(rentals.size());
            grid.setColumnCount(4);

            // Creates all the arrays of the components
            TextView[] ids = new TextView[rentals.size()];
            TextView[][] namesBooksAndDates = new TextView[rentals.size()][3];

            // Get the width of the screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int i = 0;
            for (Rental rental : rentals) {
                // Creates the TextView for the rental's id
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + rental.getId());

                // Creates the three TextViews for the rental's name, bookName and price
                namesBooksAndDates[i][0] = new TextView(this);
                namesBooksAndDates[i][1] = new TextView(this);
                namesBooksAndDates[i][2] = new TextView(this);
                namesBooksAndDates[i][0].setText(rental.getName());
                namesBooksAndDates[i][1].setText(rental.getBookName());
                namesBooksAndDates[i][2].setText(rental.getDate());
                namesBooksAndDates[i][0].setId(10 * rental.getId());
                namesBooksAndDates[i][1].setId(10 * rental.getId() + 1);
                namesBooksAndDates[i][2].setId(10 * rental.getId());

                // Adds everything to the grid
                grid.addView(ids[i], width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesBooksAndDates[i][0], (int) (width * .2),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesBooksAndDates[i][1], (int) (width * .35),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesBooksAndDates[i][2], (int) (width * .35),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                i++;
            }
            scrollView.addView(grid);
            setContentView(scrollView);
        }
    }
}
