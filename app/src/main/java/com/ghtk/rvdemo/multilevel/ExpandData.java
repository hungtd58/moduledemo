package com.ghtk.rvdemo.multilevel;

import java.util.List;

public class ExpandData {
    private String mData;

    private List<ExpandData> mChildExpandData;

    public ExpandData(String data, List<ExpandData> childExpandData) {
        mData = data;
        mChildExpandData = childExpandData;
    }

    public String getData() {
        return mData;
    }

    public ExpandData setData(String data) {
        mData = data;
        return this;
    }

    public List<ExpandData> getChildExpandData() {
        return mChildExpandData;
    }

    public ExpandData setChildExpandData(List<ExpandData> childExpandData) {
        mChildExpandData = childExpandData;
        return this;
    }
}
