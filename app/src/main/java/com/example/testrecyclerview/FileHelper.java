package com.example.testrecyclerview;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper {
    private static final ArrayList<String> filename = new ArrayList<>();

    static{
        filename.add("listinfo1.dat");
        filename.add("listinfo2.dat");
        filename.add("listinfo3.dat");
        filename.add("listinfo4.dat");
    }

    public static void writeData(ArrayList<Task> items, Context context, int indice){
        try {
            FileOutputStream fos = context.openFileOutput(filename.get(indice), Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();
            //Toast.makeText(context, "Ajout réussi", Toast.LENGTH_SHORT).show();
        }
        catch(FileNotFoundException e){
            //Toast.makeText(context, "File not found", Toast.LENGTH_SHORT).show();
        }
        catch(IOException e){
            Log.e("EUH", "Exception", e);
            //Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public static ArrayList<Task> readData(Context context, int indice){
        ArrayList<Task> itemList = new ArrayList<>();
        try{
            FileInputStream fis = context.openFileInput(filename.get(indice));
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemList = (ArrayList<Task>) ois.readObject();
        }
        catch(FileNotFoundException e){
            itemList = new ArrayList<>();
        }
        catch(IOException e){
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return itemList;
    }

    public static ArrayList<Integer> getSize(Context context){
        ArrayList<Integer> liste = new ArrayList<>();
        for(int i = 0; i<filename.size(); i++){
            ArrayList<Task> temp = readData(context, i);
            liste.add(temp.size());
        }
        return liste;
    }
}