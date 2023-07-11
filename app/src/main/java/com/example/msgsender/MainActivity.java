/**
 * This is a message-sending application designed to provide a quick and reliable communication solution during critical situations
 *
 *
 *  You can send the message to any number by entering it or by changing at line 68 also you can change the default message
 *
 *
 * Author: Aniket Biswal
 * Date: March 17 ,2022
 */
package com.example.msgsender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnsent;
    EditText pno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        btnsent=findViewById(R.id.btnsent);
        pno=findViewById(R.id.pno);
        btnsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED){
                    sendmsg();
                }else{
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},100);
                }
            }


        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100 && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            sendmsg();
        }
        else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();

        }
    }

    private void sendmsg() {
        String Phone =pno.getText().toString();
        String message="I'M IN EMERGENCY KINDLY SEND HELP";
        if(!Phone.isEmpty() && !message.isEmpty()){
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(Phone,null,message,null,null);
            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Some problem", Toast.LENGTH_SHORT).show();
        }
    }
}