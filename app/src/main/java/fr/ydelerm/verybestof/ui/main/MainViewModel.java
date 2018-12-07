package fr.ydelerm.verybestof.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import fr.ydelerm.verybestof.model.Repo;
import fr.ydelerm.verybestof.repositories.RepoRepository;

import java.util.List;

class MainViewModel extends AndroidViewModel {

    private final LiveData<List<Repo>> allRepos;
    private final RepoRepository repository;

    public MainViewModel(Application application) {
        super(application);
        repository = new RepoRepository(application);
        allRepos = repository.getRepos();
    }

    void refresh(boolean force) {
        repository.refreshRepos(force);
    }

    LiveData<List<Repo>> getTrendingRepos() {
        return allRepos;
    }
}
