package com.midlandstech.student.treywyates.libraryrentals;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//Adds the new rental to the database
public class RentActivity extends AppCompatActivity {
    private String bookName;
    private DatabaseManager dbManager;
    private Rental rental = new Rental(0, "", "", "");

    //Sets the view and instantiates the dropdown list handler and adapter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        DropdownHandler dh = new DropdownHandler();
        Spinner dropdown = findViewById(R.id.dropdown);
        dbManager = new DatabaseManager(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                rental.getBooks());
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(dh);
    }

    //Controls what happens when the dropdown list is clicked
    public class DropdownHandler implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            //Get the name that was selected
            bookName = parent.getItemAtPosition(pos).toString();
            Log.w("MainActivity", "Selected: " + bookName);
        }

        public void onNothingSelected(AdapterView<?> parent) {
            //Empty method because its needed except we assume the first title is selected
        }
    }

    //Adds the new rental to the DB
    public void rent(View view) {
        EditText etName = findViewById(R.id.etName);
        String name = etName.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy");
        Calendar cal = Calendar.getInstance();
        String dateStr;

        //Formats the date properly, with 2 weeks added for the return
        cal.setTime(new Date());
        dateStr = format.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 14);
        dateStr += "-\n" + format.format(cal.getTime());

        //Ensures a name is entered
        if (name.equals("")) {
            //If the EditText is empty we alert the user
            Log.w("MainActivity", "name is empty");
            Toast.makeText(RentActivity.this, "Please enter your name",
                    Toast.LENGTH_SHORT).show();
        } else {
            //Otherwise we try to insert everything into the database
            try {
                rental = new Rental(0, name, bookName, dateStr);
                dbManager.insert(rental);
            } catch (NumberFormatException nfe) {
                Toast.makeText(RentActivity.this, "Rent Processing Error",
                        Toast.LENGTH_SHORT).show();
            }
            //If everything goes well user is alerted and the activity closes
            Toast.makeText(RentActivity.this, "Rent Processed",
                    Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }
}

