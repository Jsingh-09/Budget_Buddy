package edu.csustan.budgetbuddy;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Savings")
public class Saving extends ParseObject {

    public static final String KEY_GOAL ="goal";
    public static final String KEY_GOALAMOUNT = "goalAmount";
    public static final String KEY_AMOUNTSAVED = "amountSaved";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED = "createdAt";


    //define getter and setters
    public String getGoal() {
        return getString(KEY_GOAL);
    }
    public void setGoal(String goal) {
        put(KEY_GOAL, goal);
    }

    public String getGoalAmount() {
        return getString(KEY_GOALAMOUNT);
    }
    public void setGoalAmount(String goalAmount) {
        put(KEY_GOALAMOUNT, goalAmount);
    }

    public String getAmountSaved() {
        return getString(KEY_AMOUNTSAVED);
    }
    public void setAmountSaved(String amountSaved) {
        put(KEY_AMOUNTSAVED, amountSaved);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public static String getKeyCreated() {
        return KEY_CREATED;
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);


    }
}
