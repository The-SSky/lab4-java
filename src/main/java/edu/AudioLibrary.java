package edu;

import java.util.Arrays;

public class AudioLibrary extends MediaLibrary{
    public AudioLibrary(){
        super();
    }
    public AudioLibrary(String title, int price, int count){
        super(title, price, count);
    }
    public AudioLibrary(String title, int price, String[] collection, int[] durations){
        super(title, price, collection, durations);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(0);
        result.append("Название аудиотеки: ").append(getTitle()).append('\n');
        result.append("Цена: ").append(getPrice()).append('\n');
        result.append("Общая продолжительность в минутах: ");
        result.append(getTotalDuration() / 60).append('\n');
        result.append("Всего аудиофайлов: ").append(getItemsCount()).append('\n');
        result.append("-----------------\n");

        return result.toString();
    }

    @Override
    public String toStringWriter() {
        return "AudioLibrary|title:" + getTitle()
                + "|price:" + getPrice()
                + "|items:" + Arrays.toString(getCollection())
                + "|durations:" + Arrays.toString(getDurations());
    }
}
