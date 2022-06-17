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

public class ResetActivity extends AppCompatActivity {
    TextView username;
    EditText pass,cpassw;
    Button confirm;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        username = findViewById(R.id.username2);
        pass = findViewById(R.id.resetpassword);
        cpassw = findViewById(R.id.conpassword);
        confirm = findViewById(R.id.confirm);
        DB = new DBHelper(this);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String password = pass.getText().toString();
                String repassword = cpassw.getText().toString();
                if(repassword.equals(password)){



                if(TextUtils.isEmpty(user)){
                    Toast.makeText(ResetActivity.this, "ALl fields are Required", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkupdate = DB.updatepassword(user, password);
                    if (checkupdate == true) {
                        Toast.makeText(ResetActivity.this, "Password Successfully Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Loginact.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ResetActivity.this, "Password not Updated", Toast.LENGTH_SHORT).show();
                    }
                }

                }
                else
                {
                    Toast.makeText(ResetActivity.this, "Password dont match", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}