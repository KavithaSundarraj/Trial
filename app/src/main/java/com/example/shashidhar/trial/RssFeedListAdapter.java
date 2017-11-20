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

/**
 * View
 */

public class RssFeedListAdapter
        extends RecyclerView.Adapter<RssFeedListAdapter.FeedModelViewHolder> {

    private List<RssFeedModel> mRssFeedModels;

    /**
     * Inner Class
     */

    public static class FeedModelViewHolder extends RecyclerView.ViewHolder {
        private View rssFeedView;

        public FeedModelViewHolder(View v) {
            super(v);
            rssFeedView = v;
        }
    }

    /**
     * Creates a new RssFeedListAdapter
     * @param rssFeedModels
     */
    public RssFeedListAdapter(List<RssFeedModel> rssFeedModels) {
        mRssFeedModels = rssFeedModels;
    }

    /**
     * To create View Holder
     * @param parent
     * @param type
     * @return FeedModelViewHolder
     */
    @Override
    public FeedModelViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rss_feed, parent, false);
        FeedModelViewHolder holder = new FeedModelViewHolder(v);
        return holder;
    }

    /**
     * To diplay and bind the news details in view holder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(FeedModelViewHolder holder, int position) {

        final RssFeedModel rssFeedModel = mRssFeedModels.get(position);
        // To display Title
        ((TextView)holder.rssFeedView.findViewById(R.id.titleText)).setText(rssFeedModel.getTitle());

        //To display Description
        ((TextView)holder.rssFeedView.findViewById(R.id.descriptionText)).setText(rssFeedModel.getDescription());

        // To display URL as link
        final TextView myClickableUrl = (TextView) holder.rssFeedView.findViewById(R.id.linkText);
        myClickableUrl.setText(rssFeedModel.getLink());
        Linkify.addLinks(myClickableUrl, Linkify.WEB_URLS);

        //To display string as image
        ImageView bindImage = (ImageView)holder.rssFeedView.findViewById(R.id.imgurlText);
        String pathToFile = rssFeedModel.getImgurl();
        URLToImage downloadTask = new URLToImage(bindImage);
        downloadTask.execute(pathToFile);
    }

    /**
     * To calculate the size
     * @return int count of mRssFeedModels
     */
    @Override
    public int getItemCount() {
        return mRssFeedModels.size();
    }
}

