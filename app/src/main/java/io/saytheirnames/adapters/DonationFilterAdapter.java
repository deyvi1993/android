package io.saytheirnames.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import io.saytheirnames.R;
import io.saytheirnames.models.DonationType;

import java.util.List;

public class DonationFilterAdapter extends RecyclerView.Adapter<DonationFilterAdapter.FilterItemHolder> {

    private List<DonationType> donationTypeList;
    private DonationFilterAdapter.DonationFilterListener listener;
    private NestedScrollView nestedScrollView;

    private int selected_item = 0;


    /**
     *
     * @param donationTypeList
     * @param listener
     * @param nestedScrollView used to bring focus to top of recycler after a new filter has been clicked
     */

    public DonationFilterAdapter(List<DonationType> donationTypeList, DonationFilterAdapter.DonationFilterListener listener, NestedScrollView nestedScrollView) {
        super();
        this.donationTypeList = donationTypeList;
        this.listener = listener;
        this.nestedScrollView = nestedScrollView;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DonationFilterAdapter.FilterItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_item, parent, false);
        return new FilterItemHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationFilterAdapter.FilterItemHolder holder, final int position) {

        DonationType donationType = donationTypeList.get(position);

        if (position == selected_item) {
            holder.filterLocation.setBackgroundResource(R.drawable.selected_button_background);
            holder.filterLocation.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.filterLocation.setBackgroundResource(R.drawable.button_background);
            holder.filterLocation.setTextColor(Color.parseColor("#101010"));
        }

        holder.filterLocation.setText(donationType.getType());

        holder.filterLocation.setOnClickListener(v -> {
            if(selected_item != position){
                selected_item = position;
                listener.onDonationFilterSelected(donationType);
                nestedScrollView.scrollTo(0, 0);
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
        return donationTypeList.size();
    }

    public interface DonationFilterListener {
        void onDonationFilterSelected(DonationType donationType);
    }

    class FilterItemHolder extends RecyclerView.ViewHolder {
        Button filterLocation;

        public FilterItemHolder(@NonNull View itemView) {
            super(itemView);
            filterLocation = itemView.findViewById(R.id.button);
        }
    }
}