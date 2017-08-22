package com.polyjoule.ylebourlout.apriou.polygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Debug;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.text.DecimalFormat;

import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.FREQUENCEZOOMNANTES;
import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.INCREMENTROTATENANTES;
import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.ROTATENANTESINIT;

/**
 * Created by Alexis on 11/08/2017.
 */

public class AccueilView  extends SurfaceView implements SurfaceHolder.Callback {
    // déclaration de l'objet définissant la boucle principale de déplacement et de rendu
    private AccueilLoopThread accueilLoopThread;
    private Boolean initialisation=false;
    private Boolean gestionBitmap=false;
    public static int cvW; // canva width
    public static int cvH; // canva heigh
   // private int deplacementPanneauNantes;
    private int exSizePanneauNantes;
    private int posPanneaNantesInit;
    private Pointilles pointillesF;
    private Pointilles pointillesS;
    private PanneauNantes panneauNantes;
    private VehiculeAccueil vehiculeAccueil;
    private FondAccueil fond;
    private Bitmap solBitmap;
    private Bitmap cielBitmap;
    private Bitmap titreBitmap;
    private Bitmap gameBitmap;
    private Bitmap socialBitmap;
    private Bitmap palmaresBitmap;
    private Bitmap cacheBitmap;
    private Bitmap paramBitmap;
    private Boolean monteePanneauNantes=false;
    private Boolean roadStarted=false;
    private Boolean uploadBitmap=true;
    private Boolean stopMenu=false;
    private int nbZoom=0;
    private int positionYtitre;
    private int positionXtitre;
    private int espacetitrebouton;
    private int posXparamButton;
    private int posYparamButton;
    private int posXgameButton;
    private int posXpalmaresButton;
    private int posXsocialButton;
    private int posYgameButton;
    private int posYpalmaresButton;
    private int posYsocialButton;
    private int longueurButton;
    private int longueurButtonParam;
    private int showStorage=0;





    // création de la surface de dessin
    public AccueilView(Context context) {
        super(context);
        getHolder().addCallback(this);
        accueilLoopThread = new AccueilLoopThread(this);

        pointillesF = new Pointilles(this.getContext());
        pointillesS = new Pointilles(this.getContext());
        panneauNantes = new PanneauNantes(this.getContext());
        vehiculeAccueil = new VehiculeAccueil(this.getContext());




    }

    // Fonction qui "dessine" un écran de jeu
    public void doDraw(Canvas canvas) {
        if(canvas==null) {return;}

        if(stopMenu){return;}
        cvH = canvas.getHeight();
        cvW = canvas.getWidth();


        if(!initialisation) {

            longueurButton=cvW/3;
            longueurButtonParam=cvW/8;

            solBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sol), cvW, (BitmapFactory.decodeResource(getResources(), R.drawable.sol).getHeight())/(BitmapFactory.decodeResource(getResources(), R.drawable.sol).getWidth()/cvW), false);
            //cielBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ciel), cvW, cvH, false);
            // solBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sol), cvW, (BitmapFactory.decodeResource(getResources(), R.drawable.sol).getHeight())*(cvW/BitmapFactory.decodeResource(getResources(), R.drawable.sol).getWidth()), false);

            titreBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.titre),cvW/2,2*(BitmapFactory.decodeResource(getResources(), R.drawable.titre).getHeight())*((cvW/2)/BitmapFactory.decodeResource(getResources(), R.drawable.titre).getWidth()),false);
            cacheBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cache),(BitmapFactory.decodeResource(getResources(), R.drawable.cache).getWidth())/(BitmapFactory.decodeResource(getResources(), R.drawable.cache).getHeight()/cvH),cvH-solBitmap.getHeight(),false);
            gameBitmap =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.game), longueurButton, 12*(BitmapFactory.decodeResource(getResources(), R.drawable.game).getHeight())/(BitmapFactory.decodeResource(getResources(), R.drawable.game).getWidth()/(longueurButton))/16, false);
            socialBitmap =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.social), longueurButton, 12*(BitmapFactory.decodeResource(getResources(), R.drawable.social).getHeight())/(BitmapFactory.decodeResource(getResources(), R.drawable.social).getWidth()/(longueurButton))/16, false);
            palmaresBitmap =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.classement), longueurButton,12* (BitmapFactory.decodeResource(getResources(), R.drawable.classement).getHeight())/(BitmapFactory.decodeResource(getResources(), R.drawable.classement).getWidth()/(longueurButton))/16, false);
            paramBitmap =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.parameters), longueurButtonParam,14* (BitmapFactory.decodeResource(getResources(), R.drawable.parameters).getHeight())/(BitmapFactory.decodeResource(getResources(), R.drawable.parameters).getWidth()/(longueurButtonParam))/16, false);


            pointillesF.setX(cvW/2-pointillesF.getpointillesW()/2);
            pointillesF.setY(cvH-pointillesF.getpointillesH());
            pointillesF.setMove(false);
            pointillesS.setMove(false);

            vehiculeAccueil.setX(cvW/2-vehiculeAccueil.getvehiculeAccueilW()/2);
            vehiculeAccueil.setY(cvH);
            vehiculeAccueil.setMove(true);

            panneauNantes.setX(cvW/4-17*panneauNantes.getpanneauNantesW()/16);
            panneauNantes.setRotate(ROTATENANTESINIT);
            panneauNantes.setY((cvH- 17 * solBitmap.getHeight() / 20)-panneauNantes.getpanneauNantesH());
            panneauNantes.setMove(false);
            posPanneaNantesInit=panneauNantes.getX();
            //deplacementPanneauNantes=panneauNantes.getpanneauNantesW()/45;
            monteePanneauNantes=false;


