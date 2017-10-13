package com.example.admin.flickrapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.admin.flickrapi.data.RemoteDataSource;
import com.example.admin.flickrapi.model.FlickrImage;
import com.example.admin.flickrapi.model.Item;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    RecyclerView recyclerView;
    List<Item> flickrList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private DividerItemDecoration dividerItemDecoration;
    private FlickrListAdapter flickrListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the views
        recyclerView = findViewById( R.id.rvFlickrList );
        layoutManager = new LinearLayoutManager( this );
        itemAnimator = new DefaultItemAnimator();
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);

        RemoteDataSource.getFlickrImages()
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribeOn(  Schedulers.io() )
                .subscribe(new Observer<FlickrImage>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull FlickrImage flickrImage) {
                            Log.d(TAG, "onNext: ");
                            flickrList = flickrImage.getItems();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.d(TAG, "onError: " + e.toString());
                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG, "onComplete: ");

                            recyclerView.setAdapter( new FlickrListAdapter( flickrList,
                                    new FlickrListAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Item item) {
                                    //Log.d(TAG, "onItemClick: " + item.getTitle());
                                    goToActiviy( item );
                                }
                            }) );

                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(itemAnimator);
                            recyclerView.addItemDecoration(dividerItemDecoration);
                        }
                });
    }

    public void goToActiviy( Item item ) {
        Intent intent = new Intent ( this, FullSizeImageActivity.class );

        intent.putExtra( "imageURL", item.getMedia().getM() );
        intent.putExtra( "title", item.getTitle() );
        intent.putExtra( "link", item.getLink() );
        intent.putExtra( "mediaLink", item.getMedia().getM() );
        intent.putExtra( "date", item.getDateTaken() );
        intent.putExtra( "description", item.getDescription() );
        intent.putExtra( "published", item.getPublished() );
        intent.putExtra( "author", item.getAuthor() );
        intent.putExtra( "authorId", item.getAuthorId() );
        intent.putExtra( "tags", item.getTags() );

        startActivity( intent );
    }
}
