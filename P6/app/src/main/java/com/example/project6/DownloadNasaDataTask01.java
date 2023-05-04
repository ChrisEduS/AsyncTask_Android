package com.example.project6;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadNasaDataTask01 extends AsyncTask<String,Nasa,String> {
    NasaMap nasaMap = NasaMap.getInstance();
    private CustomAdapter mAdapter;
    private Button dispButton;

    CustomAdapter adaptador;

   public DownloadNasaDataTask01(ListView list, Button button){
       adaptador = (CustomAdapter) list.getAdapter() ;
       this.dispButton = button;
    }


    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = result.replaceAll("&quot;", "\"");
        result = result.replaceAll("&#39;", "\'");

        String pattern = "<div id='results'>(.*)</div>";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(result);
        if (m.find()) {
            String resultsHtml = m.group(1);
            Pattern linkPattern = Pattern.compile("<a[^>]*href=\"([^\"]+)\"[^>]*>([^<]+)</a>");
            Matcher linkMatcher = linkPattern.matcher(resultsHtml);

            while (linkMatcher.find()) {
                String match = linkMatcher.group(0);
                if (match.contains("<a class="))
                    break;

                String http = linkMatcher.group(1);
                String title = linkMatcher.group(2);
                //FIRST DOWNLOAD DONE -----------------------------------------------------

                //SECOND DOWNLOAD ----------------------------------------------------------------
                String result1 = "";
                try {
                    URL url = new URL(http);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result1 += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    urlConnection.disconnect();

                    // Define the regular expression pattern to match the "Download JPG" label block
                    Pattern getimgPattern = Pattern.compile("href=\"([^\"]+\\.jpg)\"");

                    // Create a Matcher object to apply the pattern to the HTML source code
                    Matcher matcher = getimgPattern.matcher(result1);

                    // Find the first match of the pattern in the HTML source code
                    if (matcher.find()) {
                        // Extract the URL from the matching group
                        String imageurl = matcher.group(1);
                        try {
                            URL fURL = new URL(imageurl);
                            HttpURLConnection imgConnection = (HttpURLConnection) fURL.openConnection();
                            if (imgConnection.getContentLength() < 1000000) {
                                if (imageurl.contains("https")) {
                                    Nasa currNasa = new Nasa(title, http, imageurl);
                                    nasaMap.addNasa(currNasa);
                                    publishProgress(currNasa);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        //SECOND DOWNLOAD DONE -----------------------------------------------------------------
        return null;
    }

    @Override
    protected void onProgressUpdate(Nasa... nasa) {
        // add the new item to the adapter and notify it
        adaptador.add(nasa[0]);}

    @Override
    protected void onPostExecute(String result) {
        // Do something with the result
        dispButton.setEnabled(true);
    }
}
