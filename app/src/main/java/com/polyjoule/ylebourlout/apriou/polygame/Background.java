package com.polyjoule.ylebourlout.apriou.polygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import static com.polyjoule.ylebourlout.apriou.polygame.GameView.cvH;

/**
 * Created by Alexis on 19/07/2017.
 */

public class Background {
    private Bitmap image;
    private int x, y, dx;
    private int wEcran,hEcran; // largeur et hauteur de l'écran en pixels
    private final int bgWIDTH=1000;
    private int bgW, bgH;
    private Context c;
    private Boolean resize=false;
    private float scaleX, scaleY;
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
        x+=dx;
        if(bitmap!=null) {
            if (x < -bitmap.getWidth()) {
                x = 0;
            }
        }

    }
    public void draw(Canvas canvas)
    {

        if (!resize) {
            int scale = image.getHeight() / cvH;
            if (scale < 1) {
                scale = cvH / image.getHeight();
                bitmap = Bitmap.createScaledBitmap(image, image.getWidth() * scale,
                        cvH
                        , true);
            } else {
                bitmap = Bitmap.createScaledBitmap(image, image.getWidth() / scale,
                        cvH
                        , true);
            }
            resize = true;
            canvas.drawBitmap(bitmap, x, y, null);
        } else {
            canvas.drawBitmap(bitmap, x, y, null);
        }

        if(x<0)
        {
            canvas.drawBitmap(bitmap, x+bitmap.getWidth(), y, null);
        }

    }
    public void setVector(int dx)
    {
        this.dx = dx;
    }

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
