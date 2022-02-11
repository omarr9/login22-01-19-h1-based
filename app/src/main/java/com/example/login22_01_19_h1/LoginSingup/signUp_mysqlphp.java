package com.example.login22_01_19_h1.LoginSingup;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class signUp_mysqlphp extends AppCompatActivity implements View.OnClickListener {
    EditText EditTextName, EditTextNumber, EditTextEmail, EditTextPass;
    TextView login;
    DBHelper dbHelper;
    Button BttonSignUpAcc;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditTextName = findViewById(R.id.textName);
        EditTextNumber = findViewById(R.id.textNumber);
        EditTextEmail = findViewById(R.id.textEmail);
        EditTextPass = findViewById(R.id.textPass);
        progressDialog = new ProgressDialog(this);

        BttonSignUpAcc = findViewById(R.id.btnSignUpAcc);
        BttonSignUpAcc.setOnClickListener(this);
//        String name1 = name.getText().toString();
////                    String number1 = number.getText().toString();
//        String email1 = email.getText().toString();
//        String pass1 = pass.getText().toString();

//            dbHelper = new DBHelper(this);
//            signUpAcc.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {

//                    boolean b =dbHelper.insetUserData(name1,email1,pass1);
//                    if (b){
//                        Toast.makeText(signUp_mysqlphp.this,"Data inserted",Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(signUp_mysqlphp.this, Login.class);
//                        startActivity(i);
//                    }else {
//                        Toast.makeText(signUp_mysqlphp.this,"Failed To insert Data",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//            login=findViewById(R.id.loginAcc);
//            login.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(signUp_mysqlphp.this,Login.class);
//                    startActivity(i);
//                }
//            });
    }

    private void registerUSer() {
        String email = EditTextEmail.getText().toString().trim();
        String username = EditTextName.getText().toString().trim();
        String pass = EditTextPass.getText().toString().trim();
        progressDialog.setMessage("REGISTER IS IN PROCCISING ");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message") + "+JSON", Toast.LENGTH_LONG).show();
//                        if (response.trim().equalsIgnoreCase("You are registered successfully")){

//                            Toast.makeText(getApplicationContext(), "You are registered successfully", Toast.LENGTH_LONG).show();

//                        }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            }

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", pass);
                params.put("email", email);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onClick(View view) {
        if (view == BttonSignUpAcc)
            registerUSer();
    }
}

