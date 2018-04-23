package co.udistrital.android.thomasmensageria.login;

/**
 * Created by wisuarez on 05/09/2017.
 * contiene los casos de uso
 */

public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractorImpl() {
        this.loginRepository = new LoginRepositoryImpl();
    }


    @Override
    public void checkSession() {
        loginRepository.checkSesion();
    }

    @Override
    public void doSignIn(String email, String password) {
        loginRepository.signIn(email,password);
    }
}
