package com.example.miskaparfums;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Perfume> {

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public OrderAdapter(Context context, int resource, List<Perfume> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.products_list, parent, false);
        }

        Perfume currentPerfume = getItem(position);

        int captionPath = currentPerfume.getCaptionResourceID();
        String output = "";

        try {
            // Load the file from the raw folder
            output = MainActivity.loadFile("" + captionPath);
        } catch (Exception e) {
            // Display an error toast message
            Toast toast = Toast.makeText(getContext(), "File not found", Toast.LENGTH_SHORT);
            toast.show();
        }

        TextView productDescription = convertView.findViewById(R.id.product_description);
        productDescription.setText(output);

        TextView productPrice = convertView.findViewById(R.id.product_price);
        productPrice.setText(String.valueOf(currentPerfume.getProductPrice()));

        ImageView productPhoto = convertView.findViewById(R.id.product_photo);
        productPhoto.setImageResource(currentPerfume.getImageResourceID());

        return convertView;
    }
}
