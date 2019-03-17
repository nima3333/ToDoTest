package com.example.testrecyclerview;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    public static final String FILENAME = "listinfo.dat";

    public static void writeData(ArrayList<ListItem> items, Context context){
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();
            Toast.makeText(context, "Ajout réussi?", Toast.LENGTH_SHORT).show();
        }
        catch(FileNotFoundException e){
            Toast.makeText(context, "File not found", Toast.LENGTH_SHORT).show();
        }
        catch(IOException e){
            Log.e("EUH", "Exception", e);
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public static ArrayList<ListItem> readData(Context context){
        ArrayList<ListItem> itemList = new ArrayList<>();
        try{
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemList = (ArrayList<ListItem>) ois.readObject();
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
}