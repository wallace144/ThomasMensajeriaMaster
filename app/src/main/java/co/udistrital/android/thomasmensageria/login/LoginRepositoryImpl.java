package co.udistrital.android.thomasmensageria.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import co.udistrital.android.thomasmensageria.domain.FirebaseHelper;
import co.udistrital.android.thomasmensageria.lib.EventBus;
import co.udistrital.android.thomasmensageria.lib.GreenRobotEventBus;
import co.udistrital.android.thomasmensageria.login.events.LoginEvent;

/**
 * Created by wisuarez on 05/09/2017.
 */

public class LoginRepositoryImpl implements LoginRepository {
    private FirebaseHelper helper;
    private DatabaseReference dataReference;
    private DatabaseReference myUserReference;
    private PruebaRepositoryBorrar repositoryBorrar;


    public LoginRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.dataReference = helper.getDataReference();
    }

    @Override
    public void signIn(String email, String password) {
       FirebaseAuth auth = FirebaseAuth.getInstance();
       try {
           auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
               @Override
               public void onSuccess(AuthResult authResult) {

                   postEvent(LoginEvent.onSignInSuccess);
               }
           }).addOnFailureListener(new OnFailureListener() {

               @Override
               public void onFailure(@NonNull Exception e) {
                   postEvent(LoginEvent.onSignInError, e.getMessage());
               }
           });
       }catch (Exception e){
           postEvent(LoginEvent.onSignInError, e.getMessage());
       }

       //PRUEBA_SOAP
       // repositoryBorrar = new PruebaRepositoryBorrar(email,password);
        //repositoryBorrar.execute();


    }

    @Override
    public void checkSesion() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
            postEvent(LoginEvent.onSignInSuccess);
        else
            postEvent(LoginEvent.onFailedToRecoverSession);
    }

    private void postEvent(int type, String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (errorMessage != null ){
            loginEvent.setErrorMessage(errorMessage);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }
    private void postEvent(int type){
        postEvent(type, null);
    }
}
