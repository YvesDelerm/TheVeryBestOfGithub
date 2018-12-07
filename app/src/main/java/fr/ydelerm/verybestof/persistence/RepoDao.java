package fr.ydelerm.verybestof.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import fr.ydelerm.verybestof.model.Repo;

import java.util.List;

@Dao
public interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRepos(List<Repo> repos);

    @Query("SELECT * FROM repo")
    LiveData<List<Repo>> getAllRepos();

    @Query("SELECT * FROM repo where id= :repoId")
    Repo findById(int repoId);
}
