package com.codi.showhidetoolbar.one;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codi.showhidetoolbar.R;

import java.util.List;

/**
 * Created by Codi on 2015/3/3.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mItemList;

    public RecyclerAdapter(List<String> itemList) {
        this.mItemList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new RecyclerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerItemViewHolder recyclerItemViewHolder = (RecyclerItemViewHolder) holder;
        String text = mItemList.get(position);
        recyclerItemViewHolder.setItemText(text);
    }


    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTv;

        public RecyclerItemViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.itemTextView);
        }

        public void setItemText(CharSequence text) {
            mTv.setText(text);
        }
    }
}
