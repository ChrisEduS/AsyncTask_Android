package com.example.project6;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;

public class NasaMap {

    private static NasaMap instance;
    private Map<String, Nasa> nasaMap;
    private Map<String, Bitmap> cache = new HashMap<>();
    private Map<Integer, String> alreadyInScreen = new HashMap<>();
    private static int currPos = 0;

    private NasaMap() {
        nasaMap = new HashMap<>();
    }

    public static synchronized NasaMap getInstance() {
        if (instance == null) {
            instance = new NasaMap();
        }
        return instance;
    }
    public static synchronized int getCurrPos(){
        return currPos;
    }
    public static synchronized void setCurrPos(int pos){
        currPos = pos;
    }
    public synchronized void addNasa(Nasa nasa) {
        nasaMap.put(nasa.getTitle(), nasa);
    }

    public synchronized Nasa getNasa(String title) {
        return nasaMap.get(title);
    }

    public synchronized Bitmap getImgBitmap(String title){
        return cache.get(title);
    }
    public Bitmap resizeBitmap(Bitmap bitmap) {
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap,500, 500, false);
        return resizedBitmap;
    }
    public synchronized Map<String, Nasa> getNasaMap() {
        return nasaMap;
    }
    public synchronized void addToCache(String title, Bitmap url){
        cache.put(title, url);
    }
    public synchronized boolean isInCache(String title){
        return cache.containsKey(title);
    }
    public synchronized boolean isInView(int pos, String title){
        try {
            return alreadyInScreen.containsValue(title);
        } catch (Exception e){
            updateInView(pos, title);
            return false;
        }
    }
    public synchronized void updateInView(int pos, String title){
        alreadyInScreen.put(pos, title);
    }
    public synchronized String getRandImage(){
        Map.Entry<String, Nasa> randomEntry = nasaMap.entrySet()
                .stream()
                .skip(ThreadLocalRandom.current().nextInt(nasaMap.size()))
                .findFirst()
                .orElse(null);


        if (isInView(currPos, randomEntry.getKey())){
            return getRandImage();
        }
        else{
            return randomEntry.getKey();
        }
    }


}