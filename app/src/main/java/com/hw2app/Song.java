package com.hw2app;

/**
 * The <code>Song</code> class represents a song in the playlist.
 *
 * <p>
 * A song is characterized by its name, artist, album, and length. The name of the song is unique within the playlist.
 * </p>
 *
 * @author Karen Zhao
 * Email: karen.zhao@stonybrook.edu
 * Student ID: 115931297
 * CSE214 - R02
 */
public class Song {
    private String name; // The name of the song. This should be unique.
    private String artist; // The performer of the song.
    private String album; // The album where the song was released.
    private int length; // The length of the song in seconds.

    /**
     * Default constructor for the Song class.
     */
    public Song() {
        this.name = "";
        this.artist = "";
        this.album = "";
        this.length = 0;
    }

    /**
     * Returns a parameterized instance of <code>Song</code>.
     *
     * @param name   the name of the song
     * @param artist the performer of the song
     * @param album  the album where the song was released
     * @param length the length of the song in seconds
     *
     * <dt>Preconditions:
     * <dd><code>name</code> must not be null or empty.
     * <dd><code>artist</code> must not be null or empty.
     * <dd><code>album</code> must not be null or empty.
     * <dd><code>length</code> must be greater than or equal to 0.
     */
    public Song(String name, String artist, String album, int length) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = length;
    }

    /**
     * Gets name of the song.
     *
     * @return Returns the name of the song.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the song.
     *
     * @param name the name of the song
     *
     * <dt>Postcondition:
     *    <dd> The instance variable <code>name</code> will be updated to the new value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the artist of the song.
     *
     * @return Returns the performer of the song.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Sets the artist of the song.
     *
     * @param artist the performer of the song
     * <dt>Postcondition:
     *     <dd> The instance variable <code>artist</code> will be updated to the new value.
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Gets the album of the song.
     *
     * @return Returns the album where the song was released.
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Sets the album of the song.
     *
     * @param album the album where the song was released
     *
     * <dt>Postcondition:
     *     <dd>The instance variable <code>album</code> will be updated to the new value.
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * Gets the length of the song.
     *
     * @return Returns the length of the song in seconds.
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets the length of the song.
     *
     * @param length the length of the song in seconds
     *
     * <dt>Postcondition:
     *     <dd>The instance variable <code>length</code> will be updated to the new value.
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Returns a string representation of the <code>Song</code> object.
     *
     * @return A formatted string containing the name, artist, album, and length of the song.
     */
    public String toString(){
        return String.format("%-26s %-26s %-26s %-5d", name, artist, album, length);
    }
    public String getFormattedLength() {
        int minutes = length / 60;
        int seconds = length % 60;
        return String.format("%d:%02d", minutes, seconds); }

}
