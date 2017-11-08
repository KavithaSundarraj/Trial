package com.example.shashidhar.trial;

/**
 * Created by Shashidhar on 28-10-2017.
 */


import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;



public class RssFeedListAdapter
        extends RecyclerView.Adapter<RssFeedListAdapter.FeedModelViewHolder> {

    private List<RssFeedModel> mRssFeedModels;

    public static class FeedModelViewHolder extends RecyclerView.ViewHolder {
        private View rssFeedView;

        public FeedModelViewHolder(View v) {
            super(v);
            rssFeedView = v;
        }
    }

    public RssFeedListAdapter(List<RssFeedModel> rssFeedModels) {
        mRssFeedModels = rssFeedModels;
    }

    @Override
    public FeedModelViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rss_feed, parent, false);
        FeedModelViewHolder holder = new FeedModelViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(FeedModelViewHolder holder, int position) {
        final RssFeedModel rssFeedModel = mRssFeedModels.get(position);

        ((TextView)holder.rssFeedView.findViewById(R.id.titleText)).setText(rssFeedModel.title);
        ((TextView)holder.rssFeedView.findViewById(R.id.descriptionText)).setText(rssFeedModel.description);
        // To display URL as link
        final TextView myClickableUrl = (TextView) holder.rssFeedView.findViewById(R.id.linkText);
        myClickableUrl.setText(rssFeedModel.link);
        Linkify.addLinks(myClickableUrl, Linkify.WEB_URLS);
        //To display string as image
        ImageView bindImage = (ImageView)holder.rssFeedView.findViewById(R.id.imgurlText);
        String pathToFile = rssFeedModel.imgurl;
        URLToImage downloadTask = new URLToImage(bindImage);
        downloadTask.execute(pathToFile);
    }

    @Override
    public int getItemCount() {
        return mRssFeedModels.size();
    }
}

