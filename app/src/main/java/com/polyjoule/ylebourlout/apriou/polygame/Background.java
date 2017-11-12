package com.polyjoule.ylebourlout.apriou.polygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import static com.polyjoule.ylebourlout.apriou.polygame.GameView.cvH;
import static com.polyjoule.ylebourlout.apriou.polygame.GameView.cvW;

/**
 * Created by Alexis on 19/07/2017.
 */

public class Background {
    private Bitmap image;
    private int x, y, dx, dy;
    private int wEcran,hEcran; // largeur et hauteur de l'écran en pixels
    private final int bgWIDTH=1000;
    private int bgW, bgH;
    private Context c;
    private Boolean resize=false;
    private float scaleX, scaley;
    private Bitmap bitmap;
    private Boolean move=false;


    public Background(Bitmap res)
    {
        //res.setWidth(cvW);

        image = res;

    }
    public void update()
    {

        if(!move){return;}
        y-=dy;
        if(bitmap!=null) {
            if (y+bitmap.getHeight() > 2*bitmap.getHeight()) {
                y = 0;
            }
        }

    }
    public void draw(Canvas canvas)
    {

        if (!resize) {

            Log.d("heigh",Integer.toString(image.getHeight()));
            Log.d("width",Integer.toString(image.getWidth()));
            Log.d("cvH",Integer.toString(image.getHeight()));
            Log.d("cvW",Integer.toString(cvW));
            //int scale = image.getHeight() / cvH;
            bitmap = Bitmap.createScaledBitmap(image, cvW,
                        cvH
                        , true);

//            if (scale < 1) {
//                scale = cvW / image.getHeight();
//
//                bitmap = Bitmap.createScaledBitmap(image, image.getWidth() * scale,
//                        cvH
//                        , true);
//            } else {
//                bitmap = Bitmap.createScaledBitmap(image, image.getWidth() / scale,
//                        cvH
//                        , true);
//            }
            resize = true;
            canvas.drawBitmap(bitmap, x, y, null);
        } else {
            canvas.drawBitmap(bitmap, x, y, null);
        }

        if(y+bitmap.getHeight()>cvH)
        {
            canvas.drawBitmap(bitmap, x, y-bitmap.getHeight(), null);
        }

    }
    public void setVector(int dy)
    {
        this.dy = dy;
    }
    public int getVitesse (){return dy;}
    public void setMove (Boolean bool){move=bool;}

    public int getX () {
        return x;
    }
    public int getBgW(){
        return bitmap.getWidth();
    }
    public int getBgH(){
        return bitmap.getHeight();
    }

    public void resize(int wScreen, int hScreen) {
        // wScreen et hScreen sont la largeur et la hauteur de l'écran en pixel
        // on sauve ces informations en variable globale, car elles serviront
        // à détecter les collisions sur les bords de l'écran
        wEcran=wScreen;
        hEcran=hScreen;





    }
}
