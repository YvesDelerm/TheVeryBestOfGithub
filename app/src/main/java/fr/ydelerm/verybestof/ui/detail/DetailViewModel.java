package fr.ydelerm.verybestof.ui.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import fr.ydelerm.verybestof.model.Repo;
import fr.ydelerm.verybestof.persistence.AppDatabase;
import fr.ydelerm.verybestof.persistence.RepoDao;

class DetailViewModel extends AndroidViewModel {

    private final MutableLiveData<Repo> selectedRepo;
    private final RepoDao repoDao;

    public DetailViewModel(Application application) {
        super(application);
        selectedRepo = new MutableLiveData<>();
        AppDatabase db = AppDatabase.getAppDatabase(application);
        repoDao = db.repoDAO();
    }

    public void setRepoId(final int repoId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                selectedRepo.postValue(repoDao.findById(repoId));
            }
        }).start();

    }

    public LiveData<Repo> getSelectedRepo() {
        return selectedRepo;
    }
}
