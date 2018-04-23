package co.udistrital.android.thomasmensageria.main.ui;

import co.udistrital.android.thomasmensageria.entities.Messenger;



/**
 * Created by wisuarez on 26/02/2018.
 */

public interface MainView {


    void setUser(Messenger messenger);
    void onGetUserError(String error);

}
