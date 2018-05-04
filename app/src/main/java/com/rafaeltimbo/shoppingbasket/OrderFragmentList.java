package com.rafaeltimbo.shoppingbasket;

import android.app.ListFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderFragmentList extends ListFragment {

    public OrderAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new OrderAdapter(getActivity());
        setListAdapter(adapter);
        this.update();

        TextView noOrdersText = getActivity().findViewById(R.id.noOrdersText);
        if (adapter.getCount() > 0) {
            noOrdersText.setVisibility(View.GONE);
        } else noOrdersText.setVisibility(View.VISIBLE);

    }

    public void update() {
        SharedPreferences preferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        boolean isClerk = preferences.getBoolean("isClerk", false);
        ArrayList<Order> dbOrders;

        if (isClerk) dbOrders = Order.fetchOrders();
        else {
            User customer = User.queryCustomerByUsername( preferences.getString("username", null));
            dbOrders = Order.fetchOrders(customer.id);
        }

        this.adapter.clear();
        this.adapter.addAll(dbOrders);
        this.adapter.notifyDataSetChanged();
    }
}
