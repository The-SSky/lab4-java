package edu;

import edu.exceptions.MediaIndexOutOfRangeException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Arrays;

import static edu.InputOutput.*;

public abstract class MediaLibrary implements Collectable, Serializable {

    private String title;
    private int price;
    private String[] collection;
    private int[] durations;

    public MediaLibrary() {
        title = Defaults.TITLE;
        price = Defaults.PRICE;
        collection = new String[Defaults.COUNT];
        durations = new int[collection.length];
    }

    public MediaLibrary(String title, int price, int count) {
        this.title = title;
        this.price = price;
        collection = new String[count];
        durations = new int[collection.length];
    }

    public MediaLibrary(String title, int price, String[] collection, int[] durations) {
        this.title = title;
        this.price = price;
        this.collection = collection;
        this.durations = durations;
    }

    public String[] getCollection() {
        return collection;
    }

    public int[] getDurations() {
        return durations;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getItemsCount() {
        return collection.length;
    }

    public String getItem(int index) {
        if (index < 0 || index >= collection.length) {
            throw new MediaIndexOutOfRangeException("Index out of range");
        }

        return collection[index];
    }

    public int getDuration(int index) {
        if (index < 0 || index >= durations.length) {
            throw new MediaIndexOutOfRangeException("Index out of range");
        }
        return durations[index];
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setItem(int index, String title) {
        if (index < 0 || index >= collection.length) {
            throw new MediaIndexOutOfRangeException("illegal index");
        }

        collection[index] = title;
    }

    public void setDuration(int index, int duration) {
        if (index < 0 || index >= collection.length) {
            throw new MediaIndexOutOfRangeException("illegal index");
        }
        durations[index] = duration;
    }

    public void output(OutputStream out) throws IOException {
        outputLibrary(this, out);
    }

    public void write(BufferedWriter out) throws IOException {
        writeLibrary(this, out);
    }

    // функциональный метод
    public int getTotalDuration() {
        int sum = 0;
        for (int duration : durations) {
            sum += duration;
        }
        return sum;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final MediaLibrary other = (MediaLibrary) obj;

        if (!(this.title.equals(other.title)
                && this.price == other.price
                && Arrays.equals(this.collection, other.collection)
                && Arrays.equals(this.durations, other.durations))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode(){
        int hash = 53;
        if (title != null) hash ^= title.hashCode();
        if (collection != null) hash ^= collection.hashCode();
        if (durations != null) hash ^= durations.hashCode();
        if (price != 0) hash ^= price;

        return hash;
    }

    public String toStringWriter(){
        return "object";
    }
}