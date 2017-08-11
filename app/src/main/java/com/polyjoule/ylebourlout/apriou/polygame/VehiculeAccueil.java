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

public class VehiculeAccueil {
    private BitmapDrawable img = null; // image du vehiculeAccueil
    private int x, y; // coordonnées x,y du vehiculeAccueil en pixel
    private int vehiculeAccueilW, vehiculeAccueilH; // largeur et hauteur du vehiculeAccueil en pixels
    private int vehiculeAccueilW2, vehiculeAccueilH2;
    private int wEcran, hEcran; // largeur et hauteur de l'écran en pixels
    private boolean move = false; // 'true' si la vehiculeAccueil doit se déplacer automatiquement, 'false' sinon
    int exPosX = 0;
    int exPosy = 0;
    private boolean isOnItBool = false;
    // pour déplacer la vehiculeAccueil on ajoutera INCREMENT à ses coordonnées x et y
    private static final int INCREMENT = 2;
    private int speedX = INCREMENT, speedY = INCREMENT;
    private Boolean switchDirection = false;
    private final double RATIO = 2.6;
    private Boolean stop=false;

    // contexte de l'application Android
    // il servira à accéder aux ressources, dont l'image du vehiculeAccueil
    private final Context mContext;

    // Constructeur de l'objet "vehiculeAccueil"
    public VehiculeAccueil(final Context c) {
        x = cvW;
        y = cvH;
        mContext = c; // sauvegarde du contexte
    }

    // on attribue à l'objet "vehiculeAccueil" l'image passée en paramètre
    // w et h sont sa largeur et hauteur définis en pixels
    public BitmapDrawable setImage(final Context c, final int ressource, final int w, final int h) {
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
        wEcran = wScreen;
        hEcran = hScreen;

//        Drawable d = mContext.getResources().getDrawable(R.drawable.vehiculeplayer2);
//
//        double ratio = d.getIntrinsicWidth()/d.getIntrinsicHeight();
//        Log.d("ratio",Double.toString(ratio));

        // on définit (au choix) la taille du vehiculeAccueil
        vehiculeAccueilH = 6 * hScreen / 64;
        vehiculeAccueilW = ((int) Math.round(vehiculeAccueilH / RATIO));
        //vehiculeAccueilH=hScreen/8;
        //vehiculeAccueilH= ((int) Math.round( vehiculeAccueilW/ratio)); // 12 pour vehiculeplayer

        //d=null;


        img = setImage(mContext, R.drawable.vehiculeaccueil, vehiculeAccueilW, vehiculeAccueilH); //vehiculeAccueil3


    }

    // définit la coordonnée X du vehiculeAccueil
    public void setX(int x) {
        this.x = x;
    }

    // définit la coordonnée Y du vehiculeAccueil
    public void setY(int y) {
        this.y = y;
    }

    // retourne la coordonnée X du vehiculeAccueil
    public int getX() {
        return x;
    }

    // retourne la coordonnée Y du vehiculeAccueil
    public int getY() {
        return y;
    }

    // retourne la largeur du vehiculeAccueil en pixel
    public int getvehiculeAccueilW() {
        return vehiculeAccueilW;
    }

    // retourne la hauteur du vehiculeAccueil en pixel
    public int getvehiculeAccueilH() {
        return vehiculeAccueilH;
    }

    // déplace la vehiculeAccueil en détectant les collisions avec les bords de l'écran
    public void moveWithCollisionDetection() {
        // si on ne doit pas déplacer la vehiculeAccueil (lorsqu'elle est sous le doigt du joueur)
        // on quitte
        if (!move) {
            return;
        }

        // on incrémente les coordonnées X et Y
        x += speedX;
        y += speedY;

        // si x dépasse la largeur de l'écran, on inverse le déplacement
        if (x + vehiculeAccueilW > wEcran) {
            speedX = -INCREMENT;
        }

        // si y dépasse la hauteur l'écran, on inverse le déplacement
        if (y + vehiculeAccueilH > hEcran) {
            speedY = -INCREMENT;
        }

        // si x passe à gauche de l'écran, on inverse le déplacement
        if (x < 0) {
            speedX = INCREMENT;
        }

        // si y passe à dessus de l'écran, on inverse le déplacement
        if (y < 0) {
            speedY = INCREMENT;
        }
    }

    public void avance(){

        if(!move){
            return;
        }
        if(y<cvH-9*getvehiculeAccueilH()/8){
            stop=true;
            return;
        }

        y-=speedY;

    }

    public Boolean isStop(){
        return stop;
    }


    // on dessine la vehiculeAccueil, en x et y
    public void draw(Canvas canvas) {
        if (img == null) {
            return;
        }
        canvas.drawBitmap(img.getBitmap(), x, y, null);
    }

    public Boolean hasBeenTouched(int x, int y, int w, int h) {
//        if(x<this.x+getvehiculeAccueilW() && x>this.x && ((y>this.y && y<this.y+getvehiculeAccueilH())|| (y+h>this.y && y+h<this.y+getvehiculeAccueilH()) )){
//            return true;
//        }
//        // véhicule plus petit que bonbonne
//        if(this.y>y && this.y+getvehiculeAccueilH()<y+h && x<this.x+getvehiculeAccueilW() && x>this.x){
//            return true;
//        }
//        if(this.x>x && this.x+getvehiculeAccueilW()<x+w && y<this.y+getvehiculeAccueilH() && y>this.y){
//            return true;
//        }

        if (x > this.x && x < this.x + getvehiculeAccueilW() && ((y + h > this.y && y + h < this.y + getvehiculeAccueilH()) || y > this.y && y < this.y + getvehiculeAccueilH())) {
            return true;
        }
        if (x + w > this.x && x + w < this.x + getvehiculeAccueilW() && ((y + h > this.y && y + h < this.y + getvehiculeAccueilH()) || y > this.y && y < this.y + getvehiculeAccueilH())) {
            return true;
        }
        if (this.x < x + w && this.x > x && ((y + h > this.y && y + h < this.y + getvehiculeAccueilH()) || y > this.y && y < this.y + getvehiculeAccueilH())) {
            return true;
        }
        return false;
    }

    public void isOnIt(boolean bool) {
        isOnItBool = bool;
    }

    public boolean getisOnIt() {
        return isOnItBool;
    }
}