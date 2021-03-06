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

import edu.csustan.budgetbuddy.fragments.AddGoalFragment;
import edu.csustan.budgetbuddy.fragments.ProgressFragment;
import edu.csustan.budgetbuddy.fragments.UpdateFragment;

public class SavingGoalActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_goal);
        setTitle("Saving Goal");
        bottomNavigationView = findViewById(R.id.bottomNavigation2);

                bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        Fragment fragment;
                        switch (menuItem.getItemId()) {
                            case R.id.action_progress:
                                fragment = new ProgressFragment();
                                // fragment = fragment1;
                                break;
                            case R.id.action_update:
                                fragment = new UpdateFragment();

                                //  fragment = fragment2;
                                break;
                            case R.id.action_add_goal:
                            default:
                                fragment = new AddGoalFragment();
                                //  fragment = fragment3;
                                break;
                        }

                       fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                        return true;
                    }
                });
        bottomNavigationView.setSelectedItemId(R.id.action_add_goal);


    }
}