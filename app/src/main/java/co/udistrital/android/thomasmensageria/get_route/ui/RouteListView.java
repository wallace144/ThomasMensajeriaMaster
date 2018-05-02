package co.udistrital.android.thomasmensageria.get_route.ui;

import co.udistrital.android.thomasmensageria.entities.Route;

/**
 * Created by wisuarez on 26/02/2018.
 */

public interface RouteListView {

    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void onRouteAdd(Route route);
    void onRouteChanged(Route route);
    void onRouteRemoved(Route route);
    void onRouteError(String error);

    void changedState();
}
