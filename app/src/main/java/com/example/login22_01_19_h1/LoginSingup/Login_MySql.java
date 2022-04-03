package com.example.login22_01_19_h1.LoginSingup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.login22_01_19_h1.Constants;
import com.example.login22_01_19_h1.DBHelper;
import com.example.login22_01_19_h1.MainActivity;
import com.example.login22_01_19_h1.Menu.Navigation_Main;
import com.example.login22_01_19_h1.R;
import com.example.login22_01_19_h1.RequestHandlerSingleton;
import com.example.login22_01_19_h1.SharedPrefManger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login_MySql extends AppCompatActivity implements View.OnClickListener {
    EditText email, password;
    Button btnSubmit;
    TextView createAcc;
    private ProgressDialog progressDialog;

    static String UserID ;
    String emailCheck ;
    String passCheck ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Boolean e = false, p = false;
        setContentView(R.layout.activity_login);

        if(SharedPrefManger.getInstance(this).isLoggedIn()){
            finish();
            /// ------------------*************------------------------
            startActivity(new Intent(this , Navigation_Main.class));
            return;
        }


        email = findViewById(R.id.text_email);
        password = findViewById(R.id.text_pass);
        btnSubmit = findViewById(R.id.btnSubmit_login);
        progressDialog = new ProgressDialog(this);
        btnSubmit.setOnClickListener(this);
        createAcc = findViewById(R.id.createAcc);
        createAcc.setOnClickListener(this);

    }

    private void LoginUSer() {

        emailCheck = email.getText().toString();
        passCheck = password.getText().toString();

        progressDialog.setMessage("REGISTER IS IN PROCCISING ");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("message").equalsIgnoreCase("Login Success")) {

                        Log.println(Log.ASSERT, "LoginPage",    jsonObject.getString("email"));

                        Log.println(Log.ASSERT, "NAV", SharedPrefManger.getInstance(MainActivity.getAppContext()).getUserId() + ":");

                        SharedPrefManger.getInstance(MainActivity.getAppContext()).userLogin(  jsonObject.getInt("id"), jsonObject.getString("username"),
                                jsonObject.getString("email"));



                        Toast.makeText(MainActivity.getAppContext(), "You are Logeed in successfully", Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(Login_MySql.this, Navigation_Main.class);
                        startActivity(intent1);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.getAppContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(MainActivity.getAppContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            }

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("password", passCheck);
                params.put("email", emailCheck);
                return params;
            }
        };


        RequestHandlerSingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

    public void onClick(View view) {
        if (view == btnSubmit) {
            LoginUSer();
        }else if (view == createAcc) {


            Intent intent = new Intent(Login_MySql.this, signUp_mysqlphp.class);
            startActivity(intent);
        }


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public static String getuserID(){
        return UserID;
    }

}