package edu;

import java.util.Arrays;

public class VideoLibrary extends MediaLibrary{
    public VideoLibrary(){
        super();
    }
    public VideoLibrary(String title, int price, int count){
        super(title, price, count);
    }
    public VideoLibrary(String title, int price, String[] collection, int[] durations){
        super(title, price, collection, durations);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(0);
        result.append("Название видеотеки: ").append(getTitle()).append('\n');
        result.append("Цена: ").append(getPrice()).append('\n');
        result.append("Общая продолжительность в минутах: ");
        result.append(getTotalDuration() / 60).append('\n');
        result.append("Всего видеофайлов: ").append(getItemsCount()).append('\n');
        result.append("-----------------\n");

        return result.toString();
    }

    @Override
    public String toStringWriter() {
        return "VideoLibrary|title:" + getTitle()
                + "|price:" + getPrice()
                + "|items:" + Arrays.toString(getCollection())
                + "|durations:" + Arrays.toString(getDurations());
    }
}

