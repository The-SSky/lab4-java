package edu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

// import interfaces.Film;
// import interfaces.Serial;
// import interfaces.Collectable;


public class InputOutput {

    public static void outputLibrary(MediaLibrary o, OutputStream out) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(out);
        outputStream.writeObject(o);
    }

    public static MediaLibrary inputLibrary(InputStream in) throws IOException, ClassNotFoundException {
        if (in.available() > 0) {
            ObjectInputStream inputStream = new ObjectInputStream(in);
            return (MediaLibrary) inputStream.readObject();
        } else return null;
    }

    public static void writeLibrary(MediaLibrary o, BufferedWriter out) throws IOException {
        out.write(o.toStringWriter());
        out.newLine();
    }

    public static MediaLibrary readLibrary(BufferedReader in) throws IOException {
        if (in.ready()) {
            return getLibraryFromString(in.readLine());
        } else {
            return null;
        }
    }


    public static MediaLibrary getLibraryFromString(String s) {
        MediaLibrary library = null;

        int count = s.length();

        int ind = s.indexOf('|');
        String type = s.substring(0, ind);

        HashMap<String, Object> hashMap = new HashMap<>();

        for (int i = ind + 1; i < count; ) {
            ind = s.indexOf('|', i);
            String sub;
            if (ind > 0) {
                sub = s.substring(i, ind);
                i = ind + 1;
            } else {
                sub = s.substring(i, count);
                i = count;
            }
            int subIndex = sub.indexOf(':');
            String subType = sub.substring(0, subIndex);
            String subValue = sub.substring(subIndex + 1);

            if (subType.equals("price")) hashMap.put(subType, Integer.valueOf(subValue));
            else if (subType.equals("items")) hashMap.put(subType, getArrayFromString(subValue));
            else if (subType.equals("durations")) hashMap.put(subType, getIntArrayFromString(subValue));
            else hashMap.put(subType, subValue);
        }

        if (type.equals("AudioLibrary")) {
            String title_ = (String) hashMap.get("title");
            int price_ = (int) hashMap.get("price");
            String[] items_ = (String[]) hashMap.get("items");
            int[] durations_ = (int[]) hashMap.get("durations");
            AudioLibrary audioLib = new AudioLibrary(title_, price_, items_, durations_);

            library = audioLib;

        } else if (type.equals("VideoLibrary")) {
            String title_ = (String) hashMap.get("title");
            int price_ = (int) hashMap.get("price");
            String[] items_ = (String[]) hashMap.get("items");
            int[] durations_ = (int[]) hashMap.get("durations");
            VideoLibrary videoLib = new VideoLibrary(title_, price_, items_, durations_);

            library = videoLib;
        }
        return library;
    }

    private static int[] getIntArrayFromString(String value) {
        ArrayList<Integer> integers = new ArrayList<>();
        int index = 0;
        for (int i = index + 1; i < value.length() - 1; ) {
            index = value.indexOf(", ", i);
            String subString;
            if (index > 0) {
                subString = value.substring(i, index);
                i = index + 2;
            } else {
                subString = value.substring(i, value.length() - 1);
                i = value.length() - 1;
            }
            integers.add(Integer.valueOf(subString));
        }

        int[] ints = new int[integers.size()];
        for (int g = 0; g < ints.length; g++) ints[g] = integers.get(g);

        return ints;
    }

    private static String[] getArrayFromString(String value) {
        ArrayList<String> elements = new ArrayList<>();
        int index = 0;
        for (int i = index + 1; i < value.length() - 1; ) {
            index = value.indexOf(", ", i);
            String subString;
            if (index > 0) {
                subString = value.substring(i, index);
                i = index + 2;
            } else {
                subString = value.substring(i, value.length() - 1);
                i = value.length() - 1;
            }
            elements.add(subString);
        }

        String[] strs = new String[elements.size()];
        for (int g = 0; g < strs.length; g++) strs[g] = elements.get(g);

        return strs;
    }

}
