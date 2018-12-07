package fr.ydelerm.verybestof.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult<T> {
    @SerializedName("totalCount")
    private int totalCount;
    @SerializedName("items")
    private List<T> items;
    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }
}
