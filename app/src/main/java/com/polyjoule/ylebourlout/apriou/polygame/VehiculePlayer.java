package com.polyjoule.ylebourlout.apriou.polygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import static com.polyjoule.ylebourlout.apriou.polygame.GameView.cvH;
import static com.polyjoule.ylebourlout.apriou.polygame.GameView.cvW;

/**
 * Created by Alexis on 19/07/2017.
 */

public class VehiculePlayer {
    private BitmapDrawable img=null; // image du vehiculePlayer
    private int x,y; // coordonnées x,y du vehiculePlayer en pixel
    private int vehiculePlayerW, vehiculePlayerH; // largeur et hauteur du vehiculePlayer en pixels
    private int vehiculePlayerW2, vehiculePlayerH2;
    private int wEcran,hEcran; // largeur et hauteur de l'écran en pixels
    private boolean move = false; // 'true' si la vehiculePlayer doit se déplacer automatiquement, 'false' sinon
    int exPosX=0;
    int exPosy=0;
    private boolean isOnItBool=false;
    // pour déplacer la vehiculePlayer on ajoutera INCREMENT à ses coordonnées x et y
    private static final int INCREMENT = 5;
    private int speedX=INCREMENT, speedY=INCREMENT;
    private Boolean switchDirection=false;
    private final double RATIO=2.6;

    // contexte de l'application Android
    // il servira à accéder aux ressources, dont l'image du vehiculePlayer
    private final Context mContext;

    // Constructeur de l'objet "vehiculePlayer"
    public VehiculePlayer(final Context c)
    {
        x=cvW/16; y=(cvH/2)-getvehiculePlayerH()/2; // position de départ
        mContext=c; // sauvegarde du contexte
    }

    // on attribue à l'objet "vehiculePlayer" l'image passée en paramètre
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

        // on définit (au choix) la taille du vehiculePlayer
        vehiculePlayerH=11*hScreen/64; // 6  pour vehiculeplayer
        vehiculePlayerW=((int)Math.round(vehiculePlayerH/RATIO));
        //vehiculePlayerH=hScreen/8;
        //vehiculePlayerH= ((int) Math.round( vehiculePlayerW/ratio)); // 12 pour vehiculeplayer

        //d=null;


