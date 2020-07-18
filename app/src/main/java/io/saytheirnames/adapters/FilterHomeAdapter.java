package io.saytheirnames.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.saytheirnames.R;
import io.saytheirnames.models.HomeFilter;

import java.util.ArrayList;

public class FilterHomeAdapter extends RecyclerView.Adapter<FilterHomeAdapter.FilterItemHolder> {

    private ArrayList<HomeFilter> filterList;
    private Context context;
    private int filterCount = 0;


    public FilterHomeAdapter(ArrayList<HomeFilter>  filterList, Context context) {
        super();
        this.filterList = filterList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilterHomeAdapter.FilterItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_item, parent, false);
        return new FilterItemHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterHomeAdapter.FilterItemHolder holder, final int position) {

        if (filterList.get(position).isSelected()) {
            holder.filterType.setBackgroundResource(R.drawable.selected_button_background);
            holder.filterType.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.filterType.setBackgroundResource(R.drawable.button_background);
            holder.filterType.setTextColor(Color.parseColor("#101010"));
        }

        if(filterCount > 0 & filterList.get(position).isSelected()){
            String count = "(" + filterCount + ")";
            holder.filterType.setText(filterList.get(position).getFilterName() + " " + count);
        } else{
            holder.filterType.setText(filterList.get(position).getFilterName());
        }


        holder.filterType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!filterList.get(position).isSelected()){
                    filterList.get(position).setSelected(true);
                    filterCount++;
                } else{
                    filterList.get(position).setSelected(false);
                    filterCount--;
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    class FilterItemHolder extends RecyclerView.ViewHolder {
        Button filterType;

        public FilterItemHolder(@NonNull View itemView) {
            super(itemView);
            filterType = itemView.findViewById(R.id.button);
        }
    }
}
