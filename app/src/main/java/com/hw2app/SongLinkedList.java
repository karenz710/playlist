package com.hw2app;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.TextView;

public class SongLinkedList {
    private SongNode head;
    private SongNode tail;
    private SongNode cursor;
    private int size;
    private MediaPlayer mediaPlayer;
    private Context context;
    private TextView currentlyPlayingText;

    public SongLinkedList() {
        head = null;
        tail = null;
        cursor = null;
    }

    public SongLinkedList(Context context, TextView currentlyPlayingText) {
        this();
        this.context = context;
        this.currentlyPlayingText = currentlyPlayingText;
    }

    public SongNode getCursor() {
        return cursor;
    }

    public SongNode getSongNode(int index) {
        if (index < 0 || index >= size)
            return null;
        SongNode nodePtr = head;
        for (int i = 0; i < index; i++) {
            nodePtr = nodePtr.next;
        }
        return nodePtr;
    }

    /**
     * Plays the song at the SongNode!!
     *
     * @param song
     */
    public void play(SongNode song) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        String fileName = SongFileMapping.getFileName(song.getData().getName());
        if (fileName != null) {
            int resId = context.getResources().getIdentifier(fileName, "raw", context.getPackageName());
            if (resId != 0) {
                mediaPlayer = MediaPlayer.create(context, resId);
                mediaPlayer.start();
                // Update the TextView with the current song name
                currentlyPlayingText.setText("Currently Playing: " + song.getData().getName());
            }
        }
    }

    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }
    /**
     * Moves the cursor to point at the next SongNode.
     *
     * <dt>Preconditions:
     * <dd>The list is not empty (cursor is not null).
     *
     * <dt>Postconditions:
     * <dd>The cursor has been advanced to the next SongNode, or has remained at the tail of the list.
     */
    public void cursorForwards() {
        if (cursor != null) { // if the list is not empty
            if (cursor != tail) { // if cursor is at tail and nothing happens
                cursor = cursor.getNext(); // move cursor to next SongNode
            } else {
                System.out.println("Already at the end of the playlist.");
            }
        } else {
            System.out.println("List is empty!");
        }
    }

    /**
     * Moves the cursor to point at the previous SongNode.
     *
     * <dt>Preconditions:
     * <dd>The list is not empty (cursor is not null).
     *
     * <dt>Postconditions:
     * <dd>The cursor has been moved back to the previous SongNode, or has remained at the head of the list.
     */
    public void cursorBackwards() {
        if (cursor != null) { // if the list is not empty
            if (cursor != head) {
                cursor = cursor.getPrev();
            } else {
                System.out.println("Already at the beginning of the playlist.");
            }
        } else {
            System.out.println("List is empty!");
        }
    }

    /**
     * Inserts a song into the playlist AFTER the cursor position. The user will have to move the cursor using cursor methods
     * above to add a specific song.
     *
     * @param newSong the song to be inserted into the playlist.
     *
     *                <dt>Preconditions:
     *                <dd>The newSong object has been instantiated.
     *
     *                <dt>Postconditions:
     *                <dd>The new Song is inserted into the playlist after the position of the cursor.
     *                <dd>All Song objects previously in the playlist are still in the playlist, and the order of the playlist is preserved.
     *                <dd>The cursor now points to the inserted node.
     * @throws IllegalArgumentException Indicates that newSong is null.
     */
    public void insertAfterCursor(Song newSong) throws IllegalArgumentException {
        if (head == null) { // if list is empty, create first node (new node's prev link has to point to null)
            head = new SongNode(null, null, newSong);
            tail = head;
            cursor = head;
        } else {
            cursor.setNext(new SongNode(cursor, cursor.getNext(), newSong)); // sets current cursor's next link to the new element, new element prev link references current cursor.
            if (cursor == tail) { // change tail if added to the end
                tail = cursor.getNext();
            }
            cursorForwards(); // change cursor reference to newly inserted Song
            if(cursor.getNext()!=null)
                cursor.getNext().setPrev(cursor);
        }
        size++; // increments size since a node is added.
    }

    /**
     * Removes the songNode referenced by the cursor and returns the Song contained within the node.
     *
     * <dt>Preconditions:
     * <dd>The cursor is not null.
     *
     * <dt>Postconditions:
     * <dd>The SongNode referenced by the cursor has been removed from the playlist.
     * <dd>The cursor now references the next node, or the previous node if no next node exists.
     *
     * @return The Song contained within the removed SongNode.
     */
    public Song removeCursor() {
        if (cursor == null) { // check if cursor is null (list is empty)
            return null;
        }
        Song removed = cursor.getData();
        // There are 4 scenarios to removing the cursor node.
        if (head == tail) { // list is one node
            cursor = null;
            head = null;
            tail = null;
        } else if (tail == cursor) { // cursor is at the end of the linked list and goes to the previous node
            cursorBackwards();
            tail = cursor;
            cursor.setNext(null);// set link of new cursor to null since it is at the end of the list now
        } else if (head == cursor) { // cursor is at the beginning of the linked list and goes to next node
            cursorForwards();
            head = head.getNext();
            cursor.setPrev(null); // update new cursor's previous link to ensure access to removed link is non-accessible
        } else { // cursor is in the middle of linked list
            cursor.getPrev().setNext(cursor.getNext()); // sets previous node's next link to next node
            cursor.getNext().setPrev(cursor.getPrev()); // sets next node's previous link to previous node
            cursorForwards();
        }
        size--; // updates size of list since a node is removed.
        return removed;
    }

    /**
     * Gets the size of the linked list.
     *
     * @return the size of the linked list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Select one of the songs in the playlist and play it at random. Note: This will NOT change the order of the playlist.
     *
     * <dt>Postconditions:
     *    <dd>The randomly selected song is now playing.
     *
     * @return The Song which was randomly selected.
     */
    public void playRandom(){
         SongNode res = randomSongNode();
         play(res);
     }

    // helper method to find random SongNode

    /**
     * Returns a randomly selected <code>SongNode</code> from the playlist.
     * <p>
     * This method generates a random number within the range of the size of the playlist,
     * then traverses the linked list to return the corresponding <code>SongNode</code>.
     * </p>
     *
     * <dt>Preconditions:
     * <dd>The playlist must contain at least one song; otherwise, this method will return null.
     *
     * @return A randomly selected <code>SongNode</code> from the playlist.
     */
    public SongNode randomSongNode() {
        int randomNumber = (int) (Math.random() * size) + 1;
        SongNode pointer = head;
        for (int i = 0; i < randomNumber - 1; i++) {
            pointer = pointer.getNext();
        }
        return pointer;
    }

    /**
     * Randomly shuffles the order of the songs contained within the playlist.
     *
     * <dt>Postconditions:
     * <dd>The playlist is randomly reordered.
     * <dd>cursor should reference the SongNode which contains the same Song as when this method was entered.
     */
    public void shuffle() {
        if(size>0) {
            Song originalCursor = cursor.getData(); // To later point the new cursor to the same node.
            SongLinkedList shuffled = new SongLinkedList(); // initialize a new list to store randomized SongNodes
            while (this.size > 0) {
                shuffled.insertNode(this.removeRandomNode(), shuffled); // insert in order in shuffled a randomly removed node from playlist
                // unnecessary to decrement size since removeRandomNode uses method removeCursor() which decrements size
            }
            // Set shuffled to playlist
            this.head = shuffled.head;
            SongNode pointer = head;
            while (pointer.getData() != originalCursor) { // back to original cursor pointer O(n) time complexity
                pointer = pointer.getNext();
            }
            this.cursor = pointer;
            this.tail = shuffled.tail;
            this.size = shuffled.size;
        }
    }

    /**
     * Inserts a <code>SongNode</code> into the specified <code>SongLinkedList</code>.
     * <p>
     * This method takes a <code>SongNode</code> and inserts its data into specified shuffled SongLinkedList.
     * </p>
     *
     * @param n        the <code>SongNode</code> being inserted into the playlist
     * @param shuffled the <code>SongLinkedList</code> where the node will be inserted.
     *
     *                 <dt>Preconditions:
     *                 <dd>The <code>SongNode</code> n must not be null, and the shuffled list must be properly initialized.
     *
     *                 <dt>Postconditions:
     *                 <dd>The data from the specified <code>SongNode</code> has been added to the shuffled list.
     */
    public void insertNode(SongNode n, SongLinkedList shuffled) {
        shuffled.insertAfterCursor(n.getData());
    }

    /**
     * Removes a random SongNode from the original playlist.
     * <p>
     * This method selects a random node, updates the cursor to that node,
     * and removes it from the original list. The cursor is then restored
     * to its original position.
     * </p>
     * <dt>Preconditions:
     * <dd>The playlist must contain at least one song; otherwise, this method will return null.
     *
     * @return The SongNode that was removed from the playlist.
     */
    public SongNode removeRandomNode() {
        SongNode toRemove = randomSongNode();
        SongNode pointer = cursor; // in case of removing just one random node, have a pointer to set cursor back to original cursor.
        cursor = toRemove;
        removeCursor(); // removes from original list and decrements size
        cursor = pointer;
        return toRemove;
    }

    /**
     * Prints the playlist in a neatly-formatted table. Assumed strings are at most 25 characters long.
     */
    public void printPlaylist() {
        System.out.print(this);
    }

    /**
     * This will simply delete all songs from the playlist.
     *
     * <dt>Postconditions:
     * <dd>All songs have been removed
     */
    public void deleteAll() {
        head = null; // let java garbage collection automatically delete
        cursor = null;
        tail = null;
        size = 0; // reset size
    }

    /**
     * Returns a neatly formatted String representation of the playlist.
     *
     * @return A neatly formatted String representing the playlist in tabular form.
     */
    public String toString() {
        StringBuilder res = new StringBuilder();
        // Append Header
        res.append(String.format("%-25s| %-25s| %-25s| %-25s %n", "Song", "Artist", "Album", "Length (s)"));
        res.append("-".repeat(100)).append("\n");
        // Iterate through the playlist
        SongNode pointer = head;
        while (pointer != null) {
            res.append(pointer);
            if (pointer == cursor) {
                res.append("<-");
            }
            res.append("\n");
            pointer = pointer.getNext(); // when this reaches the last node, pointer is set to null and loop terminates.
        }
        return res.toString();
    }
}
