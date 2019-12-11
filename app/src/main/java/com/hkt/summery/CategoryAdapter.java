package com.hkt.summery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ExampleViewHolder> {
    private Context context;
    private ArrayList<Category> categoryArrayList;
    private ArrayList<Category> categoryArrayListTemp;
    public ImageView imageView;
    ProgressBar progressBar;
    private Toolbar toolbar;

    public CategoryAdapter(Context context, ArrayList<Category> categoryArrayList) {
        this.context = context;
        this.categoryArrayList = categoryArrayList;
        categoryArrayListTemp = new ArrayList<Category>(categoryArrayList);
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        Category category = categoryArrayList.get(i);
        String imageUrl = category.getUrlImage();
        String name = category.getName();
        exampleViewHolder.name.setText(name);
        Glide.with(context).load(imageUrl).apply(new RequestOptions().placeholder(R.drawable.ic_file_download_black_24dp)).into(imageView);
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

//    @Override
//    public Filter getFilter() {
//        return categoryFilter;
//    }
//
//    private Filter categoryFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<Category> filteredList = new ArrayList<>();
//
//            if (constraint == null || constraint.length() == 0){
//                filteredList.addAll(categoryArrayListTemp);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//                for (Category item: categoryArrayListTemp){
//                    if (item.getName().toLowerCase().contains(filterPattern)){
//                        filteredList.add(item);
//                    }
//                }
//            }
//
//            FilterResults results = new FilterResults();
//            results.values = filteredList;
//
//            return  results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            categoryArrayList.clear();
//            categoryArrayList.addAll((List) results.values);
//            notifyDataSetChanged();
//        }
//    };

    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView name;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
        }
    }
}
