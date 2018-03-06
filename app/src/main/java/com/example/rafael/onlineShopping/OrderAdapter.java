package com.example.rafael.onlineShopping;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class OrderAdapter extends ArrayAdapter<Order> {

    public OrderAdapter(@NonNull Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        final Order order = getItem(position);
        Log.d("rafael.ORDER=", order.toStringLog());

        User customer = User.queryCustomerById(order.customerId);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_fragment_item, parent, false);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "ON CLICK LISTENER" + product.toString(), Toast.LENGTH_SHORT).show();

                Intent orderDetails = new Intent(getContext(), OrderDetailsActivity.class);
                orderDetails.putExtra("order", order.id);
                orderDetails.putExtra("productPosition", position);
                getContext().startActivity(orderDetails);
            }
        });

        final TextView idtv = convertView.findViewById(R.id.orderId);
        final TextView orderCustomertv = convertView.findViewById(R.id.orderCustomer);
        final TextView datetv = convertView.findViewById(R.id.orderDate);
        final TextView statustv = convertView.findViewById(R.id.orderStatus);
        final TextView pricetv = convertView.findViewById(R.id.orderPrice);

        // Populate the data into the template view using the data object
        idtv.setText(order.id);
        orderCustomertv.setText(customer.fullName());
        datetv.setText(order.orderDate);
        statustv.setText(order.status.toString());
        pricetv.setText(String.format("$%5.2f", order.price * order.quantity));

        return convertView;
    }
}
