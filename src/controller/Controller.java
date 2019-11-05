package controller;
import java.io.*;
import java.util.Random;


public class Controller {
    public static void main(String[] args) throws IOException {
        fillFIle();
        int[] array = parseIntoArray("ages.txt");
        formOutputArr(array,"newAges");
    }

    private static int[] parseIntoArray(String file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[2];
        int[] arr = new int[32767];//Максимальное 16бит число
        while (fis.read(data) != -1) {
            int s = (((data[0] & 0xFF) << 8) | (data[1] & 0xFF));
            arr[s] = arr[s] + 1;
        }
        return arr;
    }

    public static void fillFIle() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("ages.txt");
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            byte[] ar = new byte[2];
            ar[0] = 0;
            ar[1] = (byte) random.nextInt(5);
            fileOutputStream.write(ar);
        }
        fileOutputStream.close();
    }


    private static void formOutputArr(int[] arr,String toFile) throws IOException {
        FileOutputStream fos = new FileOutputStream(toFile);
        DataOutputStream dos = new DataOutputStream(fos);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                int ageVal = i;
                int ageCount = arr[i];
                int valToWrite = (ageVal << 16) | ageCount;
                dos.writeInt(valToWrite);
            }
        }
        fos.close();
        dos.close();
    }


}
