package com.hw2app;

/**
 * The <code>SongNode</code> class acts as a node wrapper around a <code>Song</code> object in a doubly linked list.
 *
 * <p>
 * Each node contains a <code>Song</code> variable that represents the data being stored in each node,
 * and contains references to the previous and next nodes in the list.
 * </p>
 *
 * @author Karen Zhao
 * Email: karen.zhao@stonybrook.edu
 * Student ID: 115931297
 * CSE214 - R02
 */
public class SongNode {
    SongNode prev; // the previous SongNode in the list
    SongNode next; // the next SongNode in the list
    Song data; // the Song object stored in this node

    /**
     * Default constructor for the SongNode class.
     * Initializes prev, next to null and data to a new Song object.
     */
    public SongNode() {
        this.prev = null;
        this.next = null;
        this.data = new Song();
    }

    /**
     * Initialize a node with specified initial data and links to the prev and next node. Note
     * that the next node may be the null reference, which indicates that the
     * new node has nothing after it.
     *
     * @param prev
     * The previous SongNode in the list.
     * @param next
     * The next SongNode in the list.
     * @param data
     * The Song object stored in this node.
     *
     * <dt>Postconditions:
     *    <dd>This new node contains the specified Song data and links to the prev and next node.
     */
    public SongNode(SongNode prev, SongNode next, Song data){
        this.prev = prev;
        this.next = next;
        this.data = data;
    }

    /**
     * Gets the previous <code>SongNode</code>.
     *
     * @return The previous <code>SongNode</code> in the list.
     */
    public SongNode getPrev(){
        return prev;
    }

    /**
     * Sets the previous <code>SongNode</code>.
     *
     * @param prev The previous <code>SongNode</code> to be set.
     *
     * <dt>Postcondition:
     *    <dd>The instance variable <code>prev</code> will be updated to the new value.
     */
    public void setPrev(SongNode prev){
        this.prev = prev;
    }

    /**
     * Gets the next <code>SongNode</code>.
     *
     * @return The next <code>SongNode</code> in the list.
     */
    public SongNode getNext(){
        return next;
    }

    /**
     * Sets the next <code>SongNode</code>.
     *
     * @param next The next <code>SongNode</code> to be set.
     *
     * <dt>Postcondition:
     *    <dd>The instance variable <code>next</code> will be updated to the new value.
     */
    public void setNext(SongNode next){
        this.next = next;
    }

    /**
     * Accessor method to get Song data from this node.
     *
     * @return Song data from this node.
     */
    public Song getData(){
        return data;
    }

    /**
     * Modification method to set the Song data to this node.
     *
     * @param data the new Song data to set to this node.
     *
     * <dt>Postconditions:
     *    <dd>The instance variable data of this node has been set to the new <code>data</code> value.
     */
    public void setData(Song data){
        this.data = data;
    }

    /**
     * Returns a string representation of the <code>SongNode</code> object.
     *
     * @return A string containing the details of the <code>Song</code> object associated with this node.
     */
    public String toString(){
        return data.toString();
    }
}


