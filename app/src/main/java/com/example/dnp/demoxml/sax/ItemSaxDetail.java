package com.example.dnp.demoxml.sax;

/**
 * Created by DNP on 12/17/2016.
 */

public class ItemSaxDetail {
    private String links;
    private String titles;
    private boolean isGuids;
    private String pubDates;
    private ItemSaxObject descriptions;

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public boolean isGuids() {
        return isGuids;
    }

    public void setGuids(boolean guids) {
        isGuids = guids;
    }

    public String getPubDates() {
        return pubDates;
    }

    public void setPubDates(String pubDates) {
        this.pubDates = pubDates;
    }

    public ItemSaxObject getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(ItemSaxObject descriptions) {
        this.descriptions = descriptions;
    }
}
