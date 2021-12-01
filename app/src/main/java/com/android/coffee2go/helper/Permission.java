package com.android.coffee2go.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.List;

public class Permission {

    public static boolean validatePermissions(String[] permissions, Activity activity, int requestCode) {

        if (Build.VERSION.SDK_INT >= 23) {

            List<String> listPermissions = new ArrayList<>();

            /* Check past permissions,
            checking one by one, if it has the permission available
             */
            for (String permission : permissions) {
                Boolean hasPermission = ContextCompat
                        .checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
                if ( !hasPermission ) listPermissions.add(permission);
            }

            /* if list is empty, it's not necessary ask permission */
            if (listPermissions.isEmpty()) return true;
            String[] newPermissions = new String[ listPermissions.size() ];
            listPermissions.toArray( newPermissions );

            // ask permission
            ActivityCompat.requestPermissions(activity, newPermissions, requestCode);
        }

        return true;
    }

}
