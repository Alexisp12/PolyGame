package com.polyjoule.ylebourlout.apriou.polygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.INCREMENTZOOMPANNEAUNANTES;
import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.VITESSEDEPLACEMENTNANTES;
import static com.polyjoule.ylebourlout.apriou.polygame.AccueilView.cvH;
import static com.polyjoule.ylebourlout.apriou.polygame.AccueilView.cvW;

/**
 * Created by Alexis on 29/04/2018.
 */

public class PanneauValencienne {
    private BitmapDrawable img=null; // image du panneauNantes
    private int x,y; // coordonnées x,y du panneauNantes en pixel
    private int panneauNantesW, panneauNantesH; // largeur et hauteur du panneauNantes en pixels
    private int panneauNantesWInit, panneauNantesHInit; // largeur et hauteur du panneauNantes en pixels

    private int panneauNantesW2, panneauNantesH2;
    private int wEcran,hEcran; // largeur et hauteur de l'écran en pixels
    private boolean move = false; // 'true' si la panneauNantes doit se déplacer automatiquement, 'false' sinon
    int exPosX=0;
    int exPosy=0;
    private boolean isOnItBool=false;
    // pour déplacer la panneauNantes on ajoutera INCREMENT à ses coordonnées x et y
    private static final int INCREMENT = VITESSEDEPLACEMENTNANTES;
    private int speedX=INCREMENT, speedY=INCREMENT;
    private Boolean switchDirection=false;
    private final double RATIO=1.8;
    private double widthOriginal;
    private double heightOriginal;
    private int incrementZoom=INCREMENTZOOMPANNEAUNANTES;
    private Matrix matrix;
    private float angleRot;

    // contexte de l'application Android
    // il servira à accéder aux ressources, dont l'image du panneauNantes
    private final Context mContext;

    // Constructeur de l'objet "panneauNantes"
    public PanneauValencienne(final Context c)
    {
        x=cvW; y=cvH; // position de départ
        mContext=c; // sauvegarde du contexte
    }

    // on attribue à l'objet "panneauNantes" l'image passée en paramètre
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

        Drawable dr = mContext.getResources().getDrawable(R.drawable.valenciennes);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        widthOriginal=bitmap.getWidth();
        heightOriginal=bitmap.getHeight();



        // on définit (au choix) la taille du panneauNantes
        panneauNantesHInit=hScreen/10;
        panneauNantesWInit=((int)Math.round(panneauNantesHInit/(heightOriginal/widthOriginal)));

        panneauNantesH=panneauNantesHInit;
        panneauNantesW=panneauNantesWInit;



        img = setImage(mContext,R.drawable.valenciennes,panneauNantesW,panneauNantesH); //panneauNantes3



    }

    public void setRotate(float angle ){
        angleRot=angle;

        matrix = new Matrix();

        matrix.postRotate(angleRot);
    }

    public float getRotate(){
        return angleRot;
    }

    public void zoom(){

        panneauNantesH=panneauNantesH+incrementZoom;
        panneauNantesW=((int)Math.round(panneauNantesH/(heightOriginal/widthOriginal)));


        img = setImage(mContext,R.drawable.valenciennes,panneauNantesW,panneauNantesH); //panneauNantes3
    }

    public void resetSize(){

        panneauNantesH=panneauNantesHInit;
        panneauNantesW=panneauNantesWInit;


        img = setImage(mContext,R.drawable.valenciennes,panneauNantesW,panneauNantesH); //panneauNantes3
    }

    // définit la coordonnée X du panneauNantes
    public void setX(int x) {
        this.x = x;
    }

    // définit la coordonnée Y du panneauNantes
    public void setY(int y) {
        this.y = y;
    }

    // retourne la coordonnée X du panneauNantes
    public int getX() {
        return x;
    }

    // retourne la coordonnée Y du panneauNantes
    public int getY() {
        return y;
    }

    // retourne la largeur du panneauNantes en pixel
    public int getpanneauNantesW() {
        return panneauNantesW;
    }

    // retourne la hauteur du panneauNantes en pixel
    public int getpanneauNantesH() {
        return panneauNantesH;
    }

    // déplace la panneauNantes en détectant les collisions avec les bords de l'écran
    public void moveUp()
    {

        if(!move) {return;}

        y-=speedY;

    }

    public void moveDown()
    {

        if(!move) {return;}

        y+=speedY;

    }


    // on dessine la panneauNantes, en x et y
    public void draw(Canvas canvas)
    {
        if(img==null) {return;}
        canvas.drawBitmap(Bitmap.createBitmap(img.getBitmap() , 0, 0, img.getBitmap().getWidth(), img.getBitmap().getHeight(), matrix, true), x, y, null);
    }

    public Boolean hasBeenTouched(int x, int y, int w, int h){
        if(x<this.x+getpanneauNantesW() && x>this.x && ((y>this.y && y<this.y+getpanneauNantesH())|| (y+h>this.y && y+h<this.y+getpanneauNantesH()) )){
            return true;
        } else {
            // véhicule plus petit que bonbonne
            if(this.y>y && this.y+getpanneauNantesH()<y+h && x<this.x+getpanneauNantesW() && x>this.x){
                return true;
            } else {
                return false;
            }
        }
    }

    public void setVitesse (int vitesse){
        speedY=vitesse;
    }

    public void isOnIt(boolean bool){
        isOnItBool= bool;
    }
    public boolean getisOnIt(){
        return isOnItBool;
    }

}

