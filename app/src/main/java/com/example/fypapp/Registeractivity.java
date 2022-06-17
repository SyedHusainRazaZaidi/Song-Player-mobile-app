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

public class Registeractivity extends AppCompatActivity {

    EditText username,password,email,cpassword;
    Button register,login;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);
        TextView btn = findViewById(R.id.textView2);
        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        email = findViewById(R.id.email);
        cpassword = findViewById(R.id.cpassword);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        DB =new DBHelper(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registeractivity.this,Loginact .class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String em = email.getText().toString();
                String cpass = cpassword.getText().toString();

                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)|| TextUtils.isEmpty(em)||TextUtils.isEmpty(cpass))
                    Toast.makeText(Registeractivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                else{

                    if(pass.equals(cpass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert =DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(Registeractivity.this, "Regstered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Registeractivity.this,Loginact .class));

                            }else{
                                Toast.makeText(Registeractivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Registeractivity.this, "User Already Exist", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Registeractivity.this, "Passwords are not matched", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}