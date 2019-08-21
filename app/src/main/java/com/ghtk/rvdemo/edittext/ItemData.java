package com.ghtk.rvdemo.edittext;

public class ItemData {
    private String mName;

    public ItemData(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public ItemData setName(String name) {
        mName = name;
        return this;
    }
}
