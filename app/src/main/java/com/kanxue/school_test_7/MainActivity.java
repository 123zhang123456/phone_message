package com.kanxue.school_test_7;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private Activity mContext;
    String mobile="";
    String body="";
    private Permission permission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.btn_call);
        EditText edit_mobile=findViewById(R.id.edit_mobile);

        EditText sms_body=findViewById(R.id.sms_body);
        this.mContext = this;
        permission=new Permission();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile=edit_mobile.getText().toString();
                String body=sms_body.getText().toString();
                Intent intent = new Intent();
                //进入电话拨打界面的意图: Intent.ACTION_DIAL
                intent.setAction(Intent.ACTION_CALL);
                //传入电话号码 拨打电话的Uri: tel:110
                intent.setData(Uri.parse("tel:"+mobile));
                permission.checkPerssion(mContext);
                //启动Activity
                startActivity(intent);
            }
        });

        Button send_sms=findViewById(R.id.sendSMS);
        send_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile=edit_mobile.getText().toString();
                String body=sms_body.getText().toString();
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"+mobile));
                intent.putExtra("sms_body",body);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100){
            for (int i = 0; i < grantResults[i]; i++) {
                if(grantResults[i]!=PackageManager.PERMISSION_GRANTED)
                {
                    Log.i("permission",permissions[i]+"not");
                    Log.i("permission",grantResults[i]+"not");
                }else{
                    Log.i("permission",permissions[i]+"");
                    Log.i("permission",grantResults[i]+"");
                }

            }
        }
       }
}