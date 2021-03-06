// written by: Stephanie Gamboa
// tested by: Stephanie, Jashan, Chris, and Jorge
// debugged by:  Stephanie Gamboa
package edu.csustan.budgetbuddy;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.csustan.budgetbuddy.fragments.AddFragment;
import edu.csustan.budgetbuddy.fragments.GraphFragment;
import edu.csustan.budgetbuddy.fragments.ListItemsFragment;

public class Track_Expenses extends AppCompatActivity {

    //Stephanie's Code
    private BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    //handle Navigation section
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track__expenses);
        setTitle("Track my Expense");



            bottomNavigationView = findViewById(R.id.bottomNavigation);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment;
                    switch (menuItem.getItemId()) {
                        case R.id.action_graphs:
                            fragment = new GraphFragment();
                            // fragment = fragment1;
                            break;
                        case R.id.action_list:
                            //fragment = new AddFragment();
                            fragment = new ListItemsFragment();
                            //  fragment = fragment2;
                            break;
                        case R.id.action_add:
                        default:
                            fragment = new AddFragment();
                            //  fragment = fragment3;
                            break;
                    }

                    fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                    return true;
                }
            });
        //set default section
            bottomNavigationView.setSelectedItemId(R.id.action_add);
        }
    }