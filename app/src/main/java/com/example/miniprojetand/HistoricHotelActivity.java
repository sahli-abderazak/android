package com.example.miniprojetand;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoricHotelActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReservationAdapter adapter;
    private List<Reservation> reservationList;
    private DatabaseReference reservationRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic_hotel);

        // Initialiser la RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialiser la liste des réservations
        reservationList = new ArrayList<>();
        adapter = new ReservationAdapter(reservationList);
        recyclerView.setAdapter(adapter);

        // Référence Firebase
        reservationRef = FirebaseDatabase.getInstance().getReference("reservations");

        // Récupérer les réservations depuis Firebase
        reservationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reservationList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Reservation reservation = snapshot.getValue(Reservation.class);
                    if (reservation != null) {
                        reservationList.add(reservation);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(HistoricHotelActivity.this, "Failed to load reservations.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
