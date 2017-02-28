package com.example.android.drinkingappwithlogin;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.transition.Fade.IN;
import static com.example.android.drinkingappwithlogin.R.id.Name;
import static com.example.android.drinkingappwithlogin.R.id.Password;
import static com.example.android.drinkingappwithlogin.SignUpActivity.CheckLogin;


public class MainActivity extends AppCompatActivity {

    EditText mUsername, mPassword, mName;
    Sqlite sql;
    final String[] User = new String [3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button createAccount = (Button) findViewById(R.id.createAccount);

        //Set a clickListener on sign up View
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new intent to open the {@link SignUpActivity)
                Intent SignUpIntent = new Intent(MainActivity.this, SignUpActivity.class);

                //Start the new activity
                startActivity(SignUpIntent);
            }
        });

        mUsername = (EditText) findViewById(R.id.Email);
        mPassword = (EditText) findViewById(Password);
        mName = (EditText) findViewById(Name);

        //Start SQLite
        sql = new Sqlite(MainActivity.this);
    }


    public void LoginClick(View view)
    {

        User[0] = mUsername.getText().toString();
        User[1] = mPassword.getText().toString();


        if (InputValidation.isValidEmail(User[0])==true)
        {
            if (InputValidation.isValidPassword(User[1]) == true)
            {
                if (CheckLogin(User) == true && User[0] != " " && User[1] != " ") {
                    login(User);
                } else {
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

                    dlgAlert.setMessage("Wrong password or username");
                    dlgAlert.setTitle("Incorrect Login Details");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();

                }

            }
            else{
                mPassword.setError("Invalid Password");
            }
        }
        else{
            mUsername.setError("Invalid Email");
        }
    }

    private void showError() {
        mPassword.setError("Password and username didn't match");
    }

    public boolean CheckLogin(String[] user) {

        Cursor res = sql.getAllData();
        res.moveToFirst();
        while (res.moveToNext())
        {
            if (user[0].equals(res.getString(3)))
            {
                if (user[1].equals(res.getString(4)))
                {
                    User[2] = res.getString(1);
                    return true;

                }
                else
                {

                    return false;
                }
            }
        }
        return false;
    }

    public void login(String[] user) {
        //Create a new intent to open the {@link SignUpActivity)
        Intent HomeIntent = new Intent (MainActivity.this, HomeActivity.class);
        Bundle extras = new Bundle();
        extras.putString("my_username",user[0]);
        extras.putString("my_name",user[2]);

        HomeIntent.putExtras(extras);


        //Start the new activity
        startActivity(HomeIntent);

    }

}


