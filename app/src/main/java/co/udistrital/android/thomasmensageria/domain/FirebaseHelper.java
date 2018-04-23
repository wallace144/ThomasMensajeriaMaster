package co.udistrital.android.thomasmensageria.domain;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by wisuarez on 30/08/2017.
 */

public class FirebaseHelper {
    private DatabaseReference dataReference;
    private final static String PRODUCT_PATH = "products";
    private final static String ROUTE_PATH = "routes";
    private final static String MESSENGER_PATH = "messengers";
    private final static String CODE_TEST_PATH = "11";

    private final static String FIREBASE_URL = "https://mensajeria-20caa.firebaseio.com";



    private static class SingletonHolper {
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance(){
        return SingletonHolper.INSTANCE;
    }

    public FirebaseHelper() {
        dataReference = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDataReference() {
        return dataReference;
    }


    public String getAuthUserEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = null;
        if (user != null){
            email = user.getEmail();
        }
        return email;
    }



    public void signOff(){
        FirebaseAuth.getInstance().signOut();
    }

    public DatabaseReference getUserReferents(){
        DatabaseReference userReference = null;

        userReference = dataReference.getRoot().child(MESSENGER_PATH).child(CODE_TEST_PATH );
        return userReference;
    }

    public DatabaseReference getOneRouteReference(String idGuide){
        return dataReference.getRoot().child(ROUTE_PATH).child(idGuide);
    }

    public DatabaseReference getRouteReferents(){
        DatabaseReference routeReference = null;
        return dataReference.getRef().child(ROUTE_PATH);
    }




    /*public void notifyContactsOfConnectionChange(final boolean online, final boolean signoff) {
        final String myEmail = getAuthUserEmail();
    }*/
}
