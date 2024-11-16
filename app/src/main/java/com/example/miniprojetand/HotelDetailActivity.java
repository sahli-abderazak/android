package com.example.miniprojetand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class HotelDetailActivity extends AppCompatActivity {

    private TextView hotelNameTextView, hotelLocationTextView, hotelPriceTextView, hotelDescriptionTextView;
    private ImageView hotelImageView;
    private Button reserveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);

        // Initialize UI elements
        hotelNameTextView = findViewById(R.id.hotelNameTextView);
        hotelLocationTextView = findViewById(R.id.hotelLocationTextView);
        hotelPriceTextView = findViewById(R.id.hotelPriceTextView);
        hotelImageView = findViewById(R.id.hotelImageView);
        hotelDescriptionTextView = findViewById(R.id.hotelDescriptionTextView);
        reserveButton = findViewById(R.id.reserveButton);
        // Get data passed from the previous activity
        String hotelName = getIntent().getStringExtra("hotelName");
        String hotelLocation = getIntent().getStringExtra("hotelLocation");
        String hotelPrice = getIntent().getStringExtra("hotelPrice");
        String hotelImageUrl = getIntent().getStringExtra("hotelImage");
        String hotelDescription = getIntent().getStringExtra("hotelDescription"); // Retrieve the description

        // Set the data to the UI elements
        hotelNameTextView.setText(hotelName);
        hotelLocationTextView.setText(hotelLocation);
        hotelPriceTextView.setText(hotelPrice + " TND");
        hotelDescriptionTextView.setText(hotelDescription); // Ensure description is set here

        // Load the hotel image using Glide
        Glide.with(this).load(hotelImageUrl).into(hotelImageView);

        reserveButton.setOnClickListener(v -> {
            Intent intent = new Intent(HotelDetailActivity.this, ReservationActivity.class);
            intent.putExtra("hotelName", hotelName);
            intent.putExtra("hotelPrice", hotelPrice);
            startActivity(intent);
        });

    }
}
