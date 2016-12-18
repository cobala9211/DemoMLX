package com.example.dnp.demoxml.sax;

import java.util.List;

/**
 * Created by DNP on 12/17/2016.
 */

public class ItemSax {
    private String title;
    private String link;
    private String pupDate;
    private List<ItemSaxDetail> listDetail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPupDate() {
        return pupDate;
    }

    public void setPupDate(String pupDate) {
        this.pupDate = pupDate;
    }

    public List<ItemSaxDetail> getListDetail() {
        return listDetail;
    }

    public void setListDetail(List<ItemSaxDetail> listDetail) {
        this.listDetail = listDetail;
    }
}
