package co.udistrital.android.thomasmensageria.login;

/**
 * Created by wisuarez on 01/09/2017.
 */

public interface LoginRepository {
    void signIn(String email, String password);
    void checkSesion();
}
