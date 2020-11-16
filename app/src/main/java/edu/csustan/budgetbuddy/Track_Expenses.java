package edu.csustan.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.csustan.budgetbuddy.fragments.AddFragment;
import edu.csustan.budgetbuddy.fragments.GraphFragment;
import edu.csustan.budgetbuddy.fragments.ListItemsFragment;

public class Track_Expenses extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track__expenses);

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
            bottomNavigationView.setSelectedItemId(R.id.action_add);
        }
    }