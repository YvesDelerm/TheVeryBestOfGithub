package fr.ydelerm.verybestof.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import fr.ydelerm.verybestof.model.Repo;
import fr.ydelerm.verybestof.repositories.RepoRepository;

import java.util.List;

class MainViewModel extends ViewModel {

    private final LiveData<List<Repo>> allRepos;
    private final RepoRepository repository;

    public MainViewModel() {
        super();
        repository = new RepoRepository();
        allRepos = repository.getRepos();
    }

    void refresh() {
        repository.refreshRepos();
    }

    LiveData<List<Repo>> getTrendingRepos() {
        return allRepos;
    }
}
