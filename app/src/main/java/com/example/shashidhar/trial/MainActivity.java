package com.example.shashidhar.trial;

import android.os.AsyncTask;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private Button mFetchFeedButton;
    public  RecyclerView mRecyclerView;
    public SwipeRefreshLayout mSwipeLayout;

    public List<RssFeedModel> mFeedModelList;
    private List<RssFeedModel> searchList;

    public String FeedUrl;
    public String SearchKey;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mEditText = (EditText) findViewById(R.id.rssFeedEditText);
        mFetchFeedButton = (Button) findViewById(R.id.SearchButton);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFetchFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchKey=mEditText.getText().toString();
               search(SearchKey);

            }
        });

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
                FeedUrl = new String("feeds.bbci.co.uk/news/rss.xml");
                new FetchFeedTask().execute((Void) null);
                return true;
            case R.id.World:
                FeedUrl  = new String("feeds.bbci.co.uk/news/world/rss.xml");
                new FetchFeedTask().execute((Void) null);
                return true;
            case R.id.Europe:
                FeedUrl  = new String("feeds.bbci.co.uk/news/world/europe/rss.xml");
                new FetchFeedTask().execute((Void) null);
                return true;
            case R.id.Business:
                FeedUrl  = new String("feeds.bbci.co.uk/news/business/rss.xml");
                new FetchFeedTask().execute((Void) null);
                return true;
            case R.id.Politics:
                FeedUrl = new String("feeds.bbci.co.uk/news/politics/rss.xml");
                new FetchFeedTask().execute((Void) null);
                return true;
            case R.id.Health:
                FeedUrl = new String("feeds.bbci.co.uk/news/health/rss.xml");
                new FetchFeedTask().execute((Void) null);
                return true;
            case R.id.Technology:
                FeedUrl  = new String("feeds.bbci.co.uk/news/technology/rss.xml");
                new FetchFeedTask().execute((Void) null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void search(String searchKey) {
        searchList = new ArrayList<>();
        //searchList=null;
        for(RssFeedModel search:mFeedModelList)
        {
            if(search.title.contains(SearchKey))
            {
                searchList.add(search);
            }
        }
        if(searchList.size()!=0)
             mRecyclerView.setAdapter(new RssFeedListAdapter(searchList));
        else
            Toast.makeText(MainActivity.this,"News does'nt contains such keyword.Try with new Keyword",Toast.LENGTH_LONG).show();
    }


    public class FetchFeedTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            mSwipeLayout.setRefreshing(true);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            if (TextUtils.isEmpty(FeedUrl))
                return false;

            try {
                if (!FeedUrl.startsWith("http://") && !FeedUrl.startsWith("https://"))
                    FeedUrl = "http://" + FeedUrl;

                URL url = new URL(FeedUrl);
                InputStream inputStream = url.openConnection().getInputStream();
                ParserXml xmlparse=new ParserXml();

                mFeedModelList = xmlparse.parseFeed(inputStream);
                return true;
            } catch (IOException e) {
                Log.e("Error", e.getMessage());
            } catch (XmlPullParserException e) {
                Log.e("Error", e.getMessage());
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            mSwipeLayout.setRefreshing(false);

            if (success) {
                // Fill RecyclerView
                mRecyclerView.setAdapter(new RssFeedListAdapter(mFeedModelList));
            }
            /*else {
               Toast.makeText(MainActivity.this,"Enter a valid Rss feed url",Toast.LENGTH_LONG).show(); */

        }


    }

}