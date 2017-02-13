package com.docto.subham;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.widgets.SnackBar;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {
    MaterialEditText dUsername;
    MaterialEditText dPassword;
    String StrUserName;
    String StrPassword;
    ButtonRectangle bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this); //setting the firebase database
         dUsername=(MaterialEditText)findViewById(R.id.editUsername);
         dPassword=(MaterialEditText)findViewById(R.id.editPassword);
        TextView text=(TextView)findViewById(R.id.textDocto);
        bLogin=(ButtonRectangle)findViewById(R.id.buttonLogin);

        Typeface typeFace =  Typeface.createFromAsset(getAssets(),"fonts/century_gothic.ttf");
        text.setTypeface(typeFace);


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrUserName = dUsername.getText().toString();
                StrPassword = dPassword.getText().toString();


                final ProgressDialog progress = new ProgressDialog(LoginActivity.this);
                progress.setTitle("Signing in");
                progress.setMessage("Please wait,It will take few minutes...");
                progress.show();

                Runnable progressRunnable = new Runnable() {

                    @Override
                    public void run() {
                        progress.cancel();
                        //checking textfield is empty or not
                if (TextUtils.isEmpty(StrUserName) || TextUtils.isEmpty(StrPassword)) {
                    dUsername.setError("E-mail can't be empty");
                    dPassword.setError("Password can't be empty");
                    return;
                } else {
                    Firebase ref = new Firebase("https://doctoteam.firebaseio.com");
                    ref.authWithPassword(StrUserName, StrPassword, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                            Intent k=new Intent(getApplicationContext(),MenuitemActivity.class);
                            startActivity(k);
                            Toast.makeText(getApplicationContext(), "login succesfully", Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            // there was an error
                            Toast.makeText(getApplicationContext(), "Unauthenicated User", Toast.LENGTH_LONG).show();
                        }
                    });
                }
//                        Intent k=new Intent(getApplicationContext(),MenuitemActivity.class);
//                        startActivity(k);
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 7000);


//                //checking textfield is empty or not
//                if (TextUtils.isEmpty(StrUserName) || TextUtils.isEmpty(StrPassword)) {
//                    dUsername.setError("E-mail can't be empty");
//                    dPassword.setError("Password can't be empty");
//                    return;
//                } else {
//                    Firebase ref = new Firebase("https://doctoteam.firebaseio.com");
//                    ref.authWithPassword(StrUserName, StrPassword, new Firebase.AuthResultHandler() {
//                        @Override
//                        public void onAuthenticated(AuthData authData) {
//                            System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
//
//                            Toast.makeText(getApplicationContext(), "login succesfully", Toast.LENGTH_LONG).show();
//                        }
//
//                        @Override
//                        public void onAuthenticationError(FirebaseError firebaseError) {
//                            // there was an error
//                            Toast.makeText(getApplicationContext(), "Unauthenicated User", Toast.LENGTH_LONG).show();
//                        }
//                    });
//                }
            }



        });




    }
    public void resett(View v1)
    {
        Intent j= new Intent(getApplicationContext(),ResetPassword.class);
        startActivity(j);
    }
  public  void reg(View v)
    {
        Intent i= new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
