package com.divanxan.json;

import com.divanxan.dto.Product;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is required to read json data from InternetShop application.
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
public class JsonReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray jsonArray = new JSONArray(jsonText);
//            JSONObject json = new JSONObject(jsonText);
//            return json;
            return jsonArray;
        } finally {
            is.close();
        }
    }

    /**
     * This method return list top products for showcase
     *
     * @return List<Product> - top products
     * @throws IOException
     * @throws JSONException
     */
    public List<Product> getTop() throws IOException, JSONException {
        JSONArray json = readJsonFromUrl("http://192.168.99.100:8081/json/data/top");
        Gson gson = new Gson();
        List<Product> list = new ArrayList<>();
        for (int i = 0; i <json.length()-2 ; i++) {
            Product product = gson.fromJson(json.get(i).toString(), Product.class);
            list.add(product);
        }
        return list;
    }
}
