package com.ezgroceries.shoppinglist.models;

import java.util.ArrayList;
import java.util.List;

public class Resources<Resource> extends ArrayList<Resource> {
    private List<Resource> resourceList;

    public Resources(List resourceList) {
        this.resourceList = resourceList;
    }
}
