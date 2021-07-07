package com.example.miskaparfums;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

public class ProductsAdapter extends ArrayAdapter<Perfume> {

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public ProductsAdapter(Context context, int resource, List<Perfume> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.image_list, parent, false);
        }

        Perfume currentPerfume = getItem(position);

        ImageView productPhoto = convertView.findViewById(R.id.image_list);
        productPhoto.setImageResource(currentPerfume.getImageResourceID());

        return convertView;
    }
}
