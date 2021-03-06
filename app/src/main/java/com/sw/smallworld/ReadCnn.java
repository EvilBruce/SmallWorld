package com.sw.smallworld;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sw.smallworld.adapter.MyAdapter;
import com.sw.smallworld.model.FeedItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by wdw88_000 on 7/26/2016.
 */
public class ReadCnn extends AsyncTask<Void,Void,Void>{
    Context context;
    ProgressDialog progressDialog;

    URL url;
    ArrayList<FeedItem> feedItems;
    RecyclerView recyclerView;

    public String address;

    public ReadCnn(Context context, RecyclerView recyclerView){
        this.recyclerView = recyclerView;
        this.context=context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();

    }
    @Override
    protected void onPostExecute(Void avoid) {
        super.onPostExecute(avoid);
        progressDialog.dismiss();
        MyAdapter adapter= new MyAdapter(context,feedItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected Void doInBackground(Void... params) {
        ProcessXml(GetData());
        return null;
    }

    private void ProcessXml(Document data) {
        if(data!=null) {
            feedItems = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();
            for(int i=0;i<items.getLength();i++){
                Node currentchild = items.item(i);
                if(currentchild.getNodeName().equalsIgnoreCase("item")){
                    FeedItem item = new FeedItem();
                    NodeList itemChilds = currentchild.getChildNodes();
                    for(int j = 0;j<itemChilds.getLength();j++){
                        Node current = itemChilds.item(j);
                        if(current.getNodeName().equalsIgnoreCase("title")){
                            item.setTitle(current.getTextContent());
                        }else  if(current.getNodeName().equalsIgnoreCase("description")){
                            item.setDescription(current.getTextContent());
                        }else  if(current.getNodeName().equalsIgnoreCase("pubDate")){
                            item.setPubDate(current.getTextContent());
                        }else  if(current.getNodeName().equalsIgnoreCase("link")){
                            item.setLink(current.getTextContent());
                        }else if(current.getNodeName().equalsIgnoreCase("media:content")){
                            String url = current.getAttributes().item(0).getTextContent();
                        item.setThumbnailUrl(url);}

                    }
                    feedItems.add(item);


                }
            }
        }
    }

    public Document GetData(){
        try{
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream =connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc =  builder.parse(inputStream);
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
