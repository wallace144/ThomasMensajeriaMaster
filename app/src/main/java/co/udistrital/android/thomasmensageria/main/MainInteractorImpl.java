package co.udistrital.android.thomasmensageria.main;

/**
 * Created by wisuarez on 26/02/2018.
 */

public class MainInteractorImpl implements MainInteractor {

    private MainRepository mainRepository;


    public MainInteractorImpl() {
        mainRepository = new MainRepositoryImpl();
    }

    @Override
    public void signOff() {
        mainRepository.signoff();
    }

    @Override
    public void execute() {
        mainRepository.updateProfileShow();
    }
}
