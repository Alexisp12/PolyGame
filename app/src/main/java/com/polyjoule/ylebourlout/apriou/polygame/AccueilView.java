package com.polyjoule.ylebourlout.apriou.polygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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
    private Bitmap solBitmap;
    private Bitmap cielBitmap;
    private Bitmap titreBitmap;
    private Bitmap gameBitmap;
    private Bitmap socialBitmap;
    private Bitmap palmaresBitmap;
    private Bitmap cacheBitmap;
    private Boolean monteePanneauNantes=false;
    private Boolean roadStarted=false;
    private int nbZoom=0;
    private int positionYtitre;
    private int positionXtitre;
    private int espacetitrebouton;



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
        cvH = canvas.getHeight();
        cvW = canvas.getWidth();

        if(!initialisation) {

            solBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sol), cvW, (BitmapFactory.decodeResource(getResources(), R.drawable.sol).getHeight())/(BitmapFactory.decodeResource(getResources(), R.drawable.sol).getWidth()/cvW), false);
            cielBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ciel), cvW, cvH, false);
            Log.d("titreWidth",Integer.toString((BitmapFactory.decodeResource(getResources(), R.drawable.titre).getWidth())));
            Log.d("titreH",Integer.toString((BitmapFactory.decodeResource(getResources(), R.drawable.titre).getHeight())));
            titreBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.titre),cvW/2,2*(BitmapFactory.decodeResource(getResources(), R.drawable.titre).getHeight())*((cvW/2)/BitmapFactory.decodeResource(getResources(), R.drawable.titre).getWidth()),false);
            cacheBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cache),(BitmapFactory.decodeResource(getResources(), R.drawable.cache).getWidth())/(BitmapFactory.decodeResource(getResources(), R.drawable.cache).getHeight()/cvH),cvH-solBitmap.getHeight(),false);
            gameBitmap =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.game), cvW/3, 12*(BitmapFactory.decodeResource(getResources(), R.drawable.game).getHeight())/(BitmapFactory.decodeResource(getResources(), R.drawable.game).getWidth()/(cvW/3))/16, false);
            socialBitmap =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.social), cvW/3, 12*(BitmapFactory.decodeResource(getResources(), R.drawable.social).getHeight())/(BitmapFactory.decodeResource(getResources(), R.drawable.social).getWidth()/(cvW/3))/16, false);
            palmaresBitmap =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.classement), cvW/3,12* (BitmapFactory.decodeResource(getResources(), R.drawable.classement).getHeight())/(BitmapFactory.decodeResource(getResources(), R.drawable.classement).getWidth()/(cvW/3))/16, false);


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

        canvas.drawBitmap(cielBitmap,0,0,null);

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

        canvas.drawBitmap(titreBitmap,positionXtitre,positionYtitre,null);

        canvas.drawBitmap(gameBitmap,positionXtitre+titreBitmap.getWidth()/2-gameBitmap.getWidth()/2,positionYtitre+espacetitrebouton,null);

        canvas.drawBitmap(palmaresBitmap,positionXtitre+titreBitmap.getWidth()/2-(gameBitmap.getWidth()/5)-socialBitmap.getWidth(),positionYtitre+espacetitrebouton+(8*gameBitmap.getHeight()/10),null);

        canvas.drawBitmap(socialBitmap,positionXtitre+titreBitmap.getWidth()/2+(gameBitmap.getWidth()/6),positionYtitre+espacetitrebouton+19*gameBitmap.getHeight()/20,null);



    }

    // Fonction appelée par la boucle principale (accueilLoopThread)
    // On gère ici le déplacement des objets
    public void update() {

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

