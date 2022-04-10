package com.kanxue.school_test_7;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permission {
    int REQUEST_CODE_ASK_CALL_PHONE = 100;

    private String[] permission={
            Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private List<String> permissionList=new ArrayList<>();

    public   void checkPerssion(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < permission.length; i++) {
                if (ContextCompat.checkSelfPermission(activity, permission[i]) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(permission[i]);
                }
            }
            if (permissionList.size() > 0) {
                requestPermissions(activity);
            }
        }
    }

    private void requestPermissions(Activity activity){
        ActivityCompat.requestPermissions(activity,permissionList.toArray(new String[permissionList.size()]),REQUEST_CODE_ASK_CALL_PHONE);
    }


}
