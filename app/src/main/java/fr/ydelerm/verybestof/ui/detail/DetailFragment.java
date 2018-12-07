package fr.ydelerm.verybestof.ui.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.ydelerm.verybestof.R;
import fr.ydelerm.verybestof.model.Repo;

public class DetailFragment extends Fragment {

    private static final String REPO_ID_PARAM = "DetailFragment.repoId";
    private DetailViewModel mViewModel;
    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvGazers;
    private TextView tvWatchers;

    public static DetailFragment newInstance(int repoId) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(REPO_ID_PARAM, repoId);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = view.findViewById(R.id.title);
        tvDescription= view.findViewById(R.id.description);
        tvGazers = view.findViewById(R.id.gazers);
        tvWatchers= view.findViewById(R.id.watchers);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        int repoId = getArguments() != null ? getArguments().getInt(REPO_ID_PARAM) : 0;

        mViewModel.getSelectedRepo().observe(this, new Observer<Repo>() {
            @Override
            public void onChanged(@Nullable Repo repo) {
                if (repo!=null) {
                    refreshData(repo);
                }
            }
        });

        mViewModel.setRepoId(repoId);

    }

    private void refreshData(Repo repo) {
        tvTitle.setText(repo.getName());
        tvDescription.setText(repo.getDescription());
        tvGazers.setText(getString(R.string.gazers, repo.getStargazersCount()));
        tvWatchers.setText(getString(R.string.watchers, repo.getWatchersCount()));
    }

}
