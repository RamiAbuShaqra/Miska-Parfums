package com.example.miskaparfums;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SummaryAdapter extends ArrayAdapter<Perfume> {

    public SummaryAdapter(Context context, int resource, List<Perfume> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.summary_list, parent, false);
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

        TextView productDescription = convertView.findViewById(R.id.select_to_buy_product_description);
        productDescription.setText(output);

        TextView productPrice = convertView.findViewById(R.id.select_to_buy_product_price);
        productPrice.setText(String.valueOf(currentPerfume.getProductPrice()));

        ImageView productPhoto = convertView.findViewById(R.id.select_to_buy_product_photo);
        productPhoto.setImageResource(currentPerfume.getImageResourceID());

        TextView productQuantity = convertView.findViewById(R.id.current_selected_quantity);
        productQuantity.setText(String.valueOf(currentPerfume.getQuantity()));

        // Here, we will set OnClickListener to the decrease, increase, and delete buttons.
        // The trick is to call performItemClick and pass this view.
        // We will be able to catch this view in onItemClickListener’s onItemClick method.
        // According to the official doc, performItemClick method calls the OnItemClickListener
        // if it is defined.

        TextView decreaseQuantity = convertView.findViewById(R.id.decrease_quantity);
        decreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Let the event be handled in onItemClick()
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });

        TextView increaseQuantity = convertView.findViewById(R.id.increase_quantity);
        increaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Let the event be handled in onItemClick()
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });

        TextView deleteView = convertView.findViewById(R.id.delete_from_order);
        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Let the event be handled in onItemClick()
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });

        return convertView;
    }
}
