package com.example.miskaparfums;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.example.MyApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
    }

    public static class CartPreferenceFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }

        public static int updateCart(int items){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getAppContext());
            int currentItems = prefs.getInt("cart_items", 0);

            if (items == -100){
                currentItems = 0;
            } else {
                currentItems += items;
            }

            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("cart_items", currentItems);
            editor.apply();

            return prefs.getInt("cart_items", 0);
        }

        public static void addPerfumeToPreference(ArrayList<Perfume> list) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getAppContext());
            SharedPreferences.Editor editor = prefs.edit();

            Gson gson = new Gson();
            String json = gson.toJson(list);

            editor.putString("summary_items", json);
            editor.apply();
        }

        public static ArrayList<Perfume> getSummaryOfPerfumes() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getAppContext());
            String json = prefs.getString("summary_items", "");

            Gson gson = new Gson();
            Type type = new TypeToken<List<Perfume>>() {}.getType();

            ArrayList<Perfume> perfumes = gson.fromJson(json, type);

            if (perfumes == null){
                return new ArrayList<>();
            } else return perfumes;
        }
    }
}