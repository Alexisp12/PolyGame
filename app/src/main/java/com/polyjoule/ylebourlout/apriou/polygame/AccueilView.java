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
    //private bool menuPaused=false;
    public static int cvW; // canva width
    public static int cvH; // canva heigh
   // private int deplacementPanneauNantes;
    private int exSizePanneauNantes;
    private int exSizePanneauLondon;
    private int exSizePanneauNougaro;
    private int exSizePanneauRoterdam;
    private int exSizePanneauValencienne;
    private int posPanneauNantesInit;
    private int posPanneauNougaroInit;
    private int posPanneauRoterdamInit;
    private int posPanneauValencienneInit;
    private int posPanneauLondonInit;

    private Pointilles pointillesF;
    private Pointilles pointillesS;
    private PanneauNantes panneauNantes;
    private PanneauNougaro panneauNougaro;
    private PanneauRoterdam panneauRoterdam;
    private PanneauValencienne panneauValencienne;
    private PanneauLondon panneauLondon;
    private VehiculeAccueil vehiculeAccueil;
    //private FondAccueil fond;
    private Bitmap solBitmap;
    private Bitmap cielBitmap;
    private Bitmap titreBitmap;
    private Bitmap gameBitmap;
    private Bitmap socialBitmap;
    private Bitmap palmaresBitmap;
    private Bitmap cacheBitmap;
    private Bitmap paramBitmap;
    private Boolean monteePanneauNantes=false;
    private Boolean monteePanneauNougaro=false;
    private Boolean monteePanneauRoterdam=false;
    private Boolean monteePanneauValencienne=false;
    private Boolean monteePanneauLondon=false;
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

    //private enum Panneau {NANTES,LONDON,ROTERDAM,VALENCIENNE,NOUGARO}
    private int Panneau;




    // création de la surface de dessin
    public AccueilView(Context context) {
        super(context);
        getHolder().addCallback(this);
        accueilLoopThread = new AccueilLoopThread(this);

        pointillesF = new Pointilles(this.getContext());
        pointillesS = new Pointilles(this.getContext());
        panneauNantes = new PanneauNantes(this.getContext());
        panneauNougaro = new PanneauNougaro(this.getContext());
        panneauRoterdam = new PanneauRoterdam(this.getContext());
        panneauValencienne = new PanneauValencienne(this.getContext());
        panneauLondon = new PanneauLondon(this.getContext());
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



            Log.d("LongueurEcran",Integer.toString(cvW));
            Log.d("NewLongueurButton",Integer.toString(cvW/3));
            Log.d("LongueurButtonOrigin",Integer.toString(BitmapFactory.decodeResource(getResources(), R.drawable.game).getWidth()));
            Log.d("LongueurOri/NewLongueur",Integer.toString((BitmapFactory.decodeResource(getResources(), R.drawable.game).getWidth())/(cvW/3)));
            Log.d("HauteurOri",Integer.toString(BitmapFactory.decodeResource(getResources(), R.drawable.game).getHeight()));
            Log.d("HauteurCalculé",Integer.toString((BitmapFactory.decodeResource(getResources(), R.drawable.game).getHeight()) / (BitmapFactory.decodeResource(getResources(), R.drawable.game).getWidth() / (longueurButton))));


            cielBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ciel), cvW, cvH, false);

            if(BitmapFactory.decodeResource(getResources(), R.drawable.sol).getWidth()/cvW<1){
                solBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sol), cvW, (BitmapFactory.decodeResource(getResources(), R.drawable.sol).getHeight()) * (cvW/BitmapFactory.decodeResource(getResources(), R.drawable.sol).getWidth()), false);
            } else {
                solBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sol), cvW, (BitmapFactory.decodeResource(getResources(), R.drawable.sol).getHeight()) / (BitmapFactory.decodeResource(getResources(), R.drawable.sol).getWidth() / cvW), false);
            }
            titreBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.titredeux),cvW/2,cvW/2,false);

