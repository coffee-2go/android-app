package com.android.coffee2go.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.coffee2go.R;
import com.android.coffee2go.models.MenuItem;
import com.android.coffee2go.view.activities.MenuItemActivity;
import com.android.coffee2go.view.adapters.MainMenuCategoryAdapter;
import com.android.coffee2go.view.adapters.MainMenuItemsAdapter;
import com.android.coffee2go.viewmodels.MenuVM;
import com.android.coffee2go.viewmodels.OnListItemClickListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenu extends Fragment implements OnListItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView categoriesList;
    private RecyclerView menuItemList;
    private MainMenuCategoryAdapter menuCategoryAdapter;
    private MainMenuItemsAdapter menuItemAdapter;

    MenuVM vm;

    public MainMenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenu.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenu newInstance(String param1, String param2) {
        MainMenu fragment = new MainMenu();
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
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        categoriesList = view.findViewById(R.id.categoryItems_categories);
        categoriesList.hasFixedSize();
        categoriesList.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL,false));

        MenuVM menuVM = new ViewModelProvider(this).get(MenuVM.class);

        menuCategoryAdapter = new MainMenuCategoryAdapter(this,menuVM);
        categoriesList.setAdapter(menuCategoryAdapter);

        menuItemList = view.findViewById(R.id.main_menu_menuItemsList);
        menuItemList.hasFixedSize();
        menuItemList.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL,false));

        vm = new ViewModelProvider(this).get(MenuVM.class);

        menuItemAdapter = new MainMenuItemsAdapter(this,vm);
        menuItemList.setAdapter(menuItemAdapter);

        vm.getAllItems().observe(getViewLifecycleOwner(), menuItems -> {
                menuItemList.getRecycledViewPool().clear();
                menuItemAdapter.notifyDataSetChanged();
                menuItemAdapter.setItemsToShow(menuItems);
        });
        return view;
    }

    @Override
    public void onListItemClick(int id, int clickedItemIndex) {
        if (id == 0){
            List<MenuItem> categoryItems = vm.getCategoryItems(clickedItemIndex);
            menuItemAdapter.setItemsToShow(categoryItems);
        }else
        {
            Intent intent = new Intent(getActivity(), MenuItemActivity.class);
            intent.putExtra("position",clickedItemIndex);
            startActivity(intent);
        }
    }
}