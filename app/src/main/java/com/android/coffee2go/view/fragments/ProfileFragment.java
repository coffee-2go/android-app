package com.android.coffee2go.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.coffee2go.R;
import com.android.coffee2go.data.FirebaseAuthDAO;
import com.android.coffee2go.helper.ConfigFirebase;
import com.android.coffee2go.models.Account;
import com.android.coffee2go.view.activities.EditProfileActivity;
import com.android.coffee2go.viewmodels.ProfileVM;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    private ProgressBar progressBar;
    private CircleImageView imageProfile;
    private TextView textUsername;
    private Button buttonEditProfile;

    private ProfileVM profileVM;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // config components
        initComponents(view);

        profileVM = new ViewModelProvider(this).get(ProfileVM.class);

        profileVM.getDataLoggedAccount().observe(getViewLifecycleOwner(), account -> {

            showAccountPicture(account);
            showUsername(account);
        });

        // open edit profile
        buttonEditProfile.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), EditProfileActivity.class);
            startActivity(intent);
        });

        return view;
    }

    public void initComponents(View view) {
        progressBar = view.findViewById(R.id.profile_progressBar);
        imageProfile = view.findViewById(R.id.profile_imageView);
        textUsername = view.findViewById(R.id.profile_textUsername);
        buttonEditProfile = view.findViewById(R.id.profile_buttonEditProfile);
    }

    private void showUsername(Account account) {

        String username = String.valueOf( account.getUsername() );

        textUsername.setText(username);
    }

    private void showAccountPicture(Account account) {
        String urlPicture = account.getUrlPicture();
        if (urlPicture != null) {
            Glide.with(this)
                    .load(urlPicture)
                    .into(imageProfile);
        } else
            imageProfile.setImageResource(R.drawable.avatar);
    }

}