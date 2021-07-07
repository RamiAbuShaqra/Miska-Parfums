package com.example.miskaparfums;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderFragment extends Fragment {

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_order, container, false);

        ArrayList<Perfume> perfumesList = new ArrayList<>();
        perfumesList.add(new Perfume(R.drawable.img_0001, R.raw.caption_0001, 8.0));
        perfumesList.add(new Perfume(R.drawable.img_0002, R.raw.caption_0002, 6.5));
        perfumesList.add(new Perfume(R.drawable.img_0003, R.raw.caption_0003, 7.5));
        perfumesList.add(new Perfume(R.drawable.img_0004, R.raw.caption_0004, 8.0));
        perfumesList.add(new Perfume(R.drawable.img_0005, R.raw.caption_0005, 7.0));
        perfumesList.add(new Perfume(R.drawable.img_0006, R.raw.caption_0006, 6.5));
        perfumesList.add(new Perfume(R.drawable.img_0007, R.raw.caption_0007, 6.5));
        perfumesList.add(new Perfume(R.drawable.img_0008, R.raw.caption_0008, 7.5));
        perfumesList.add(new Perfume(R.drawable.img_0009, R.raw.caption_0009, 9.0));
        perfumesList.add(new Perfume(R.drawable.img_0010, R.raw.caption_0010, 7.0));
        perfumesList.add(new Perfume(R.drawable.img_0011, R.raw.caption_0011, 7.0));
        perfumesList.add(new Perfume(R.drawable.img_0012, R.raw.caption_0012, 9.0));
        perfumesList.add(new Perfume(R.drawable.img_0013, R.raw.caption_0013, 12.0));
        perfumesList.add(new Perfume(R.drawable.img_0014, R.raw.caption_0014, 8.0));
        perfumesList.add(new Perfume(R.drawable.img_0015, R.raw.caption_0015, 7.0));
        perfumesList.add(new Perfume(R.drawable.img_0016, R.raw.caption_0016, 7.0));
        perfumesList.add(new Perfume(R.drawable.img_0017, R.raw.caption_0017, 20.0));
        perfumesList.add(new Perfume(R.drawable.img_0018, R.raw.caption_0018, 20.0));
        perfumesList.add(new Perfume(R.drawable.img_0019, R.raw.caption_0019, 9.0));
        perfumesList.add(new Perfume(R.drawable.img_0020, R.raw.caption_0020, 10.0));
        perfumesList.add(new Perfume(R.drawable.img_0021, R.raw.caption_0021, 18.0));
        perfumesList.add(new Perfume(R.drawable.img_0022, R.raw.caption_0022, 9.0));
        perfumesList.add(new Perfume(R.drawable.img_0023, R.raw.caption_0023, 8.0));

        OrderAdapter adapter = new OrderAdapter(getActivity(), R.layout.products_list, perfumesList);

        ListView productsListView = view.findViewById(R.id.list_view);
        productsListView.setAdapter(adapter);

        productsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Perfume clickedPerfume = adapter.getItem(position);

                Intent intent = new Intent(getContext(), SelectedProductActivity.class);
                intent.putExtra("Product Description", clickedPerfume.getCaptionResourceID());
                intent.putExtra("Product Photo", clickedPerfume.getImageResourceID());
                intent.putExtra("Product Price", clickedPerfume.getProductPrice());
                startActivity(intent);
            }
        });

        return view;
    }
}