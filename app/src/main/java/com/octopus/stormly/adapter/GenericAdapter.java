package com.project.semicolon.recyclerviewdatabinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.octopus.stormly.BR;
import com.octopus.stormly.adapter.AbstractListItem;
import com.octopus.stormly.adapter.OnListItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class GenericAdapter<T extends AbstractListItem>
        extends RecyclerView.Adapter<GenericAdapter<T>.GenericViewHolder<T>> {
    private List<T> items;
    private OnListItemClickListener clickListener;
    @LayoutRes
    private int layoutRes;
    private LayoutInflater inflater;

    public GenericAdapter(int layoutRes) {
        this.layoutRes = layoutRes;
        items = new ArrayList<>();
    }

    public void setClickListener(OnListItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void addList(List<T> list) {
        items.clear();
        items.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GenericAdapter<T>.GenericViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding viewBinding = DataBindingUtil.inflate(inflater, layoutRes, parent, false);
        return new GenericViewHolder<>(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericAdapter<T>.GenericViewHolder<T> holder, int position) {
        T t = items.get(position);
        t.currentPosition = position;
        t.listItemClickListener = clickListener;
        holder.bind(t);

    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.size();
    }

    public class GenericViewHolder<T extends AbstractListItem> extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public GenericViewHolder(@NonNull ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            binding = viewDataBinding;

        }

        public void bind(T t) {
            binding.setVariable(BR.obj, t);
            binding.executePendingBindings();
        }
    }
}