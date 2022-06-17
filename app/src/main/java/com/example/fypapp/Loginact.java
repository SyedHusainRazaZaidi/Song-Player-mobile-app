package com.example.fypapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Loginact extends AppCompatActivity {
    EditText username,password;
    Button login;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginact);
        TextView btn = findViewById(R.id.textsignup);
        TextView forgot = findViewById(R.id.forgot);
        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        login = findViewById(R.id.login);
        DB = new DBHelper(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Loginact.this,Registeractivity.class));
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Loginact.this,PasswordActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user =username.getText().toString();
                String pass =password.getText().toString();
                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass))
                    Toast.makeText(Loginact.this, "All fields are required", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(Loginact.this, "Login Succesfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Loginact.this,MainActivity.class));
                    }else {
                        Toast.makeText(Loginact.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}