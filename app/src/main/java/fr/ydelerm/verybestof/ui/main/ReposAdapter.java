package fr.ydelerm.verybestof.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import fr.ydelerm.verybestof.R;
import fr.ydelerm.verybestof.model.Repo;
import fr.ydelerm.verybestof.ui.detail.DetailActivity;

import java.util.List;

class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.RepoViewHolder> {
    private final Context context;
    private final List<Repo> repos;

    ReposAdapter(Context context, List<Repo> repos) {
        this.context = context;
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
        Picasso.get()
                .load(repo.getOwner().getAvatarUrl())
                .placeholder(R.drawable.account)
                .error(R.drawable.account)
                .into(holder.ivAvatar);

        holder.cellView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Repo selectedRepo = repos.get(holder.getAdapterPosition());
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.PARAM_REPO_ID, selectedRepo.getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    static class RepoViewHolder extends RecyclerView.ViewHolder {
        final View cellView;
        final TextView tvName;
        final ImageView ivAvatar;

        RepoViewHolder(@NonNull View v) {
            super(v);
            cellView = v.findViewById(R.id.cellView);
            tvName = v.findViewById(R.id.name);
            ivAvatar = v.findViewById(R.id.avatar);
        }
    }
}
