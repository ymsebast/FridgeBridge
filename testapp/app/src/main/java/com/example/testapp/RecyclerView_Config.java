package com.example.testapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private FoodAdapter mFoodAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Food> foods, List<String> keys) {
        mContext = context;
        mFoodAdapter = new FoodAdapter(foods,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mFoodAdapter);
    }

    class FoodListView extends RecyclerView.ViewHolder {
        private TextView mFood;
        private TextView mValue;
        private TextView mUnit;
        private String key;

        public FoodListView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.food_list_item, parent, false));
            mFood = (TextView) itemView.findViewById(R.id.food_textView);
            mValue = (TextView) itemView.findViewById(R.id.value_textView);
            mUnit = (TextView) itemView.findViewById(R.id.unit_textView);

        }

        public void bind(Food food, String key) {
            mFood.setText(food.getFood());
            mValue.setText(food.getValue());
            mUnit.setText(food.getUnit());
            this.key = key;
        }
    }
    class FoodAdapter extends RecyclerView.Adapter<FoodListView>{
        private List<Food> mFoodList;
        private List<String> mKeys;

        public FoodAdapter(List<Food> mFoodList, List<String> mKeys) {
            this.mFoodList = mFoodList;
            this.mKeys = mKeys;
        }

        @Override
        public FoodListView onCreateViewHolder(ViewGroup parent, int viewType) {
            return new FoodListView(parent);
        }

        @Override
        public void onBindViewHolder(FoodListView holder, int position) {
            holder.bind(mFoodList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mFoodList.size();
        }
    }
}
