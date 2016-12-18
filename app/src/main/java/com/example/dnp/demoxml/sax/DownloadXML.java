package com.example.dnp.demoxml.sax;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by DNP on 12/17/2016.
 */

public class DownloadXML extends AsyncTask<String, Void, Void> {
    private SaxActivity mContext;
    private ProgressDialog mDialog;
    private XmlHandler handler;

    public DownloadXML(SaxActivity context) {
        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //create progress dialog
        mDialog = new ProgressDialog(mContext);
        mDialog.setTitle("Parse dialog sax xml in android");
        mDialog.setMessage("Loading ...");
        mDialog.setIndeterminate(false);
        mDialog.show();
    }

    @Override
    protected Void doInBackground(String... url) {
        try {
            //get link xml from internet
            InputStream urlXml = new URL(url[0]).openStream();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();

            //create object data xml
            handler = new XmlHandler();
            reader.setContentHandler(handler);
            InputSource input = new InputSource(urlXml);
            reader.parse(input);
            Log.d("TAG11", "doInBackground: ");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mDialog.dismiss();
        ((SaxActivity) mContext).setListData(handler.mItemSax);
    }
}
