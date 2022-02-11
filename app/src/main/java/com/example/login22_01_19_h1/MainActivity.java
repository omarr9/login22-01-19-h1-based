package com.example.login22_01_19_h1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.login22_01_19_h1.LoginSingup.Login;
import com.example.login22_01_19_h1.LoginSingup.Login_MySql;
import com.example.login22_01_19_h1.LoginSingup.SignUp;

//essam
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import android.content.Intent;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import com.example.login22_01_19_h1.LoginSingup.signUp_mysqlphp;
import com.example.login22_01_19_h1.sliderhome.adapterCard;
import com.example.login22_01_19_h1.sliderhome.CardHelper;
import com.example.login22_01_19_h1.sliderhome.car;

import java.util.ArrayList;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity  {
    Button login, Reg;
    Toolbar toolbar;
    DBHelper dbHelper;

    @Override
    public void onBackPressed() {

        MainActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Hello world");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        login = (Button) findViewById(R.id.btnLogin);
        toolbar = (Toolbar) findViewById(R.id.tool_main);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login_MySql.class);
                startActivity(intent);
            }
        });
        Reg = findViewById(R.id.btnSignUp);
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, signUp_mysqlphp.class);
                startActivity(intent);
            }
        });

    }

}