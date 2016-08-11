package com.sw.smallworld.TwitterTrend;

/**
 * Created by Nan on 8/5/2016.
 */
public class TwitterTrend {
    int tweet_volume;
    String name;

    public int getTwitter_volumn() {
        return tweet_volume;
    }

    public void setTwitter_volumn(int twitter_volumn) {
        this.tweet_volume = twitter_volumn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TwitterTrend() {
        this.toString();
    }
}
