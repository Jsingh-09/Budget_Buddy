package edu.csustan.budgetbuddy;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//Stephanie's code
//This class represents the database class "Expenses" and its objects
@ParseClassName("Expenses")
public class Expense extends ParseObject {


    public static final String KEY_LOCATION = "location";
    public static final String KEY_ITEMTYPE ="itemType";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED = "createdAt";
    public static final String KEY_Object = "objectId";


    //define getter and setters
    public String getLocation() {
        return getString(KEY_LOCATION);
    }

    public void setLocation(String location) {
        put(KEY_LOCATION, location);

    }

    public String getItemType(){
        return getString(KEY_ITEMTYPE);
    }

    public void setItemType(String itemType) {
        put(KEY_ITEMTYPE, itemType);

    }
    public String getAmount() {
        return getString(KEY_AMOUNT);
    }
    public Double getCost(){                                            //getter method, Pulls down KEY_AMOUNT as a String
        Double value = Double.parseDouble(getString(KEY_AMOUNT));       //then Parses it into a Double and stores it in a variable
        return  value;                                                  //the variable is returned
    }
    public void setAmount(String amount) {
        put(KEY_AMOUNT, amount);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {

        put(KEY_USER, user);
    }

    public String getObject() {
        return getString(KEY_Object);
    }

    public Date getCreatedAt(){
        return getDate(KEY_CREATED);
    }
    public String getDatePlaced(){
        Date date = getCreatedAt();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String datePlaced = df.format(date);
        return datePlaced;
    }



}
