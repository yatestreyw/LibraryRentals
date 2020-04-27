package com.midlandstech.student.treywyates.libraryrentals;

import java.lang.reflect.Array;
import java.util.Date;

//Holds the information needed for a Rental
public class Rental {
    private int id;
    private String name;
    private String bookName;
    private String date;

    //Holds the names of the available books for rent
    private String[] bookArray = {"Confinement Without Shame", "Guardian Of The Nation",
            "Adopting The Sea", "Failure Of The North", "Strangers Of The Night"};

    //Creates a rental with an id, name, and price 
    public Rental(int newId, String newName, String newBookName, String newDate) {
        setId(newId);
        setName(newName);
        setBookName(newBookName);
        setDate(newDate);
    }

    //Sets a new id
    public void setId(int newId) {
        id = newId;
    }

    //Sets a new name
    public void setName(String newName) {
        name = newName;
    }

    //Sets a new bookName
    public void setBookName(String newBookName) {
        bookName = newBookName;
    }

    //Sets a new price
    public void setDate(String newDate) {
        date = newDate;
    }

    //Returns the current id
    public int getId() {
        return id;
    }

    //Returns the current name
    public String getName() {
        return name;
    }

    //Returns the current bookName
    public String getBookName() {
        return bookName;
    }

    //Returns the current price
    public String getDate() {
        return date;
    }

    //Returns the list of the available book to rent
    public String[] getBooks() {
        return bookArray;
    }

    //toString() for output 
    public String toString() {
        return id + "; " + name + "; " + bookName + "; " + date;
    }
}
