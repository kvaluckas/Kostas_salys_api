package com.example.asynctaskwithapiexample.parsers;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
public class NamesJsonParser {
    public static ArrayList<String> getNamesInfo(InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line = "";
        String data = "";
        ArrayList<String> resultList = new ArrayList<>();
        while(line != null){
            line = bufferedReader.readLine();
            data = data + line;
        }
        try {
            JSONObject jData = new JSONObject(data);

            JSONArray nameNodes = jData.getJSONArray("country");
            for (int i = 0, size = nameNodes.length(); i < size; i++)
            {
                JSONObject nameNode = nameNodes.getJSONObject(i);
                String name = nameNode.getString("country_id");
                String prob = nameNode.getString("probability");
                //Add to arraylist
                resultList.add(String.format("salis %s , tikimybe %s", name, prob));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultList;
    }

}
