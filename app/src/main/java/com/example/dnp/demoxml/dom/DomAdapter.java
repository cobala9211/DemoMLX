package com.example.dnp.demoxml.dom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dnp.demoxml.R;

/**
 * Created by DNP on 12/14/2016.
 */

public class DomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ROW_ITEM = 1;
    private DomItem mDataItem;

    public DomAdapter(Context context, DomItem data) {
        this.mContext = context;
        this.mDataItem = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
                viewType == TYPE_ROW_ITEM ? R.layout.item_list_dom : R.layout.item_header_dom, parent, false);
        if (viewType == TYPE_HEADER) {
            return new DomHeaderHolder(view);
        }
        return new DomHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_HEADER) {
            DomHeaderHolder viewHolder = (DomHeaderHolder) holder;
            viewHolder.tvIdOder.setText(mDataItem.getCartId());
            viewHolder.tvCustomer.setText(mDataItem.getCustomerId());
            viewHolder.tvEmail.setText(mDataItem.getEmail());
        } else {
            DomHolder viewHolder = (DomHolder) holder;
            viewHolder.tvPrice.setText(mDataItem.getListItems().get(position).getPrice());
            viewHolder.tvProduct.setText(mDataItem.getListItems().get(position).getProduct());
            viewHolder.tvQuanity.setText(mDataItem.getListItems().get(position).getQuatity());
            viewHolder.tvLineNumber.setText(mDataItem.getListItems().get(position).getNumber());
        }
    }

    @Override
    public int getItemCount() {
        if (mDataItem.getListItems() == null) {
            return 0;
        }
        return mDataItem.getListItems().size();
    }

    @Override
    public int getItemViewType(int position) {
        return isHeaderRecyclerView(position) ? TYPE_HEADER : TYPE_ROW_ITEM;
    }

    private boolean isHeaderRecyclerView(int position) {
        return position == 0;
    }

    static class DomHolder extends RecyclerView.ViewHolder {
        private TextView tvLineNumber;
        private TextView tvProduct;
        private TextView tvQuanity;
        private TextView tvPrice;

        public DomHolder(View itemView) {
            super(itemView);
            tvLineNumber = (TextView) itemView.findViewById(R.id.tvLineNumber);
            tvProduct = (TextView) itemView.findViewById(R.id.tvProduct);
            tvQuanity = (TextView) itemView.findViewById(R.id.tvQuantity);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
        }
    }

    static class DomHeaderHolder extends RecyclerView.ViewHolder {
        private TextView tvIdOder;
        private TextView tvCustomer;
        private TextView tvEmail;

        public DomHeaderHolder(View itemView) {
            super(itemView);
            tvIdOder = (TextView) itemView.findViewById(R.id.tvIdOrder);
            tvCustomer = (TextView) itemView.findViewById(R.id.tvCustomer);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
        }
    }
}
