package com.example.fypapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {

    EditText username;
    Button reset;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        username= findViewById(R.id.username2);
        reset= findViewById(R.id.reset);
        DB =new DBHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                
                if(TextUtils.isEmpty(user)){
                    Toast.makeText(PasswordActivity.this, "Enter Username", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuser = DB.checkusername(user);
                    if(checkuser==true){
                        Toast.makeText(PasswordActivity.this, "Username entered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),ResetActivity.class);
                        intent.putExtra("username",user);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(PasswordActivity.this, "User does not Exist", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}