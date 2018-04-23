package co.udistrital.android.thomasmensageria.login;

import co.udistrital.android.thomasmensageria.login.events.LoginEvent;

/**
 * Created by wisuarez on 01/09/2017.
 */

public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void onEventMainThread(LoginEvent event);

}
