package fr.ydelerm.verybestof.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import fr.ydelerm.verybestof.model.Repo;
import fr.ydelerm.verybestof.model.SearchResult;
import fr.ydelerm.verybestof.network.NetworkManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class RepoRepository {
    private static final String TAG = "RepoRepository";
    private final NetworkManager networkManager;
    private MutableLiveData<List<Repo>> allRepos;
    public RepoRepository() {
        networkManager = NetworkManager.getInstance();
        if (allRepos==null) {
            allRepos = new MutableLiveData<>();
        }
    }

    public void refreshRepos() {
        Callback<SearchResult<Repo>> callback = new Callback<SearchResult<Repo>>() {
            @Override
            public void onResponse(@NonNull Call<SearchResult<Repo>> call, @NonNull Response<SearchResult<Repo>> response) {
                if (response.body()!=null) {
                    allRepos.postValue(response.body().getItems());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchResult<Repo>> call, @NonNull Throwable throwable) {
                Log.e(TAG, "error while calling webservice", throwable);
            }
        };

        networkManager.getTrendingRepos(callback);
    }

    public LiveData<List<Repo>> getRepos() {
        return allRepos;
    }
}
