package com.prototype.swgoh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        databaseSetup();
        recyclerViewSetup();
    }

    private void databaseSetup() {
        Realm.init(this);
        RealmDatabaseService realm = new RealmDatabaseService();
        realm.createDatabase();
    }

    private ArrayList<Character> loadCharacters() {
        RealmDatabaseService realm = new RealmDatabaseService();
        return realm.retrieveAllCharactersFromDatabase();
    }

    private void deleteDatabase() {
        Realm.init(this);
        Realm.deleteRealm(Realm.getDefaultConfiguration());
    }

    private void recyclerViewSetup() {
        RecyclerView recyclerView = findViewById(R.id.character_list_recycler_view);
        List<Character> characters = loadCharacters();
        CharacterRecyclerViewAdapter adapter = new CharacterRecyclerViewAdapter(this, characters);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
