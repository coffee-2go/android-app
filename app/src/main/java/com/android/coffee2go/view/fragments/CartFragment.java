package com.android.coffee2go.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.coffee2go.R;
import com.android.coffee2go.models.MenuItem;
import com.android.coffee2go.models.OrderLine;
import com.android.coffee2go.viewmodels.adapters.OrderLineListAdapter;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView orderLinesList;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        orderLinesList = view.findViewById(R.id.orderLineList);
        orderLinesList.hasFixedSize();
        orderLinesList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //TODO delete this part later, only for testing purposes
        MenuItem item = new MenuItem("Cappuccino",R.drawable.cappuccino);
        item.setUnitPrice(35);
        MenuItem item1 = new MenuItem("Espresso",R.drawable.espresso);
        item1.setUnitPrice(35);
        MenuItem item2 = new MenuItem("Caffe Americano",R.drawable.caffe_americano);
        item2.setUnitPrice(35);
        MenuItem item3 = new MenuItem("Caffe Misto",R.drawable.caffe_misto);
        item3.setUnitPrice(35);
        MenuItem item4 = new MenuItem("Caramel Macchiato",R.drawable.caramel_macchiato);
        item4.setUnitPrice(35);
        MenuItem item5 = new MenuItem("Flat White",R.drawable.flat_white);
        item5.setUnitPrice(35);
        MenuItem item6 = new MenuItem("Coffee Mocha",R.drawable.caffe_mocha);
        item6.setUnitPrice(35);


        ArrayList<OrderLine> orderLines = new ArrayList<>();

        OrderLine orderLine = new OrderLine(item,1);
        orderLines.add(orderLine);
        OrderLine orderLine1 = new OrderLine(item1,2);
        orderLines.add(orderLine1);
        OrderLine orderLine2 = new OrderLine(item2,3);
        orderLines.add(orderLine2);
        OrderLine orderLine3 = new OrderLine(item3,4);
        orderLines.add(orderLine3);
        OrderLine orderLine4 = new OrderLine(item4,5);
        orderLines.add(orderLine4);
        OrderLine orderLine5 = new OrderLine(item5,6);
        orderLines.add(orderLine5);
        OrderLine orderLine6 = new OrderLine(item6,7);
        orderLines.add(orderLine6);

        OrderLineListAdapter orderLineListAdapter = new OrderLineListAdapter(orderLines);
        orderLinesList.setAdapter(orderLineListAdapter);
        return view;
    }
}