package com.ghtk.rvdemo.multilevel;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghtk.rvdemo.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpandableAdapter extends RecyclerView.Adapter<ExpandableAdapter.VH> {
    private List<ExpandData> mExpandDataList;
    private Map<ExpandData, ExpandableAdapter> mDataAdapterManageMap;
    private Map<ExpandData, Boolean> mDataAdapterExpandMap;


    public ExpandableAdapter(List<ExpandData> expandDataList) {
        mExpandDataList = expandDataList;
        mDataAdapterManageMap = new HashMap<>();
        mDataAdapterExpandMap = new HashMap<>();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VH(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_expand, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int position) {
        ExpandData item = mExpandDataList.get(position);
        vh.mDataTv.setText(item.getData());

        Boolean expand = mDataAdapterExpandMap.get(item);
        if (expand != null && expand) {
            ExpandableAdapter mAdapter = mDataAdapterManageMap.get(item);
            if (mAdapter == null) {
                mAdapter = new ExpandableAdapter(item.getChildExpandData());
                mDataAdapterManageMap.put(item, mAdapter);
            }
            vh.mChildRv.setVisibility(View.VISIBLE);
            vh.mChildRv.setLayoutManager(new LinearLayoutManager(vh.itemView.getContext()));
            vh.mChildRv.setAdapter(mAdapter);
        } else {
            vh.mChildRv.setVisibility(View.GONE);
        }
//        vh.mChildRv.setHasFixedSize(true);
        vh.itemView.setOnClickListener(v -> {
            if (expand != null && expand) {
                mDataAdapterExpandMap.put(item, false);
            } else {
                mDataAdapterExpandMap.put(item, true);
            }
            notifyItemChanged(vh.getAdapterPosition());
//            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return mExpandDataList.size();
    }

    class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.data_tv)
        TextView mDataTv;
        @BindView(R.id.child_rv)
        RecyclerView mChildRv;

        VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
