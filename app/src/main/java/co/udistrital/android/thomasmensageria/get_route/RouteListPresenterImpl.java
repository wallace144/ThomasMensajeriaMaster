package co.udistrital.android.thomasmensageria.get_route;

import org.greenrobot.eventbus.Subscribe;

import co.udistrital.android.thomasmensageria.entities.Route;
import co.udistrital.android.thomasmensageria.get_route.events.RouteListEvent;
import co.udistrital.android.thomasmensageria.get_route.ui.RouteListView;
import co.udistrital.android.thomasmensageria.lib.EventBus;
import co.udistrital.android.thomasmensageria.lib.GreenRobotEventBus;

/**
 * Created by wisuarez on 08/03/2018.
 */

public class RouteListPresenterImpl implements RouteListPresenter {

    private RouteListInteractor interactor;
    private RouteListView view;
    private EventBus eventBus;

    public RouteListPresenterImpl(RouteListView routeListView) {
        this.view = routeListView;
        eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new RouteListInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor.destroyListener();
        eventBus.unregister(this);
    }

    @Override
    public void onPause() {
        interactor.unsubscribe();
    }

    @Override
    public void onResume() {
        interactor.subscribe();
    }


    @Override
    public void removeRoute(String idRoute) {
        interactor.removeRoute(idRoute);
    }

    public void approveRoute(Boolean state){
        interactor.approveRoute(state);
    }



    @Override
    @Subscribe
    public void onEventMainThread(RouteListEvent event) {
        Route route = event.getRoute();
        switch (event.getEventType()){
            case RouteListEvent.onRouteAdded:
                onAddRoute(route);
                break;
            case RouteListEvent.onRouteChanged:
                onChangedRoute(route);
                break;
            case RouteListEvent.onRouteRemoved:
                onRemoveRoute(route);
                break;
        }
    }

    public void onAddRoute(Route route) {
        if (view != null){
           view.onRouteAdd(route);
        }
    }

    public void onChangedRoute(Route route) {
        if (view != null){
            view.onRouteChanged(route);
        }
    }

    public void onRemoveRoute(Route route){
        if (view != null){
            view.onRouteRemoved(route);
        }
    }
}
