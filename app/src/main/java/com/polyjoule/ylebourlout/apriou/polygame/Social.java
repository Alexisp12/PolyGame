package com.polyjoule.ylebourlout.apriou.polygame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;


/**
 * Created by Alexis on 08/01/2018.
 */



public class Social extends Activity {
    /**developer account key for this app*/
    public final static String _consumerKey = "tnPpEIGRkNECX9AWi4AnQsUrM";
    /**developer secret for the app*/
    public final static String _consumerSecret = "cHJ1AGrqNrr33MTUlSfEM5jVW6OcZUJwhNBwd8fb1lrN512ZAg";
    /**app url*/
    public final static String TWIT_URL = "tnice-android:///";
    /*Token*/
    public final static String _accessToken = "574483380-Gor7Bt8ZtfxJiCjuZzKPRksJP0okUnYGY2kjlbI3";
    public final static String _accessTokenSecret = "aXleRgd5m3SW0hw1dClf7hCzLCDqoXD98eekbMqzausjX";

    /*Twitter instance*/
    private Twitter niceTwitter;
    /**request token for accessing user account*/
    private RequestToken niceRequestToken;
    /**shared preferences to store user details*/
    private SharedPreferences nicePrefs;

    private String TWEETSETS="TwitNicePrefs";

    //for error logging
    private String LOG_TAG = "SocialActivity";//alter for your Activity name

    private List<String> listPseudo;
    private List<String> listStatus;
    private List<String> listSource;
    private List<String> listPP;
    private List<Bitmap> listBitmap;
    private ListView tweetListView;

    private TwitterStream twitterStream;
    private Context mThis;
    private Thread thread;
    private Thread thread2;
    private RelativeLayout tweetRL;
    private URL urlpp;
    private Boolean seekPP=false,seekStatus=false;

    private int startUrl=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_layout);

        tweetListView = (ListView) findViewById(R.id.listViewTweet);
        final RelativeLayout tweetRl = (RelativeLayout) findViewById(R.id.tweetRL);

        mThis= this;

        listPseudo = new ArrayList<String>();
        listStatus = new ArrayList<String>();
        listSource = new ArrayList<String>();
        listBitmap = new ArrayList<Bitmap>();
        listPP = new ArrayList<String>();

        tweetRl.setOnClickListener(new View.OnClickListener(){
            public void onClick (View tweetView){
                if(seekPP && seekStatus) {
                    TweetAdaptateur adapter = new TweetAdaptateur(mThis, listPseudo, listStatus, listBitmap);
                    tweetListView.setAdapter(adapter);


                    tweetListView.setOnItemClickListener(new ListView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                            Log.d("StatusClicked", listStatus.get(pos));
                            Log.d("UrlClicked", listSource.get(pos));

                            String url = listSource.get(pos);


                            //// Ouvre une page web
                            //Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
                            //startActivity(intent);

                            Intent tweetIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            ///tweetIntent.putExtra(Intent.EXTRA_TEXT, "This is a Test.");
                            //tweetIntent.setType("text/plain");


                            PackageManager packManager = getPackageManager();
                            List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);

                            boolean resolved = false;
                            for (ResolveInfo resolveInfo : resolvedInfoList) {
                                if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
                                    tweetIntent.setClassName(
                                            resolveInfo.activityInfo.packageName,
                                            resolveInfo.activityInfo.name);
                                    resolved = true;
                                    break;
                                }
                            }
                            if (resolved) {
                                startActivity(tweetIntent);
                            } else {
                                Toast.makeText(mThis, "Twitter app isn't found", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(intent);
                            }
                        }
                    });
                }

            }
        });

