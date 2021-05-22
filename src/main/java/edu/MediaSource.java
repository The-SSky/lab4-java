package edu;

public class MediaSource {

    private String title;
    private int numOfAbstractPages;
    private String[] articles;
    private int[] numsOfPages;

    private String name;
    private int length;

    public MediaSource(String name, int length){
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }
}
