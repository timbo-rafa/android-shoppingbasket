package com.example.rafael.rafaelmatos_MAPD711_OnlinePurchase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button signupBtn = findViewById(R.id.signupBtn);
        final Button loginBtn = findViewById(R.id.loginBtn);

        final EditText usernameET = findViewById(R.id.usernameLoginScreen);
        final EditText passwordET = findViewById(R.id.passwordLoginScreen);

        User.setDb(getApplicationContext());
        Order.setDb(getBaseContext());

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String usernameInput = usernameET.getText().toString();
            String passwordInput = passwordET.getText().toString();

            if (login(usernameInput, passwordInput, false)) {
                // load initial product settings
                Product.setDb(getBaseContext());
                Intent listOrders = new Intent(getBaseContext(), ListOrdersActivity.class);
                startActivity(listOrders);
            }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(getBaseContext(), SignupActivity.class);
                startActivity(signUp);
            }
        });
    }

    private boolean login(String usernameInput, String passwordInput, boolean isClerk) {
        User savedUser;
        try {
            if (isClerk) savedUser = User.queryClerkByUsername(usernameInput);
            else         savedUser = User.queryCustomerByUsername(usernameInput);
            if (usernameInput.equals(savedUser.username)) {
                if (passwordInput.equals(savedUser.password)) {
                    savePreferences(savedUser.username, isClerk);
                    return true;
                } else {
                    Toast.makeText(LoginActivity.this, "invalid password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "invalid username", Toast.LENGTH_SHORT).show();
            }
            return false;

        } catch ( NullPointerException e) {
            if (isClerk) {
                Toast.makeText(LoginActivity.this, "user does not exist", Toast.LENGTH_SHORT).show();
            } else return login(usernameInput, passwordInput, true);
            return false;
        }
    }

    private void savePreferences(String username, Boolean isClerk) {

        SharedPreferences preference = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("username", username);
        editor.putBoolean("isClerk", isClerk);
        editor.commit();
    }

}
