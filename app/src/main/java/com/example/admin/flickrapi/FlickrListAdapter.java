package com.example.admin.flickrapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.flickrapi.model.Item;

import java.util.List;

/**
 * Created by Admin on 10/13/2017.
 */

public class FlickrListAdapter extends RecyclerView.Adapter<FlickrListAdapter.ViewHolder>  {

    List<Item> items;
    Context context;
    private final OnItemClickListener listener;

    public FlickrListAdapter(List<Item> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public FlickrListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        View view = LayoutInflater
                .from( parent.getContext() )
                .inflate( R.layout.flickr_list_item, parent, false );

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(FlickrListAdapter.ViewHolder holder, int position) {
        Item f = items.get( position );

        Glide.with( context )
                .load( f.getMedia().getM() )
                .into( holder.image );

        holder.imageTitle.setText( f.getTitle() );
        holder.imageAuthor.setText( f.getAuthor() );

        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView imageTitle, imageAuthor;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById( R.id.ivImage );
            imageTitle = itemView.findViewById( R.id.tvImageTitle );
            imageAuthor = itemView.findViewById( R.id.tvImageAuthor );
        }

        public void bind(final Item item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick( Item item);
    }
}
