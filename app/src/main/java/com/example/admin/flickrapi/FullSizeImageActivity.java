package com.example.admin.flickrapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FullSizeImageActivity extends AppCompatActivity {

    ImageView image;
    TextView title, link, mediaLink, dateTaken, description, published, author, authorId, tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_size_imag);

        image = findViewById( R.id.ivImage );
        title = findViewById( R.id.tvTitle );
        link = findViewById( R.id.tvLink );
        mediaLink = findViewById( R.id.tvMediaLink );
        dateTaken = findViewById( R.id.tvDateTaken );
        description = findViewById( R.id.tvDescription );
        published = findViewById( R.id.tvPublished );
        author = findViewById( R.id.tvAuthor );
        authorId = findViewById( R.id.tvAuthorId );
        tags = findViewById( R.id.tvTags );

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("imageURL") != null)
            Glide.with( this )
                    .load( bundle.getString("imageURL") )
                    .into( image );

        if(bundle.getString("title") != null)
            title.setText( bundle.getString("title") );

        if(bundle.getString("link") != null)
            link.setText( bundle.getString("link") );

        if(bundle.getString("mediaLink") != null)
            mediaLink.setText( bundle.getString("mediaLink") );

        if(bundle.getString("date") != null)
            dateTaken.setText( bundle.getString("date") );

        if(bundle.getString("description") != null)
            description.setText( bundle.getString("description") );

        if(bundle.getString("published") != null)
            published.setText( bundle.getString("published") );

        if(bundle.getString("author") != null)
            author.setText( bundle.getString("author") );

        if(bundle.getString("authorId") != null)
            authorId.setText( bundle.getString("authorId") );

        if(bundle.getString("tags") != null)
            tags.setText( bundle.getString("tags") );
    }
}
