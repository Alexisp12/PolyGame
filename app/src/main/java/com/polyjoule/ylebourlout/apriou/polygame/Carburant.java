package com.polyjoule.ylebourlout.apriou.polygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import static com.polyjoule.ylebourlout.apriou.polygame.Game.DEPLACEMENTBG;
import static com.polyjoule.ylebourlout.apriou.polygame.GameView.cvH;
import static com.polyjoule.ylebourlout.apriou.polygame.GameView.cvW;

/**
 * Created by Alexis on 20/07/2017.
 */

public class Carburant {
    private BitmapDrawable img=null; // image du carburant
    private int x,y; // coordonnées x,y du carburant en pixel
    private int carburantW, carburantH; // largeur et hauteur du carburant en pixels
    private int carburantW2, carburantH2;
    private int wEcran,hEcran; // largeur et hauteur de l'écran en pixels
    private boolean move = false; // 'true' si la carburant doit se déplacer automatiquement, 'false' sinon
    int exPosX=0;
    int exPosy=0;
    private boolean isOnItBool=false;
    // pour déplacer la carburant on ajoutera INCREMENT à ses coordonnées x et y
    private static final int INCREMENT = DEPLACEMENTBG;
    private int speedX=INCREMENT, speedY=INCREMENT;
    private Boolean switchDirection=false;
    private int positionnementY;
    private int positionnementX;
    private Boolean repositionY=false;
    private Boolean repositionX=false;


    // contexte de l'application Android
    // il servira à accéder aux ressources, dont l'image du carburant
    private final Context mContext;

    // Constructeur de l'objet "carburant"
    public Carburant(final Context c)
    {
        x=cvW; y=cvH; // position de départ
        mContext=c; // sauvegarde du contexte
    }

    // on attribue à l'objet "carburant" l'image passée en paramètre
    // w et h sont sa largeur et hauteur définis en pixels
    public BitmapDrawable setImage(final Context c, final int ressource, final int w, final int h)
    {
        Drawable dr = c.getResources().getDrawable(ressource);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        return new BitmapDrawable(c.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
    }

    // retourne 'true' si la carburant se déplace automatiquement
    // 'false' sinon
    // car on la bloque sous le doigt du joueur lorsqu'il la déplace
    public boolean isMoving() {
        return move;
    }

    // définit si oui ou non la carburant doit se déplacer automatiquement
    // car on la bloque sous le doigt du joueur lorsqu'il la déplace
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

        // on définit (au choix) la taille du carburant à 1/5ème de la largeur de l'écran
        carburantW=wScreen/64;
        carburantH=wScreen/12;

        img = setImage(mContext,R.drawable.carburant2,carburantW,carburantH); //carburant3


    }

    // définit la coordonnée X du carburant
    public void setX(int x) {
        this.x = x;
    }

    // définit la coordonnée Y du carburant
    public void setY(int y) {
        this.y = y;
    }

    // retourne la coordonnée X du carburant
    public int getX() {
        return x;
    }

    // retourne la coordonnée Y du carburant
    public int getY() {
        return y;
    }

    // retourne la largeur du carburant en pixel
    public int getcarburantW() {
        return carburantW;
    }

    // retourne la hauteur du carburant en pixel
    public int getcarburantH() {
        return carburantH;
    }

    // déplace la carburant en détectant les collisions avec les bords de l'écran
    public void move()
    {
        // si on ne doit pas déplacer la carburant (lorsqu'elle est sous le doigt du joueur)
        // on quitte
        if(!move) {return;}

        if(!repositionY) {
            positionnementY = (int) (Math.random() * (100 + 1));

            if (positionnementY < 33) {
                y = cvH / 8 + 7*getcarburantH() / 8;
            } else {
                if (positionnementY < 66) {
                    y = cvH / 2 - getcarburantH() / 2;
                } else {
                    y = cvH- (cvH / 8) - 15*getcarburantH()/8;
                }
            }
        repositionY=true;
        }

        // on incrémente les coordonnées X et Y
        x-=speedX;


        if(x+carburantW < 0) {
            positionnementX = (int) (Math.random() * (100 + 1));
            if (positionnementX < 33) {
                x = cvW+cvW/2;
            } else {
                if (positionnementX < 66) {
                    x = 2*cvW;
                } else {
                    x = 2*cvW+cvW/2;
                }
            }

            repositionY=false;
        }

    }

    public void disparition(){
        positionnementX = (int) (Math.random() * (100 + 1));
        if (positionnementX < 33) {
            x = cvW+cvW/2;
        } else {
            if (positionnementX < 66) {
                x = 2*cvW;
            } else {
                x = 2*cvW+cvW/2;
            }
        }

        repositionY=false;
    }


    // on dessine la carburant, en x et y
    public void draw(Canvas canvas)
    {
        if(img==null) {return;}
        canvas.drawBitmap(img.getBitmap(), x, y, null);
    }

    public void isOnIt(boolean bool){
        isOnItBool= bool;
    }
    public boolean getisOnIt(){
        return isOnItBool;
    }
}