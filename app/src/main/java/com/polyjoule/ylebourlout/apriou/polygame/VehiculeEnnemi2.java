package com.polyjoule.ylebourlout.apriou.polygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import static com.polyjoule.ylebourlout.apriou.polygame.Game.VITESSEDEPLACEMENTENNEMI2;
import static com.polyjoule.ylebourlout.apriou.polygame.GameView.cvH;
import static com.polyjoule.ylebourlout.apriou.polygame.GameView.cvW;

/**
 * Created by Alexis on 22/08/2017.
 */

public class VehiculeEnnemi2 {
    private BitmapDrawable img=null; // image du vehiculeEnnemi
    private int x,y; // coordonnées x,y du vehiculeEnnemi en pixel
    private int vehiculeEnnemiW, vehiculeEnnemiH; // largeur et hauteur du vehiculeEnnemi en pixels
    private int vehiculeEnnemiW2, vehiculeEnnemiH2;
    private int wEcran,hEcran; // largeur et hauteur de l'écran en pixels
    private boolean move = false; // 'true' si la vehiculeEnnemi doit se déplacer automatiquement, 'false' sinon
    int exPosX=0;
    int exPosy=0;
    private boolean isOnItBool=false;
    // pour déplacer la vehiculeEnnemi on ajoutera INCREMENT à ses coordonnées x et y
    private static final int INCREMENT = VITESSEDEPLACEMENTENNEMI2;
    private int speedX=INCREMENT, speedY=INCREMENT;
    private Boolean switchDirection=false;
    private final double RATIO=2.6;

    // contexte de l'application Android
    // il servira à accéder aux ressources, dont l'image du vehiculeEnnemi
    private final Context mContext;

    // Constructeur de l'objet "vehiculeEnnemi"
    public VehiculeEnnemi2(final Context c)
    {
        x=cvW; y=-cvH/4; // position de départ
        mContext=c; // sauvegarde du contexte
    }

    // on attribue à l'objet "vehiculeEnnemi" l'image passée en paramètre
    // w et h sont sa largeur et hauteur définis en pixels
    public BitmapDrawable setImage(final Context c, final int ressource, final int w, final int h)
    {
        Drawable dr = c.getResources().getDrawable(ressource);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
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

//        Drawable d = mContext.getResources().getDrawable(R.drawable.vehiculeplayer2);
//
//        double ratio = d.getIntrinsicWidth()/d.getIntrinsicHeight();
//        Log.d("ratio",Double.toString(ratio));

        // on définit (au choix) la taille du vehiculeEnnemi
        vehiculeEnnemiH=11*hScreen/64; // 6  pour vehiculeplayer
        vehiculeEnnemiW=((int)Math.round(vehiculeEnnemiH/RATIO));



        img = setImage(mContext,R.drawable.aalborg,vehiculeEnnemiW,vehiculeEnnemiH); //vehiculeEnnemi3


    }

    // définit la coordonnée X du vehiculeEnnemi
    public void setX(int x) {
        this.x = x;
    }

    // définit la coordonnée Y du vehiculeEnnemi
    public void setY(int y) {
        this.y = y;
    }

    // retourne la coordonnée X du vehiculeEnnemi
    public int getX() {
        return x;
    }

    // retourne la coordonnée Y du vehiculeEnnemi
    public int getY() {
        return y;
    }

    // retourne la largeur du vehiculeEnnemi en pixel
    public int getvehiculeEnnemiW() {
        return vehiculeEnnemiW;
    }

    // retourne la hauteur du vehiculeEnnemi en pixel
    public int getvehiculeEnnemiH() {
        return vehiculeEnnemiH;
    }

    // déplace la vehiculeEnnemi en détectant les collisions avec les bords de l'écran
    public void moveHautBas()
    {

        if(!move) {return;}

        y+=speedY;

    }


    // on dessine la vehiculeEnnemi, en x et y
    public void draw(Canvas canvas)
    {
        if(img==null) {return;}
        canvas.drawBitmap(img.getBitmap(), x, y, null);
    }

    public Boolean hasBeenTouched(int x, int y, int w, int h){
        if(x<this.x+getvehiculeEnnemiW() && x>this.x && ((y>this.y && y<this.y+getvehiculeEnnemiH())|| (y+h>this.y && y+h<this.y+getvehiculeEnnemiH()) )){
            return true;
        } else {
            // véhicule plus petit que bonbonne
            if(this.y>y && this.y+getvehiculeEnnemiH()<y+h && x<this.x+getvehiculeEnnemiW() && x>this.x){
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

    public void setVitesse (int vitesse){
        speedY=vitesse;
    }
}
