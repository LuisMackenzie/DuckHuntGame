package com.mackenzie.duckhuntgame.activities;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mackenzie.duckhuntgame.databinding.FragmentUserRankingBinding;
import com.mackenzie.duckhuntgame.models.User;

import java.util.List;

public class MyUserRecyclerViewAdapter extends RecyclerView.Adapter<MyUserRecyclerViewAdapter.ViewHolder> {

    private final List<User> mValues;

    public MyUserRecyclerViewAdapter(List<User> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentUserRankingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        int pos = position + 1;
        holder.tvPosition.setText(pos + "º");
        holder.tvDucks.setText(String.valueOf(mValues.get(position).getDucks()));
        holder.tvNick.setText(mValues.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvPosition;
        public final TextView tvDucks;
        public final TextView tvNick;
        public User mItem;

        public ViewHolder(FragmentUserRankingBinding binding) {
            super(binding.getRoot());

            tvPosition = binding.tvPosition;
            tvDucks = binding.tvDucks;
            tvNick = binding.tvNick;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvNick.getText() + "'";
        }
    }
}