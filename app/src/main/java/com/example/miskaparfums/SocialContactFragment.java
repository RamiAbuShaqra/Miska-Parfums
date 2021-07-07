package com.example.miskaparfums;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SocialContactFragment extends Fragment {

    public SocialContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_social_contact, container, false);

        ImageView facebookPage = view.findViewById(R.id.social_facebook_icon);
        facebookPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("fb://facewebmodal/f?href=https://www.facebook.com/2872606072965016");
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(facebookIntent);
            }
        });

        ImageView instagramPage = view.findViewById(R.id.social_instagram_icon);
        instagramPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/miskaparfums/");
                Intent instagramIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(instagramIntent);
            }
        });

        return view;
    }
}