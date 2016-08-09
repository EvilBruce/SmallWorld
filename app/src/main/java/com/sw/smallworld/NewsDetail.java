package com.sw.smallworld;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetail extends AppCompatActivity {
    Context context;
    ImageView imageView;
    TextView title, description,date,link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        imageView = (ImageView) findViewById(R.id.imageView);
        title = (TextView) findViewById(R.id.title);
        description = (TextView)findViewById(R.id.description);
        date = (TextView) findViewById(R.id.pubdate);
        link = (TextView) findViewById(R.id.link);
        Picasso.with(context).load(getIntent().getStringExtra("Image")).into(imageView);
        title.setText(getIntent().getStringExtra("Title"));
        description.setText(getIntent().getStringExtra("Description"));
        date.setText(getIntent().getStringExtra("Date"));
        link.setText(getIntent().getStringExtra("Link"));


    }
}
