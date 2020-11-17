//Author: Jorge Hernandez Ortega
//The following code was partially adapted using the AnyChart documentation: https://github.com/AnyChart/AnyChart-Android
//The following code was partially adapted using the Back4App documentation: https://www.back4app.com/docs/get-started/welcome

package edu.csustan.budgetbuddy.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import edu.csustan.budgetbuddy.Expense;
import edu.csustan.budgetbuddy.R;


public class GraphFragment extends Fragment {

    public static final String TAG = "GraphFragment";                                                       //TAG used for debugging in Logcat

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View chartView =  inflater.inflate(R.layout.fragment_graph, container, false);    //View that is returned at the end of the method
        List<Expense> dataList;                                                                             //List used in ParseQuery, Stores Expense objects
        ParseQuery<Expense> query = ParseQuery.getQuery(Expense.class);                                     //initiates a parseQuery based on the Expense class
        query.whereEqualTo("user", ParseUser.getCurrentUser());                                             //query parameter that calls info only from the currently logged in user
        query.include(Expense.KEY_ITEMTYPE);                                                                //query parameter that includes Expense type
        query.include(Expense.KEY_AMOUNT);                                                                  //query parameter that includes Expense amount
        final List<DataEntry> data = new ArrayList<>();                                                     //initiates a DataEntry List that will be used to populate chart

        query.findInBackground(new FindCallback<Expense>() {                                                //Finds all query matching the given parameters in database
            @Override
            public void done(List<Expense> dataList, ParseException e) {
                if(e != null){
                    return;
                }
                for(Expense objects : dataList){                                                            //advanced for loop that iterates through the entries in the database
                    Log.i(TAG, "Type: " + objects.getItemType() + " Amount: " + objects.getCost());   //Log used for debugging

                    //Todo: add formatting to queried data before adding to List(by type).

                    data.add(new ValueDataEntry(objects.getItemType(), objects.getCost()));                 //adds Expense type and amount as a new DataEntry into the List data
                }
                Pie pie = AnyChart.pie();                                                                   //initiates a new chart that is a Pie chart
                pie.data(data);                                                                             //feeds the API the List data to populate the Pie chart

                pie.title("Expense Report");                                                                //This API method adds a custom title to the chart view
                pie.legend().position("center-bottom").itemsLayout(LegendLayout.VERTICAL).align(Align.LEFT);//This API method is used for formatting the position of the Legend

                AnyChartView anyChartView = (AnyChartView) chartView.findViewById(R.id.any_chart_view);     //initiates a chartview that is bound to the chartView in the xml file
                anyChartView.setChart(pie);                                                                 //binds the pie chart into the view anyChartView
            }
        });
        return chartView;
    }
}