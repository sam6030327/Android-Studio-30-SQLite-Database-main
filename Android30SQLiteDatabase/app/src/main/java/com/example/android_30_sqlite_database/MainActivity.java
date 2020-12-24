package com.example.android_30_sqlite_database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataActivity myDB;
    EditText edName, edLevel, edSite, edID;
    Button add, all, upDate, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DataActivity(this);

        edName = (EditText)findViewById(R.id.name);
        edLevel = (EditText)findViewById(R.id.level);
        edSite = (EditText)findViewById(R.id.site);
        edID = (EditText)findViewById(R.id.id);

        add = (Button)findViewById(R.id.add);
        all = (Button)findViewById(R.id.all);
        upDate = (Button)findViewById(R.id.update);
        delete = (Button)findViewById(R.id.delete);
        addData();
        viewAll();
        Update();
        deleteData();
    }
    public void addData(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(edName.getText().toString(),
                        edLevel.getText().toString(),
                        edSite.getText().toString());
                if (isInserted == true){
                    Toast.makeText(MainActivity.this, "ADD DATA", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Not ADD DATA", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void viewAll(){
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.getData();
                if (res.getCount() == 0){
                    showMsg("Error", "Nothing");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append("NAME : " + res.getString(1) + "\n");
                    buffer.append("LEVEL : " + res.getString(2) + "\n");
                    buffer.append("SITE: " + res.getString(3) + "\n\n");
                }
                showMsg("SCP Data", buffer.toString());
            }
        });
    }
    public void showMsg(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void Update(){
        upDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDB.updateData(edID.getText().toString(),
                        edName.getText().toString(),
                        edLevel.getText().toString(),
                        edSite.getText().toString());
                if (isUpdate == true){
                    Toast.makeText(MainActivity.this, "DATA Update", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "DATA Not Update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void deleteData(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = myDB.deleteData(edID.getText().toString());
                if (deleteRows > 0){
                    Toast.makeText(MainActivity.this, "DATA Delete", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "DATA Not Delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}