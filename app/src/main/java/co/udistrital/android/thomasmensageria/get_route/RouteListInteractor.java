package co.udistrital.android.thomasmensageria.get_route;

/**
 * Created by wisuarez on 26/02/2018.
 */

public interface RouteListInteractor {

    void removeRoute(String idRoute);
    void addRoute(String idRoute);
    void approveRoute(boolean approve);

    void destroyListener();
    void unsubscribe();
    void subscribe();
}
