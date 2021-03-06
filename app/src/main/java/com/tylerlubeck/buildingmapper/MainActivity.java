package com.tylerlubeck.buildingmapper;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Tyler on 2/20/2015.
 */
public class MainActivity extends Activity {
    /**
     * Create the Activity Instance
     * @param savedInstanceState    Any saved instance
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_layout);

        if (findViewById(R.id.fragment_container) != null) {
            /* If there is a saved instance, then the fragment is already showing */
            if (savedInstanceState != null) {
                return;
            }

            /* Otherwise create the fragment and display it */
            BuildingMapperFragment buildingMapperFragment = new BuildingMapperFragment();
            buildingMapperFragment.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, buildingMapperFragment).commit();
        }
    }

    /**
     * Create a menu when the button is pressed
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.building_mapper, menu);
        return true;
    }

    /**
     * When a menu item is chosen, process it.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.action_settings:
                PrefsFragment prefsFragment = new PrefsFragment();
                prefsFragment.setArguments(getIntent().getExtras());
                FragmentManager fragmentManager = getFragmentManager();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, prefsFragment)
                        .addToBackStack(null)
                        .commit();
        }

        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
