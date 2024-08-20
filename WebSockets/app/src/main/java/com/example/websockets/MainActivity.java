package com.example.websockets;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    private MyWebSockets myWebSockets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         ChessCell[] list = new ChessCell[]{new ChessCell(false),new ChessCell(true),new ChessCell(false),new ChessCell(true)};


        RecyclerView recyclerView = findViewById(R.id.RecycleViewGameBoard);
        ChessCellAdapter adapter = new ChessCellAdapter(list);
        recyclerView.setAdapter(adapter);


    }
}