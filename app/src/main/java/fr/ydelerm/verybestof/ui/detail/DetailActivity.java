package fr.ydelerm.verybestof.ui.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import fr.ydelerm.verybestof.R;

public class DetailActivity extends AppCompatActivity {

    public static final String PARAM_REPO_ID = "DetailActivity.RepoId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        int repoId = getIntent().getIntExtra(PARAM_REPO_ID, -1);
        if (repoId < 0) {
            throw new IllegalArgumentException("movieId parameter is required for activity " + getClass().getName());
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DetailFragment.newInstance(repoId))
                    .commitNow();
        }
    }
}
