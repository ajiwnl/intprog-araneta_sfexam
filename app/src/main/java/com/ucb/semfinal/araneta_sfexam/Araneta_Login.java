package com.ucb.semfinal.araneta_sfexam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Araneta_Login extends AppCompatActivity {

    EditText userInput, passInput;
    Button regIn;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "user";
    private  static final String KEY_PASS = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_araneta_login);

        userInput = findViewById(R.id.user);
        passInput = findViewById(R.id.pass);
        regIn = findViewById(R.id.btn1);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String user = sharedPreferences.getString(KEY_USER, null);
        String pass = sharedPreferences.getString(KEY_PASS, null);

        if (user!= null && pass != null){
            Intent logs = new Intent(Araneta_Login.this, Araneta_ClassSched.class);
            startActivity(logs);


        }

        regIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passInput.getText().toString();
                if (isPasswordValid(password)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_USER, userInput.getText().toString());
                    editor.putString(KEY_PASS, password);
                    editor.apply();

                    Intent navtoClass = new Intent(Araneta_Login.this, Araneta_ClassSched.class);
                    startActivity(navtoClass);

                    Toast.makeText(Araneta_Login.this, "Login Successful", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Araneta_Login.this, "Invalid password. Password should be 8 characters long with at least one special character.", Toast.LENGTH_LONG).show();
                }
            }


            private boolean isPasswordValid(String password) {
                if (password.length() < 8) {
                    return false;
                }
                // Check password
                String specialCharacters = "!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
                for (char c : specialCharacters.toCharArray()) {
                    if (password.contains(String.valueOf(c))) {
                        return true;
                    }
                }
                return false;
            }
        });

    }
}