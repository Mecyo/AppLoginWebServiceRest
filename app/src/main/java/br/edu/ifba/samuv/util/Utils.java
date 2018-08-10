package br.edu.ifba.samuv.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by Emerson Santos 03/08/2018.
 */
public class Utils {

    public static Object JsonToObject(String jsonInString, Class objectClass) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        //JSON from String to Object
        return mapper.readValue(jsonInString, objectClass);
    }

    public static String objectToJson(Object jsonObject, Class objectClass) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        //JSON from Object to String
        return mapper.writeValueAsString(objectClass.cast(jsonObject));
    }

    private Bitmap baixarImagem(String url) {
        try{
            URL endereco;
            InputStream inputStream;
            Bitmap imagem; endereco = new URL(url);
            inputStream = endereco.openStream();
            imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return imagem;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File bitmapToFile(Bitmap bitmap, Context context, String name){
        File filesDir = context.getFilesDir();
        File imageFile = new File(filesDir, name);

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            //Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
        }

        return imageFile;
    }
}