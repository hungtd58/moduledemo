package com.ghtk.rvdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.ghtk.rvdemo.edittext.EditTextAdapter;
import com.ghtk.rvdemo.edittext.ItemData;
import com.ghtk.rvdemo.multilevel.ExpandData;
import com.ghtk.rvdemo.multilevel.ExpandableAdapter;
import com.ghtk.rvdemo.rvinrv.RvInRvAdapter;
import com.ghtk.rvdemo.swipedrag.ItemTouchHelperCallback;
import com.ghtk.rvdemo.swipedrag.SwipeDragAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.data_rv)
    RecyclerView mDataRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                return i % 3 == 2 ? 2 : 1;
            }
        });

        StaggeredGridLayoutManager stagGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        initRecyclerView(linearLayoutManager);

        initData(3);
    }

    private void initRecyclerView(RecyclerView.LayoutManager layoutManager) {
        mDataRv.setLayoutManager(layoutManager);

//        mDataRv.setHasFixedSize(true);
        mDataRv.setClipToPadding(true);
    }

    private void initData(int demo) {
        if (3 != demo) {
            mDataRv.setNestedScrollingEnabled(false);
        } else {
            mDataRv.setNestedScrollingEnabled(true);
        }
        switch (demo) {
            case 1:
                demoMultiLevelRv();
                break;
            case 2:
                demoEditTextInRv();
                break;
            case 3:
                demoRvInScrv();
                break;
            case 4:
                demoRvInRv();
                break;
            case 5:
                demoDragAndSwipe();
                break;
        }
    }

    private void demoDragAndSwipe() {
        List<String> mContents = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mContents.add("Item " + i);
        }
        SwipeDragAdapter adapter = new SwipeDragAdapter(mContents);
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mDataRv);
        mDataRv.setAdapter(adapter);
    }

    private void demoRvInRv() {
        List<ExpandData> mDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            List<ExpandData> mChildData = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                List<ExpandData> mSubChildData = new ArrayList<>();
                mChildData.add(new ExpandData("Child " + j, mSubChildData));
            }
            mDatas.add(new ExpandData("Data " + i, mChildData));
        }
        RvInRvAdapter adapter = new RvInRvAdapter(mDatas);
        mDataRv.setAdapter(adapter);
    }

    private void demoRvInScrv() {
        List<String> mContents = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mContents.add("Item " + i);
        }
        SwipeDragAdapter adapter = new SwipeDragAdapter(mContents);
        mDataRv.setAdapter(adapter);
    }

    private void demoEditTextInRv() {
        List<ItemData> mContents = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mContents.add(new ItemData(""));
        }
        EditTextAdapter adapter = new EditTextAdapter(mContents);
        mDataRv.setAdapter(adapter);
    }

    private void demoMultiLevelRv() {
        List<ExpandData> mDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            List<ExpandData> mChildData = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                List<ExpandData> mSubChildData = new ArrayList<>();
                for (int k = 0; k < 10; k++) {
                    mSubChildData.add(new ExpandData("Sub Child " + k, new ArrayList<>()));
                }
                mChildData.add(new ExpandData("Child " + j, mSubChildData));
            }
            mDatas.add(new ExpandData("Data " + i, mChildData));
        }
        ExpandableAdapter adapter = new ExpandableAdapter(mDatas);
        mDataRv.setAdapter(adapter);

    }
}
