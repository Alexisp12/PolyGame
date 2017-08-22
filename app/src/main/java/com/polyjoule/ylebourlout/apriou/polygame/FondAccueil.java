//package com.polyjoule.ylebourlout.apriou.polygame;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//
//import static com.polyjoule.ylebourlout.apriou.polygame.AccueilView.cvH;
//import static com.polyjoule.ylebourlout.apriou.polygame.AccueilView.cvW;
//
///**
// * Created by Alexis on 21/08/2017.
// */
//
//public class FondAccueil {
//
//    private Bitmap image;
//    private int x, y, dx, dy;
//    private int wEcran,hEcran; // largeur et hauteur de l'écran en pixels
//    private final int bgWIDTH=1000;
//    private int bgW, bgH;
//    private Context c;
//    private Boolean resize=false;
//    private float scaleX, scaley;
//    private Bitmap bitmap;
//    private Boolean move=false;
//
//
//    public FondAccueil(Bitmap res)
//    {
//        //res.setWidth(cvW);
//
//        image = res;
//
//    }
//    public void update()
//    {
//
//        if(!move){return;}
//        y-=dy;
//        if(bitmap!=null) {
//            if (y+bitmap.getHeight() > 2*bitmap.getHeight()) {
//                y = 0;
//            }
//        }
//
//    }
//    public void draw(Canvas canvas)
//    {
//
//        if (!resize) {
//            //int scale = image.getHeight() / cvH;
//            bitmap = Bitmap.createScaledBitmap(image, cvW,
//                    cvH
//                    , true);
//
////            if (scale < 1) {
////                scale = cvW / image.getHeight();
////
////                bitmap = Bitmap.createScaledBitmap(image, image.getWidth() * scale,
////                        cvH
////                        , true);
////            } else {
////                bitmap = Bitmap.createScaledBitmap(image, image.getWidth() / scale,
////                        cvH
////                        , true);
////            }
//            resize = true;
//            canvas.drawBitmap(bitmap, 0, 0, null);
//        } else {
//            canvas.drawBitmap(bitmap, 0, 0, null);
//        }
//
//
//    }
//    public void setVector(int dy)
//    {
//        this.dy = dy;
//    }
//
//    public void setMove (Boolean bool){move=bool;}
//
//    public int getX () {
//        return x;
//    }
//    public int getFondEcranW(){
//        return bitmap.getWidth();
//    }
//    public int getFondEcranH(){
//        return bitmap.getHeight();
//    }
//
//    public void resize(int wScreen, int hScreen) {
//        // wScreen et hScreen sont la largeur et la hauteur de l'écran en pixel
//        // on sauve ces informations en variable globale, car elles serviront
//        // à détecter les collisions sur les bords de l'écran
//        wEcran=wScreen;
//        hEcran=hScreen;
//
//
//
//
//
//    }
//}
