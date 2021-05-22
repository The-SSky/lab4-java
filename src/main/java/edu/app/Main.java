package edu.app;

import edu.*;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {

            System.out.println("OutputStream/InputStream:\n");

            //Запись
            FileOutputStream outputStream = new FileOutputStream("data.ser");
            new AudioLibrary("A", 100, 10).output(outputStream);
            new VideoLibrary("V", 200, 5).output(outputStream);

            outputStream.close();

            //Чтение
            FileInputStream inputStream = new FileInputStream("data.ser");
            while (inputStream.available()>0){
                MediaLibrary mediaLib = InputOutput.inputLibrary(inputStream);
                if(mediaLib!=null){
                    System.out.println("Read:\n "+ mediaLib.toString());
                }
            }
            inputStream.close();


            System.out.println("\n***********\n");
            System.out.println("\nWriter/Reader:\n");

            //Запись
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data.txt"));
            String[] items = {"abc", "def", "ghk", "lmn"};
            int[] dur1 = {5, 6, 7, 8};
            int[] dur2 = {20, 21, 22, 23};
            new AudioLibrary("A",100, items, dur1).write(bufferedWriter);
            new VideoLibrary("V", 200, items, dur2).write(bufferedWriter);
            bufferedWriter.close();

            //Чтение
            BufferedReader bufferedReader = new BufferedReader(new FileReader("data.txt"));
            while (bufferedReader.ready()){
                MediaLibrary mediaLib = InputOutput.readLibrary(bufferedReader);
                if(mediaLib!=null){
                    System.out.println("Read: " + mediaLib.toStringWriter());
                }else break;
            }
            bufferedReader.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
