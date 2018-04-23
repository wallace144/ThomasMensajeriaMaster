package co.udistrital.android.thomasmensageria.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.udistrital.android.thomasmensageria.R;
import co.udistrital.android.thomasmensageria.login.LoginPresenterImpl;
import co.udistrital.android.thomasmensageria.main.ui.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {


    @BindView(R.id.editTxtEmail)
    EditText editTxtEmail;
    @BindView(R.id.editTxtPassword)
    EditText editTxtPassword;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layoutMainContainer)
    RelativeLayout layoutMainContainer;
    @BindView(R.id.btnSignin)
    Button btnSignin;

    private LoginPresenterImpl loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCreate();
        loginPresenter.checkForAuthenticatedUser();

    }


    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.btnSignin)
    public void handleSigin() {
        Toast.makeText(this, "Listos", Toast.LENGTH_LONG).show();
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnSignin)
    @Override
    public void handleSingIn() {
        loginPresenter.validateLogin(editTxtEmail.getText().toString(), editTxtPassword.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
       startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void loginError(String error) {
        editTxtPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        editTxtPassword.setError(msgError);
    }

    public void setInputs(boolean enable) {
        editTxtEmail.setEnabled(enable);
        editTxtPassword.setEnabled(enable);
        btnSignin.setEnabled(enable);
    }
}
