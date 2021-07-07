package com.example.miskaparfums;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import java.util.ArrayList;

public class SelectedProductActivity extends MainActivity {

    public static ArrayList<Perfume> perfumesToOrder;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(Activity.RESULT_CANCELED);
            finish(); // close this activity and return to previous activity
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_product);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();
        int description = bundle.getInt("Product Description");
        int photo = bundle.getInt("Product Photo");
        double price = bundle.getDouble("Product Price");

        String output = "";

        try {
            // Load the file from the raw folder
            output = loadFile("" + description);
        } catch (Exception e) {
            // Display an error toast message
            Toast toast = Toast.makeText(this, "File not found", Toast.LENGTH_SHORT);
            toast.show();
        }

        TextView productDescription = findViewById(R.id.description_of_selected_product);
        productDescription.setText(output);

        ImageView productPhoto = findViewById(R.id.photo_of_selected_product);
        productPhoto.setImageResource(photo);

        Spinner spinner = findViewById(R.id.spinner);
        Integer[] selectedQuantity = new Integer[]{1, 2, 3, 4, 5};

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, selectedQuantity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        FrameLayout addToCart = findViewById(R.id.add_to_cart_view);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedQuantity = (int) spinner.getSelectedItem();
                boolean isNewPerfume = true;

                int addToShoppingCart = PreferenceActivity.CartPreferenceFragment.updateCart(selectedQuantity);
                cartTV.setText(String.valueOf(addToShoppingCart));

                perfumesToOrder = PreferenceActivity.CartPreferenceFragment.getSummaryOfPerfumes();

                if (!perfumesToOrder.isEmpty()) {
                    for (int i = 0; i < perfumesToOrder.size(); i++) {
                        if (perfumesToOrder.get(i).getImageResourceID() == photo) {
                            int previousQuantity = perfumesToOrder.get(i).getQuantity();
                            perfumesToOrder.set(i, new Perfume(photo, description, price, previousQuantity + selectedQuantity));

                            isNewPerfume = false;
                        }
                    }
                }

                if (isNewPerfume) {
                    perfumesToOrder.add(new Perfume(photo, description, price, selectedQuantity));
                }

                PreferenceActivity.CartPreferenceFragment.addPerfumeToPreference(perfumesToOrder);

                Toast toast = Toast.makeText(SelectedProductActivity.this, "Added to Cart.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}