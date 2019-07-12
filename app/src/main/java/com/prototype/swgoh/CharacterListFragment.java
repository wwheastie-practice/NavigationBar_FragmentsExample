package com.prototype.swgoh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.behavior.HideBottomViewOnScrollBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.realm.Realm;

public class CharacterListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.character_list, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        databaseSetup();
        viewsSetup(view);
    }

    private void databaseSetup() {
        Realm.init(getActivity());
        RealmDatabaseService realm = new RealmDatabaseService();
        realm.createDatabase();
    }

    private void viewsSetup(View view) {
        SearchView searchView = view.findViewById(R.id.character_search_view);
        RecyclerView recyclerView = view.findViewById(R.id.character_list_recycler_view);
        CharacterRecyclerViewAdapter adapter = new CharacterRecyclerViewAdapter(getActivity(), getAllCharacters());

        setRecyclerView(recyclerView, searchView, adapter);
        setSearchView(searchView, adapter);
    }

    private void setRecyclerView(RecyclerView recyclerView, final SearchView searchView, CharacterRecyclerViewAdapter adapter) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                searchView.clearFocus();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void setSearchView(SearchView searchView, final CharacterRecyclerViewAdapter adapter) {
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.updateCharacterList(getCharactersByName(s));

                return true;
            }
        });
    }

    private ArrayList<Character> getAllCharacters() {
        RealmDatabaseService realm = new RealmDatabaseService();
        return realm.retrieveAllCharactersFromDatabase();
    }

    private ArrayList<Character> getCharactersByName(String userInput) {
        RealmDatabaseService realm = new RealmDatabaseService();
        return realm.retrieveCharactersByName(userInput);
    }

    private void deleteDatabase() {
        Realm.init(getActivity());
        Realm.deleteRealm(Realm.getDefaultConfiguration());
    }

}
