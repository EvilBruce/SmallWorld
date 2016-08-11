package com.sw.smallworld.TwitterTrend;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrendsActivity extends ListActivity {

    ArrayList<String> names = new ArrayList<String>();;//store top 10 hashtags
    ArrayList<Integer> volumnArray = new ArrayList<Integer>();//store each volumn

    String[] values = new String[50];
    int[] volumns = new int[50];
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        getTwitterHashTagsVolumn();//get hashtags and volumn
        // use your custom layout

    }

    ArrayList<TwitterTrend> trends;
    private void getTwitterHashTagsVolumn() {
        String twitterUrl = "https://api.twitter.com/1.1/trends/place.json?id=23424977";//Twitter Trends Api
        Map<String, String> myheaders = new HashMap();
        myheaders.put("Authorization", "OAuth oauth_consumer_key=\"FYIsuYJoCOqZIsXSzRQbwMaj5\", oauth_nonce=\"e1b47e0d19efabb3d7d97555343512bc\", oauth_signature=\"lKqakwn2zL0FQHqSCNvFzFKgm2E%3D\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1470888145\", oauth_token=\"1567541287-1xK5iB0v4yiD4WHIXEtdeyuUvpFDmFvrhdQqHAg\", oauth_version=\"1.0\"");
        GsonRequest<TrendArray[]> myReq = new GsonRequest<TrendArray[]>(
                twitterUrl,
                TrendArray[].class,
                myheaders) {

            @Override
            protected void deliverResponse(TrendArray[] response, boolean isFromCache) {
                Log.e("shaneTest", "success = ");

                for (TwitterTrend t: response[0].trends) {
                    //get each hashtag and its volumn
                    names.add(t.getName());
                    volumnArray.add(t.getTwitter_volumn());
                }

                values = names.toArray(new String[names.size()]);// arraylist<string> to string[]
//                Log.e("Nan1", String.valueOf(values[0]));
//                Log.e("Nan2", String.valueOf(volumnArray));

                int i = 0;
                for (Integer volumn : volumnArray) {
                    volumns[i] = volumn;
                    i++;
                }

//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TrendsActivity.this,
//                        R.layout.activity_trends, R.id.hashTag, values);
//                setListAdapter(adapter);

                TwitterTrendsAdapter adapter=new TwitterTrendsAdapter(TrendsActivity.this, values, volumns);
                setListAdapter(adapter);
            }

            @Override
            public void deliverError(VolleyError error, TrendArray[] cachedResponse) {
                Log.e("shaneTest", "error = " + error.getMessage());
                //Toast.makeText(getApplicationContext(), "xxxxxxxxx", Toast.LENGTH_LONG).show();
            }
        }.sendRequest(this);


    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
        Intent eachHashTag = new Intent(TrendsActivity.this, twitterSearch.class);
        eachHashTag.putExtra("hashtag", item);
        //if(item!=null) Toast.makeText(TrendsActivity.this, item, Toast.LENGTH_SHORT).show();;
        startActivity(eachHashTag);
    }
}
