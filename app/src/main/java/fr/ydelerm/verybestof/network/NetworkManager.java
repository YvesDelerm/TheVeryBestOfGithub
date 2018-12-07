package fr.ydelerm.verybestof.network;


import fr.ydelerm.verybestof.model.Repo;
import fr.ydelerm.verybestof.model.SearchResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    private static NetworkManager instance;
    private final GitHubApiService gitHubApiService;

    private static final String BASE_URL = "https://api.github.com";

    private NetworkManager() {
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        gitHubApiService = retrofit.create(GitHubApiService.class);
    }

    public static synchronized NetworkManager getInstance() {
        if (instance==null) {
            instance = new NetworkManager();
        }
        return instance;
    }


    public void getTrendingRepos(Callback<SearchResult<Repo>> callback) {
        Call<SearchResult<Repo>> call = gitHubApiService.getTrendingRepositories();
        call.enqueue(callback);
    }

}
