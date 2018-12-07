package fr.ydelerm.verybestof.network;

import android.support.annotation.NonNull;
import fr.ydelerm.verybestof.model.Repo;
import fr.ydelerm.verybestof.model.SearchResult;
import retrofit2.Call;
import retrofit2.http.GET;

interface GitHubApiService {

    @NonNull
    @GET("/search/repositories?q=+language:kotlin&sort=stars&order=desc")
    Call<SearchResult<Repo>> getTrendingRepositories();
}
