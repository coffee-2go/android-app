package com.android.coffee2go.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.coffee2go.R;
import com.android.coffee2go.models.MenuItem;
import com.android.coffee2go.view.activities.CategoryItemsActivity;
import com.android.coffee2go.viewmodels.adapters.MenuListAdapter;
import com.android.coffee2go.viewmodels.OnListItemClickListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment implements OnListItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mMenuList;
    private MenuListAdapter mMenuAdapter;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        mMenuList = view.findViewById(R.id.menuItems);
        mMenuList.hasFixedSize();
        mMenuList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Hot Coffees",R.drawable.hot_coffee));
        items.add(new MenuItem("Cold Coffees",R.drawable.cold_coffee));
        items.add(new MenuItem("Hot Drinks",R.drawable.hot_drinks));
        items.add(new MenuItem("Cold Drinks",R.drawable.cold_drinks));
        items.add(new MenuItem("Breakfast",R.drawable.breakfast));
        items.add(new MenuItem("Sandwiches",R.drawable.sandwiches));
        items.add(new MenuItem("Bakery",R.drawable.bakery));
        items.add(new MenuItem("Snacks",R.drawable.snacks));
        mMenuAdapter = new MenuListAdapter(items,this);
        mMenuList.setAdapter(mMenuAdapter);
        return view;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (clickedItemIndex == 0){
            Intent intent = new Intent(getActivity(), CategoryItemsActivity.class);
            startActivity(intent);
        }
    }
}