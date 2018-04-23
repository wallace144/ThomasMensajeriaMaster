package co.udistrital.android.thomasmensageria;

import android.app.Application;

import com.google.firebase.FirebaseApp;





/**
 * Created by wisuarez on 30/08/2017.
 */

public class ThomasMensageriaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {


        FirebaseApp.initializeApp(FirebaseApp.getInstance().getApplicationContext());

    }
}
