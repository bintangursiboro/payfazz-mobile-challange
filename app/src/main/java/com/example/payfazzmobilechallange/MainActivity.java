package com.example.payfazzmobilechallange;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payfazzmobilechallange.favorit.FavoriteActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<TopNews> data = new ArrayList<>();
    private HashMap<Integer,TopNews> hashMapNews = new HashMap<>();
    private List<Integer> allId = new ArrayList<>();
    private Retrofit retrofit;
    private ProgressBar progressBar;
    private TextView textView;
    private ApiService service;
    private OkHttpClient client = new OkHttpClient.Builder().build();
    private NewsAdapter newsAdapter;
    RecyclerView.LayoutManager lmanager;
    NewsAdapter adapter;
    RecyclerView rview;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Hacker News");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        //Prgressbar & toolbar
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);

        //setup retrofit utk fetching API
        initRetrofit();
        getAllId();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Karena RxJava async task maka ditunggu proses fetching data 2 detik
                Log.e("news",allId.size()+"");
                for (Integer x: allId){
                    getNews(x);
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),"Fetching data selesai",Toast.LENGTH_SHORT).show();
                        Log.e("jumlah data", data.size()+"");
                        Collections.sort(data);

                        rview = (RecyclerView)findViewById(R.id.rView);
                        rview.setHasFixedSize(true);

                        lmanager = new LinearLayoutManager(getApplicationContext());
                        rview.setLayoutManager(lmanager);
                        adapter = new NewsAdapter(data);
                        rview.setAdapter(adapter);
                    }
                }, 35000);

            }
        }, 1500);
    }

    void initRetrofit (){
        retrofit =new Retrofit.Builder()
                .baseUrl("https://hacker-news.firebaseio.com/v0/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        service =retrofit.create(ApiService.class);
    }

    public List<Integer> getAllId() {
        final ArrayList<Integer> listId = new ArrayList<>();
        Observable<List<Integer>> listObservable = service.getAllId();
        listObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        listId.addAll(integers);
                        Log.e("id",integers.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error","karena"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        allId.addAll(listId);
                        Log.e("jumlahNews", String.valueOf(allId.size()));
                        for (Integer x: listId){
//                            getNews(x);
                        }
                    }
                });

        return listId;
    }

    void getNews(Integer integer){
        Call<TopNews> call = service.getTopNews(integer);
        call.enqueue(new Callback<TopNews>() {
            @Override
            public void onResponse(Call<TopNews> call, Response<TopNews> response) {
                data.add(response.body());
            }

            @Override
            public void onFailure(Call<TopNews> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ArrayList<TopNews> news = new ArrayList<>();
        Intent intent = new Intent(getApplicationContext(), FavoriteActivity.class);
        for (TopNews x: data){
            if (x.clicked %2 !=0){
                news.add(x);
            }
        }
        if (news != null){
            Log.e("isi news",news.size()+"");
            intent.putParcelableArrayListExtra("topFavoriteNews",news);
        }else {
            Log.e("Error","list kosong");
        }
        if (id == R.id.action_favorite){
            startActivity(intent);
        }
        return true;
    }
}
