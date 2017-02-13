package com.docto.subham;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ThankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank);
    }

    public void backk(View v) {
        Intent k = new Intent(getApplicationContext(), MenuitemActivity.class);
        startActivity(k);

    }
}
