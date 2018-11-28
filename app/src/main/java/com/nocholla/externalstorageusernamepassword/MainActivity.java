package com.nocholla.externalstorageusernamepassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    // Widgets
    private EditText inputUsername;
    private EditText inputPassword;
    private Button btnLogin;

    // External File
    private String filename = "credentials.txt";
    private String filepath = "MyFileStorageNocholla";
    File myExternalFile;
    String myData = "";

    /**
     * @method onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Widgets
        inputUsername = findViewById(R.id.username);
        inputPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);

        // Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = inputUsername.getText().toString();
                Log.d("DEBUG USERNAME", username);
                // 2018-11-28 10:02:21.793 30321-30321/com.nocholla.externalstorageusernamepassword D/DEBUG USERNAME: nocholla

                final String password = inputPassword.getText().toString();
                Log.d("DEBUG PASSWORD", password);
                // 2018-11-28 10:02:21.794 30321-30321/com.nocholla.externalstorageusernamepassword D/DEBUG PASSWORD: nocholla123

                // Username Empty Validation
                if (TextUtils.isEmpty(username)) {
                    inputUsername.setError(getString(R.string.error_enter_username));

                    return;
                }

                // Password Empty Validation
                if (TextUtils.isEmpty(password)) {
                    inputPassword.setError(getString(R.string.error_enter_password));

                    return;
                }

                // Save Username To External Storage
                SaveUsernameToExternalStorage();

                // Save Password To External Storage
                //SavePasswordToExternalStorage();

            }
        });

    }

    private void SaveUsernameToExternalStorage() {
        String username = inputUsername.getText().toString();
        Log.d("DEBUG SAVE EXSTOR NAME", username);
        // 2018-11-28 10:02:21.794 30321-30321/com.nocholla.externalstorageusernamepassword D/DEBUG SAVE EXSTOR NAME: nocholla

        try {
            FileOutputStream fos = new FileOutputStream(myExternalFile);
            fos.write(username.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        inputUsername.setText("");
    }

    private void SavePasswordToExternalStorage() {
        final String password = inputPassword.getText().toString();
        Log.d("DEBUG SAVE EXSTOR PASS", password);
        // 2018-11-28 10:02:21.794 30321-30321/com.nocholla.externalstorageusernamepassword D/DEBUG SAVE EXSTOR PASS: nocholla123

        try {
            FileOutputStream fos = new FileOutputStream(myExternalFile);
            fos.write(password.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        inputPassword.setText("");
    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Check if external storage is available and not read only
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            // disable the login button
            btnLogin.setEnabled(false);
        } else {
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
            Log.d("DEBUG EXTERNAL FILE"," External File Path and Name : " + myExternalFile);
            // 2018-11-28 09:56:00.752 30321-30321/com.nocholla.externalstorageusernamepassword D/DEBUG EXTERNAL FILE:  External File Path and Name : /storage/emulated/0/Android/data/com.nocholla.externalstorageusernamepassword/files/MyFileStorageNocholla/credentials.txt

            // Get External Storage
            if(myExternalFile.exists()) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(myExternalFile);
                    DataInputStream in = new DataInputStream(fis);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    while ((strLine = br.readLine()) != null) {
                        myData = myData + strLine;
                        Log.d("DEBUG MY DATA", myData);
                        // 2018-11-28 09:56:00.755 30321-30321/com.nocholla.externalstorageusernamepassword D/DEBUG MY DATA: nochollanocholla123
                    }
                    in.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Set Username
                inputUsername.setText(myData);

            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

}
