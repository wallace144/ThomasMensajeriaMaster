package co.udistrital.android.thomasmensageria.get_route.events;

import co.udistrital.android.thomasmensageria.entities.Route;

/**
 * Created by wisuarez on 26/02/2018.
 */

public class  RouteListEvent {
    public final static int onRouteAdded = 0;
    public final static int onRouteChanged = 1;
    public final static int onRouteRemoved = 2;

    private Route route;
    private int eventType;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
