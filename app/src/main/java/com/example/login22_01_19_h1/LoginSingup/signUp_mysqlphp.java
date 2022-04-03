package com.example.login22_01_19_h1.LoginSingup;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.login22_01_19_h1.Constants;
import com.example.login22_01_19_h1.DBHelper;
import com.example.login22_01_19_h1.R;
import com.example.login22_01_19_h1.RequestHandlerSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class signUp_mysqlphp extends AppCompatActivity implements View.OnClickListener {
    EditText EditTextName, EditTextUserName , EditTextNumber, EditTextEmail, EditTextPass;

    TextView loginAcc;
    Button BttonSignUpAcc;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditTextName = findViewById(R.id.textName);
        EditTextUserName = findViewById(R.id.username);
        EditTextNumber = findViewById(R.id.textNumber);
        EditTextEmail = findViewById(R.id.textEmail);
        EditTextPass = findViewById(R.id.textPass);
        progressDialog = new ProgressDialog(this);

        BttonSignUpAcc = findViewById(R.id.btnSignUpAcc);
        loginAcc = findViewById(R.id.loginAcc);

        BttonSignUpAcc.setOnClickListener(this);
        loginAcc.setOnClickListener(this);

    }

    private void registerUSer() {
        String name = EditTextName.getText().toString().trim();
        String username = EditTextUserName.getText().toString().trim();
        String email = EditTextEmail.getText().toString().trim();
        String phone = EditTextNumber.getText().toString().trim();
        String pass = EditTextPass.getText().toString().trim();
        progressDialog.setMessage("REGISTER IS IN PROCCISING ");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // show the output of the response
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message") + "+JSON", Toast.LENGTH_LONG).show();
                    System.out.println("Here sam : "+jsonObject.getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("haha error");
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("username", username);
                params.put("email", email);
                params.put("phone", email);
                params.put("password", pass);
                return params;
            }
        };

        RequestHandlerSingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public void onClick(View view) {
        if (view == BttonSignUpAcc) {
            registerUSer();
        } else if (view == loginAcc) {
            Intent intent = new Intent(signUp_mysqlphp.this, Login_MySql.class);
            startActivity(intent);
        }
    }
}

