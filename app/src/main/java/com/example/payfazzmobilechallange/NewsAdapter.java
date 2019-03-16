package com.example.payfazzmobilechallange;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    ArrayList<TopNews> datalist = new ArrayList<>();

    public NewsAdapter (List<TopNews> dataList){
        this.datalist = (ArrayList<TopNews>) dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.txtAuthor.setText("Author: "+datalist.get(i).getAuthor());
        viewHolder.txtId.setText("#"+String.valueOf(datalist.get(i).getId()));
        viewHolder.txtJudul.setText(datalist.get(i).getTitle());
        try {
            viewHolder.txtTime.setText(intToDate(datalist.get(i).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (datalist.get(i).clicked %2 ==0){
            viewHolder.favorite.setImageResource(R.mipmap.icons_outlined);
        }else {
            viewHolder.favorite.setImageResource(R.drawable.icons_love);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clicked = datalist.get(i).clicked;
                datalist.get(i).clicked++;
                Log.e("click",datalist.get(i).clicked+"");
                notifyDataSetChanged();
            }
        });

    }

    public String intToDate (Integer x) throws ParseException {
        String newString;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date newDate = new Date(x*1000L);
        Date date = format.parse(x.toString());

        SimpleDateFormat newFormat = new SimpleDateFormat("MMM d, yyyy");
        String formatedDate = newFormat.format(newDate);
        return formatedDate;
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtId;
        TextView txtJudul ;
        TextView txtAuthor ;
        TextView txtTime;
        ImageView favorite;
        int clicked = 0;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = (TextView) itemView.findViewById(R.id.textId);
            txtJudul = (TextView) itemView.findViewById(R.id.txtTitle);
            txtAuthor =(TextView) itemView.findViewById(R.id.txtAuthor);
            txtTime = (TextView) itemView.findViewById(R.id.txtTime);
            favorite = (ImageView) itemView.findViewById(R.id.imageView);
            if (clicked%2 == 0){
                favorite.setImageResource(R.mipmap.icons_outlined);
            }else {
                favorite.setImageResource(R.drawable.icons_love);
            }
        }
    }

}
