package br.edu.ifba.samuv.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
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
}