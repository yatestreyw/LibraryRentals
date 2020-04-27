package com.midlandstech.student.treywyates.libraryrentals;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

//View and Controller in one that mediates between the different activities
public class MainActivity extends AppCompatActivity {
    //No need for a static model because we do not pass data between*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Opens RentActivity to add a new rental
    public void addRental(View view) {
        this.startActivity(new Intent(this, RentActivity.class));
    }

    //Opens ListActivity to open the list of existing rentals
    public void listRentals(View view) {
        this.startActivity(new Intent(this, ListActivity.class));
    }
}
