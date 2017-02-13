package com.docto.subham;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ButtonRectangle;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    String strGender;
    MaterialEditText dRegEmail;
    MaterialEditText dRegPassword;
    MaterialEditText dRegName;
    MaterialEditText dRegAge;


    String StrRegEmail;
    String StrRegPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Firebase.setAndroidContext(this);


        dRegName=(MaterialEditText)findViewById(R.id.editRegName);
        dRegAge=(MaterialEditText)findViewById(R.id.editRegAge);
        dRegEmail=(MaterialEditText)findViewById(R.id.editRegEmail);
        dRegPassword=(MaterialEditText)findViewById(R.id.editRegPassword);

        ButtonRectangle button=(ButtonRectangle)findViewById(R.id.buttonflat);

        RadioGroup rg = (RadioGroup) findViewById(R.id.RadioGender);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioMale:
                        // do operations specific to this selection
                        strGender="Male";
                        break;

                    case R.id.radioFemale:
                        // do operations specific to this selection
                        strGender="Female";
                        break;

                    case R.id.radioOthers:
                        // do operations specific to this selection
                        strGender="Others";
                        break;

                }
            }
        });


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        StrRegEmail = dRegEmail.getText().toString();
                        StrRegPassword = dRegPassword.getText().toString();

                        if (TextUtils.isEmpty(StrRegEmail) || TextUtils.isEmpty(StrRegPassword)) {
                            dRegEmail.setError("E-mail can't be empty");
                            dRegPassword.setError("Password can't be empty");
                            return;
                        }
                        else {

                            Firebase ref = new Firebase("https://doctoteam.firebaseio.com");
                            ref.createUser(StrRegEmail, StrRegPassword, new Firebase.ValueResultHandler<Map<String, Object>>() {
                                @Override
                                public void onSuccess(Map<String, Object> result) {
                                    Toast.makeText(getApplicationContext(), "Sucessfully created,Thank you", Toast.LENGTH_LONG).show();
                                    System.out.println("Successfully created user account with uid: " + result.get("uid"));
                                }

                                @Override
                                public void onError(FirebaseError firebaseError) {
                                    // there was an error
                                }
                            });
                        }

                    }
                });


            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_register, menu);
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
