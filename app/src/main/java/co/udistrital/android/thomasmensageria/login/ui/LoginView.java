package co.udistrital.android.thomasmensageria.login.ui;

/**
 * Created by wisuarez on 31/08/2017.
 * manipulamos la vista
 */

public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSingIn();

    void navigateToMainScreen();
    void loginError(String error);
}