//            if(BitmapFactory.decodeResource(getResources(), R.drawable.titre).getWidth()/(cvW/2)<1){
//                titreBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.titredeux),cvW/2,(BitmapFactory.decodeResource(getResources(), R.drawable.titre).getHeight())*((cvW/2)/BitmapFactory.decodeResource(getResources(), R.drawable.titre).getWidth()),false);
//            } else {                                                                                                                //2*
//                titreBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.titredeux),cvW/2,(BitmapFactory.decodeResource(getResources(), R.drawable.titre).getHeight())/(BitmapFactory.decodeResource(getResources(), R.drawable.titre).getWidth()/(cvW/2)),false);
//            }

            if(BitmapFactory.decodeResource(getResources(), R.drawable.cache).getHeight()/(cvH-solBitmap.getHeight())<1) {
                cacheBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cache), (BitmapFactory.decodeResource(getResources(), R.drawable.cache).getWidth()) * (cvH-solBitmap.getHeight())/(BitmapFactory.decodeResource(getResources(), R.drawable.cache).getHeight()), cvH - solBitmap.getHeight(), false);
            } else {
                cacheBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cache), (BitmapFactory.decodeResource(getResources(), R.drawable.cache).getWidth()) / (BitmapFactory.decodeResource(getResources(), R.drawable.cache).getHeight() / (cvH-solBitmap.getHeight())), cvH - solBitmap.getHeight(), false);
            }

                gameBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.game), longueurButton,  longueurButton, false);

                socialBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.social), longueurButton,  longueurButton, false);

                palmaresBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.classement), longueurButton, longueurButton,false);

//            if((BitmapFactory.decodeResource(getResources(), R.drawable.game).getWidth()/(longueurButton)<1)) {
//                gameBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.game), longueurButton, (BitmapFactory.decodeResource(getResources(), R.drawable.game).getHeight()) * ((longueurButton)/BitmapFactory.decodeResource(getResources(), R.drawable.game).getWidth() ), false);
//            } else {
//                gameBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.game), longueurButton, (BitmapFactory.decodeResource(getResources(), R.drawable.game).getHeight()) / (BitmapFactory.decodeResource(getResources(), R.drawable.game).getWidth() / (longueurButton)), false);
//            }
//            if((BitmapFactory.decodeResource(getResources(), R.drawable.social).getWidth()/(longueurButton)<1)) {
//                socialBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.social), longueurButton,  (BitmapFactory.decodeResource(getResources(), R.drawable.social).getHeight()) * ((longueurButton)/BitmapFactory.decodeResource(getResources(), R.drawable.social).getWidth() ) , false);
//            } else {
//                socialBitmap =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.social), longueurButton, (BitmapFactory.decodeResource(getResources(), R.drawable.social).getHeight())/(BitmapFactory.decodeResource(getResources(), R.drawable.social).getWidth()/(longueurButton)), false);
//            }
//            if((BitmapFactory.decodeResource(getResources(), R.drawable.classement).getWidth()/(longueurButton))<1) {
//                palmaresBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.classement), longueurButton, (BitmapFactory.decodeResource(getResources(), R.drawable.classement).getHeight()) * ((longueurButton)/BitmapFactory.decodeResource(getResources(), R.drawable.classement).getWidth() ), false);
//            } else {
//                palmaresBitmap =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.classement), longueurButton, (BitmapFactory.decodeResource(getResources(), R.drawable.classement).getHeight())/(BitmapFactory.decodeResource(getResources(), R.drawable.classement).getWidth()/(longueurButton)), false);
//            }

            //if((BitmapFactory.decodeResource(getResources(), R.drawable.parameters).getWidth()/(longueurButtonParam))<1){
                paramBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.parameters), longueurButtonParam, longueurButtonParam, false);


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
            posPanneauNantesInit=panneauNantes.getX();
            //deplacementPanneauNantes=panneauNantes.getpanneauNantesW()/45;
            monteePanneauNantes=false;

            panneauLondon.setX(cvW/4-17*panneauLondon.getpanneauNantesW()/16);
            panneauLondon.setRotate(ROTATENANTESINIT);
            panneauLondon.setY(cvH);
            panneauLondon.setMove(false);
            posPanneauLondonInit=panneauLondon.getX();
            //deplacementPanneauNantes=panneauNantes.getpanneauNantesW()/45;
            monteePanneauLondon=true;

            panneauNougaro.setX(cvW/4-17*panneauNougaro.getpanneauNantesW()/16);
            panneauNougaro.setRotate(ROTATENANTESINIT);
            panneauNougaro.setY(cvH);
            panneauNougaro.setMove(false);
            posPanneauNougaroInit=panneauNougaro.getX();
            //deplacementPanneauNantes=panneauNantes.getpanneauNantesW()/45;
            monteePanneauNougaro=true;

            panneauRoterdam.setX(cvW/4-17*panneauRoterdam.getpanneauNantesW()/16);
            panneauRoterdam.setRotate(ROTATENANTESINIT);
            panneauRoterdam.setY(cvH);
            panneauRoterdam.setMove(false);
            posPanneauRoterdamInit=panneauRoterdam.getX();
            //deplacementPanneauNantes=panneauNantes.getpanneauNantesW()/45;
            monteePanneauRoterdam=true;

            panneauValencienne.setX(cvW/4-17*panneauValencienne.getpanneauNantesW()/16);
            panneauValencienne.setRotate(ROTATENANTESINIT);
            panneauValencienne.setY(cvH);
            panneauValencienne.setMove(false);
            posPanneauValencienneInit=panneauValencienne.getX();
            //deplacementPanneauNantes=panneauNantes.getpanneauNantesW()/45;
            monteePanneauValencienne=true;

            Panneau = 0;

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

