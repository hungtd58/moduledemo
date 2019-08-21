package com.ghtk.rvdemo.swipedrag;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghtk.rvdemo.R;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SwipeDragAdapter extends RecyclerView.Adapter<SwipeDragAdapter.VH>
        implements ItemTouchHelperAdapter {

    private List<String> mContents;

    public SwipeDragAdapter(List<String> contents) {
        mContents = contents;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_swipe_drag, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.mContentTv.setText(mContents.get(position));
    }

    @Override
    public int getItemCount() {
        return mContents == null ? 0 : mContents.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mContents, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mContents, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        mContents.remove(position);
        notifyItemRemoved(position);
    }

    class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.content_tv)
        TextView mContentTv;

        VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
