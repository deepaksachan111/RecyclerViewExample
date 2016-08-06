package com.quaere.noida.recyclerviewexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {


    private List<FeedItem> feedItemList;

    private Context mContext;

    public MyRecyclerAdapter(Context context, List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        protected ImageView thumbnail;
        protected TextView title;

        public MyViewHolder(View view) {
            super(view);
            this.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            this.title = (TextView) view.findViewById(R.id.title);
        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        MyViewHolder mh = new MyViewHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int i) {
        final FeedItem feedItem = feedItemList.get(i);

        Picasso.with(mContext).load(feedItem.getThumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(viewHolder.thumbnail);
           viewHolder.thumbnail.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   FeedItem ff = (FeedItem)feedItemList.get(i);
                   String s = ff.getTitle();

                   Toast.makeText(v.getContext(), "Position: "+ s, Toast.LENGTH_LONG).show();

               }
           });


        if(feedItem.getTitle()== null){

            viewHolder.title.setText("NULL");
        }else {
            viewHolder.title.setText(Html.fromHtml(feedItem.getTitle()));
        }
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }
}
