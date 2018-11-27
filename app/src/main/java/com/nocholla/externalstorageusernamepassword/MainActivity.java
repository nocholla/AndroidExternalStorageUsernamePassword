package com.nocholla.externalstorageusernamepassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Widgets
    private EditText inputUsername;
    private EditText inputPassword;
    private Button btnLogin;

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
