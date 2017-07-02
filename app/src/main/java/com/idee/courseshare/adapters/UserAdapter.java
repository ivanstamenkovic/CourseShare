package com.idee.courseshare.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.idee.courseshare.R;
import com.idee.courseshare.model.User;

/**
 * Created by Ivan on 7/2/2017.
 */

public class UserAdapter extends FirebaseRecyclerAdapter<User, UserAdapter.UserViewHolder>{

    public UserAdapter(DatabaseReference ref) {
        super(User.class, R.layout.row_user, UserAdapter.UserViewHolder.class, ref);
    }

    @Override
    protected void populateViewHolder(UserViewHolder viewHolder, User model, int position) {
        String name = model.getName();
        viewHolder.setName(name);

        String description = model.getInfo();
        viewHolder.setInfo(description);

        String picture = model.getPicture();
        viewHolder.setPicture(picture, name);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPicture;
        private TextView tvName;
        private TextView tvInfo;

        private Context context;

        public UserViewHolder(View itemView) {
            super(itemView);

            ivPicture = (ImageView) itemView.findViewById(R.id.row_user_picture);
            tvName = (TextView) itemView.findViewById(R.id.row_user_name);
            tvInfo = (TextView) itemView.findViewById(R.id.row_user_info);

            context = itemView.getContext();
        }

        private void setName(String name) {
            if (name != null) {
                if (name.length() > 140) {
                    String pom = name.substring(0, 137) + "...";
                    tvName.setText(pom);
                } else {
                    tvName.setText(name);
                }
            }
            else {
                tvName.setText("<User has no name>");
            }
        }

        private void setInfo(String description) {
            if (description != null) {
                if (description.length() > 140) {
                    String pom = description.substring(0, 137) + "...";
                    tvInfo.setText(pom);
                } else {
                    tvInfo.setText(description);
                }
            }
            else {
                tvInfo.setText("<User has no info>");
            }
        }

        private void setPicture(String picture, String name) {
            if (picture != null) {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference ref = storage.getReference().child(picture);

                Glide.with(context).using(new FirebaseImageLoader()).load(ref).into(ivPicture);
            }
            else {
                ivPicture.setImageResource(0);
                TextDrawable drawable = TextDrawable.builder()
                        .buildRoundRect(name.substring(0, 1), Color.DKGRAY, 16);
                ivPicture.setImageDrawable(drawable);
            }
        }
    }
}
