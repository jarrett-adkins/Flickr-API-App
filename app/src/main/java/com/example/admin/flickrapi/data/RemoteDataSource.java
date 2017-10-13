package com.example.admin.flickrapi.data;

import com.example.admin.flickrapi.model.FlickrImage;
import com.example.admin.flickrapi.model.Item;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {

    public static final String BASE_URL = "http://api.flickr.com/services/feeds/";

    public static Retrofit create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
                .build();

        return retrofit;
    }

    public static Observable<FlickrImage> getFlickrImages() {
        Retrofit retrofit = create();
        RemoteService remoteService = retrofit.create( RemoteService.class );

        return remoteService.getFlickrImages();
    }
}