package com.example.project6;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayImages extends AppCompatActivity {
    NasaMap nasaMap = NasaMap.getInstance();
    ImageView img0ImageView, img1ImageView, img2ImageView, img3ImageView, img4ImageView, img5ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_images);
        img0ImageView =findViewById(R.id.imageView0);
        img1ImageView =findViewById(R.id.imageView1);
        img2ImageView =findViewById(R.id.imageView2);
        img3ImageView =findViewById(R.id.imageView3);
        img4ImageView =findViewById(R.id.imageView4);
        img5ImageView =findViewById(R.id.imageView5);

    }
    public void loadImg0(View view){
        NasaMap.setCurrPos(0);
        String imgTitle = nasaMap.getRandImage();
        if (nasaMap.isInCache(imgTitle)) {
            img0ImageView.setImageBitmap(nasaMap.resizeBitmap(nasaMap.getImgBitmap(imgTitle)));
            nasaMap.updateInView(0, imgTitle);
        } else {
            DownloadImagesTask task = new DownloadImagesTask(img0ImageView);
            task.execute(nasaMap.getNasaMap().get(imgTitle).getUrl());
            nasaMap.updateInView(0, imgTitle);
        }

    }
    public void loadImg1(View view){
        NasaMap.setCurrPos(1);
        String imgTitle = nasaMap.getRandImage();
        if (nasaMap.isInCache(imgTitle)){
            img1ImageView.setImageBitmap(nasaMap.resizeBitmap(nasaMap.getImgBitmap(imgTitle)));
            nasaMap.updateInView(1, imgTitle);
        }
        else {
            DownloadImagesTask task = new DownloadImagesTask(img1ImageView);
            task.execute(nasaMap.getNasaMap().get(imgTitle).getUrl());
            nasaMap.updateInView(1, imgTitle);
        }

    }
    public void loadImg2(View view){
        NasaMap.setCurrPos(2);
        String imgTitle = nasaMap.getRandImage();
        if (nasaMap.isInCache(imgTitle)){
            img2ImageView.setImageBitmap(nasaMap.resizeBitmap(nasaMap.getImgBitmap(imgTitle)));
            nasaMap.updateInView(2, imgTitle);
        }
        else {
            DownloadImagesTask task = new DownloadImagesTask(img2ImageView);
            task.execute(nasaMap.getNasaMap().get(imgTitle).getUrl());
            nasaMap.updateInView(2, imgTitle);
        }

    }
    public void loadImg3(View view){
        NasaMap.setCurrPos(3);
        String imgTitle = nasaMap.getRandImage();
        if (nasaMap.isInCache(imgTitle)){
            img3ImageView.setImageBitmap(nasaMap.resizeBitmap(nasaMap.getImgBitmap(imgTitle)));
            nasaMap.updateInView(3, imgTitle);
        }
        else {
            DownloadImagesTask task = new DownloadImagesTask(img3ImageView);
            task.execute(nasaMap.getNasaMap().get(imgTitle).getUrl());
            nasaMap.updateInView(3, imgTitle);
        }

    }
    public void loadImg4(View view){
        NasaMap.setCurrPos(4);
        String imgTitle = nasaMap.getRandImage();
        if (nasaMap.isInCache(imgTitle)){
            img4ImageView.setImageBitmap(nasaMap.resizeBitmap(nasaMap.getImgBitmap(imgTitle)));
            nasaMap.updateInView(4, imgTitle);
        }
        else {
            DownloadImagesTask task = new DownloadImagesTask(img4ImageView);
            task.execute(nasaMap.getNasaMap().get(imgTitle).getUrl());
            nasaMap.updateInView(4, imgTitle);
        }

    }
    public void loadImg5(View view){
        NasaMap.setCurrPos(5);
        String imgTitle = nasaMap.getRandImage();
        if (nasaMap.isInCache(imgTitle)){
            img5ImageView.setImageBitmap(nasaMap.resizeBitmap(nasaMap.getImgBitmap(imgTitle)));
            nasaMap.updateInView(5, imgTitle);
        }
        else {
            DownloadImagesTask task = new DownloadImagesTask(img5ImageView);
            task.execute(nasaMap.getNasaMap().get(imgTitle).getUrl());
            nasaMap.updateInView(5, imgTitle);
        }

    }

}
