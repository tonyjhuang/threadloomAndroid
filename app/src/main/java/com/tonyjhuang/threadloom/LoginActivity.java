package com.tonyjhuang.threadloom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    // UI references.
    @BindView(R.id.login_form)
    private View loginView;
    @BindView(R.id.username)
    private EditText usernameView;
    @BindView(R.id.password)
    private EditText passwordView;
    @BindView(R.id.login_progress)
    private View progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        passwordView.setOnEditorActionListener((view, id, keyevent) -> {
            if (id == R.id.login || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });
    }


    @OnClick(R.id.submit)
    public void onSubmitClicked() {
        attemptLogin();
    }

    private void attemptLogin() {
        String username = usernameView.getText().toString();
        if(username.length() == 0) {
            showToast(R.string.login_error_error_missing_username);
            return;
        }

        String password = passwordView.getText().toString();

        if(password.length() == 0) {
            showToast(R.string.login_error_error_missing_password);
            return;
        }
        if(password.length() < 8) {
            showToast(R.string.login_error_password_length);
            return;
        }

        showProgressView(true);
    }

    private void showProgressView(boolean show) {
        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        loginView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    private void showToast(int resourceId) {
        Toast.makeText(this, getString(resourceId), Toast.LENGTH_SHORT).show();
    }
}

