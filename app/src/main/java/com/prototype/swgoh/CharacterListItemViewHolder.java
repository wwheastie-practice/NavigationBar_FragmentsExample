package com.prototype.swgoh;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class CharacterListItemViewHolder extends RecyclerView.ViewHolder {

    CircleImageView mImage;
    TextView mName;
    TextView mDescription;
    TextView mCategories;
    ConstraintLayout mParentLayout;

    public CharacterListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.image_character_list_item);
        mName = itemView.findViewById(R.id.name_character_list_item);
        mDescription = itemView.findViewById(R.id.description_character_list_item);
        mCategories = itemView.findViewById(R.id.categories_character_list_item);
        mParentLayout = itemView.findViewById(R.id.character_list_item_layout);
    }

}
