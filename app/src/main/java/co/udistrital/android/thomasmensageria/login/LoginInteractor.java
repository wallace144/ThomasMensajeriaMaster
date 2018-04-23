package co.udistrital.android.thomasmensageria.login;

/**
 * Created by wisuarez on 01/09/2017.
 */

public interface LoginInteractor {
    void checkSession();
    void doSignIn(String email, String password);
}