//                quatreBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.quatre), longueurStart, bordStartB - bordStartH, false);
//
//                troisBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.trois), longueurStart, bordStartB - bordStartH, false);
//
//                deuxBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.deux), longueurStart, bordStartB - bordStartH, false);
//                unBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.un), longueurStart, bordStartB - bordStartH, false);
//                goBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.go), longueurStart, bordStartB - bordStartH, false);
//                touchtostartBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.touchtostart), longueurStart, bordStartB - bordStartH, false);
            initialisation=true;


        }

        if(vehiculeAccueil.isStop()){
            panneauNantes.setMove(true);
            pointillesF.setMove(true);
        }

        //canvas.drawBitmap(cielBitmap,0,0,null);

        if(fond!=null){
            fond.draw(canvas);
        }

        if(monteePanneauNantes) {
            panneauNantes.draw(canvas);


            canvas.drawBitmap(solBitmap, 0, cvH - solBitmap.getHeight(), null);


            if (panneauNantes.getY() + panneauNantes.getpanneauNantesH() <= (cvH- 17 * solBitmap.getHeight() / 20)) {
                monteePanneauNantes=false;
                nbZoom=0;
            }
        } else {

            canvas.drawBitmap(solBitmap, 0, cvH - solBitmap.getHeight(), null);

            if(panneauNantes.isMoving()) {
                if (nbZoom % FREQUENCEZOOMNANTES == 0) {
                    nbZoom++;
                    exSizePanneauNantes = panneauNantes.getpanneauNantesW();
                    panneauNantes.zoom();
                    if (panneauNantes.getRotate() != 0) {
                        if (panneauNantes.getRotate() + INCREMENTROTATENANTES > 0) {
                            panneauNantes.setRotate(0);
                        } else {
                            panneauNantes.setRotate(panneauNantes.getRotate() + INCREMENTROTATENANTES);
                        }
                    }
                    panneauNantes.setX(panneauNantes.getX() - (panneauNantes.getpanneauNantesW() - exSizePanneauNantes));

                } else {
                    nbZoom++;
                }
            }

            panneauNantes.draw(canvas);

            if(panneauNantes.getY()>cvH){
                //panneauNantes.setMove(false); //TODO Décommenter lors de l'ajout de nouveaux éléments
                monteePanneauNantes=true;
                panneauNantes.resetSize();
                panneauNantes.setRotate(ROTATENANTESINIT);
                panneauNantes.setX(posPanneaNantesInit);

            }
        }


        pointillesF.draw(canvas);
        pointillesS.draw(canvas);


        canvas.drawBitmap(cacheBitmap,cvW/2-cacheBitmap.getWidth()/2,0,null);

        vehiculeAccueil.draw(canvas);

        positionYtitre=3*cvH/16;
        positionXtitre=2*cvW/8;
        espacetitrebouton=cvH/7;

        posXgameButton=positionXtitre+titreBitmap.getWidth()/2-gameBitmap.getWidth()/2;
        posYgameButton=positionYtitre+espacetitrebouton;
        posXpalmaresButton=positionXtitre+titreBitmap.getWidth()/2-(gameBitmap.getWidth()/5)-socialBitmap.getWidth();
        posYpalmaresButton=positionYtitre+espacetitrebouton+(8*gameBitmap.getHeight()/10);
        posXsocialButton=positionXtitre+titreBitmap.getWidth()/2+(gameBitmap.getWidth()/6);
        posYsocialButton=positionYtitre+espacetitrebouton+19*gameBitmap.getHeight()/20;
        posXparamButton=cvW-paramBitmap.getWidth();
        posYparamButton=1*paramBitmap.getHeight()/20;//cvH-paramBitmap.getHeight();

        canvas.drawBitmap(titreBitmap,positionXtitre,positionYtitre,null);

        canvas.drawBitmap(gameBitmap,posXgameButton,posYgameButton,null);

        canvas.drawBitmap(palmaresBitmap,posXpalmaresButton,posYpalmaresButton,null);

        canvas.drawBitmap(socialBitmap,posXsocialButton,posYsocialButton,null);

        canvas.drawBitmap(paramBitmap,posXparamButton,posYparamButton,null);


    }

    // Fonction appelée par la boucle principale (accueilLoopThread)
    // On gère ici le déplacement des objets
    public void update() {

        if(stopMenu){return;}

        // gestion mémoire
        if(showStorage%50==0) {
            Double allocated = new Double(Debug.getNativeHeapAllocatedSize()) / new Double((1048576));
            Double available = new Double(Debug.getNativeHeapSize()) / new Double((1048576));
            Double free = new Double(Debug.getNativeHeapFreeSize()) / new Double((1048576));
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            df.setMinimumFractionDigits(2);
            Log.d("allocated", Double.toString(allocated));
            Log.d("available", Double.toString(available));
            Log.d("free", Double.toString(free));
            showStorage++;
        } else {
            showStorage++;
        }

        vehiculeAccueil.avance();

        pointillesF.move();
        pointillesS.move();
        if(monteePanneauNantes) {
            panneauNantes.moveUp();
        } else {
            panneauNantes.moveDown();
        }

        if(pointillesF.getY()+pointillesF.getpointillesH()>cvH){
            if(!pointillesS.isMoving()) {
                pointillesS.setX(cvW / 2 - pointillesF.getpointillesW() / 2);
                pointillesS.setY(pointillesF.getY() - pointillesS.getpointillesH());
                pointillesS.setMove(true);
            }
        }

        if(pointillesF.getY()>cvH){
            pointillesF.setY(cvH-pointillesF.getpointillesH()-pointillesS.getpointillesH());
        }
        if(pointillesS.getY()>cvH){
            pointillesS.setY(pointillesF.getY() - pointillesS.getpointillesH());
        }

        ///uploadBitmap=true;

    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée immédiatement après la création de l'objet SurfaceView
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // création du processus AccueilLoopThread si cela n'est pas fait
//        Bitmap bgBm = BitmapFactory.decodeResource(getResources(), R.drawable.espace3);
//        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bgBm, getWidth(), getHeight(), false);
//        WIDTHBG = resizedBitmap.getWidth();
//        bg = new SpaceBackground(resizedBitmap);
        fond = new FondAccueil(BitmapFactory.decodeResource(getResources(), R.drawable.ciel));;


        if(accueilLoopThread.getState()==Thread.State.TERMINATED) {
            accueilLoopThread=new AccueilLoopThread(this);
        }
        accueilLoopThread.setRunning(true);
        accueilLoopThread.start();


    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée juste avant que l'objet ne soit détruit.
    // on tente ici de stopper le processus de accueilLoopThread
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        accueilLoopThread.setRunning(false);
        while (retry) {
            try {
                accueilLoopThread.join();
                retry = false;
            }
            catch (InterruptedException e) {}
        }
    }

    // Gère les touchés sur l'écran
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currentX = (int)event.getX();
        int currentY = (int)event.getY();

        switch (event.getAction()) {

            // code exécuté lorsque le doigt touche l'écran.
            case MotionEvent.ACTION_DOWN:
                if(currentX>posXgameButton && currentX<posXgameButton+longueurButton && currentY>posYgameButton && currentY<posYgameButton+longueurButton){
                    // start game

                    stopMenu=true;
                    cacheBitmap.recycle();
                    solBitmap.recycle();
                    gameBitmap.recycle();
                    palmaresBitmap.recycle();
                    paramBitmap.recycle();
                    socialBitmap.recycle();
                    cacheBitmap.recycle();
                    Accueil.game();

                }
                if(currentX>posXsocialButton && currentX<posXsocialButton+longueurButton && currentY>posYsocialButton && currentY<posYsocialButton+longueurButton){
                    // start social

                    Accueil.social();
                }
                if(currentX>posXpalmaresButton && currentX<posXpalmaresButton+longueurButton && currentY>posYpalmaresButton && currentY<posYpalmaresButton+longueurButton){
                    // start palmares
                    Accueil.palmares();
                }
                if(currentX>posXparamButton && currentX<posXparamButton+longueurButton && currentY>posYparamButton && currentY<posYparamButton+longueurButton){
                    // start parameters
                    cacheBitmap.recycle();
                    solBitmap.recycle();
                    gameBitmap.recycle();
                    palmaresBitmap.recycle();
                    paramBitmap.recycle();
                    socialBitmap.recycle();
                    cacheBitmap.recycle();
                    Accueil.param();
                }


                break;
                // code exécuté lorsque le doight glisse sur l'écran.
            case MotionEvent.ACTION_MOVE:


                break;

            // lorsque le doigt quitte l'écran
            case MotionEvent.ACTION_UP:

                break;

        }

        return true;  // On retourne "true" pour indiquer qu'on a géré l'évènement
    }


    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée à la CREATION et MODIFICATION et ONRESUME de l'écran
    // nous obtenons ici la largeur/hauteur de l'écran en pixels
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {

        pointillesF.resize(w, h);
        pointillesS.resize(w, h);
        panneauNantes.resize(w,h);
        vehiculeAccueil.resize(w,h);
    }

} // class AccueilView

