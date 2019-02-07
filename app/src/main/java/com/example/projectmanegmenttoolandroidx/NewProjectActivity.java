package com.example.projectmanegmenttoolandroidx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class NewProjectActivity extends AppCompatActivity {
    TextInputEditText textInputEditTextProjectName, textInputEditTextProjectDescription;
    EditText editTextStartDate, editTextDueDate;
    TextView textViewCreate, textViewCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        textInputEditTextProjectName = findViewById(R.id.text_input_project_name);
        textInputEditTextProjectDescription = findViewById(R.id.text_input_priject_description);
        editTextStartDate = findViewById(R.id.editText_start_date);
        editTextDueDate = findViewById(R.id.editText_due_date);
        textViewCreate = findViewById(R.id.textViewCreate);
        textViewCancel = findViewById(R.id.textViewCancel);

        textViewCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestHandler.sendNewProjectRequest(
                        NewProjectActivity.this,
                        textInputEditTextProjectName.getText().toString(),
                        textInputEditTextProjectDescription.getText().toString(),
                        getSharedPreferences(Constants.SHARED_PREFERENCES, MODE_PRIVATE).getString(Constants.P_EMAIL, ""),
                        editTextStartDate.getText().toString(),
                        editTextDueDate.getText().toString(),
                        new SuccessListener() {
                            @Override
                            public void onSuccess() {
                                RequestHandler.showSuccessDialog(NewProjectActivity.this, "Successfully created the project");
                            }
                        }
                );
            }
        });
        textViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
