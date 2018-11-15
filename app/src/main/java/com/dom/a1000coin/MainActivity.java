package com.dom.a1000coin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {


    private ItemFragment fragments[] = new ItemFragment[4];
    private int selected;

    private ItemFragment getFragment(int pos) {
        if (fragments[pos] == null) {
            fragments[pos] = ItemFragment.newInstance(pos + 1);
        }
        selected = pos;
        return fragments[pos];
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, getFragment(0)).addToBackStack(null).commit();
                    return true;
                case R.id.navigation_myCollection:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, getFragment(1)).addToBackStack(null).commit();
                    return true;
                case R.id.navigation_catalog:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, getFragment(2)).addToBackStack(null).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //cartview
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState != null) {
            selected = savedInstanceState.getInt("SELECTED");
            for (int i = 0; i < fragments.length; i++) {
                fragments[i] = (ItemFragment) getSupportFragmentManager().getFragment(savedInstanceState, "F" + i);
                if(fragments[i]!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, fragments[i]).addToBackStack(null).commit();
                }
            }
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, getFragment(selected)).addToBackStack(null).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SELECTED", selected);
        for (int i = 0; i < fragments.length; i++) {
            if(fragments[i]!=null){
                getSupportFragmentManager().putFragment(outState, "F" + i, fragments[i]);
            }
        }
    }
}
