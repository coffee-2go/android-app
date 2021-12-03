package com.android.coffee2go.view.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.android.coffee2go.R;
import com.android.coffee2go.view.activities.MainActivity;
import com.android.coffee2go.viewmodels.CartVM;
import com.android.coffee2go.viewmodels.CartVMImpl;
import com.android.coffee2go.viewmodels.OnListItemClickListener;
import com.android.coffee2go.view.adapters.OrderLineListAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class CartFragment extends Fragment implements OnListItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button button;

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

        button = view.findViewById(R.id.cart_button_checkout);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.locationFragment);
            }
        });

        orderLinesList.hasFixedSize();
        orderLinesList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        CartVM cartVM = new ViewModelProvider(this).get(CartVMImpl.class);
        OrderLineListAdapter orderLineListAdapter = new OrderLineListAdapter(cartVM,this);
        orderLinesList.setAdapter(orderLineListAdapter);

        TextView total = view.findViewById(R.id.cartFragment_Total);
        total.setText(cartVM.getTransactionTotal() + " DKK");



        cartVM.getTransactionTotal().observe(getViewLifecycleOwner(), number ->{
            total.setText(number.doubleValue()+" DKK");
        });

        cartVM.getTransactionOrderLines().observe(getViewLifecycleOwner(), items ->{
            orderLinesList.getRecycledViewPool().clear();
            orderLineListAdapter.notifyDataSetChanged();
            orderLineListAdapter.setItemsToShow(items);
        });


        return view;
    }

    @Override
    public void onListItemClick(int id, int clickedItemIndex) {

    }
}