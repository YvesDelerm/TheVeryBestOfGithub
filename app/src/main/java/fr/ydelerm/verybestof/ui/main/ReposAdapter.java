package fr.ydelerm.verybestof.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.ydelerm.verybestof.R;
import fr.ydelerm.verybestof.model.Repo;

import java.util.List;

class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.RepoViewHolder> {
    private final List<Repo> repos;

    ReposAdapter(List<Repo> repos) {
        this.repos = repos;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_repo_list_item, parent, false);
        return new RepoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final RepoViewHolder holder, int position) {
        Repo repo = repos.get(position);
        holder.tvName.setText(repo.getName());
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    static class RepoViewHolder extends RecyclerView.ViewHolder {
        final TextView tvName;

        RepoViewHolder(@NonNull View v) {
            super(v);
            tvName = v.findViewById(R.id.name);
        }
    }
}