//        if(fond!=null){
//            fond.draw(canvas);
//        }

        if(stopMenu){return;}

        switch (Panneau)
        {
            case 0 :
                if (monteePanneauNantes) {

                    Log.d("monteePanneauNantes", "true");
                    panneauNantes.draw(canvas);


                    canvas.drawBitmap(solBitmap, 0, cvH - solBitmap.getHeight(), null);


                    if (panneauNantes.getY() + panneauNantes.getpanneauNantesH() <= (cvH - 17 * solBitmap.getHeight() / 20)) {
                        monteePanneauNantes = false;
                        nbZoom = 0;
                    }
                } else {
                    Log.d("monteePanneauNantes", "false");

                    canvas.drawBitmap(solBitmap, 0, cvH - solBitmap.getHeight(), null);

                    if (panneauNantes.isMoving()) {
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

                    if (panneauNantes.getY() > cvH) {
                        //panneauNantes.setMove(false); //TODO Décommenter lors de l'ajout de nouveaux éléments

                        //panneauLondon.setMove(true);


                        panneauNantes.resetSize();
                        panneauNantes.setRotate(ROTATENANTESINIT);
                        panneauNantes.setX(posPanneauNantesInit);
                        panneauNantes.setMove(false);
                        Panneau=1;

                        panneauLondon.setMove(true);
                    }
                }
                break;
            case 1 :
                if (monteePanneauLondon) {

                    Log.d("monteePanneauLondon", "true");
                    panneauLondon.draw(canvas);


                    canvas.drawBitmap(solBitmap, 0, cvH - solBitmap.getHeight(), null);


                    if (panneauLondon.getY() + panneauLondon.getpanneauNantesH() <= (cvH - 17 * solBitmap.getHeight() / 20)) {
                        monteePanneauLondon = false;
                        nbZoom = 0;
                    }
                } else {
                    Log.d("monteePanneauLondon", "false");

                    canvas.drawBitmap(solBitmap, 0, cvH - solBitmap.getHeight(), null);

                    if (panneauLondon.isMoving()) {
                        if (nbZoom % FREQUENCEZOOMNANTES == 0) {
                            nbZoom++;
                            exSizePanneauLondon = panneauLondon.getpanneauNantesW();
                            panneauLondon.zoom();
                            if (panneauLondon.getRotate() != 0) {
                                if (panneauLondon.getRotate() + INCREMENTROTATENANTES > 0) {
                                    panneauLondon.setRotate(0);
                                } else {
                                    panneauLondon.setRotate(panneauLondon.getRotate() + INCREMENTROTATENANTES);
                                }
                            }
                            panneauLondon.setX(panneauLondon.getX() - (panneauLondon.getpanneauNantesW() - exSizePanneauLondon));

                        } else {
                            nbZoom++;
                        }
                    }

                    panneauLondon.draw(canvas);

                    if (panneauLondon.getY() > cvH) {

                        //panneauLondon.setMove(true);


                        panneauLondon.resetSize();
                        panneauLondon.setRotate(ROTATENANTESINIT);
                        panneauLondon.setX(posPanneauLondonInit);

                        panneauLondon.setMove(false); //TODO Décommenter lors de l'ajout de nouveaux éléments

                        Panneau=2;
                        panneauRoterdam.setMove(true);
                    }
                }
                break;
            case 2 :
                if (monteePanneauRoterdam) {

                    Log.d("monteePanneauNantes", "true");
                    panneauRoterdam.draw(canvas);


                    canvas.drawBitmap(solBitmap, 0, cvH - solBitmap.getHeight(), null);


                    if (panneauRoterdam.getY() + panneauRoterdam.getpanneauNantesH() <= (cvH - 17 * solBitmap.getHeight() / 20)) {
                        monteePanneauRoterdam = false;
                        nbZoom = 0;
                    }
                } else {
                    Log.d("monteePanneauNantes", "false");

                    canvas.drawBitmap(solBitmap, 0, cvH - solBitmap.getHeight(), null);

                    if (panneauRoterdam.isMoving()) {
                        if (nbZoom % FREQUENCEZOOMNANTES == 0) {
                            nbZoom++;
                            exSizePanneauRoterdam = panneauRoterdam.getpanneauNantesW();
                            panneauRoterdam.zoom();
                            if (panneauRoterdam.getRotate() != 0) {
                                if (panneauRoterdam.getRotate() + INCREMENTROTATENANTES > 0) {
                                    panneauRoterdam.setRotate(0);
                                } else {
                                    panneauRoterdam.setRotate(panneauRoterdam.getRotate() + INCREMENTROTATENANTES);
                                }
                            }
                            panneauRoterdam.setX(panneauRoterdam.getX() - (panneauRoterdam.getpanneauNantesW() - exSizePanneauRoterdam));

                        } else {
                            nbZoom++;
                        }
                    }

                    panneauRoterdam.draw(canvas);

                    if (panneauRoterdam.getY() > cvH) {

                        //panneauLondon.setMove(true);


                        panneauRoterdam.resetSize();
                        panneauRoterdam.setRotate(ROTATENANTESINIT);
                        panneauRoterdam.setX(posPanneauRoterdamInit);

                        Panneau=3;
                        panneauNougaro.setMove(true);
                        panneauRoterdam.setMove(false); //TODO Décommenter lors de l'ajout de nouveaux éléments

                    }
                }
                break;
            case 3 :
                if (monteePanneauNougaro) {

                    Log.d("monteePanneauNougaro", "true");
                    panneauNougaro.draw(canvas);


                    canvas.drawBitmap(solBitmap, 0, cvH - solBitmap.getHeight(), null);


                    if (panneauNougaro.getY() + panneauNougaro.getpanneauNantesH() <= (cvH - 17 * solBitmap.getHeight() / 20)) {
                        monteePanneauNougaro = false;
                        nbZoom = 0;
                    }
                } else {
                    Log.d("monteePanneauNougaro", "false");

                    canvas.drawBitmap(solBitmap, 0, cvH - solBitmap.getHeight(), null);

                    if (panneauNougaro.isMoving()) {
                        if (nbZoom % FREQUENCEZOOMNANTES == 0) {
                            nbZoom++;
                            exSizePanneauNougaro = panneauNougaro.getpanneauNantesW();
                            panneauNougaro.zoom();
                            if (panneauNougaro.getRotate() != 0) {
                                if (panneauNougaro.getRotate() + INCREMENTROTATENANTES > 0) {
                                    panneauNougaro.setRotate(0);
                                } else {
                                    panneauNougaro.setRotate(panneauNougaro.getRotate() + INCREMENTROTATENANTES);
                                }
                            }
                            panneauNougaro.setX(panneauNougaro.getX() - (panneauNougaro.getpanneauNantesW() - exSizePanneauNougaro));

                        } else {
                            nbZoom++;
                        }
                    }

                    panneauNougaro.draw(canvas);

                    if (panneauNougaro.getY() > cvH) {

                        //panneauLondon.setMove(true);


                        panneauNougaro.resetSize();
                        panneauNougaro.setRotate(ROTATENANTESINIT);
                        panneauNougaro.setX(posPanneauNougaroInit);
                        panneauNougaro.setMove(false); //TODO Décommenter lors de l'ajout de nouveaux éléments
                        panneauValencienne.setMove(true);
                        Panneau =4;

                    }
                }
                break;
            case 4 :
                if (monteePanneauValencienne) {

                    Log.d("monteePanneauValenc", "true");
                    panneauValencienne.draw(canvas);


                    canvas.drawBitmap(solBitmap, 0, cvH - solBitmap.getHeight(), null);


                    if (panneauValencienne.getY() + panneauValencienne.getpanneauNantesH() <= (cvH - 17 * solBitmap.getHeight() / 20)) {
                        monteePanneauValencienne = false;
                        nbZoom = 0;
                    }
                } else {
                    Log.d("monteePanneauValenc", "false");

                    canvas.drawBitmap(solBitmap, 0, cvH - solBitmap.getHeight(), null);

                    if (panneauValencienne.isMoving()) {
                        if (nbZoom % FREQUENCEZOOMNANTES == 0) {
                            nbZoom++;
                            exSizePanneauValencienne = panneauValencienne.getpanneauNantesW();
                            panneauValencienne.zoom();
                            if (panneauValencienne.getRotate() != 0) {
                                if (panneauValencienne.getRotate() + INCREMENTROTATENANTES > 0) {
                                    panneauValencienne.setRotate(0);
                                } else {
                                    panneauValencienne.setRotate(panneauValencienne.getRotate() + INCREMENTROTATENANTES);
                                }
                            }
                            panneauValencienne.setX(panneauValencienne.getX() - (panneauValencienne.getpanneauNantesW() - exSizePanneauValencienne));

                        } else {
                            nbZoom++;
                        }
                    }

                    panneauValencienne.draw(canvas);

                    if (panneauValencienne.getY() > cvH) {

                        //panneauLondon.setMove(true);


                        panneauValencienne.resetSize();
                        panneauValencienne.setRotate(ROTATENANTESINIT);
                        panneauValencienne.setX(posPanneauValencienneInit);
                        panneauValencienne.setMove(false); //TODO Décommenter lors de l'ajout de nouveaux éléments
                        panneauNantes.setMove(true);
                        Panneau =0;
                        monteePanneauNantes=true;
                        monteePanneauLondon=true;
                        monteePanneauRoterdam=true;
                        monteePanneauNougaro=true;
                        monteePanneauValencienne=true;
                        panneauNantes.setY(cvH);
                        panneauRoterdam.setY(cvH);
                        panneauValencienne.setY(cvH);
                        panneauLondon.setY(cvH);
                        panneauNougaro.setY(cvH);
//                        panneauNantes.setY(cvH);
//                        panneauValencienne.setY(cvH);
//                        panneauNougaro.setY(cvH);
//                        panneauLondon.setY(cvH);
//                        panneauRoterdam.setY(cvH);

                    }
                }
                break;
        }


        pointillesF.draw(canvas);
        pointillesS.draw(canvas);


        canvas.drawBitmap(cacheBitmap,cvW/2-cacheBitmap.getWidth()/2,0,null);

        vehiculeAccueil.draw(canvas);

        positionYtitre=9*cvH/64;
        positionXtitre=2*cvW/8;
        espacetitrebouton=cvH/6;

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


        pointillesF.setVitesse(cvW/200);
        pointillesS.setVitesse(cvW/200);
        panneauNantes.setVitesse(cvW/200);
        panneauLondon.setVitesse(cvW/200);
        panneauNougaro.setVitesse(cvW/200);
        panneauValencienne.setVitesse(cvW/200);
        panneauRoterdam.setVitesse(cvW/200);
        vehiculeAccueil.setVitesse(cvW/400);


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
        if(monteePanneauValencienne) {
            panneauValencienne.moveUp();
        } else {
            panneauValencienne.moveDown();
        }
        if(monteePanneauNougaro) {
            panneauNougaro.moveUp();
        } else {
            panneauNougaro.moveDown();
        }
        if(monteePanneauLondon) {
            panneauLondon.moveUp();
        } else {
            panneauLondon.moveDown();
        }
        if(monteePanneauRoterdam) {
            panneauRoterdam.moveUp();
        } else {
            panneauRoterdam.moveDown();
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
        //fond = new FondAccueil(BitmapFactory.decodeResource(getResources(), R.drawable.ciel));;


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

                    //cacheBitmap=null;
                    //solBitmap=null;
                    //gameBitmap=null;
                    //palmaresBitmap=null;
                    //paramBitmap=null;
                    //socialBitmap=null;
                    //cacheBitmap=null;
                    //cielBitmap=null;
                    //stopMenu=true;
 //                   .finish();
//                    cacheBitmap.recycle();
//                    solBitmap.recycle();
//                    gameBitmap.recycle();
//                    palmaresBitmap.recycle();
//                    paramBitmap.recycle();
//                    socialBitmap.recycle();
//                    cacheBitmap.recycle();
//                    cielBitmap.recycle();
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
                    //cacheBitmap=null;
                    //solBitmap=null;
                    //gameBitmap=null;
                    //palmaresBitmap=null;
                    //paramBitmap=null;
                    //socialBitmap=null;
                    //cacheBitmap=null;
                    //cielBitmap=null;
                    stopMenu=true;
//                    cacheBitmap.recycle();
//                    solBitmap.recycle();
//                    gameBitmap.recycle();
//                    palmaresBitmap.recycle();
//                    paramBitmap.recycle();
//                    socialBitmap.recycle();
//                    cacheBitmap.recycle();
//                    cielBitmap.recycle();
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
        panneauLondon.resize(w,h);
        panneauValencienne.resize(w,h);
        panneauNougaro.resize(w,h);
        panneauRoterdam.resize(w,h);
        vehiculeAccueil.resize(w,h);
    }

} // class AccueilView

