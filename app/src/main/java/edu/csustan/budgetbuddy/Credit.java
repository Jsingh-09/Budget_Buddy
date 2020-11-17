package edu.csustan.budgetbuddy;

//Natasha
// Item information for building credit activity
public class Credit {

    private final String bankName;
    private final String bankDescription;
    private final String bestFor;
    private final String applicationLink;

    public Credit(String bankName, String bankDescription, String bestFor, String applicationLink) {
        this.bankName = bankName;
        this.bankDescription = bankDescription;
        this.bestFor = bestFor;
        this.applicationLink = applicationLink;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankDescription() {
        return bankDescription;
    }

    public String getBestFor() {
        return bestFor;
    }

    public String getApplicationLink() {
        return applicationLink;
    }
}
