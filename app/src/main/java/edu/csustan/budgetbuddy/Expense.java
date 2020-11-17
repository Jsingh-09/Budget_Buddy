package edu.csustan.budgetbuddy;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

//Stephanie's code
@ParseClassName("Expenses")
public class Expense extends ParseObject {
    public static final String KEY_LOCATION = "location";
    public static final String KEY_ITEMTYPE ="itemType";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED = "createdAt";


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
    public void setAmount(String amount) {
        put(KEY_AMOUNT, amount);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {

        put(KEY_USER, user);
    }


}
