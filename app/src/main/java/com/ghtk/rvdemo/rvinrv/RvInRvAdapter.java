package com.ghtk.rvdemo.rvinrv;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghtk.rvdemo.R;
import com.ghtk.rvdemo.multilevel.ExpandData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RvInRvAdapter extends RecyclerView.Adapter<RvInRvAdapter.VH> {

    private List<ExpandData> mExpandDataList;

    public RvInRvAdapter(List<ExpandData> expandDataList) {
        mExpandDataList = expandDataList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_in_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        ExpandData item = mExpandDataList.get(position);
        holder.mDataTv.setText(item.getData());

        if (item.getChildExpandData() != null && !item.getChildExpandData().isEmpty()) {
            holder.mChildRv.setVisibility(View.VISIBLE);
            holder.mChildRv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
            holder.mChildRv.setAdapter(new RvInRvAdapter(item.getChildExpandData()));

            holder.mChildRv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                @Override
                public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                    int action = e.getAction();
                    switch (action) {
                        case MotionEvent.ACTION_MOVE:
                            rv.getParent().requestDisallowInterceptTouchEvent(true);
                            break;
                    }
                    return false;
                }

                @Override
                public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                }
            });
        } else {
            holder.mChildRv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mExpandDataList == null ? 0 : mExpandDataList.size();
    }

    class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.data_tv)
        TextView mDataTv;
        @BindView(R.id.child_rv)
        RecyclerView mChildRv;

        VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
