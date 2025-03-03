package com.example.miniprojetand;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {
    private List<Hotel> hotelList;
    private List<Hotel> filteredHotelList;

    public HotelAdapter(List<Hotel> hotelList) {
        this.hotelList = hotelList;
        this.filteredHotelList = hotelList; // Initialize both lists as identical
    }

    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ligne, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelViewHolder holder, int position) {
        Hotel hotel = filteredHotelList.get(position);

        // Displaying hotel information
        holder.nameTextView.setText(hotel.getName());
        holder.locationTextView.setText(hotel.getLocation());
        holder.priceTextView.setText(hotel.getPrice() + " TND");

        // Loading the hotel's main image
        Glide.with(holder.itemView.getContext())
                .load(hotel.getMainImageUrl())
                .into(holder.hotelImageView);

        // Click on the "View" button to show the hotel details
        holder.viewButton.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            Intent intent = new Intent(context, HotelDetailActivity.class);
            intent.putExtra("hotelId", hotel.getId());
            intent.putExtra("hotelName", hotel.getName());
            intent.putExtra("hotelLocation", hotel.getLocation());
            intent.putExtra("hotelPrice", hotel.getPrice());
            intent.putExtra("hotelImage", hotel.getMainImageUrl());
            intent.putExtra("hotelDescription", hotel.getDescription()); // Correct key here
            context.startActivity(intent);
        });
    }



    @Override
    public int getItemCount() {
        return filteredHotelList.size();
    }

    // Filtering the search results
    public void filter(String query) {
        if (query.isEmpty()) {
            filteredHotelList = hotelList;
        } else {
            filteredHotelList.clear();
            for (Hotel hotel : hotelList) {
                if (hotel.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredHotelList.add(hotel);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder {
        ImageView hotelImageView;
        TextView nameTextView, locationTextView, priceTextView;
        Button viewButton;

        public HotelViewHolder(View itemView) {
            super(itemView);
            hotelImageView = itemView.findViewById(R.id.hotelImageView);
            nameTextView = itemView.findViewById(R.id.hotelNameTextView);
            locationTextView = itemView.findViewById(R.id.hotelLocationTextView);
            priceTextView = itemView.findViewById(R.id.hotelPriceTextView);
            viewButton = itemView.findViewById(R.id.viewButton);  // "View" button
        }
    }
}
