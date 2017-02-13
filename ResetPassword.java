package com.docto.subham;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.gc.materialdesign.views.ButtonRectangle;
import com.rengwuxian.materialedittext.MaterialEditText;

public class ResetPassword extends AppCompatActivity {
    String StrResetEmail;
    MaterialEditText dResetEmail;
    ButtonRectangle dResetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Firebase.setAndroidContext(this);
        dResetEmail=(MaterialEditText)findViewById(R.id.editResetEmail);
        dResetBtn=(ButtonRectangle)findViewById(R.id.editResetBtn);

        dResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StrResetEmail = dResetEmail.getText().toString();
                Firebase ref = new Firebase("https://doctoteam.firebaseio.com");
                    if (TextUtils.isEmpty(StrResetEmail)) {
                        dResetEmail.setError("Enter the registered E-mail");

                        return;
                    }
                else {
                        ref.resetPassword(StrResetEmail, new Firebase.ResultHandler() {
                            @Override
                            public void onSuccess() {
                                // password reset email sent
                                Toast.makeText(getApplicationContext(), "Check Your E-mail", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {
                                // error encountered
                                Toast.makeText(getApplicationContext(), "Sorry,This is an invalid address", Toast.LENGTH_LONG).show();
                            }
                        });
                    }

            }
        });









    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reset_password, menu);
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
