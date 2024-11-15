package com.hw2app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllSongAdapter extends RecyclerView.Adapter<AllSongAdapter.SongViewHolder> {
    private final SongLinkedList playlist;
    private final OnItemClickListener listener;
    private static final int MAX_CHAR_LENGTH = 13;
    public interface OnItemClickListener {
        void onItemClick(Song song);
    }

    public AllSongAdapter(SongLinkedList playlist, OnItemClickListener listener) {
        this.playlist = playlist;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_view_item, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        // Get the song at the given position
        SongNode node = playlist.getSongNode(position);

        // Set data to the views in the SongViewHolder
        if (node != null) {
            Song song = node.getData();
            holder.bind(song, listener);
        }
    }

    @Override
    public int getItemCount() {
        return playlist.getSize();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {
        TextView songName;
        TextView songArtist;
        TextView songAlbum;
        TextView songLength;

        public SongViewHolder(View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.song_name);
            songArtist = itemView.findViewById(R.id.artist);
            songAlbum = itemView.findViewById(R.id.album);
            songLength = itemView.findViewById(R.id.length);
        }

        public void bind(final Song song, final OnItemClickListener listener) {
            songName.setText(song.getName());
            songArtist.setText(song.getArtist());
            if(song.getAlbum().length() > MAX_CHAR_LENGTH) {
                songAlbum.setText(song.getAlbum().substring(0, MAX_CHAR_LENGTH) + "...");
            }else{
                songAlbum.setText(song.getAlbum());
            }
            songLength.setText(song.getFormattedLength());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(song);
                }
            });
        }
    }
}
