package com.nocholla.externalstorageusernamepassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    // Widgets
    private EditText inputUsername;
    private EditText inputPassword;
    private Button btnLogin;
    FileOutputStream fstream;
    Intent intent;

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

                final String password = inputPassword.getText().toString();
                Log.d("DEBUG PASSWORD", password);

                intent = new Intent(MainActivity.this, UserDetailsActivity.class);
                startActivity(intent);

                // Save to External File
                try {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},23);
                    File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    File myFile = new File(folder,"credentials");
                    fstream = new FileOutputStream(myFile);
                    fstream.write(username.getBytes());
                    fstream.write(password.getBytes());
                    fstream.close();

                    Toast.makeText(getApplicationContext(), "Details Saved in " + myFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    //intent = new Intent(MainActivity.this, UserDetailsActivity.class);
                    //startActivity(intent);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

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
            }
        });

    }

}
