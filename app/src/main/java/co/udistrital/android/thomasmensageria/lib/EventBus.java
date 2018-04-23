package co.udistrital.android.thomasmensageria.lib;

/**
 * Created by wisuarez on 05/09/2017.
 */

public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
