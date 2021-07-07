package com.example.miskaparfums;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class OrderSummaryActivity extends AppCompatActivity {

    private final String MOBILE_NUMBER = "+962795828385";
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        ArrayList<Perfume> perfumes = PreferenceActivity.CartPreferenceFragment.getSummaryOfPerfumes();

        int qty = 0;
        for (int i = 0; i < perfumes.size(); i++) {
            qty += perfumes.get(i).getQuantity();
        }

        TextView totalQuantity = findViewById(R.id.total_quantity_of_items);
        totalQuantity.setText(String.valueOf(qty));

        double price = 0;
        for (int i = 0; i < perfumes.size(); i++) {
            price += perfumes.get(i).getQuantity() * perfumes.get(i).getProductPrice();
        }

        TextView totalPrice = findViewById(R.id.total_price);
        totalPrice.setText(String.valueOf(price));

        SummaryAdapter adapter = new SummaryAdapter(this, R.layout.summary_list, perfumes);

        ListView orderSummaryListView = findViewById(R.id.order_summary_list_view);
        orderSummaryListView.setAdapter(adapter);

        orderSummaryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Perfume perfume = adapter.getItem(position);

                long viewId = view.getId();

                if (viewId == R.id.decrease_quantity) {
                    Perfume replacePerfume = new Perfume(perfume.getImageResourceID(),
                            perfume.getCaptionResourceID(), perfume.getProductPrice(),
                            perfume.getQuantity() - 1);
                    updateSummaryActivity(position, replacePerfume, -1, false);

                } else if (viewId == R.id.increase_quantity) {
                    Perfume replacePerfume = new Perfume(perfume.getImageResourceID(),
                            perfume.getCaptionResourceID(), perfume.getProductPrice(),
                            perfume.getQuantity() + 1);
                    updateSummaryActivity(position, replacePerfume, 1, false);

                } else if (viewId == R.id.delete_from_order) {
                    updateSummaryActivity(position, null, perfume.getQuantity() * -1, true);
                }
            }
        });

        LinearLayout makeOrder = findViewById(R.id.make_the_order);
        makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalItemsQuantity = 0;
                SelectedProductActivity.perfumesToOrder = PreferenceActivity.CartPreferenceFragment.getSummaryOfPerfumes();

                StringBuilder builder = new StringBuilder();
                builder = builder.append("Hi..").append("\nI want to make the below order:\n");

                for (int i = 0; i < SelectedProductActivity.perfumesToOrder.size(); i++) {
                    builder = builder.append("\nPerfume # ").append(i + 1).append(" ------ Quantity: ")
                            .append(SelectedProductActivity.perfumesToOrder.get(i).getQuantity());

                    totalItemsQuantity += SelectedProductActivity.perfumesToOrder.get(i).getQuantity();
                }

                message = builder.toString();

                int resetTheCart = PreferenceActivity.CartPreferenceFragment.updateCart(MainActivity.RESET_THE_CART);
                MainActivity.cartTV.setText(String.valueOf(resetTheCart));

                SelectedProductActivity.perfumesToOrder.clear();
                PreferenceActivity.CartPreferenceFragment.addPerfumeToPreference(SelectedProductActivity.perfumesToOrder);

                Intent intent = getIntent();
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);

                if (totalItemsQuantity == 0) {
                    Toast toast = Toast.makeText(OrderSummaryActivity.this, "There are no items in the shopping cart !!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    String url = null;
                    try {
                        url = "https://api.whatsapp.com/send?phone=" + MOBILE_NUMBER + "&text=" + URLEncoder.encode(message, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse(url));
                    startActivity(sendIntent);
                }
            }
        });
    }

    public void updateSummaryActivity(int index, Perfume perfume, int number, boolean remove) {
        SelectedProductActivity.perfumesToOrder = PreferenceActivity.CartPreferenceFragment.getSummaryOfPerfumes();

        if (remove) {
            SelectedProductActivity.perfumesToOrder.remove(index);
        } else {
            SelectedProductActivity.perfumesToOrder.set(index, perfume);
        }
        PreferenceActivity.CartPreferenceFragment.addPerfumeToPreference(SelectedProductActivity.perfumesToOrder);

        int shoppingCart = PreferenceActivity.CartPreferenceFragment.updateCart(number);
        MainActivity.cartTV.setText(String.valueOf(shoppingCart));

        Intent intent = getIntent();
        finish();
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}