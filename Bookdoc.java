package com.docto.subham;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class Bookdoc extends AppCompatActivity {
    EditText e1,e2;
    public static final String[] DISEASES = new String[] {
            "Malaria", "Typhoid","Fever","Filarial", " Hepatitis", "Hepatitis A", "Hepatitis B","Hepatitis C","Hepatitis D","Hepatitis E","Jaundice","Diarrhoeal","Amoebiasis","Cholera","Brucellosis"," Influenza","Filariasis","flu"
    };
    public static final String[] DR = new String[] {
            "Dr. R.K Panda", "Dr. Amit Biswal","Dr. Ritesh Agrawal","Dr. Sunil Rout"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdoc);

        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.auto_dis);
        AutoCompleteTextView textView1 = (AutoCompleteTextView) findViewById(R.id.editdr);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, DISEASES);
        textView.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, DR);
        textView1.setAdapter(adapter1);
        e1=(EditText)findViewById(R.id.editDatePicker);
        e2=(EditText)findViewById(R.id.editTimePicker);

        e1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(Bookdoc.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        e1.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        e2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Bookdoc.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        e2.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

    }
public void bookk(View v)
{
    final ProgressDialog progress = new ProgressDialog(this);
    progress.setTitle("Booking");
    progress.setMessage("Please wait,It will take few minutes...");
    progress.show();

    Runnable progressRunnable = new Runnable() {

        @Override
        public void run() {
            progress.cancel();
            Intent k= new Intent(getApplicationContext(),ThankActivity.class);
            startActivity(k);
        }
    };

    Handler pdCanceller = new Handler();
    pdCanceller.postDelayed(progressRunnable, 12000);

}
    public void detailss(View v)
    {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Please wait while we loading doctor info...");
        progress.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progress.cancel();
                Intent k= new Intent(getApplicationContext(),DoctoDetails.class);
                startActivity(k);

            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 12000);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bookdoc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
