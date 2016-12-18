package com.example.dnp.demoxml.sax;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dnp.demoxml.R;

/**
 * Created by DNP on 12/18/2016.
 */

public class SaxAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ROW_ITEM = 1;
    private ItemSax mItemSax;

    public SaxAdapter(Context context, ItemSax data) {
        this.mContext = context;
        this.mItemSax = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
                viewType == TYPE_ROW_ITEM ? R.layout.item_list_dom : R.layout.item_header_dom, parent, false);
        if (viewType == TYPE_HEADER) {
            return new SaxAdapter.SaxHeaderHolder(view);
        }
        return new SaxAdapter.SaxHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_HEADER) {
            SaxAdapter.SaxHeaderHolder viewHolder = (SaxAdapter.SaxHeaderHolder) holder;
            viewHolder.tvIdOder.setText(mItemSax.getTitle());
            viewHolder.tvCustomer.setText(mItemSax.getLink());
            viewHolder.tvEmail.setText(mItemSax.getPupDate());
        } else {
            SaxAdapter.SaxHolder viewHolder = (SaxAdapter.SaxHolder) holder;
            viewHolder.tvPrice.setText(mItemSax.getListDetail().get(position).getTitles());
            viewHolder.tvProduct.setText(mItemSax.getListDetail().get(position).getTitles());
            viewHolder.tvQuanity.setText(mItemSax.getListDetail().get(position).getPubDates());
            viewHolder.tvLineNumber.setText(mItemSax.getListDetail().get(position).getDescriptions().getImg());
        }
    }

    @Override
    public int getItemCount() {
        if (mItemSax.getListDetail() == null) {
            return 0;
        }
        return mItemSax.getListDetail().size();
    }

    @Override
    public int getItemViewType(int position) {
        return isHeaderRecyclerView(position) ? TYPE_HEADER : TYPE_ROW_ITEM;
    }

    private boolean isHeaderRecyclerView(int position) {
        return position == 0;
    }

    static class SaxHolder extends RecyclerView.ViewHolder {
        private TextView tvLineNumber;
        private TextView tvProduct;
        private TextView tvQuanity;
        private TextView tvPrice;

        public SaxHolder(View itemView) {
            super(itemView);
            tvLineNumber = (TextView) itemView.findViewById(R.id.tvLineNumber);
            tvProduct = (TextView) itemView.findViewById(R.id.tvProduct);
            tvQuanity = (TextView) itemView.findViewById(R.id.tvQuantity);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
        }
    }

    static class SaxHeaderHolder extends RecyclerView.ViewHolder {
        private TextView tvIdOder;
        private TextView tvCustomer;
        private TextView tvEmail;

        public SaxHeaderHolder(View itemView) {
            super(itemView);
            tvIdOder = (TextView) itemView.findViewById(R.id.tvIdOrder);
            tvCustomer = (TextView) itemView.findViewById(R.id.tvCustomer);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
        }
    }
}
