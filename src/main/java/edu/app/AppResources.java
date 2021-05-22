package edu.app;

import java.util.Random;

import edu.AudioLibrary;
import edu.Collectable;
import edu.VideoLibrary;

public class AppResources {
    static int MAX_POSSIBLE_PRICE = 15;
    static int MIN_POSSIBLE_ITEMS = 5;
    static int MAX_POSSIBLE_ITEMS = 15;
    static int MIN_POSSIBLE_DURATION = 2 * 60;
    static int MAX_POSSIBLE_DURATION = 135 * 60;

    static void populate(Collectable[] libraries, int AL_COUNT, int VL_COUNT){
        int alToBeCreated = AL_COUNT;
        int vlToBeCreated = VL_COUNT;
        int type;
        for(int i = 0; alToBeCreated + vlToBeCreated > 0; i++){
            type = randInt(2);
            String title;
            int count = randInt(MIN_POSSIBLE_ITEMS, MAX_POSSIBLE_ITEMS);
            int price = randInt(MAX_POSSIBLE_PRICE);

            if (type == 0 && alToBeCreated > 0){
                title = "Audio Library " + i;
                libraries[i] = new AudioLibrary(title, price, count);
                alToBeCreated--;
            }
            else if (type == 1 && vlToBeCreated > 0) {
                title = "Video Library " + i;
                libraries[i] = new VideoLibrary(title, price, count);
                vlToBeCreated--;
            }
            else{
                i--;
                continue;
            }

            configureItemsInside(libraries[i]);

        }
    }

    static Collectable[] createNewCollectionForEqualTotalDurations(Collectable[] library){
        /* Получаем массив флагов о том что у объекта есть одинаковый
        * по функциональному методу,
        * потом считаем количество объектов
        * и с у четом флага формируем массив-ответ
        * */
        boolean[] hasTotalDurationEqual = findEqualToOther(library);
        int resultLength = 0;
        for (int i = 0; i < hasTotalDurationEqual.length; i++){
            if (hasTotalDurationEqual[i]){
                resultLength += 1;
            }
        }

        Collectable[] result = new Collectable[resultLength];

        for(int i = 0, j = 0; i < library.length; i++){
            if (hasTotalDurationEqual[i]){
                result[j] = library[i];
                j++;
            }
        }
        return result;
    }

    private static boolean[] findEqualToOther(Collectable[] library){
        boolean[] result = new boolean[library.length];
        for(int i = 0; i < library.length; i++){
            for(int j = i + 1; j < library.length; j++){
                if (library[j].getTotalDuration() == library[i].getTotalDuration()){
                    result[i] = true;
                    result[j] = true;
                }
            }
        }
        return result;
    }

    static Collectable[] createNewCollectionByType(Collectable[] library, Class<?> cls){
        int length = findNumberOfLibsByType(library, cls);
        Collectable[] result = new Collectable[length];
        for (int i = 0, j = 0; i < library.length; i++){
            if(library[i].getClass().equals(cls)){
                result[j] = library[i];
                j++;
            }
        }
        return result;
    }

    static int findNumberOfLibsByType(Collectable[] library, Class<?> cls){
        int result = 0;
        for(int i = 0; i < library.length; i++){
            if(library[i].getClass().equals(cls)){
                result++;
            }
        }
        return result;
    }

    private static void configureItemsInside(Collectable library){
        for (int i = 0; i < library.getItemsCount(); i++){
            library.setItem(i, "Media Source " + i);
            library.setDuration(i ,randInt(MIN_POSSIBLE_DURATION, MAX_POSSIBLE_DURATION));
        }
    }

    static void print(Collectable[] libraries) {
        for (Collectable library : libraries){
            System.out.print(library);
        }
    }

    static int randInt(int min, int max) {
        int result;

        Random rand = new Random();
        result = min + rand.nextInt(max - min);

        return result;
    }

    static int randInt(int max) {
        int min = 0;
        int result;

        Random rand = new Random();
        result = min + rand.nextInt(max - min);

        return result;
    }
}
