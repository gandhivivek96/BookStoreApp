package com.example.vivekgandhi.bookstoreapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Vivek Gandhi on 3/26/2017.
 */

public class BooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<Books> data= Collections.emptyList();
    Books current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public BooksAdapter(Context context, List<Books> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_books, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        Books current=data.get(position);
        myHolder.id.setText("ID : " + current.id);
        myHolder.idedit.setText("ID : " + current.id);
        myHolder.bookname.setText("Book: " + current.bookName);
        myHolder.authorname.setText("Author: " + current.authorName);
        myHolder.stock.setText("Stock: " + current.stock);
        myHolder.price.setText("Rs. " + current.price);
        myHolder.price.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        // load image into imageview using glide
       /* Glide.with(context).load("http://192.168.1.7/test/images/" + current.fishImage)
                .placeholder(R.drawable.ic_img_error)
                .error(R.drawable.ic_img_error)
                .into(myHolder.ivFish);*/

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
    EditText idedit;
        TextView bookname;
        ImageView ivFish;
        TextView authorname;
        TextView price;
        TextView stock;
        TextView id;
        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            idedit = (EditText) itemView.findViewById(R.id.idedit);
            bookname= (TextView) itemView.findViewById(R.id.bookname);
           // ivFish= (ImageView) itemView.findViewById(R.id.ivFish);
            authorname = (TextView) itemView.findViewById(R.id.authorname);
            price = (TextView) itemView.findViewById(R.id.price);
            stock = (TextView) itemView.findViewById(R.id.stock);
        }

    }


}
