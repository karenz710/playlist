package com.hw2app;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.content.ContextCompat;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private SongLinkedList playlist;

    // Constructor for the adapter, takes a DoublyLinkedList of songs
    public SongAdapter(SongLinkedList playlist) {
        this.playlist = playlist;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the song_item.xml layout for each item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_view_item, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        // Get the song at the given position
        SongNode node = playlist.getSongNode(position);

        // Set data to the views in the SongViewHolder
        if (node != null) {
            Song song = node.getData();
            holder.songName.setText(song.getName());
            holder.songArtist.setText(song.getArtist());
            holder.songAlbum.setText(song.getAlbum());
            holder.songLength.setText(song.getFormattedLength());
        }
        if (node == playlist.getCursor()) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.highlight_color));
        }else { // Reset background color for non-cursor nodes
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.transparent)); }
    }

    @Override
    public int getItemCount() {
        return playlist.getSize();
    }

    // ViewHolder for holding views for each item
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

            // Disable clickability for the entire item view (Optional)
            itemView.setClickable(false);
            itemView.setFocusable(false);
        }

    }
}