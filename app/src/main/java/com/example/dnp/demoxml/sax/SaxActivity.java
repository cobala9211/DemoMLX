package com.example.dnp.demoxml.sax;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.dnp.demoxml.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DNP on 12/14/2016.
 */

public class SaxActivity extends AppCompatActivity {
    private ItemSax mItemSax;
    private ItemSaxObject mObject;
    private static final String TAG = "TAG12";
    private List<ItemSaxDetail> mListItemDetails = new ArrayList<>();
    private RecyclerView mRecyclerSax;
    private SaxAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sax);
        mRecyclerSax = (RecyclerView) findViewById(R.id.recyclerViewSax);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerSax.setLayoutManager(manager);
        mRecyclerSax.setHasFixedSize(true);
        new DownloadXML(this).execute("http://gamek.vn/mobile-social.rss");
    }

    public void setListData(ItemSax item) {
        mItemSax = item;
        // log header info
        Log.d(TAG, "setListData: title " + mItemSax.getTitle());
        Log.d(TAG, "setListData: link " + mItemSax.getLink());
        Log.d(TAG, "setListData: date " + mItemSax.getPupDate());
        // log item info
        mListItemDetails.addAll(mItemSax.getListDetail());
        for (int i = 0; i < mListItemDetails.size(); i++) {
            Log.d(TAG, "item detail : title " + mListItemDetails.get(i).getTitles());
            Log.d(TAG, "item detail : link " + mListItemDetails.get(i).getLinks());
            Log.d(TAG, "item detail : date " + mListItemDetails.get(i).getPubDates());
            mObject = mListItemDetails.get(i).getDescriptions();
            Log.d(TAG, "object item : title " + mObject.getTitle());
            Log.d(TAG, "object item : link " + mObject.getLink());
            Log.d(TAG, "object item : img " + mObject.getImg());
            Log.d(TAG, "object item : description " + mObject.getDescription());
        }
        mAdapter = new SaxAdapter(this, mItemSax);
        mRecyclerSax.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }
}
