package com.android.coffee2go.view.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.coffee2go.R;
import com.android.coffee2go.helper.AccountFirebase;
import com.android.coffee2go.helper.ConfigFirebase;
import com.android.coffee2go.models.Account;
import com.android.coffee2go.view.activities.EditProfileActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ProgressBar progressBar;
    private CircleImageView imageProfile;
    private TextView textUsername;
    private Button buttonEditProfile;
    private Account loggedAccount;

    private DatabaseReference firebaseRef;
    private DatabaseReference accountsRef;
    private DatabaseReference loggedAccountRef;
    private ValueEventListener valueEventListenerAccount;


    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // initial config
        loggedAccount = AccountFirebase.getDataLoggedAccount();
        firebaseRef = ConfigFirebase.getDatabaseReference();
        accountsRef = firebaseRef.child("accounts");

        // config components
        initComponents(view);

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

    private void getDataLoggedAccount() {
        loggedAccountRef = accountsRef.child(loggedAccount.getId());
        valueEventListenerAccount = loggedAccountRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Account account = snapshot.getValue( Account.class );

                String username = String.valueOf( account.getUsername() );

                textUsername.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void retrieveAccountPicture() {
        loggedAccount = AccountFirebase.getDataLoggedAccount();

        // retrieve account picture
        String urlPicture = loggedAccount.getUrlPicture();
        if ( urlPicture != null ) {
            Uri url = Uri.parse( urlPicture );
            Glide.with(getActivity())
                    .load( url )
                    .into( imageProfile );
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // retrieve data logged account
        getDataLoggedAccount();

        // retrieve account picture
        retrieveAccountPicture();
    }

    @Override
    public void onStop() {
        super.onStop();
        loggedAccountRef.removeEventListener(valueEventListenerAccount);
    }
}