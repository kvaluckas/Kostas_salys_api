package com.example.asynctaskwithapiexample.utilities;

import com.example.asynctaskwithapiexample.parsers.FloatRatesXmlParser;
import com.example.asynctaskwithapiexample.parsers.GunfireHtmlParser;
import com.example.asynctaskwithapiexample.parsers.MeteoLtJsonParser;
import com.example.asynctaskwithapiexample.parsers.NamesJsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import static com.example.asynctaskwithapiexample.utilities.Constants.NAMES_URL;

public class ApiDataReader {
    public static ArrayList<String> getValuesFromApi(String apiCode) throws IOException {
        InputStream apiContentStream = null;
        ArrayList<String> resultList = new ArrayList<>();
        try {
            if(apiCode.contains(NAMES_URL))
            {
                    apiContentStream = downloadUrlContent(apiCode);
                    resultList = NamesJsonParser.getNamesInfo(apiContentStream);
            }
        }
        finally {
            if (apiContentStream != null) {
                apiContentStream.close();
            }
        }
        return resultList;
    }

    //Routine that creates and calls GET request to web page
    private static InputStream downloadUrlContent(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }
}
