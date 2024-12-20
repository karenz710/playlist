package com.hw2app;

import android.Manifest;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SongAdapter adapter;
    SongLinkedList playlist;
    MediaPlayer mediaPlayer;
    Button cursorPlay;
    Button cursorPrev;
    Button cursorNext;
    Button cursorRemove;
    Button cursorAdd;
    Button shuffle;
    Button clearPlaylist;
    Button randomSong;
    TextView currentlyPlayingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        currentlyPlayingText = findViewById(R.id.currently_playing);
        playlist = new SongLinkedList(this, currentlyPlayingText);
        cursorPlay = findViewById(R.id.play_arrow);
        cursorPrev = findViewById(R.id.cursor_before);
        cursorNext = findViewById(R.id.cursor_next);
        cursorRemove = findViewById(R.id.cursor_remove);
        cursorAdd = findViewById(R.id.cursor_add);
        randomSong = findViewById(R.id.play_random);
        shuffle = findViewById(R.id.shuffle);
        clearPlaylist = findViewById(R.id.clear_playlist);
        adapter = new SongAdapter(playlist);
        recyclerView.setAdapter(adapter);

        cursorPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playlist.isPlaying()) {
                    playlist.pause();
                    cursorPlay.setBackgroundResource(R.drawable.baseline_play_arrow_24);
                } else {
                    playlist.play(playlist.getCursor());
                    cursorPlay.setBackgroundResource(R.drawable.baseline_pause_24);
                }
            }
        });

        cursorAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddSongDialog();
            }
        });

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


        randomSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playlist.playRandom();
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

    private void showAddSongDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_song);
        RecyclerView recyclerViewSongs = dialog.findViewById(R.id.recycler_view_all_songs);
        recyclerViewSongs.setLayoutManager(new LinearLayoutManager(this));
        SongLinkedList entireSongList = getAvailableSongs();
        AllSongAdapter adapter = new AllSongAdapter(entireSongList, new AllSongAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Song song) {
                playlist.insertAfterCursor(song);
                MainActivity.this.adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        recyclerViewSongs.setAdapter(adapter);
        dialog.show();
        // Set the dialog window to match the parent width
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        dialog.show();
    }

    private SongLinkedList getAvailableSongs() {
        SongLinkedList result = new SongLinkedList();
        String[] songTitles = getResources().getStringArray(R.array.song_titles);
        String[] songArtists = getResources().getStringArray(R.array.song_artists);
        String[] songAlbums = getResources().getStringArray(R.array.song_albums);
        String[] songLengths = getResources().getStringArray(R.array.song_lengths);
        for (int i = 0; i < songTitles.length; i++) {
            result.insertAfterCursor(new Song(songTitles[i], songArtists[i], songAlbums[i], Integer.parseInt(songLengths[i])));
        }
        return result;
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