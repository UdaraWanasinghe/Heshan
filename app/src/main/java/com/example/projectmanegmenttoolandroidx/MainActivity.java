package com.example.projectmanegmenttoolandroidx;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    public void createOne(View view) {
        TextView textView = (TextView) view;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            textView.setTextColor(getColor(R.color.orange_dark));
        }

        goTo(SignUpActivity.class);

    }

    private void goTo(Class classToGo) {
        Intent intent = new Intent(MainActivity.this, classToGo);
        startActivity(intent);
    }

    public void login(View view) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Logging you in...");
        dialog.show();
        try {
            final DataRequest request = new DataRequest(
                    this,
                    Urls.LOGIN_URL,
                    Request.Method.POST,
                    null,
                    new JSONObject()
                            .put("email", editTextEmail.getText().toString())
                            .put("password", editTextPassword.getText().toString())
            );
            request.sendObjectRequest(
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            dialog.dismiss();
                            if (request.statusCode == 200) {
                                goTo(Home.class);
                                getSharedPreferences(Constants.SHARED_PREFERENCES, MODE_PRIVATE).edit()
                                        .putString(Constants.P_EMAIL, editTextEmail.getText().toString())
                                        .apply();
                            } else {
                                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dialog.dismiss();
                            error.printStackTrace();
                            Toast.makeText(MainActivity.this, new String(error.networkResponse.data), Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        } catch (JSONException e) {
            dialog.dismiss();
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


}


