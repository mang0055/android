package com.algonquincollege.mang0055.myfirstapp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_email;
    private EditText edit_password;
    private Switch switch_remember_me;
    private Button btn_sign_in;
    EditText focusView;
    private static final String ABOUT_DIALOG_TAG;
    private static final String LOG_TAG;

    static {
        ABOUT_DIALOG_TAG = "About Dialog";
        LOG_TAG = "LOGIN EXAMPLE";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_password = (EditText) findViewById(R.id.edit_password);
        switch_remember_me = (Switch) findViewById(R.id.switch_remember_me);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        btn_sign_in.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                validateLogin();
                break;
        }
    }

    private void validateLogin() {
        String emailid = edit_email.getText().toString();
        String password = edit_password.getText().toString();

        boolean cancel = false;

        if (TextUtils.isEmpty(emailid)) {
            focusView = edit_email;
            cancel = true;
        }
        if (!isEmailValid(emailid)) {
            focusView = edit_email;
            focusView.setError("Please check email address.");
            cancel = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (focusView != null) {
                        focusView.setError(null);
                    }
                }
            }, 2000);
        }
        if (TextUtils.isEmpty(password)) {
            focusView = edit_password;
            focusView.setError("Please check password.");
            cancel = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (focusView != null) {
                        focusView.setError(null);
                    }
                }
            }, 2000);
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            doLogin();
        }
    }

    private void doLogin() {

    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
