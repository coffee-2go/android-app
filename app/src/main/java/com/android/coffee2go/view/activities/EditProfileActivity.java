package com.android.coffee2go.view.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.coffee2go.R;
import com.android.coffee2go.data.FirebaseAuthDAO;
import com.android.coffee2go.helper.ConfigFirebase;
import com.android.coffee2go.helper.Permission;
import com.android.coffee2go.models.Account;
import com.android.coffee2go.viewmodels.ProfileVM;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

    private CircleImageView imageEditProfile;
    private TextView textChangePicture;
    private EditText editUsername;
    private TextView editEmail;
    private Button buttonSaveChanges;

    private Account loggedAccount;
    private StorageReference storageRef;
    private String idAccount;

    private ProfileVM profileVM;

    private String[] necessaryPermissions = new String[] {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        profileVM = new ViewModelProvider(this).get(ProfileVM.class);

        // validate permissions
        Permission.validatePermissions(necessaryPermissions, this, 1);

        // initial config
        loggedAccount = profileVM.getAuthLoggedAccount();
        storageRef = ConfigFirebase.getFirebaseStorage().getReference();
        idAccount = profileVM.getAuthAccountUid();

        // config toolbar
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        toolbar.setTitle("Edit Profile");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);

        // init components
        initComponents();

        // get account data from loggedAccount
        FirebaseUser accountProfile = profileVM.getCurrentAccount();
        editUsername.setText(accountProfile.getDisplayName());
        editEmail.setText(accountProfile.getEmail());

        Uri url = accountProfile.getPhotoUrl();
        if ( url != null ) {
            Glide.with(EditProfileActivity.this)
                    .load( url )
                    .into( imageEditProfile );
        } else
            imageEditProfile.setImageResource(R.drawable.avatar);

        // save changes on username
        buttonSaveChanges.setOnClickListener(view -> {
            String updatedUsername = editUsername.getText().toString();

            // update username on profile
            // update username on realtime-database
            profileVM.updateUsername(updatedUsername);
            loggedAccount.setUsername(updatedUsername);
            profileVM.updateAccount(loggedAccount);

            Toast.makeText(EditProfileActivity.this,
                    "Account updated successfully!",
                    Toast.LENGTH_SHORT).show();
        });

        // gallery activity
        ActivityResultLauncher<Intent> launchGalleryActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Bitmap image = null;

                        Intent data = result.getData();
                        try {
                            // Select only from gallery
                            assert data != null;
                            Uri imageSelectedLocation = data.getData();
                            image = MediaStore.Images.Media.getBitmap(getContentResolver(), imageSelectedLocation);


                            // if user has choosen the image, load the image
                            if (image != null) {
                                // config image in the screen
                                imageEditProfile.setImageBitmap(image);

                                // recovery image data to firebase
                                ByteArrayOutputStream boas = new ByteArrayOutputStream();
                                image.compress(Bitmap.CompressFormat.JPEG, 70, boas);
                                byte[] imageDataInBytes = boas.toByteArray();

                                // save image in firebase
                                StorageReference imageRef = storageRef
                                        .child("images")
                                        .child("profile")
                                        .child(idAccount + ".jpeg");
                                // pass an array of bytes of the image
                                UploadTask uploadTask = imageRef.putBytes(imageDataInBytes);
                                uploadTask.addOnFailureListener(e -> {
                                    Toast.makeText(EditProfileActivity.this,
                                            "Error when trying to upload image!",
                                            Toast.LENGTH_SHORT).show();
                                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        // retrieve image uri
                                        Task<Uri> taskUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                                        taskUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                updatePictureAccount(uri);
                                            }
                                        });
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });


        // Change picture
        textChangePicture.setOnClickListener(view -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                launchGalleryActivity.launch(galleryIntent);
        });
    }


    private void updatePictureAccount(Uri url) {
        // update picture in profile
        profileVM.updatePictureAccount( url );

        // update picture in firebase
        loggedAccount.setUrlPicture( url.toString() );
        profileVM.updateAccount(loggedAccount);

        Toast.makeText(EditProfileActivity.this,
                "Your profile picture has been updated!",
                Toast.LENGTH_SHORT).show();
    }

    private void initComponents() {
        imageEditProfile = findViewById(R.id.editProfile_imageEditProfile);
        textChangePicture = findViewById(R.id.editProfile_textChangePicture);
        editUsername = findViewById(R.id.editProfile_editUsername);
        editEmail = findViewById(R.id.editProfile_TextEmail);
        buttonSaveChanges = findViewById(R.id.editProfile_buttonSaveChanges);

        editEmail.setFocusable(false);
    }

    // when pressing back button, finish() this activity (and returns to previous one)
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}