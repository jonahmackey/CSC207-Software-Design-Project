package activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import usecases.FacilityMap;
import controllers.FacilityManager;

import java.io.IOException;
import java.util.ArrayList;

public class FacilityAdapter extends RecyclerView.Adapter<FacilityAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView facilityName, facilityHours, facilityAddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            facilityName = itemView.findViewById(R.id.facilityName);
            facilityHours = itemView.findViewById(R.id.facilityHours);
            facilityAddress = itemView.findViewById(R.id.facilityAddress);
        }
    }
    LayoutInflater inflater;
    FacilityManager facilityManager = new FacilityManager();
    private ArrayList<ArrayList<String>> facilityInfo;
    public FacilityAdapter(ArrayList<ArrayList<String>> facilityMap) throws IOException {
        facilityInfo = facilityMap;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_facility_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = facilityManager.getFacilitiesInfoArray().get(position).get(0);
        String address = facilityManager.getFacilitiesInfoArray().get(position).get(1);
        String hours = facilityManager.getFacilitiesInfoArray().get(position).get(3);

        holder.facilityName.setText(name);
        holder.facilityHours.setText(hours);
        holder.facilityAddress.setText(address);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return facilityInfo.size();
    }


}
