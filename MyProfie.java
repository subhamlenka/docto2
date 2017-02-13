package com.docto.subham;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MyProfie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofie);
    }
    public void updatepro(View v)
    {
        Intent i = new Intent(getApplicationContext(),MenuitemActivity.class);
        Toast.makeText(getApplicationContext(),"Succesfully Updated",Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}
