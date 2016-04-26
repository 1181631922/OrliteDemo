package com.fanyafeng.orlitedemo.recycleview.adapter;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyafeng.orlitedemo.R;

import java.util.List;

/**
 * Created by 365rili on 16/4/18.
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyRecycleViewHolder> {

    private Context context;
    private List<String> itemList;
    public OnItemClickListener onItemClickListener;
    private PopupWindow popupView;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public MyRecycleViewAdapter(Context context, List<String> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public MyRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_myrecycler, parent, false);
        View popView = LayoutInflater.from(context).inflate(R.layout.layout_popwindow_recyclerview, parent, false);
        popupView = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupView.setTouchable(true);
        popupView.setOutsideTouchable(true);
        popupView.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        return (new MyRecycleViewHolder(view));
    }

    @Override
    public void onBindViewHolder(final MyRecycleViewHolder holder, final int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(holder.itemView, position);
                    return true;
                }
            });
        }
        holder.tv_myrecycler_item.setText(itemList.get(position));
        holder.tv_myrecycler_item.setOnClickListener(new onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class MyRecycleViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_myrecycler_item;

        public MyRecycleViewHolder(View itemView) {
            super(itemView);
            tv_myrecycler_item = (TextView) itemView.findViewById(R.id.tv_myrecycler_item);
        }
    }

    class onItemClick implements View.OnClickListener {
        private int position;

        public onItemClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "第" + position + "个被点击了", Toast.LENGTH_SHORT).show();
            popupView.showAsDropDown(v);

        }
    }

    class onItemLongClick implements View.OnLongClickListener {
        private int position;

        public onItemLongClick(int position) {
            this.position = position;
        }

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(context, "第" + position + "个被点击了", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
