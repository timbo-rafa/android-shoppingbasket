package com.rafaeltimbo.shoppingbasket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    //

    private static final String record[] = new String[User.tbl_customer_fields.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final Button clerkLoginButton    = (Button)   findViewById(R.id.clerkLoginBtn);
        final Button customerLoginButton = (Button)   findViewById(R.id.customerLoginBtn);
        final EditText username          = (EditText) findViewById(R.id.usernameEditText);
        final EditText password          = (EditText) findViewById(R.id.passwordEditText);
        final EditText firstname         = (EditText) findViewById(R.id.firstnameEditText);
        final EditText lastname          = (EditText) findViewById(R.id.lastnameEditText);
        final EditText address           = (EditText) findViewById(R.id.addressEditText);
        final EditText city              = (EditText) findViewById(R.id.cityEditText);
        final EditText postalCode        = (EditText) findViewById(R.id.postalCodeEditText);
        //final TextView display           = (TextView) findViewById(R.id.display);

        final DatabaseManager db = new DatabaseManager(this);
        //db.deleteDatabase(getApplicationContext());
        //db.createDatabase(getApplicationContext());
        //db.dbInitialize( tableCreatorString);

        // Handle Save button

        customerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                record[1] = username.getText().toString();
                record[2] = password.getText().toString();
                record[3] = firstname.getText().toString();
                record[4] = lastname.getText().toString();
                record[5] = address.getText().toString();
                record[6] = city.getText().toString();
                record[7] = postalCode.getText().toString();

                User customer = new User(record);
                customer.addToDatabaseCustomer();

                Toast.makeText(getBaseContext(), "Sign up successful!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        clerkLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                record[1] = username.getText().toString();
                record[2] = password.getText().toString();
                record[3] = firstname.getText().toString();
                record[4] = lastname.getText().toString();
                record[5] = address.getText().toString();
                record[6] = city.getText().toString();
                record[7] = postalCode.getText().toString();

                User clerk = new User(record);
                clerk.addToDatabaseClerk();

                Toast.makeText(getBaseContext(), "Sign up successful!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }
}
