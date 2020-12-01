package edu.csustan.budgetbuddy;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;

public class BuildingCredit extends AppCompatActivity {

    RecyclerView rcv;
    CreditAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_credit1);
        setTitle("Building Credit");

        rcv = (RecyclerView) findViewById(R.id.recview);

        adapter = new CreditAdapter(dataqueue(),getApplicationContext());
        rcv.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rcv.setLayoutManager(gridLayoutManager);
    }

    public ArrayList<CreditModel> dataqueue() {

        ArrayList<CreditModel> holder= new ArrayList<>();

        CreditModel ob1 = new CreditModel();
        ob1.setHeader("Introduction to Credit");
        ob1.setDesc("What is credit?");
        ob1.setImgname(R.drawable.introcredit);
        ob1.setInfo("Your history with the ability to borrow money or access goods or services with the understanding that you will pay later, an agreement between a lender and a borrower who promises to repay the lender at a later date.");
        holder.add(ob1);


        CreditModel ob2 = new CreditModel();
        ob2.setHeader("Importance of Credit");
        ob2.setDesc("Why would you want good credit?");
        ob2.setImgname(R.drawable.growcredit);
        ob2.setInfo("It is important to build credit in order to be able to: \n ○ Finance a car \n \n ○ Buy a house \n \n ○ Qualify for best credit card offers \n \n - It can affect your job prospects \n \n ○ Renting an apartment \n \n ○ Qualifying for loans \n \n ○ It essentially is part of your financial power\n");
        holder.add(ob2);

        CreditModel ob3 = new CreditModel();
        ob3.setHeader("Building Credit");
        ob3.setDesc("How to build credit with a credit card");
        ob3.setImgname(R.drawable.highcredit);
        ob3.setInfo(" ○ Get a secured credit card. This is tied to a savings account which the limit is the amount of the credit card or a percentage of it. Keep balance low and make sure to pay it on time every month  \n \n ○ Open a joint account or become an authorized user. You can open a joint account with someone who already has good credit (i.e.parents). This creates a shared responsibility between who you choose to join an account with. \n \n" +
                "  ○ Request a credit limit increase. Once you paid your debt, decrease your utilization rate, and have your credit score at a good standing you can choose to ask your bank for a credit limit increase. This can eventually positively impact your credit score as long as you don’t increase your utilization with your new credit limit.");
        holder.add(ob3);

        CreditModel ob4 = new CreditModel();
        ob4.setHeader("Credit Cards");
        ob4.setDesc("Tips on using a credit card");
        ob4.setImgname(R.drawable.creditcards);
        ob4.setInfo("○ Only start out with one credit card. Applying for multiple credit cards will cause your credit score to drop. and have a higher risk for taking on credit card debt. \n \n ○ Only charge what you can afford to pay. This will help you adapt to building a better habit of managing your money efficiently. " +
                " \n \n ○ Pay your balance in full every month. Although credit card companies allow users to pay a minimum amount of your bill, interest rates begin to add up each month for not paying in full when you got the bill initially. If you only pay the minimum amount, all of the extra fees can lead you to paying $100 for something that was only $20. " +
                " This goes back to the “only charge what you can afford to pay” tip. \n ○ Stay under your credit limit. It is best to keep your charges within 10-30% of your credit card limit. \n  ");
        holder.add(ob4);

        CreditModel ob5 = new CreditModel();
        ob5.setHeader("Wells Fargo");
        ob5.setDesc("Best for Building & Rebuilding Credit");
        ob5.setImgname(R.drawable.wellsfargo);
        ob5.setInfo("https://www.wellsfargo.com/credit-cards/cash-back-college-card/");
        holder.add(ob5);

        CreditModel ob6 = new CreditModel();
        ob6.setHeader("Citi Bank");
        ob6.setDesc("Best for Earning Rewards");
        ob6.setImgname(R.drawable.citibank);
        ob6.setInfo("https://www.citi.com/credit-cards/credit-card-details/citi.action?ID=citi-rewards-plus-credit-card&category=view-all-credit-cards&afc=1C2&intc=7~7~67~1~MCT~1~CMSDefaultOffer");
        holder.add(ob6);

        CreditModel ob7 = new CreditModel();
        ob7.setHeader("Deserve Edu Mastercard");
        ob7.setDesc("Best for International Students");
        ob7.setImgname(R.drawable.mastercard);
        ob7.setInfo("https://www.deserve.com");
        holder.add(ob7);

        CreditModel ob8 = new CreditModel();
        ob8.setHeader("Discover");
        ob8.setDesc("Best for Earning Rewards");
        ob8.setImgname(R.drawable.discover);
        ob8.setInfo("https://www.discover.com/credit-cards/student/it-card.html");
        holder.add(ob8);

        CreditModel ob9 = new CreditModel();
        ob9.setHeader("Building Credit Alternative");
        ob9.setDesc("Different ways to Build Credit Without a Credit Card");
        ob9.setImgname(R.drawable.goodhabits);
        ob9.setInfo("○ Pay student loans consistently, on time, and every month. \n \n ○ Take out an auto installment loan if you are looking to buy a car, agree on monthly payments, and find the best deal that suits you. " +
                "○ Obtain a secured loan. Some banks and credit unions offer credit-builder loans/passbook loans that are low-risk loans designed just for you to build credit. It is similar to a secured credit card. \n \n " +
                " ○ Ask for credit. If you already reliably pay your rent, utilities, other monthly bills, then you can ask for a track record on those items for some companies. Some landlords will be able to report your positive payment history to credit bureaus.");
        holder.add(ob9);

        return holder;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.credit_menu,menu);
        MenuItem item = menu.findItem(R.id.search_menu);

        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }
}