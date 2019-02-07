package com.example.projectmanegmenttoolandroidx;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AlertDialog;

public class RequestHandler {
    static void sendNewProjectRequest(
            final Context context,
            final String name,
            final String description,
            final String manager,
            final String startDate,
            final String dueDate,
            final SuccessListener successListener
    ) {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("Creating project...");
        dialog.show();
        try {
            final DataRequest request = new DataRequest(
                    context,
                    Urls.NEW_PROJECT_URL,
                    Request.Method.POST,
                    null,
                    new JSONObject()
                            .put("name", name)
                            .put("description", description)
                            .put("manager", manager)
                            .put("startdate", startDate)
                            .put("duedate", dueDate)
            );
            request.sendObjectRequest(
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            dialog.dismiss();
                            if (request.statusCode == 200) {
                                if (successListener != null) {
                                    successListener.onSuccess();
                                } else {
                                    Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                showErrorDialog(context, response.toString(), new RetryListener() {
                                    @Override
                                    public void onRetry() {
                                        sendNewProjectRequest(
                                                context,
                                                name,
                                                description,
                                                manager,
                                                startDate,
                                                dueDate,
                                                successListener
                                        );
                                    }
                                });
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dialog.dismiss();
                            error.printStackTrace();
                            Toast.makeText(context, new String(error.networkResponse.data), Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        } catch (JSONException e) {
            dialog.dismiss();
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static void showErrorDialog(Context context, String message, final RetryListener retryListener) {
        new AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton(
                        retryListener == null ? "Close" : "Retry",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                if (retryListener != null) {
                                    retryListener.onRetry();
                                }
                            }
                        }
                ).show();
    }

    static void showSuccessDialog(Context context, String message) {
        new AlertDialog.Builder(context)
                .setTitle("Success")
                .setMessage(message)
                .setPositiveButton(
                        "Close",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }
                ).show();
    }
}
