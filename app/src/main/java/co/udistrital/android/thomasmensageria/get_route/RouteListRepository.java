package co.udistrital.android.thomasmensageria.get_route;

/**
 * Created by wisuarez on 06/03/2018.
 */

public interface RouteListRepository {

    void addRoute(String idRoute);
    void approveRoute(boolean approve);
    void removeRoute(String idRoute);//Route route

    void destroyListener();

    void unSubscribeToRouteListEvents();

    void subscribeToRouteListEvents();
}
