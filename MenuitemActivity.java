package com.docto.subham;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MenuitemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuitem);
        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new MyAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                switch(position) {// DO something
                    case 0:
                        Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                        startActivity(i);
                        break;
                    case 1:
                        Intent i1 = new Intent(getApplicationContext(), DrCategActivity.class);
                        startActivity(i1);
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), "It is just Beta..Please wait for full version", Toast.LENGTH_LONG).show();
//                        Intent i2 = new Intent(getApplicationContext(), MapsActivity.class);
//                        startActivity(i2);
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), "It is just Beta..Please wait for full version", Toast.LENGTH_LONG).show();
//                        Intent i3 = new Intent(getApplicationContext(), MapsActivity.class);
//                        startActivity(i3);
                        break;
                    case 4:
                        Intent i4 = new Intent(getApplicationContext(), MyProfie.class);
                        startActivity(i4);
                        break;
                    case 5:
                        Toast.makeText(getApplicationContext(), "Just enjoy the beta", Toast.LENGTH_LONG).show();
//                        Intent i5 = new Intent(getApplicationContext(), MapsActivity.class);
//                        startActivity(i5);
                        break;


                }
            }
        });
    }
}
