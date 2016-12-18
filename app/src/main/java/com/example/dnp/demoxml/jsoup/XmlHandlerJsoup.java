package com.example.dnp.demoxml.jsoup;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by DNP on 12/18/2016.
 */

public class XmlHandlerJSoup extends AsyncTask<String, Void, Void> {
    private Context mContext;
    private ProgressDialog mDialog;

    public XmlHandlerJSoup(Context context) {
        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDialog = new ProgressDialog(mContext);
        mDialog.setTitle("Parsing xml android by jsoup");
        mDialog.setMessage("Loading...");
        mDialog.setIndeterminate(true);
        mDialog.show();
    }

    @Override
    protected Void doInBackground(String... url) {
        Document document = null;
        try {
            document = (Document) Jsoup.connect(url[0]).get();
            if (document != null) {
                //get gallery-time
                Elements subElements = document.select("div.gallery-item");
                if (subElements != null && subElements.size() > 0) {
                    for (Element element : subElements) {
                        Element titleSubject = element.getElementsByTag("h3").first();
                        Element imgSubject = element.getElementsByTag("img").first();
                        if (titleSubject != null) {
                            String title = titleSubject.text();
                            Log.d("TAG111", "doInBackground: " + title);
                        }
                        if (imgSubject != null) {
                            String src = imgSubject.attr("src");
                            Log.d("TAG111", "doInBackground: " + src);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mDialog.dismiss();
    }
}
