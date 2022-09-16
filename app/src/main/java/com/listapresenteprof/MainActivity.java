package com.listapresenteprof;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.listapresenteprof.adapter.GiftAdapter;
import com.listapresenteprof.model.Gift;
import com.listapresenteprof.repository.GiftRepository;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonAddGift;
    private RecyclerView recyclerViewGift;
    private TextView textViewGiftsNotFound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Gift> gifts = GiftRepository.getInstance().getAll();

        buttonAddGift = findViewById(R.id.fab_add_gift);
        recyclerViewGift = findViewById(R.id.recycler_gifts);
        textViewGiftsNotFound = findViewById(R.id.text_gift_not_found);

        buttonAddGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(
                    getApplicationContext(),
                    AddGiftActivity.class
            );
            startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        ArrayList<Gift> gifts = GiftRepository.getInstance().getAll();
        if (gifts.size() > 0){
         textViewGiftsNotFound.setVisibility(View.INVISIBLE);
        }else{
            textViewGiftsNotFound.setVisibility(View.VISIBLE);
        }

        recyclerViewGift.setAdapter(new GiftAdapter());
    }

}