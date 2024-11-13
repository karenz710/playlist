package com.hw2app;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SongAdapter adapter;
    SongLinkedList playlist;
    MediaPlayer mediaPlayer;
    Button cursorPrev;
    Button cursorNext;
    Button cursorRemove;
    Button cursorAdd;
    Button shuffle;
    Button clearPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        playlist = new SongLinkedList();
        cursorPrev = findViewById(R.id.cursor_before);
        cursorNext = findViewById(R.id.cursor_next);
        cursorRemove = findViewById(R.id.cursor_remove);
        cursorAdd = findViewById(R.id.cursor_add);
        shuffle = findViewById(R.id.shuffle);
        clearPlaylist = findViewById(R.id.clear_playlist);

        playlist.insertAfterCursor(new Song("1812 Overture", "Pyotr Tchaikovsky", "Royal Albert Hall", 9));
        playlist.insertAfterCursor(new Song("Danse Macabre", "Camille Saint-Saens", "Opus 40", 10));
        playlist.insertAfterCursor(new Song("1812 Overture", "Pyotr Tchaikovsky", "Royal Albert Hall", 9));
        playlist.insertAfterCursor(new Song("Danse Macabre", "Camille Saint-Saens", "Opus 40", 10));
        playlist.insertAfterCursor(new Song("1812 Overture", "Pyotr Tchaikovsky", "Royal Albert Hall", 9));
        playlist.insertAfterCursor(new Song("Danse Macabre", "Camille Saint-Saens", "Opus 40", 10));
        playlist.insertAfterCursor(new Song("1812 Overture", "Pyotr Tchaikovsky", "Royal Albert Hall", 9));
        playlist.insertAfterCursor(new Song("Danse Macabre", "Camille Saint-Saens", "Opus 40", 10));
        playlist.insertAfterCursor(new Song("1812 Overture", "Pyotr Tchaikovsky", "Royal Albert Hall", 9));
        playlist.insertAfterCursor(new Song("Danse Macabre", "Camille Saint-Saens", "Opus 40", 10));
        playlist.insertAfterCursor(new Song("1812 Overture", "Pyotr Tchaikovsky", "Royal Albert Hall", 9));
        playlist.insertAfterCursor(new Song("Danse Macabre", "Camille Saint-Saens", "Opus 40", 10));
        adapter = new SongAdapter(playlist);
        recyclerView.setAdapter(adapter);

        cursorPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playlist.cursorBackwards();
                adapter.notifyDataSetChanged();
            }
        });

        cursorNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playlist.cursorForwards();
                adapter.notifyDataSetChanged();
            }
        });

        cursorRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playlist.removeCursor();
                adapter.notifyDataSetChanged();
            }
        });

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShufflePlaylistDialog();
            }
        });
        clearPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showClearPlaylistDialog();
            }
        });
    }

    private void showClearPlaylistDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Clear Playlist")
                .setMessage("Are you sure you want to clear the playlist?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Clear the playlist
                    playlist.deleteAll();
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void showShufflePlaylistDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Shuffle Playlist")
                .setMessage("Shuffle the playlist?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Clear the playlist
                    playlist.shuffle();
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("No", null)
                .show();
    }

}