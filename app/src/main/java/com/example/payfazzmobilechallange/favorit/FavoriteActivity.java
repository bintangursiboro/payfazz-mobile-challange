package com.example.payfazzmobilechallange.favorit;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.payfazzmobilechallange.NewsAdapter;
import com.example.payfazzmobilechallange.R;
import com.example.payfazzmobilechallange.TopNews;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        ArrayList<TopNews> news = intent.getParcelableArrayListExtra("topFavoriteNews");
        RecyclerView rview;
        RecyclerView.LayoutManager lmanager;
        FavAdapter adapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        getSupportActionBar().setTitle("Favorites");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.fav_layoutss);

        rview = (RecyclerView)findViewById(R.id.rviewFav);
        rview.setHasFixedSize(true);

        lmanager = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(lmanager);
        adapter = new FavAdapter(news);
        rview.setAdapter(adapter);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
