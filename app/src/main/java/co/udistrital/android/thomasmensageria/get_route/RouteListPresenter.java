package co.udistrital.android.thomasmensageria.get_route;

import android.content.Intent;

import co.udistrital.android.thomasmensageria.entities.Route;
import co.udistrital.android.thomasmensageria.get_route.events.RouteListEvent;

/**
 * Created by wisuarez on 26/02/2018.
 */

public interface RouteListPresenter {


    void onCreate();
    void onDestroy();
    void onPause();
    void onResume();

    void removeRoute(String idRoute);
    void onEventMainThread(RouteListEvent event);


    void validateGuia(int id_guia, Intent intent);
}
