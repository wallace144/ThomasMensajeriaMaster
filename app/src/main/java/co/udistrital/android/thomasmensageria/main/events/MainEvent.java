package co.udistrital.android.thomasmensageria.main.events;

import co.udistrital.android.thomasmensageria.entities.Messenger;

/**
 * Created by wisuarez on 26/02/2018.
 */

public class MainEvent {
    private int type;
    private String error;
    private Messenger user;

    public static final int READ_EVENT = 0;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Messenger getUser() {
        return user;
    }

    public void setUser(Messenger user) {
        this.user = user;
    }
}
