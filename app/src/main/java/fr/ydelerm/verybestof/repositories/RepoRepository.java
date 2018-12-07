package fr.ydelerm.verybestof.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import fr.ydelerm.verybestof.model.Repo;
import fr.ydelerm.verybestof.model.SearchResult;
import fr.ydelerm.verybestof.network.NetworkManager;
import fr.ydelerm.verybestof.persistence.AppDatabase;
import fr.ydelerm.verybestof.persistence.PrefsManager;
import fr.ydelerm.verybestof.persistence.RepoDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Date;
import java.util.List;

public class RepoRepository {
    private static final String TAG = "RepoRepository";
    private static final long REFRESH_DELAY_IN_MSECS = 2 * 60 * 60 * 1000;

    private final NetworkManager networkManager;
    private final PrefsManager prefsManager;
    private final RepoDao repoDao;
    public RepoRepository(Application application) {
        networkManager = NetworkManager.getInstance();
        prefsManager = new PrefsManager(application.getApplicationContext());
        AppDatabase db = AppDatabase.getAppDatabase(application);
        repoDao = db.repoDAO();
    }

    public LiveData<List<Repo>> getRepos() {
        return repoDao.getAllRepos();
    }

    public void refreshRepos(boolean forceUpdate) {
        if (forceUpdate || prefsManager.getLastUpdateTimestamp() < getRefreshTimestamp() ) {
            doRefreshRepos();
            prefsManager.updateLastUpdateTimestamp(new Date().getTime());
        }
    }

    private void doRefreshRepos() {
        Callback<SearchResult<Repo>> callback = new Callback<SearchResult<Repo>>() {
            @Override
            public void onResponse(@NonNull Call<SearchResult<Repo>> call, @NonNull Response<SearchResult<Repo>> response) {
                if (response.body()!=null) {
                    insertRepos(response.body().getItems());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchResult<Repo>> call, @NonNull Throwable throwable) {
                Log.e(TAG, "error while calling webservice", throwable);
            }
        };

        networkManager.getTrendingRepos(callback);
    }

    private void insertRepos(@NonNull final List<Repo> repos) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                repoDao.insertRepos(repos);
            }
        }).start();

    }


    private long getRefreshTimestamp() {
        return new Date().getTime() - REFRESH_DELAY_IN_MSECS;
    }

}
