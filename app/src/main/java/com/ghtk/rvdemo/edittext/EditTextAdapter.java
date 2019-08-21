package com.ghtk.rvdemo.edittext;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ghtk.rvdemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditTextAdapter extends RecyclerView.Adapter<EditTextAdapter.VH> {

    private List<ItemData> mContents;

    public EditTextAdapter(List<ItemData> contents) {
        mContents = contents;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_edit_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        final ItemData item = mContents.get(position);
        holder.mSttTv.setText(String.valueOf(position + 1));


//        holder.mContentEdt.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                item.setName(s.toString());
//                Log.d("hoho", "afterTextChanged: "+position+"---"+mContents.get(position).getName());
//            }
//        });
//        holder.mContentEdt.setText(item.getName());


        holder.mMyCustomEditTextListener.updatePosition(position);
        holder.mContentEdt.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mContents == null ? 0 : mContents.size();
    }


    class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.stt_tv)
        TextView mSttTv;
        @BindView(R.id.content_edt)
        EditText mContentEdt;

        MyCustomEditTextListener mMyCustomEditTextListener;

        VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mMyCustomEditTextListener = new MyCustomEditTextListener();
            mContentEdt.addTextChangedListener(mMyCustomEditTextListener);
        }
    }

    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            mContents.get(position).setName(editable.toString());
            Log.d("hoho", "afterTextChanged: "+position+"---"+mContents.get(position).getName());
        }
    }
}
