package com.example.favepertemuan3.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.favepertemuan3.model.Characters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favepertemuan3.R;
import com.example.favepertemuan3.model.Characters;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.viewHolder> {
    List<Characters> characterList;
    Context context;

    public CharacterAdapter(List<Characters> characterList, Context context){
        this.characterList = characterList;
        this.context = context;
     }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Characters c = characterList.get(position);
        holder.tvName.setText( c.getName() );
        holder.tvKanji.setText(c.getNameKanji());
        Picasso.with(context)
                .load(c.getImages().getJpg().getImageUrl())
                .resize(250, 250)
                .placeholder(R.drawable.baseline_image_24)
                .error(R.drawable.baseline_image_24)
                .centerCrop()
                .into(holder.ivThumbnail);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public void setCharacterList(List<Characters> characterList) {
        this.characterList = characterList;
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvKanji, tvFavorites;
        ImageView ivThumbnail;
        Button detailButton;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_character_name);
            tvKanji = itemView.findViewById(R.id.tv_character_kanji);
            ivThumbnail = itemView.findViewById(R.id.iv_thumbnail);
            detailButton = itemView.findViewById(R.id.detail_btn);
         }
    }
}
