package com.example.admin.flickrapi.data;

import com.example.admin.flickrapi.model.FlickrImage;
import com.example.admin.flickrapi.model.Item;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RemoteService {

    //http://api.flickr.com/services/feeds/photos_public.gne?tag=kitten&format=json&nojsoncallback=1
    //@GET("services/feeds/photos_public.gne?tag=kitten&format=json&nojsoncallback=1")
    @GET("photos_public.gne?tag=kitten&format=json&nojsoncallback=1")
    Observable<FlickrImage> getFlickrImages();
}
