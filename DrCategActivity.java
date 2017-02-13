package com.docto.subham;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DrCategActivity extends AppCompatActivity {


    ListView list;
    String[] web = {
            "Allergist",
            "Cardiologist",
            "Cytopathologist",
            "Dermatologist",
            "Ear, Nose and Throat Specialist (ENT)",
            "Endocrinologist",
            "Epidemiologist",
            "Family Practice Doctor",
            "Gynecologists/Obstetrician",
            "Hematologist",
            "Neurologist",
            "Psychiatrist",
            "Radiologist",
            "Rheumatologist",
            "Urologist"
    } ;

    Integer[] imageId = {
            R.drawable.fdocicon,
            R.drawable.fdocicon,
            R.drawable.fdocicon,
            R.drawable.fdocicon,
            R.drawable.fdocicon,
            R.drawable.fdocicon,
            R.drawable.fdocicon,
            R.drawable.fdocicon,
            R.drawable.fdocicon,
            R.drawable.fdocicon,
            R.drawable.fdocicon,
            R.drawable.fdocicon,
            R.drawable.fdocicon,
            R.drawable.fdocicon,
            R.drawable.fdocicon



    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drcateg);

        Customlist adapter = new Customlist(DrCategActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent j = new Intent(getApplication(), Bookdoc.class);
                startActivity(j);

            }




});
    }
}
