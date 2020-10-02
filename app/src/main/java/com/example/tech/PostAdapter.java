package com.example.tech;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Postviewadapter> {
    private Context context;
    private List<Item> items;

    public PostAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public Postviewadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.card,parent,false);
        return new Postviewadapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Postviewadapter holder, int position) {
        Item item=items.get(position);
        holder.posttext.setText(item.getTitle());

        Document document= Jsoup.parse(item.getContent());
        Elements elements=document.select("img");
        holder.postdesc.setText(document.text());

        Glide.with(context).load(elements.get(0).attr("src")).into(holder.postimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(context,detail.class);
                it.putExtra("url",item.getUrl());
                context.startActivity(it);

            }
        });

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Postviewadapter extends RecyclerView.ViewHolder {
        ImageView postimage;
        TextView posttext;
        TextView postdesc;

        public Postviewadapter(@NonNull View itemView) {
            super(itemView);
            postimage = (ImageView) itemView.findViewById(R.id.imagecard);
            postdesc = (TextView) itemView.findViewById(R.id.body);
            posttext = (TextView) itemView.findViewById(R.id.posttitle);
        }
    }
}