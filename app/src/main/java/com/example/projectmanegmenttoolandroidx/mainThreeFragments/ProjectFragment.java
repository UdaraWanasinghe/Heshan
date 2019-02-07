package com.example.projectmanegmenttoolandroidx.mainThreeFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.projectmanegmenttoolandroidx.Constants;
import com.example.projectmanegmenttoolandroidx.DataRequest;
import com.example.projectmanegmenttoolandroidx.R;
import com.example.projectmanegmenttoolandroidx.RequestHandler;
import com.example.projectmanegmenttoolandroidx.RetryListener;
import com.example.projectmanegmenttoolandroidx.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProjectFragment extends Fragment {
    private ListView listView;

    public ProjectFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.projects_fragment, container, false);
        listView = view.findViewById(R.id.listView);
        sendRequest();
        return view;
    }

    private void sendRequest() {
        final DataRequest request = new DataRequest(
                getContext(),
                Urls.GET_BY_EMAIL_URL + "?email=" +
                        getContext().getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
                                .getString(Constants.P_EMAIL, ""),
                Request.Method.GET,
                null,
                null);
        request.sendArrayRequest(
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (request.statusCode == 200 || request.statusCode == 304) {
                            updateList(response);

                        } else {
                            RequestHandler.showErrorDialog(
                                    getContext(),
                                    response.toString(),
                                    new RetryListener() {
                                        @Override
                                        public void onRetry() {
                                            sendRequest();
                                        }
                                    }
                            );
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        RequestHandler.showErrorDialog(
                                getContext(),
                                error.toString(),
                                new RetryListener() {
                                    @Override
                                    public void onRetry() {
                                        sendRequest();
                                    }
                                }
                        );
                    }
                }
        );

    }

    private void updateList(final JSONArray response) {
        listView.setAdapter(
                new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return response.length();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return i;
                    }

                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        if (view == null) {
                            view = getLayoutInflater().inflate(R.layout.project_list_item, viewGroup, false);
                            TextView name = view.findViewById(R.id.text_view_project_name);
                            TextView description = view.findViewById(R.id.text_view_project_description);
                            TextView dueDate = view.findViewById(R.id.text_view_due_date);

                            try {
                                JSONObject obj = response.getJSONObject(i).getJSONObject("project");
                                name.setText(obj.getString("name"));
                                description.setText(obj.getString("description"));
                                dueDate.setText(obj.getString("duedate"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        return view;
                    }
                }
        );
    }
}