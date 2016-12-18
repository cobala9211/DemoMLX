package com.example.dnp.demoxml.sax;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DNP on 12/17/2016.
 */

public class XmlHandlerSax extends DefaultHandler {
    private boolean mIsCurrentElement = false;
    //item root
    private static final String ITEM = "item";
    private static final String CHANNEL = "channel";
    //item header
    private static final String LINK = "link";
    private static final String TITLE = "title";
    private static final String PUP_DATE = "pubDate";
    //item detail
    private static final String LINKS = "link";
    private static final String TITLES = "title";
    private static final String IS_GUIDS = "guid";
    private static final String PUP_DATES = "pubDate";
    private static final String DESCRIPTIONS = "description";

    public ItemSax mItemSax;
    private String mCurrentValue = "";
    private ItemSaxDetail mItemSaxDetail;
    private List<ItemSaxDetail> mListItem = new ArrayList<>();
    private ItemSaxObject mItemObject;
    private boolean mIsItems = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        mIsCurrentElement = true;
        if (qName.equals(CHANNEL)) {
            mItemSax = new ItemSax();
            mIsItems = false;
        } else if (qName.equals(ITEM)) {
            mItemSaxDetail = new ItemSaxDetail();
            mIsItems = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        mIsCurrentElement = false;
        if (!mIsItems) {
            //get data from header
            if (qName.equalsIgnoreCase(TITLE)) {
                mItemSax.setTitle(mCurrentValue.trim());
            } else if (qName.equalsIgnoreCase(LINK)) {
                mItemSax.setLink(mCurrentValue.trim());
            } else if (qName.equalsIgnoreCase(PUP_DATE)) {
                mItemSax.setPupDate(mCurrentValue.trim());
            }
        } else {
            //get data from body
            if (qName.equals(TITLES)) {
                mItemSaxDetail.setTitles(mCurrentValue.trim());
            } else if (qName.equals(LINKS)) {
                mItemSaxDetail.setLinks(mCurrentValue.trim());
            } else if (qName.equals(PUP_DATES)) {
                mItemSaxDetail.setPubDates(mCurrentValue.trim());
            } else if (qName.equals(IS_GUIDS)) {
                mItemSaxDetail.setGuids(mCurrentValue.trim().equals("true") ? true : false);
            } else if (qName.equals(DESCRIPTIONS)) {
                mItemObject = new ItemSaxObject();
                Log.d("TAG11", "endElement: " + mCurrentValue.trim());
                try {
                    //get data html inside item
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(new StringReader(mCurrentValue.trim()));
                    int eventType = xpp.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if (eventType == XmlPullParser.START_DOCUMENT) {
                            Log.d("TAG11", "Start document");
                        } else if (eventType == XmlPullParser.START_TAG) {
                            //get tag html <a/>
                            if (xpp.getName().equals("a")) {
                                mItemObject.setLink(xpp.getAttributeValue(null, "href"));
                                mItemObject.setTitle(xpp.getAttributeValue(null, "title"));
                                Log.d("TAG11", "Start tag: " + xpp.getAttributeValue(null, "href"));
                                Log.d("TAG11", "Start title " + xpp.getAttributeValue(null, "title"));
                            } else if (xpp.getName().equals("img")) {
                                //get tag html <img/>
                                mItemObject.setImg(xpp.getAttributeValue(null, "img"));
                                Log.d("TAG11", "Start src " + xpp.getAttributeValue(null, "src"));
                                Log.d("TAG11", "Start width " + xpp.getAttributeValue(null, "width"));
                                Log.d("TAG11", "Start border " + xpp.getAttributeValue(null, "border"));
                                Log.d("TAG11", "Start alt " + xpp.getAttributeValue(null, "alt"));
                            }
                        } else if (eventType == XmlPullParser.TEXT) {
                            //get text string <span>test</span>
                            mItemObject.setDescription(xpp.getText());
                            Log.d("TAG11", "Text: " + xpp.getText());
                        }
                        mItemSaxDetail.setDescriptions(mItemObject);
                        eventType = xpp.next();
                    }
                    Log.d("TAG11", "endElement: ------------------------------------------");
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mListItem.add(mItemSaxDetail);
            }
        }
        mCurrentValue = "";
        mItemSax.setListDetail(mListItem);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        // get values item
        if (mIsCurrentElement) {
            mCurrentValue = mCurrentValue + new String(ch, start, length);
        }
    }
}
