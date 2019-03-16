package com.example.payfazzmobilechallange.favorit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.payfazzmobilechallange.NewsAdapter;
import com.example.payfazzmobilechallange.R;
import com.example.payfazzmobilechallange.TopNews;

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {
    ArrayList<TopNews> news = new ArrayList<>();

    public FavAdapter (ArrayList<TopNews> news){
        this.news = news;
    }

    @NonNull
    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fav_card_layout,viewGroup,false);
        FavAdapter.ViewHolder viewHolder = new FavAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(news.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txtTitleFav);
        }
    }

}
