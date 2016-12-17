package com.example.dnp.demoxml.dom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DNP on 12/14/2016.
 */

public class DomItem {
    String cartId;
    String customerId;
    String email;
    List<ItemDetail> listItems = new ArrayList<>();

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ItemDetail> getListItems() {
        return listItems;
    }

    public void setListItems(List<ItemDetail> listItems) {
        this.listItems = listItems;
    }
}
