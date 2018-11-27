package com.nocholla.externalstorageusernamepassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import android.os.Environment;
import java.io.FileInputStream;

public class UserDetailsActivity extends AppCompatActivity {
    // Widgets
    private EditText inputUsername;
    private EditText inputPassword;
    private TextView tviewUsername;
    private Button btnBack;
    FileInputStream fstream;
    Intent intent;

    /**
     * @method onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        // Widgets
        inputUsername = findViewById(R.id.username);
        inputPassword = findViewById(R.id.password);
        tviewUsername = findViewById(R.id.tview_username);
        btnBack = findViewById(R.id.btn_login);

        // Get External File
        try {
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File myFile = new File(folder,"credentials");
            fstream = new FileInputStream(myFile);
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1) {
                sbuffer.append((char)i);
            }

            fstream.close();

            String details[] = sbuffer.toString().split("\n");

            //result.setText("Name: "+ details[0] + " \nPassword: " + details[1]);

            tviewUsername.setText(details[0]);
            inputUsername.setText(details[0]);
            inputPassword.setText(details[1]);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Login
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}

