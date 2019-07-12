package com.prototype.swgoh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //deleteDatabase();
        init();
    }

    private void init() {
        databaseSetup();
        viewsSetup();
    }

    private void databaseSetup() {
        Realm.init(this);
        RealmDatabaseService realm = new RealmDatabaseService();
        realm.createDatabase();
    }

    private void viewsSetup() {
        SearchView searchView = findViewById(R.id.character_search_view);
        RecyclerView recyclerView = findViewById(R.id.character_list_recycler_view);
        CharacterRecyclerViewAdapter adapter = new CharacterRecyclerViewAdapter(this, getAllCharacters());

        setRecyclerView(recyclerView, searchView, adapter);
        setSearchView(searchView, adapter);
    }

    private void setRecyclerView(RecyclerView recyclerView, final SearchView searchView, CharacterRecyclerViewAdapter adapter) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        Realm.init(this);
        Realm.deleteRealm(Realm.getDefaultConfiguration());
    }

}
