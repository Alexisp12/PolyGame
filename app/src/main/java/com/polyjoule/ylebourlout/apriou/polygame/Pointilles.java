package com.polyjoule.ylebourlout.apriou.polygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import static com.polyjoule.ylebourlout.apriou.polygame.AccueilView.cvH;
import static com.polyjoule.ylebourlout.apriou.polygame.AccueilView.cvW;

/**
 * Created by Alexis on 11/08/2017.
 */

public class Pointilles {
    private BitmapDrawable img=null; // image du pointilles
    private int x,y; // coordonnées x,y du pointilles en pixel
    private int pointillesW, pointillesH; // largeur et hauteur du pointilles en pixels
    private int pointillesW2, pointillesH2;
    private int wEcran,hEcran; // largeur et hauteur de l'écran en pixels
    private boolean move = false; // 'true' si la pointilles doit se déplacer automatiquement, 'false' sinon
    int exPosX=0;
    int exPosy=0;
    private boolean isOnItBool=false;
    // pour déplacer la pointilles on ajoutera INCREMENT à ses coordonnées x et y
    private static final int INCREMENT = 3;
    private int speedX=INCREMENT, speedY=INCREMENT;
    private Boolean switchDirection=false;
    private final double RATIO=1.8;
    private double widthOriginal;
    private double heightOriginal;

    // contexte de l'application Android
    // il servira à accéder aux ressources, dont l'image du pointilles
    private final Context mContext;

    // Constructeur de l'objet "pointilles"
    public Pointilles(final Context c)
    {
        x=cvW; y=cvH; // position de départ
        mContext=c; // sauvegarde du contexte
    }

    // on attribue à l'objet "pointilles" l'image passée en paramètre
    // w et h sont sa largeur et hauteur définis en pixels
    public BitmapDrawable setImage(final Context c, final int ressource, final int w, final int h)
    {
        Drawable dr = c.getResources().getDrawable(ressource);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
//        widthOriginal=bitmap.getWidth();
//        heightOriginal=bitmap.getHeight();


        return new BitmapDrawable(c.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
    }


    public boolean isMoving() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    // redimensionnement de l'image selon la largeur/hauteur de l'écran passés en paramètre
    public void resize(int wScreen, int hScreen) {
        // wScreen et hScreen sont la largeur et la hauteur de l'écran en pixel
        // on sauve ces informations en variable globale, car elles serviront
        // à détecter les collisions sur les bords de l'écran
        wEcran=wScreen;
        hEcran=hScreen;

        Drawable dr = mContext.getResources().getDrawable(R.drawable.pointilles);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        widthOriginal=bitmap.getWidth();
        heightOriginal=bitmap.getHeight();



        // on définit (au choix) la taille du pointilles
        pointillesH=4*hScreen/10;
        pointillesW=((int)Math.round(pointillesH/(heightOriginal/widthOriginal)));
        //pointillesH=hScreen/8;
        //pointillesH= ((int) Math.round( pointillesW/ratio)); // 12 pour vehiculeplayer

        //d=null;


        img = setImage(mContext,R.drawable.pointilles,pointillesW,pointillesH); //pointilles3


    }

    // définit la coordonnée X du pointilles
    public void setX(int x) {
        this.x = x;
    }

    // définit la coordonnée Y du pointilles
    public void setY(int y) {
        this.y = y;
    }

    // retourne la coordonnée X du pointilles
    public int getX() {
        return x;
    }

    // retourne la coordonnée Y du pointilles
    public int getY() {
        return y;
    }

    // retourne la largeur du pointilles en pixel
    public int getpointillesW() {
        return pointillesW;
    }

    // retourne la hauteur du pointilles en pixel
    public int getpointillesH() {
        return pointillesH;
    }

    // déplace la pointilles en détectant les collisions avec les bords de l'écran
    public void move()
    {

        if(!move) {return;}

        y+=speedY;

    }


    // on dessine la pointilles, en x et y
    public void draw(Canvas canvas)
    {
        if(img==null) {return;}
        canvas.drawBitmap(img.getBitmap(), x, y, null);
    }

    public Boolean hasBeenTouched(int x, int y, int w, int h){
        if(x<this.x+getpointillesW() && x>this.x && ((y>this.y && y<this.y+getpointillesH())|| (y+h>this.y && y+h<this.y+getpointillesH()) )){
            return true;
        } else {
            // véhicule plus petit que bonbonne
            if(this.y>y && this.y+getpointillesH()<y+h && x<this.x+getpointillesW() && x>this.x){
                return true;
            } else {
                return false;
            }
        }
    }

    public void isOnIt(boolean bool){
        isOnItBool= bool;
    }
    public boolean getisOnIt(){
        return isOnItBool;
    }
}