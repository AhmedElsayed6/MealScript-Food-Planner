package com.example.mealscript.Search.Views;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mealscript.Model.Meal;
import com.example.mealscript.R;
import com.example.mealscript.Search.Presenter.SearchPagePresenter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;


public class SearchPage extends Fragment implements SearchPageInterface {
    ChipGroup chipGroupSearchCat;
    RecyclerView recyclerViewSearch;
    EditText searchView;
    SearchPagePresenter presenter;
    SearchRecyclerViewAdapter searchRecyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new SearchPagePresenter(this, this.getContext());
        chipGroupSearchCat = view.findViewById(R.id.chipGroupSearchCat);
        recyclerViewSearch = view.findViewById(R.id.recyclerViewSearch);
        searchView = view.findViewById(R.id.searchView);
        recyclerViewSearch.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        searchRecyclerViewAdapter = new SearchRecyclerViewAdapter(this,new ArrayList<Meal>());
        recyclerViewSearch.setAdapter(searchRecyclerViewAdapter);
        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String enteredText = searchView.getText().toString();
                    Chip selectedChip = view.findViewById(chipGroupSearchCat.getCheckedChipId());
                    String selectedText = selectedChip != null ? selectedChip.getText().toString() : "";
                    presenter.search(enteredText, selectedText);
                    return true;
                }
                return false;
            }
        });


    }


    @Override
    public void viewData(List<Meal> meals) {
        searchRecyclerViewAdapter.setData(meals);
    }

    @Override
    public void showErrorSnackBar(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addToFavorite(Meal meal) {

        presenter.insertMeal(meal);
    }

    @Override
    public void removeFromFavorite(Meal meal) {
        presenter.deleteMeal(meal);
    }
}