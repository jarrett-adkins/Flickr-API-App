package com.example.admin.flickrapi.data;

import com.example.admin.flickrapi.model.FlickrImage;
import com.example.admin.flickrapi.model.Item;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RemoteService {

    @GET("photos_public.gne?tag=kitten&format=json&nojsoncallback=1")
    Observable<FlickrImage> getFlickrImages();
}
