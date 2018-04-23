package co.udistrital.android.thomasmensageria.get_route;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import co.udistrital.android.thomasmensageria.domain.FirebaseHelper;
import co.udistrital.android.thomasmensageria.entities.Route;
import co.udistrital.android.thomasmensageria.get_route.events.RouteListEvent;
import co.udistrital.android.thomasmensageria.lib.EventBus;
import co.udistrital.android.thomasmensageria.lib.GreenRobotEventBus;

/**
 * Created by wisuarez on 08/03/2018.
 */

public class RouteListRepositoryImpl implements RouteListRepository {


    private FirebaseHelper helper;
    private ChildEventListener routeEventListener;
    private EventBus eventBus;

    public RouteListRepositoryImpl() {
        helper = FirebaseHelper.getInstance();
        eventBus = GreenRobotEventBus.getInstance();
    }


    @Override
    public void addRoute(String idRoute) {

    }

    @Override
    public void approveRoute(boolean approve) {

    }

    @Override
    public void removeRoute(String idRoute) {
        helper.getOneRouteReference(idRoute).removeValue();
    }

    @Override
    public void destroyListener() {
        routeEventListener = null;
    }

    @Override
    public void unSubscribeToRouteListEvents() {
        if(routeEventListener != null){
            helper.getRouteReferents().removeEventListener(routeEventListener);
        }


    }

    @Override
    public void subscribeToRouteListEvents() {

        if (routeEventListener == null){
            routeEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    handleRoute(dataSnapshot, RouteListEvent.onRouteAdded);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    handleRoute(dataSnapshot, RouteListEvent.onRouteChanged);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleRoute(dataSnapshot,RouteListEvent.onRouteRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
        }


        helper.getRouteReferents().addChildEventListener(routeEventListener);

    }

    private void handleRoute(DataSnapshot dataSnapshot, int type) {
        Route route = dataSnapshot.getValue(Route.class);
        post(type, route);
    }

    private void post(int type, Route route) {
        RouteListEvent event = new RouteListEvent();
        event.setEventType(type);
        event.setRoute(route);
        eventBus.post(event);
    }
}
