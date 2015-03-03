package com.codi.showhidetoolbar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Codi on 2015/3/3.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 2;
    private static final int TYPE_ITEM = 1;

    private List<String> mItemList;

    public RecyclerAdapter(List<String> itemList) {
        this.mItemList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        if(viewType == TYPE_ITEM) {
            final View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
            return new RecyclerItemViewHolder(view);
        } else if(viewType == TYPE_HEADER) {
            final View view = LayoutInflater.from(context).inflate(R.layout.recycler_header, parent, false);
            return new RecyclerHeaderViewHolder(view);
        }
        throw new RuntimeException("There is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(!isPositionHeader(position)) {
            RecyclerItemViewHolder recyclerItemViewHolder = (RecyclerItemViewHolder) holder;
            String text = mItemList.get(position - 1);  // 因为添加了Header
            recyclerItemViewHolder.setItemText(text);
        }
    }

    public int getBaseItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public int getItemCount() {
        return getBaseItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
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

    public class RecyclerHeaderViewHolder extends RecyclerView.ViewHolder {

        public RecyclerHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
