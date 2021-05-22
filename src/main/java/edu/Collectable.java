package edu;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public interface Collectable {

    String getTitle();

    int getItemsCount();

    int getPrice();

    String getItem(int index);

    int getDuration(int index);

    void setTitle(String title);

    void setPrice(int price);

    void setItem(int index, String title);

    void setDuration(int index, int duration);

    int getTotalDuration();

    void output(OutputStream out) throws IOException;

    void write(BufferedWriter out) throws IOException;
}
