package com.example.miskaparfums;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class ProductsFragment extends Fragment {

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_products, container, false);

        ArrayList<Perfume> perfumes = new ArrayList<>();
        perfumes.add(new Perfume(R.drawable.img_0001, R.raw.caption_0001));
        perfumes.add(new Perfume(R.drawable.img_0002, R.raw.caption_0002));
        perfumes.add(new Perfume(R.drawable.img_0003, R.raw.caption_0003));
        perfumes.add(new Perfume(R.drawable.img_0004, R.raw.caption_0004));
        perfumes.add(new Perfume(R.drawable.img_0005, R.raw.caption_0005));
        perfumes.add(new Perfume(R.drawable.img_0006, R.raw.caption_0006));
        perfumes.add(new Perfume(R.drawable.img_0007, R.raw.caption_0007));
        perfumes.add(new Perfume(R.drawable.img_0008, R.raw.caption_0008));
        perfumes.add(new Perfume(R.drawable.img_0009, R.raw.caption_0009));
        perfumes.add(new Perfume(R.drawable.img_0010, R.raw.caption_0010));
        perfumes.add(new Perfume(R.drawable.img_0011, R.raw.caption_0011));
        perfumes.add(new Perfume(R.drawable.img_0012, R.raw.caption_0012));
        perfumes.add(new Perfume(R.drawable.img_0013, R.raw.caption_0013));
        perfumes.add(new Perfume(R.drawable.img_0014, R.raw.caption_0014));
        perfumes.add(new Perfume(R.drawable.img_0015, R.raw.caption_0015));
        perfumes.add(new Perfume(R.drawable.img_0016, R.raw.caption_0016));
        perfumes.add(new Perfume(R.drawable.img_0017, R.raw.caption_0017));
        perfumes.add(new Perfume(R.drawable.img_0018, R.raw.caption_0018));
        perfumes.add(new Perfume(R.drawable.img_0019, R.raw.caption_0019));
        perfumes.add(new Perfume(R.drawable.img_0020, R.raw.caption_0020));
        perfumes.add(new Perfume(R.drawable.img_0021, R.raw.caption_0021));
        perfumes.add(new Perfume(R.drawable.img_0022, R.raw.caption_0022));
        perfumes.add(new Perfume(R.drawable.img_0023, R.raw.caption_0023));

        ProductsAdapter adapter = new ProductsAdapter(getActivity(), R.layout.image_list, perfumes);

        GridView productsView = view.findViewById(R.id.grid_view);
        productsView.setAdapter(adapter);

        productsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Perfume perfume = perfumes.get(position);

                Intent intent = new Intent(getActivity(), FullImageActivity.class);
                intent.putExtra("Image Path", perfume.getImageResourceID());
                intent.putExtra("Caption Path", perfume.getCaptionResourceID());
                startActivity(intent);
            }
        });

        return view;
    }
}