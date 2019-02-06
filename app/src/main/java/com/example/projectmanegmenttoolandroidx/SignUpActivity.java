package com.example.projectmanegmenttoolandroidx;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {
    TextInputEditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextRepeatPassword;
    Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextFirstName = findViewById(R.id.editTexFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword);

        buttonSignUp = findViewById(R.id.buttonSignUp);

    }

    public void Login(View view) {
        goTo(MainActivity.class);
    }

    private void goTo(Class classToGo) {
        Intent intent = new Intent(SignUpActivity.this, classToGo);
        startActivity(intent);
    }


    public void login(View view) {

        if (!editTextRepeatPassword.getText().toString().equals(editTextRepeatPassword.getText().toString())) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Passwords doesn't match")
                    .setPositiveButton(
                            "Close",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }
                    ).show();
            return;
        }
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Signing you up...");
        dialog.show();
        try {
            final DataRequest request = new DataRequest(
                    this,
                    Urls.REGISTER_URL,
                    Request.Method.POST,
                    null,
                    new JSONObject()
                            .put("firstname", editTextFirstName.getText().toString())
                            .put("lastname", editTextLastName.getText().toString())
                            .put("email", editTextEmail.getText().toString())
                            .put("password", editTextPassword.getText().toString())
            );
            request.sendRequest(
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            dialog.dismiss();
                            if (request.statusCode == 200) {
                                new AlertDialog.Builder(SignUpActivity.this)
                                        .setTitle("Success")
                                        .setMessage("Successfully signed up")
                                        .setPositiveButton(
                                                "Login",
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.dismiss();
                                                        goTo(MainActivity.class);
                                                    }
                                                }
                                        ).show();
                            } else {
                                Toast.makeText(SignUpActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dialog.dismiss();
                            error.printStackTrace();
                            Toast.makeText(SignUpActivity.this, new String(error.networkResponse.data), Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        } catch (JSONException e) {
            dialog.dismiss();
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
