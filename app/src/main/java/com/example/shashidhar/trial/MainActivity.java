package com.example.shashidhar.trial;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private FetchFeedTask Feed;
    private EditText mEditText;
    private Button mFetchFeedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Feed = new FetchFeedTask();
        Feed.mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mEditText = (EditText) findViewById(R.id.rssFeedEditText);
        mFetchFeedButton = (Button) findViewById(R.id.SearchButton);
        Feed.mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        Feed.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFetchFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Feed.SearchKey=mEditText.getText().toString();
                Feed.search(Feed.SearchKey);

            }
        });

        Feed.mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new FetchFeedTask().execute((Void) null);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Top_Stories:
                Feed.FeedUrl = new String("feeds.bbci.co.uk/news/rss.xml");
                Feed.execute((Void) null);
                return true;
            case R.id.World:
                Feed.FeedUrl  = new String("feeds.bbci.co.uk/news/world/rss.xml");
                Feed.execute((Void) null);
                return true;
            case R.id.Europe:
                Feed.FeedUrl  = new String("feeds.bbci.co.uk/news/world/europe/rss.xml");
                Feed.execute((Void) null);
                return true;
            case R.id.Business:
                Feed.FeedUrl  = new String("feeds.bbci.co.uk/news/business/rss.xml");
                Feed.execute((Void) null);
                return true;
            case R.id.Politics:
                Feed.FeedUrl = new String("feeds.bbci.co.uk/news/politics/rss.xml");
                Feed.execute((Void) null);
                return true;
            case R.id.Health:
                Feed.FeedUrl = new String("feeds.bbci.co.uk/news/health/rss.xml");
                Feed.execute((Void) null);
                return true;
            case R.id.Technology:
                Feed.FeedUrl  = new String("feeds.bbci.co.uk/news/technology/rss.xml");
                Feed.execute((Void) null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}