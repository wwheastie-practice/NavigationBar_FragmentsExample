package com.prototype.swgoh;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterListItemViewHolder> {

    private List<Character> mCharacters;
    private Context mContext;

    public CharacterRecyclerViewAdapter(Context context, List<Character> characters) {
        mContext = context;
        mCharacters = characters;
    }

    @NonNull
    @Override
    public CharacterListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.character_list_item, viewGroup, false);
        CharacterListItemViewHolder viewHolder = new CharacterListItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterListItemViewHolder characterListItemViewHolder, int i) {
        setViewHolder(mCharacters.get(i),characterListItemViewHolder);
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }

    public void updateCharacterList(List<Character> queryCharacters) {
        mCharacters = new ArrayList<>();
        mCharacters.addAll(queryCharacters);
        notifyDataSetChanged();
    }

    private void setViewHolder(Character character, CharacterListItemViewHolder viewHolder) {
        setCharacterImageViewWithURL(character, viewHolder);
        setCharacterDisplayInformation(character, viewHolder);
        setOnClickListenerForView(character, viewHolder);
    }

    private void setCharacterImageViewWithURL(Character character, CharacterListItemViewHolder viewHolder) {
        Glide.with(mContext)
                .asBitmap()
                .load("https://swgoh.gg" + character.getImage())
                .into(viewHolder.mImage);
    }

    private void setCharacterDisplayInformation(Character character, CharacterListItemViewHolder viewHolder) {
        viewHolder.mName.setText(character.getName());
        viewHolder.mDescription.setText(character.getDescription());
        viewHolder.mCategories.setText(setCharacterCategoriesIntoSingleString(character));
        viewHolder.setImageBorderByAlignment(character.getAlignment());
    }

    private String setCharacterCategoriesIntoSingleString(Character character) {
        StringBuilder categoryString = new StringBuilder();

        for(int i = 0; i < character.getCategories().size(); i++) {
            categoryString.append(character.getCategories().get(i));

            if(i != character.getCategories().size() - 1) {
                categoryString.append(" | ");
            }
        }

        return categoryString.toString();
    }

    private void setOnClickListenerForView(final Character character, CharacterListItemViewHolder viewHolder) {
        viewHolder.mParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, character.getName(), Toast.LENGTH_SHORT).show();
//                View view = View.inflate(mContext, R.layout.activity_main, null);
//                Activity activity = new Activity();
//                FragmentActivity fragmentActivity = new FragmentActivity();
//                fragmentActivity.getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.main_frame, new OtherFragment())
//                        .commit();
                Fragment fragment = new OtherFragment();
                FragmentManager fragmentManager = ((MainActivity)mContext).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.main_frame, new OtherFragment())
                        .commit();
            }
        });
    }

}