//        listPseudo.add("@pseudo1");
//        listPseudo.add("@pseudo2");
//        listPseudo.add("@pseudo3");
//
//        listStatus.add("Mon pseudo est pseudo1");
//        listStatus.add("Mon pseudo est pseudo2");
//        listStatus.add("Mon pseudo est pseudo3");



        //twitter = TwitterFactory.getSingleton();


        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
                    configurationBuilder.setOAuthConsumerKey(_consumerKey)
                            .setOAuthConsumerSecret(_consumerSecret)
                            .setOAuthAccessToken(_accessToken)
                            .setOAuthAccessTokenSecret(_accessTokenSecret);
                    // The factory instance is re-useable and thread safe.
                    TwitterFactory tf = new TwitterFactory(configurationBuilder.build());

                    Twitter twitter = tf.getInstance();

                    List<Status> statuses = null;

                    //statuses = twitter.getHomeTimeline();
                    //statuses = twitter.getRetweetsOfMe();
                    statuses = twitter.getUserTimeline();

                    //System.out.println("Showing home timeline.");

                    for (Status status : statuses) {
                        Log.d("UserName",status.getUser().getName());
                        Log.d("status",status.getText());
                        if(listPseudo.size()<statuses.size()-1) { //nb tweet à afficher
                            listPseudo.add("@" + status.getUser().getName());
                            listStatus.add(status.getText());
                            listPP.add(status.getUser().getOriginalProfileImageURL());
                            Log.d("listPP",status.getUser().getBiggerProfileImageURL());

                            try {
                                urlpp = new URL(status.getUser().getMiniProfileImageURL());
                            } catch (MalformedURLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            thread2 = new Thread(new Runnable() {

                                @Override
                                public void run() {
                                    //Bitmap bitmap = null;
                                    try {
                                        HttpURLConnection connection = (HttpURLConnection) urlpp.openConnection();
                                        InputStream inputStream = connection.getInputStream();
                                        listBitmap.add(BitmapFactory.decodeStream(inputStream));
                                    } catch (MalformedURLException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    seekPP=true;
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            tweetRl.performClick();
                                        }
                                    });
                                }
                            });
                            thread2.start();

                            for(int i =0; i<status.getText().length()-10;i++){
                                //Log.d("charAt "+i, status.getText().substring(i,i+1));
                                if("https://t".equals(status.getText().substring(i,i+9))){
                                    startUrl=i;
                                }
                            }
                            Log.d("startUrl",Integer.toString(startUrl));
                            listSource.add(status.getText().substring(startUrl));

                            //status.getUser().
                        }
                    }
                    seekStatus=true;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tweetRl.performClick();
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
                //updateTweet();
            }
        });

        thread.start();









//        twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();
//
//        twitterStream.addListener(new StatusListener() {
//            @Override
//            public void onException(Exception e) {
//
//            }
//
//            public void onStatus(Status status) {
//                //listPseudo.clear();
//                //listStatus.clear();
//
//                listPseudo.add(status.getUser().getScreenName().toString());
//                listStatus.add(status.getText().toString());
//                Log.d("pseudo", status.getUser().getScreenName().toString()); // print tweet text to console
//
//                Log.d("tweet", status.getText().toString()); // print tweet text to console
//
//               // updateTweet();
//            }
//
//            @Override
//            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
//
//            }
//
//            @Override
//            public void onTrackLimitationNotice(int i) {
//
//            }
//
//            @Override
//            public void onScrubGeo(long l, long l1) {
//
//            }
//
//            @Override
//            public void onStallWarning(StallWarning stallWarning) {
//
//            }
//        });
//
//        FilterQuery tweetFilterQuery = new FilterQuery(); // See
//        tweetFilterQuery.track(new String[]{"harry potter"}); // OR on keywords
////        tweetFilterQuery.locations(new double[][]{new double[]{-126.562500, 30.448674},
////                new double[]{-61.171875, 44.087585
////                }}); // See https://dev.twitter.com/docs/streaming-apis/parameters#locations for proper location doc.
////        //Note that not all tweets have location metadata set.
////        tweetFilterQuery.language(new String[]{"fr"}); // Note that language does not work properly
//        twitterStream.filter(tweetFilterQuery);

        //TweetAdaptateur adapter = new TweetAdaptateur(this,listPseudo,listStatus);
        //tweetListView.setAdapter(adapter);

    }


    @Override
    public void onBackPressed() {
        //twitterStream.clearListeners();

        this.finish();

        Intent retourMenuIntent = new Intent(Social.this, Accueil.class);

        startActivity(retourMenuIntent);
    }




}
