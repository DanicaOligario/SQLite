package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etId;
    EditText etName;
    EditText etAddress;
    Button btnInsert;
    Button btnDelete;
    Button btnUpdate;
    Button btnView;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnView = findViewById(R.id.btnView);
        db = new DBHelper(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String InputId = etId.getText().toString();
                String InputName = etName.getText().toString();
                String InputAddress = etAddress.getText().toString();

                if (InputId.matches("") && InputName.matches("") && InputAddress.matches("")) {
                    Toast.makeText(MainActivity.this, "Fields cannot be left blank", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean InsertData = db.InsertStudent(InputId, InputName, InputAddress);
                    if (InsertData == true)
                        Toast.makeText(MainActivity.this, "Record Added", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Failed to Insert Data", Toast.LENGTH_SHORT).show();
                }
                etId.getText().clear();
                etName.getText().clear();
                etAddress.getText().clear();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String InputId = etId.getText().toString();
                String InputName = etName.getText().toString();
                String InputAddress = etAddress.getText().toString();

                if (InputId.matches("") && InputName.matches("") && InputAddress.matches("")) {
                    Toast.makeText(MainActivity.this, "Fields cannot be left blank", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean UpdateData = db.UpdateStudent(InputId, InputName, InputAddress);
                    if (UpdateData == true)
                        Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Entry failed to Update", Toast.LENGTH_SHORT).show();
                }
                etId.getText().clear();
                etName.getText().clear();
                etAddress.getText().clear();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String InputId = etId.getText().toString();

                if (InputId.matches("")) {
                    Toast.makeText(MainActivity.this, "Fields cannot be left blank", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean DeleteData = db.DeleteStudent(InputId);
                    if (DeleteData == true)
                        Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Entry failed to Delete", Toast.LENGTH_SHORT).show();
                }
                etId.getText().clear();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor view = db.getdata();
                if (view.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Records Found", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (view.moveToNext()) {
                    buffer.append("Student ID: " + view.getString(0) + "\n");
                    buffer.append("Name: " + view.getString(1) + "\n");
                    buffer.append("Address: " + view.getString(2) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student Information");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}