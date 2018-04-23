package co.udistrital.android.thomasmensageria.main;

import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import co.udistrital.android.thomasmensageria.lib.EventBus;
import co.udistrital.android.thomasmensageria.lib.GreenRobotEventBus;
import co.udistrital.android.thomasmensageria.main.events.MainEvent;
import co.udistrital.android.thomasmensageria.main.ui.MainView;

/**
 * Created by wisuarez on 26/02/2018.
 */

public class MainPresenterImpl  implements MainPresenter {

    private MainInteractor mainInteractor;
    private MainView mainView;
    private EventBus eventBus;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.mainInteractor = new MainInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mainView= null;
        eventBus.unregister(this);
    }

    @Override
    public void signOff() {
        mainInteractor.signOff();
    }

    @Override
    public void updateProfileShow() {
        mainInteractor.execute();

    }




    @Override
    @Subscribe
    public void onEventMainThread(MainEvent event) {
        String errorMsg = event.getError();
        if(this.mainView !=  null){
            if (errorMsg != null)
                mainView.onGetUserError(errorMsg);
            else
                mainView.setUser(event.getUser());
        }

    }
}
