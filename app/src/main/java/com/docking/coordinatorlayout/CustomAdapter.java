package com.docking.coordinatorlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.docking.coordinatorlayout.bean.CustomEntity;
import com.docking.coordinatorlayout.coordinatorlayout.demo.R;
import com.docking.coordinatorlayout.holder.CustomHolder;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<CustomEntity> mList = null;

    public CustomAdapter(Context context, List<CustomEntity> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void replace(List<CustomEntity> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_item, null);
        CustomHolder customHolder = new CustomHolder(view);
        return customHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        CustomEntity customEntity = mList.get(position);
        if (viewHolder instanceof CustomHolder) {
            CustomHolder holder = (CustomHolder) viewHolder;
            holder.initData(customEntity);
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        } else {
            return mList.size();
        }
    }
}
