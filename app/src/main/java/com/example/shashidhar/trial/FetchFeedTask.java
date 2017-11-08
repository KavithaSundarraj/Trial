package com.example.shashidhar.trial;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmp-sda-1124 on 11/8/17.
 */

   public class FetchFeedTask extends AsyncTask<Void, Void, Boolean> {

   public  RecyclerView mRecyclerView;
   public SwipeRefreshLayout mSwipeLayout;

    private List<RssFeedModel> mFeedModelList;

    private List<RssFeedModel> searchList;
    public String FeedUrl;
    public String SearchKey;
   // private String urlLink;



        public void search(String searchKey) {
        searchList = new ArrayList<>();
        for(RssFeedModel search:mFeedModelList)
        {
            if(search.title.contains(SearchKey))
            {
                searchList.add(search);
            }
        }
        mRecyclerView.setAdapter(new RssFeedListAdapter(searchList));
        }



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
                //Log.e(TAG, "Error", e);
            } catch (XmlPullParserException e) {
                //Log.e(TAG, "Error", e);
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
            //** //*
            /*else {
               Toast.makeText(MainActivity.this,
                        "Enter a valid Rss feed url",
                        Toast.LENGTH_LONG).show(); */
            }


    }

