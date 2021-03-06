package com.cjt.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjt.shopping.R;
import com.cjt.shopping.bean.ShopList;
import com.cjt.shopping.model.server.ServerAPI;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/6/12
 * 邮箱: 445263848@qq.com.
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopViewHolder> {
    private Context mContext;
    private List<ShopList.VendorsBean> datas;
    private OnItemClickListener listener;

    public void updata(List<ShopList.VendorsBean> list) {
        datas.clear();
        datas=list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public ShopAdapter(List<ShopList.VendorsBean> datas, Context mContext, OnItemClickListener listener) {
        this.datas = datas;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shop_item, parent, false);
        return new ShopViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, int position) {
        holder.tv_shopname.setText(datas.get(position).getStore().getName());
        Picasso.with(mContext).load(ServerAPI.baseUrl+datas.get(position).getStore().getStoreImage().getShopFile().getUrl()).resize(100, 100).into(holder.iv_shopcover);
        holder.tv_startprice.setText("起送价￥"+datas.get(position).getStore().getStartFee());
        holder.tv_giveprice.setText("配送费￥"+datas.get(position).getStore().getPackingFee());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


}

class ShopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ShopAdapter.OnItemClickListener listener;

    ImageView iv_shopcover;
    TextView tv_shopname;
    TextView tv_distance;
    TextView tv_time;
    TextView tv_startprice;
    TextView tv_giveprice;

    public ShopViewHolder(View itemView, ShopAdapter.OnItemClickListener listener) {
        super(itemView);
        iv_shopcover = (ImageView) itemView.findViewById(R.id.iv_shopcover);
        tv_shopname = (TextView) itemView.findViewById(R.id.tv_shopname);
        tv_distance = (TextView) itemView.findViewById(R.id.tv_distance);
        tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        tv_startprice = (TextView) itemView.findViewById(R.id.tv_startprice);
        tv_giveprice = (TextView) itemView.findViewById(R.id.tv_giveprice);
        itemView.setOnClickListener(this);
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onItemClick(v, getPosition());
        }
    }
}