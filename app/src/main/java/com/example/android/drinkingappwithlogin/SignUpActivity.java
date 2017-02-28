package com.example.android.drinkingappwithlogin;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/**
 * Created by jpower707 on 20/02/2017.
 */


public class SignUpActivity extends AppCompatActivity {

    EditText name,surname,email,password;
    static Sqlite sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        //Linking elements in xml to Java

        name=(EditText)findViewById(R.id.Name);
        surname=(EditText)findViewById(R.id.Surname);
        email=(EditText)findViewById(R.id.Email);
        password=(EditText)findViewById(R.id.Password);

        //Start SQLite
        sql = new Sqlite(SignUpActivity.this);

    }

    public void AddData(View view)
    {
        boolean isInserted = sql.AddData(
                name.getText().toString(),
                surname.getText().toString(),
                email.getText().toString(),
                password.getText().toString());
        if(isInserted == true)
            Toast.makeText(SignUpActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(SignUpActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
    }

    public void view(View view)
    {
        Cursor res = sql.getAllData();

        if(res.getCount() == 0)
        {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while (res.moveToNext())
        {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("Name :"+ res.getString(1)+"\n");
            buffer.append("Surname :"+ res.getString(2)+"\n");
            buffer.append("Email :"+ res.getString(3)+"\n");
            buffer.append("Password :"+ res.getString(4)+"\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString());
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    public static boolean CheckLogin(String[] user) {

        Cursor res = sql.getAllData();

        while (res.moveToNext())
        {
            if (user[0].equals(res.getString(3)))
            {
                if (user[1].equals(res.getString(4)))
                {
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
}
