package com.example.dnp.demoxml.dom;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dnp.demoxml.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DNP on 12/14/2016.
 */

public class DomActivity extends AppCompatActivity {

    public static final String NODE_ROOT = "PurchaseOrder";
    public static final String ORDER_ID = "OrderId";
    public static final String CUSTOMER_ID = "CustomerId";
    public static final String EMAIL = "Email";
    private static final String TAG = "DomActivity";
    private List<ItemDetail> mListItems = new ArrayList<>();
    private DomItem mItemDom;
    private DomAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dom);
        getDataXml();
        initView();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleViewDom);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        mAdapter = new DomAdapter(this, mItemDom);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void getDataXml() {
        //get data from xml with dom on android
        DomUtil domUtil = new DomUtil();
        mItemDom = new DomItem();
        AssetManager manager = getAssets();
        try {
            InputStream input = manager.open("DomData.xml");
            Document document = domUtil.getDocument(input);
            NodeList nodeList = document.getElementsByTagName(NODE_ROOT);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                //set dom item
                mItemDom.setCartId(domUtil.getValue(element, ORDER_ID));
                mItemDom.setCustomerId(domUtil.getValue(element, CUSTOMER_ID));
                mItemDom.setEmail(domUtil.getValue(element, EMAIL));
                //get list product
                NodeList nodeItem = element.getChildNodes();
                for (int j = 0; j < nodeItem.getLength(); j++) {
                    Node nodeItemDetail = nodeItem.item(j);
                    if (nodeItemDetail instanceof Element) {
                        Element eProduct = (Element) nodeItemDetail;
                        String lineNumber = domUtil.getValue(eProduct, "LineNumber");
                        String product = domUtil.getValue(eProduct, "ProductSku");
                        String quantity = domUtil.getValue(eProduct, "Quantity");
                        String price = domUtil.getValue(eProduct, "Price");
                        ItemDetail item = new ItemDetail(lineNumber, product, quantity, price);
                        mListItems.add(item);
                        Log.d(TAG, "getDataXml: " + lineNumber + " product " + product);
                    }
                }
                mItemDom.setListItems(mListItems);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