        img = setImage(mContext,R.drawable.vehiculeplayernologo,vehiculePlayerW,vehiculePlayerH); //vehiculePlayer3


    }

    // définit la coordonnée X du vehiculePlayer
    public void setX(int x) {
        this.x = x;
    }

    // définit la coordonnée Y du vehiculePlayer
    public void setY(int y) {
        this.y = y;
    }

    // retourne la coordonnée X du vehiculePlayer
    public int getX() {
        return x;
    }

    // retourne la coordonnée Y du vehiculePlayer
    public int getY() {
        return y;
    }

    // retourne la largeur du vehiculePlayer en pixel
    public int getvehiculePlayerW() {
        return vehiculePlayerW;
    }

    // retourne la hauteur du vehiculePlayer en pixel
    public int getvehiculePlayerH() {
        return vehiculePlayerH;
    }

    // déplace la vehiculePlayer en détectant les collisions avec les bords de l'écran
    public void moveWithCollisionDetection()
    {
        // si on ne doit pas déplacer la vehiculePlayer (lorsqu'elle est sous le doigt du joueur)
        // on quitte
        if(!move) {return;}

        // on incrémente les coordonnées X et Y
        x+=speedX;
        y+=speedY;

        // si x dépasse la largeur de l'écran, on inverse le déplacement
        if(x+vehiculePlayerW > wEcran) {speedX=-INCREMENT;}

        // si y dépasse la hauteur l'écran, on inverse le déplacement
        if(y+vehiculePlayerH > hEcran) {speedY=-INCREMENT;}

        // si x passe à gauche de l'écran, on inverse le déplacement
        if(x<0) {speedX=INCREMENT;}

        // si y passe à dessus de l'écran, on inverse le déplacement
        if(y<0) {speedY=INCREMENT;}
    }


    // on dessine la vehiculePlayer, en x et y
    public void draw(Canvas canvas)
    {
        if(img==null) {return;}
        canvas.drawBitmap(img.getBitmap(), x, y, null);
    }

    public Boolean hasBeenTouched(int x, int y, int w, int h,BitmapDrawable bd){
        // Ennemi large haut
        if(this.x>x && this.x+getvehiculePlayerW()<x+w && (y+h>this.y && y+h<this.y+getvehiculePlayerH())){
            Rect rectEtude = new Rect(this.x,this.y,this.x+getvehiculePlayerW(),y+h);
            for(int j=0; j<rectEtude.height();j++) {
                for (int i = 0; i < rectEtude.width(); i++) {
                    if (img.getBitmap().getPixel(i+(img.getBitmap().getWidth()-rectEtude.width()), j)!=0 && bd.getBitmap().getPixel(i,j+(bd.getBitmap().getHeight()-rectEtude.height()))!=0){
                        Log.d("Ennemi_large","Haut");
                        return true;
                    }
                }
            }
        }
        // Ennemi large bas
        if(this.x>x && this.x+getvehiculePlayerW()<x+w && (y>this.y && y <this.y+getvehiculePlayerH())){
            Rect rectEtude = new Rect(this.x,y,this.x+getvehiculePlayerW(),this.y+getvehiculePlayerH());
            for(int j=0; j<rectEtude.height();j++) {
                for (int i = 0; i < rectEtude.width(); i++) {
                    if (img.getBitmap().getPixel(i+(img.getBitmap().getWidth()-rectEtude.width()), j)!=0 && bd.getBitmap().getPixel(i,j+(bd.getBitmap().getHeight()-rectEtude.height()))!=0){
                        Log.d("Ennemi_large","bas");
                        return true;
                    }
                }
            }
        }
        // Ennemi étroit haut
        if(this.x>x && this.x+getvehiculePlayerW()<x+w && (y+h>this.y && y+h<this.y+getvehiculePlayerH())){
            Rect rectEtude = new Rect(x,this.y,x+w,y+h);
            for(int j=0; j<rectEtude.height();j++) {
                for (int i = 0; i < rectEtude.width(); i++) {
                    if (img.getBitmap().getPixel(i+(img.getBitmap().getWidth()-rectEtude.width()), j)!=0 && bd.getBitmap().getPixel(i,j+(bd.getBitmap().getHeight()-rectEtude.height()))!=0){
                        Log.d("Ennemi_large","Haut");
                        return true;
                    }
                }
            }
        }
        // Ennemi étroit bas
        if(this.x>x && this.x+getvehiculePlayerW()<x+w && (y>this.y && y <this.y+getvehiculePlayerH())){
            Rect rectEtude = new Rect(x,y,x+w,this.y+getvehiculePlayerH());
            for(int j=0; j<rectEtude.height();j++) {
                for (int i = 0; i < rectEtude.width(); i++) {
                    if (img.getBitmap().getPixel(i+(img.getBitmap().getWidth()-rectEtude.width()), j)!=0 && bd.getBitmap().getPixel(i,j+(bd.getBitmap().getHeight()-rectEtude.height()))!=0){
                        Log.d("Ennemi_large","bas");
                        return true;
                    }
                }
            }
        }



        // Ennemi en haut à droite
        if(x>this.x && x<this.x+getvehiculePlayerW() && (y+h>this.y && y+h<this.y+getvehiculePlayerH()  )){
            Rect rectEtude = new Rect(x,this.y,this.x+getvehiculePlayerW(),y+h);
            for(int j=0; j<rectEtude.height();j++) {
                for (int i = 0; i < rectEtude.width(); i++) {
                    if (img.getBitmap().getPixel(i+(img.getBitmap().getWidth()-rectEtude.width()), j)!=0 && bd.getBitmap().getPixel(i,j+(bd.getBitmap().getHeight()-rectEtude.height()))!=0){
                        Log.d("Ennemi","Haut_Droite");
                        return true;
                    }
                }
            }
        }

        // Ennemi en bas à droite
        if(x>this.x && x<this.x+getvehiculePlayerW() && (y>this.y && y <this.y+getvehiculePlayerH() )){
            Rect rectEtude = new Rect(x,y,this.x+getvehiculePlayerW(),this.y+getvehiculePlayerH());
            for(int j=0; j<rectEtude.height();j++) {
                for (int i = 0; i < rectEtude.width(); i++) {
                    if (img.getBitmap().getPixel(i+(img.getBitmap().getWidth()-rectEtude.width()), j+(img.getBitmap().getHeight()-rectEtude.height()))!=0 && bd.getBitmap().getPixel(i,j)!=0){
                        Log.d("Ennemi","Bas_Droite");
                        return true;
                    }
                }
            }
        }

        // Ennemi en haut à gauche
        if(x+w>this.x && x+w<this.x+getvehiculePlayerW() && (y+h>this.y && y+h<this.y+getvehiculePlayerH()  )){
            Rect rectEtude = new Rect(this.x,this.y,x+w,y+h);
            for(int j=0; j<rectEtude.height();j++) {
                for (int i = 0; i < rectEtude.width(); i++) {
                    if (img.getBitmap().getPixel(i, j)!=0 && bd.getBitmap().getPixel(i+(bd.getBitmap().getWidth()-rectEtude.width()),j+(bd.getBitmap().getHeight()-rectEtude.height()))!=0){
                        Log.d("Ennemi","Haut_Gauche");
                        return true;
                    }
                }
            }
        }

        // Ennemi en bas à gauche
        if(x+w>this.x && x+w<this.x+getvehiculePlayerW() && (y>this.y && y <this.y+getvehiculePlayerH() )){
            Rect rectEtude = new Rect(this.x,y,x+w,this.y+getvehiculePlayerH());
            for(int j=0; j<rectEtude.height();j++) {
                for (int i = 0; i < rectEtude.width(); i++) {
                    if (img.getBitmap().getPixel(i, j+(img.getBitmap().getHeight()-rectEtude.height()))!=0 && bd.getBitmap().getPixel(i+(bd.getBitmap().getWidth()-rectEtude.width()),j)!=0){
                        Log.d("Ennemi","Bas_Gauche");
                        return true;
                    }
                }
            }
        }


        //TODO UTILE OU PAS ?
//        if(x<this.x && x+w>this.x  && ((y+h>this.y && y+h<this.y+getvehiculePlayerH() ) || y>this.y && y <this.y+getvehiculePlayerH() ) ){
//            return true;
//        }

        return false;
    }

    public Boolean hasBeenTouchedbyCarb(int x, int y, int w, int h){

        // Carbu à droite
        if(x>this.x && x<this.x+getvehiculePlayerW() && ( (y+h>this.y && y+h<this.y+getvehiculePlayerH()) || (y>this.y && y <this.y+getvehiculePlayerH())  )){
            return true;
        }

        // Ennemi en haut à gauche
        if(x+w>this.x && x+w<this.x+getvehiculePlayerW() && ( (y+h>this.y && y+h<this.y+getvehiculePlayerH()) || (y>this.y && y <this.y+getvehiculePlayerH())  )){
            return true;
        }

        //TODO UTILE OU PAS ?
        if(x<this.x && x+w>this.x  && ((y+h>this.y && y+h<this.y+getvehiculePlayerH() ) || y>this.y && y <this.y+getvehiculePlayerH() ) ){
            return true;
        }

        return false;
    }

    public void isOnIt(boolean bool){
        isOnItBool= bool;
    }
    public boolean getisOnIt(){
        return isOnItBool;
    }

}


