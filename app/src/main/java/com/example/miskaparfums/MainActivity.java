package com.example.miskaparfums;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    // A handle to the application's resources
    private static Resources resources;

    public static final int RESET_THE_CART = -100;

    public static TextView cartTV;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        int numberOfItems = PreferenceActivity.CartPreferenceFragment.updateCart(0);

        RelativeLayout badgeLayout = (RelativeLayout) menu.findItem(R.id.shopping_cart).getActionView();
        cartTV = badgeLayout.findViewById(R.id.number_of_items_in_cart);
        cartTV.setText(String.valueOf(numberOfItems));

        badgeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderSummary = new Intent(MainActivity.this, OrderSummaryActivity.class);
                startActivity(orderSummary);
            }
        });

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get shared preferences
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        // first time run?
        if (pref.getBoolean("firstTimeRun", true)) {
            // reset the shared preferences by passing -1 and make the cart items = 0
            PreferenceActivity.CartPreferenceFragment.updateCart(RESET_THE_CART);
            // get the preferences editor
            SharedPreferences.Editor editor = pref.edit();
            // avoid for next run
            editor.putBoolean("firstTimeRun", false);
            editor.apply();
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
        }

        // Get the application's resources
        resources = getResources();

        CategoryFragmentAdapter fragmentAdapter = new CategoryFragmentAdapter(getSupportFragmentManager(), this);

        ViewPager viewPager = findViewById(R.id.pager_view);
        viewPager.setAdapter(fragmentAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        invalidateOptionsMenu();
    }

    /** Load file from apps res/raw folder */
    public static String loadFile(String fileName) throws IOException {
        // Get the resource id from the file name.
        // The getIdentifier() method from the resources object returns a resource ID based
        // on the path. This parameter is composed by: package name:type of resource/file name
        // Package name is self explanatory; type of resource can be one of the
        // following: raw, drawable, string. File name is the fileName parameter,
        int rID = resources.getIdentifier(fileName, "raw", "com.example.miskaparfums");

        // Create a InputStream to read the file into, and then get the file as a stream
        InputStream iS = resources.openRawResource(rID);

        // Create a buffer that has the same size as the InputStream
        byte[] buffer = new byte[iS.available()];
        // Read the text file as a stream, into the buffer
        iS.read(buffer);
        // Create an output stream to write the buffer into
        ByteArrayOutputStream oS = new ByteArrayOutputStream();
        // Write this buffer to the output stream
        oS.write(buffer);
        // Close the Input and Output streams
        oS.close();
        iS.close();

        // Return the output stream as a String
        return oS.toString();
    }
}