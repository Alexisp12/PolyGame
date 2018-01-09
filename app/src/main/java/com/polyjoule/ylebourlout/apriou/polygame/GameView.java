package com.polyjoule.ylebourlout.apriou.polygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.userInfo;
import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.users;
import static com.polyjoule.ylebourlout.apriou.polygame.Game.COEFVEHICULESEVITES;
import static com.polyjoule.ylebourlout.apriou.polygame.Game.DUREEAFFICHAGEGO;
import static com.polyjoule.ylebourlout.apriou.polygame.Game.DUREEAFFICHAGETOTALPANNEAUX;
import static com.polyjoule.ylebourlout.apriou.polygame.Game.DUREEEXPLOFINAL;
import static com.polyjoule.ylebourlout.apriou.polygame.Game.GAINCARBURANT;
import static com.polyjoule.ylebourlout.apriou.polygame.Game.NBENNEMIPARCOLONNEMAX;
import static com.polyjoule.ylebourlout.apriou.polygame.Game.NBVEHICULESENNEMIS;
import static com.polyjoule.ylebourlout.apriou.polygame.Game.PERTECARBURANT;
import static com.polyjoule.ylebourlout.apriou.polygame.Game.RATIOTABLEAUSCORE;
import static com.polyjoule.ylebourlout.apriou.polygame.Game.nbVie;
import static com.polyjoule.ylebourlout.apriou.polygame.Game.pause;

/**
 * Created by Alexis on 19/07/2017.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    // déclaration de l'objet définissant la boucle principale de déplacement et de rendu
    private GameLoopThread gameLoopThread;
    private VehiculePlayer vehicule;
    private VehiculeEnnemi vehiculeEnnemi1;
    private VehiculeEnnemi2 vehiculeEnnemi2;
    private VehiculeEnnemi3 vehiculeEnnemi3;
    private VehiculeEnnemi4 vehiculeEnnemi4;
    private VehiculeEnnemi vehiculeEnnemi5;
    private VehiculeEnnemi2 vehiculeEnnemi6;
    private Carburant carburant;
    private Boolean canMoveVehicule=false;
    private Boolean initialisation=false;
    public static int cvW; // canva width
    public static int cvH; // canva heigh
    private int dureeExplo=0;
    private int dureeClignotementOn=0;
    private int dureeClignotementOff=0;
    private Background bg;
    private int refreshScore=0;
    private int score=0;
    private int levelCarburant;
    private int levelVie;
    private int longueurBarreCarburant;
    private int longueurBarreVie;
    private int levelCarburantMaxInit;
    private int levelVieMaxInit;
    private int coinGPause;
    private int coinDPause;
    private int coinHPause;
    private int coinBPause;
    private int coinGRestart;
    private int coinDRestart;
    private int coinHRestart;
    private int coinBRestart;
    private int coinGClassement;
    private int coinDClassement;
    private int coinHClassement;
    private int coinBClassement;
    private int coinGClassementInit;
    private int coinDClassementInit;
    private int coinHClassementInit;
    private int coinBClassementInit;
    private int bordStartG;
    private int bordStartD;
    private int bordStartB;
    private int bordStartH;
    private int bordTableauGauche;
    private int bordTableauDroit;
    private int longueurTableau;
    private double ratio = RATIOTABLEAUSCORE;
    private int hauteurTableau;
    private int bordTableauHaut;
    private int bordTableauBas;
    private int bordTableauPauseBas;
    public static int routeH;
    public static int routeM;
    public static int routeB;
    private int levelCarburantMinInit;
    private int levelVieMinInit;
    private int tempsAffichageGO=0;
    private int dureeAffichageGO=DUREEAFFICHAGEGO;
    private Boolean baisseCarburant=false;
    private Boolean start=false;
    private Boolean perdu=false;
    private Boolean toastHSdone=false;
    private Boolean startDone=false;
    private String textStart="Cliquez pour commencer";
    private String textPause="Pause";
    private String textPerdu="GAME OVER";
    private String textHS="Record personnel : ";
    private String classementText="Classement :";
    private String textDistance="Distance parcourue";
    private String textVoitureEvites="Collisions évités";
    private String textScoreTotal="Score total";
    private String textRestart="Restart !";
    private int nbVehicules=0;
    private int comptageScoreFinal=0;
    private int comptageDistance=0;
    private int comptageCollision=0;
    private int scorefinal=0;
    private int rangScore=0;
    private Boolean restartEnable=false;
    private int positionnementV1X;
    private int distanceEntreVehiculesRand;
    private int distanceDoigtVoiture=0;
    private int tailleTexte;
    private Boolean collisionVehicule=false;
    private Boolean comptageVehiculeDone=false;
    private Boolean depart=false;
    private Boolean gestionBitmap=false;
    private Boolean touched1=false;
    private Boolean touched2=false;
    private int dureeAffichagePanneaux=0;
    private int longueurStart;
    private Drawable cinqdraw;
    private Drawable quatredraw;
    private Drawable troisdraw;
    private Drawable deuxdraw;
    private Drawable undraw;
    private Drawable godraw;
    private Drawable touchtostartdraw;
    private Bitmap cinqBitmap;
    private Bitmap quatreBitmap;
    private Bitmap troisBitmap;
    private Bitmap deuxBitmap;
    private Bitmap unBitmap;
    private Bitmap goBitmap;
    private Bitmap touchtostartBitmap;
    private Bitmap tableauScoreBitmap;
    private Bitmap tableauPauseBitmap;
    private Bitmap brokenBitmap;
    private Bitmap levelCarburantBitmapInit;
    private Bitmap levelCarburantBitmapCroped;
    private Bitmap contourCarburant;
    private Paint contourCarburantPaint;
    private Boolean[] stratEnnemiDone; // Une case par rang sur l'écran
    private int [] stratEnnemi;
    private int randomStrat;
    private int colonneEnnemiAVenir=0;
    private Boolean [] vehiculeDispo;
    private Boolean newColonne=true;
    private int highestEnnemi; // Ennemi le plus dans l'écran
    private int distanceEntreVehicules1;
    private int distanceEntreVehicules2;
    private int distanceEntreVehicules3;
    private int distanceEntreVehicules4;
    private int decalage;
    private int cpt1,cpt2;
    private int decalageRanded;
    private int vitesse1,vitesse2,vitesse3,vitesse4,vitesse5,vitesse6;
    private double incrementVitesse;
    private double sommeIncrementVitesse=0;
    private int vitesseDeplacementBG,vitesseDeplacementBG2,vitesseDeplacementBG3,vitesseDeplacementBG4,vitesseDeplacementBG5,vitesseDeplacementBG6;
    private int vitesseDeplacementBGActuel;

    //private String textRestart="Record : ";


    // création de la surface de dessin
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        gameLoopThread = new GameLoopThread(this);

        // création d'un objet "vehicule", dont on définira la largeur/hauteur
        // selon la largeur ou la hauteur de l'écran
        vehicule = new VehiculePlayer(this.getContext());
        carburant = new Carburant (this.getContext());
        vehiculeEnnemi1 = new VehiculeEnnemi(this.getContext());
        vehiculeEnnemi2 = new VehiculeEnnemi2(this.getContext());
        vehiculeEnnemi3 = new VehiculeEnnemi3(this.getContext());
        vehiculeEnnemi4 = new VehiculeEnnemi4(this.getContext());
        vehiculeEnnemi5 = new VehiculeEnnemi(this.getContext());
        vehiculeEnnemi6 = new VehiculeEnnemi2(this.getContext());


        stratEnnemiDone = new Boolean [NBENNEMIPARCOLONNEMAX];
        stratEnnemi = new int [NBENNEMIPARCOLONNEMAX];
        vehiculeDispo = new Boolean [NBVEHICULESENNEMIS];

        for(int i=0;i<stratEnnemiDone.length;i++){
            stratEnnemiDone[i]=false;
        }
        for(int j=0; j<vehiculeDispo.length;j++){
            vehiculeDispo[j]=true;
        }
//        cinqdraw = ContextCompat.getDrawable(this.getContext(),R.drawable.cinq);
//        quatredraw = ContextCompat.getDrawable(this.getContext(),R.drawable.quatre);
//        troisdraw = ContextCompat.getDrawable(this.getContext(),R.drawable.trois);
//        deuxdraw = ContextCompat.getDrawable(this.getContext(),R.drawable.deux);
//        undraw = ContextCompat.getDrawable(this.getContext(),R.drawable.un);
//        godraw = ContextCompat.getDrawable(this.getContext(),R.drawable.go);
//        touchtostartdraw = ContextCompat.getDrawable(this.getContext(),R.drawable.touchtostart);




    }

    // Fonction qui "dessine" un écran de jeu
    public void doDraw(Canvas canvas) {
        if(canvas==null) {return;}

        // Conv dp
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        float dp = 30f; // 30 dp
        float dp2 = 50f;
        float dp3=10f;
        float dp4=20f;
        float dp5=14f;
        float dp6=12f;
        float dp7=9f;// 9f
        float dp8=25f;
        float dp9=22f;
        float fpixels9=metrics.density*dp9;
        float fpixels8=metrics.density*dp8;
        float fpixels7=metrics.density*dp7;
        float fpixels6=metrics.density*dp6;
        float fpixels5=metrics.density*dp5;
        float fpixels4=metrics.density*dp4;
        float fpixels3=metrics.density*dp3;
        float fpixels2=metrics.density*dp2;
        float fpixels = metrics.density * dp;
        int pixels = (int) (fpixels + 0.5f);
        int pixels2=(int) (fpixels2+0.5f);
        int pixels3=(int) (fpixels3+0.5f);
        int pixels4=(int) (fpixels4+0.5f);
        int pixels5=(int) (fpixels5+0.5f);
        int pixels6=(int) (fpixels6+0.5f);
        int pixels7=(int) (fpixels7+0.5f);
        int pixels8 = (int) (fpixels8 + 0.5f);
        int pixels9=(int) (fpixels9+0.5f);
        cvH = canvas.getHeight();
        cvW = canvas.getWidth();

        routeH=5*cvW/16-pixels9;
        routeM=cvW/2-pixels8;
        routeB=43*cvW/64-pixels9;

        if(bg!=null) {
            bg.draw(canvas);
        }

        if(!initialisation){

            vehicule.setY(cvH-vehicule.getvehiculePlayerH()-cvH/8);
            vehicule.setX((cvW/2)-vehicule.getvehiculePlayerW()/2);

            vehiculeEnnemi1.setX(cvW);
            vehiculeEnnemi1.setY(-2*vehiculeEnnemi1.getvehiculeEnnemiH());
            vehiculeEnnemi2.setX(cvW);
            vehiculeEnnemi2.setY(-2*vehiculeEnnemi2.getvehiculeEnnemiH());
            vehiculeEnnemi3.setX(cvW);
            vehiculeEnnemi3.setY(-2*vehiculeEnnemi3.getvehiculeEnnemiH());
            vehiculeEnnemi4.setX(cvW);
            vehiculeEnnemi4.setY(-2*vehiculeEnnemi4.getvehiculeEnnemiH());
            vehiculeEnnemi5.setX(cvW);
            vehiculeEnnemi5.setY(-2*vehiculeEnnemi5.getvehiculeEnnemiH());
            vehiculeEnnemi6.setX(cvW);
            vehiculeEnnemi6.setY(-2*vehiculeEnnemi6.getvehiculeEnnemiH());

            vitesse1=cvW/80;
            vitesse2=cvW/60;
            vitesse3=cvW/40;
            vitesse4=vitesse1;
            vitesse5=vitesse1;
            vitesse6=vitesse1;
            incrementVitesse=(double) cvW/8000;
            vitesseDeplacementBG=cvW/60; // 64
            vitesseDeplacementBG2=cvW/44;
            vitesseDeplacementBG3=cvW/34;

            vehiculeEnnemi1.setVitesse(vitesse1);
            vehiculeEnnemi2.setVitesse(vitesse1);
            vehiculeEnnemi3.setVitesse(vitesse1);
            vehiculeEnnemi4.setVitesse(vitesse1);
            vehiculeEnnemi5.setVitesse(vitesse1);
            vehiculeEnnemi6.setVitesse(vitesse1);

            distanceEntreVehicules1=-7*vehicule.getvehiculePlayerH()/4;
            distanceEntreVehicules2=-2*vehicule.getvehiculePlayerH();
            distanceEntreVehicules3=-5*vehicule.getvehiculePlayerH()/2;
            distanceEntreVehicules4=-3*vehicule.getvehiculePlayerH();


            carburant.setY(cvH);
            carburant.setX(cvW);
            carburant.setVitesse(vitesseDeplacementBG);

            coinGPause=cvW/2-cvW/64;
            coinHPause=pixels3;
            coinDPause=cvW/2+cvW/64;
            coinBPause=pixels3+pixels/2;

            coinGClassementInit = cvW/3;
            coinDClassementInit = 2*cvW/3;
            //longueurClassement = coinDClassement-coinGClassement;
            //hauteurClassement = ((int) Math.round(longueurClassement/ratioClassement));
            coinHClassementInit = 4*cvH/64;
            coinBClassementInit =coinHClassementInit + ((coinDClassementInit-coinGClassementInit)/(BitmapFactory.decodeResource(getResources(), R.drawable.classemenths).getWidth()/BitmapFactory.decodeResource(getResources(), R.drawable.classemenths).getHeight()));    // 12/16;


            coinGClassement = cvW/2-vehicule.getvehiculePlayerW()/2;
            coinDClassement = cvW/2+vehicule.getvehiculePlayerW()/2;
            //longueurClassement = coinDClassement-coinGClassement;
            //hauteurClassement = ((int) Math.round(longueurClassement/ratioClassement));
            coinHClassement = 32*cvH/64;
            coinBClassement =coinHClassement + ((coinDClassement-coinGClassement)/(BitmapFactory.decodeResource(getResources(), R.drawable.classemenths).getWidth()/BitmapFactory.decodeResource(getResources(), R.drawable.classemenths).getHeight()));    // 12/16;


            //start
            bordStartG=cvW/16;
            bordStartD=cvW-bordStartG;
            bordStartH=cvH/3;
            longueurStart=bordStartD-bordStartG;
            bordStartB=bordStartH + (longueurStart/(BitmapFactory.decodeResource(getResources(), R.drawable.touchtostart).getWidth()/BitmapFactory.decodeResource(getResources(), R.drawable.touchtostart).getHeight()));   //bordStartH + ((int) Math.round( longueurStart/RATIOSTART)

            // carburant
            levelCarburantMinInit=3*pixels3;
            levelCarburantMaxInit=3*pixels3+vehicule.getvehiculePlayerH()/2;
            //levelVieMaxInit=cvW-cvW/40;
            //levelVieMinInit=cvW-3*cvW/32;
            levelCarburant=levelCarburantMaxInit;
            //levelVie=levelVieMaxInit;
            longueurBarreCarburant=levelCarburant-(levelCarburantMinInit);
            //longueurBarreVie=levelVie-(cvW-3*cvW/32);

            // ANCIENNE VALEURS
//            levelCarburantMaxInit=cvW-cvW/50;
//            levelCarburantMinInit=cvW-3*cvW/40;
//            levelVieMaxInit=cvW-cvW/40;
//            levelVieMinInit=cvW-3*cvW/32;
//            levelCarburant=levelCarburantMaxInit;
//            levelVie=levelVieMaxInit;
//            longueurBarreCarburant=levelCarburant-(levelCarburantMinInit);
//            longueurBarreVie=levelVie-(cvW-3*cvW/32);

            // tableau score
            bordTableauGauche=cvW/32;
            bordTableauDroit =31*cvW/32;
            longueurTableau = bordTableauDroit - bordTableauGauche;
            hauteurTableau = ((int) Math.round(longueurTableau/ratio));
            bordTableauHaut=cvH/4;
            bordTableauBas=bordTableauHaut+22*(longueurTableau/(BitmapFactory.decodeResource(getResources(), R.drawable.finish).getWidth()/BitmapFactory.decodeResource(getResources(), R.drawable.finish).getHeight()))/32;    // 12/16;
            bordTableauPauseBas=bordTableauHaut+22*(longueurTableau/(BitmapFactory.decodeResource(getResources(), R.drawable.tableaupause).getWidth()/BitmapFactory.decodeResource(getResources(), R.drawable.tableaupause).getHeight()))/32;    // 12/16;


            if(!gestionBitmap) {
                cinqBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cinq), longueurStart, bordStartB - bordStartH, false);
                quatreBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.quatre), longueurStart, bordStartB - bordStartH, false);

                troisBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.trois), longueurStart, bordStartB - bordStartH, false);

                deuxBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.deux), longueurStart, bordStartB - bordStartH, false);
                unBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.un), longueurStart, bordStartB - bordStartH, false);
                goBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.go), longueurStart, bordStartB - bordStartH, false);
                touchtostartBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.touchtostart), longueurStart, bordStartB - bordStartH, false);
                tableauScoreBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.finish), longueurTableau, bordTableauBas - bordTableauHaut, false);
                tableauPauseBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tableaupause), longueurTableau, bordTableauPauseBas - bordTableauHaut, false);
                brokenBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.broken), vehicule.getvehiculePlayerW(), vehicule.getvehiculePlayerH(), false);

                levelCarburantBitmapInit = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.carburantlogo), vehicule.getvehiculePlayerW()/3, longueurBarreCarburant, false);
                //canvas.drawBitmap(levelCarburantBitmap,cvW-2*vehicule.getvehiculePlayerW()/3,levelCarburantMinInit,null);

                levelCarburantBitmapCroped = Bitmap.createBitmap(levelCarburantBitmapInit,0,levelCarburantMaxInit-levelCarburant,vehicule.getvehiculePlayerW()/3,levelCarburant-levelCarburantMinInit);
                //canvas.drawBitmap(levelCarburantBitmapCroped,cvW-2*vehicule.getvehiculePlayerW()/3,levelCarburantMinInit+(levelCarburantMaxInit-levelCarburant),null);
                contourCarburant = Bitmap.createBitmap(levelCarburantBitmapInit.getWidth(),levelCarburantBitmapInit.getHeight(),Bitmap.Config.ARGB_8888);// RGB_565
                //contourCarburantPaint = new Paint();
                //contourCarburantPaint.setColor(Color.WHITE);
                //contourCarburantPaint.setStrokeWidth(10);


                for(int j=0;j<levelCarburantBitmapInit.getHeight();j++) {
                    for (int i = 0; i < levelCarburantBitmapInit.getWidth(); i++) {
                        if(contourCarburant.getPixel(i,j)!=Color.WHITE) {
                            contourCarburant.setPixel(i, j, Color.TRANSPARENT);
                        }
                        if(i==0){
                            if(levelCarburantBitmapInit.getPixel(i, j) != 0){
                                contourCarburant.setPixel(i, j, Color.WHITE);
                                contourCarburant.setPixel(i+1, j, Color.WHITE);
                                contourCarburant.setPixel(i+2, j, Color.WHITE);
                            }
                        }
                        if(i==levelCarburantBitmapInit.getWidth()-1){
                            if(levelCarburantBitmapInit.getPixel(i, j) != 0){
                                contourCarburant.setPixel(i, j, Color.WHITE);
                                contourCarburant.setPixel(i-1, j, Color.WHITE);
                                contourCarburant.setPixel(i-2, j, Color.WHITE);
                            }
                        }
                        if(j==0){
                            if(levelCarburantBitmapInit.getPixel(i, j) != 0){
                                contourCarburant.setPixel(i, j, Color.WHITE);
                                contourCarburant.setPixel(i, j+1, Color.WHITE);
                                contourCarburant.setPixel(i, j+2, Color.WHITE);
                            }
                        }
                        if(j==levelCarburantBitmapInit.getHeight()-1){
                            if(levelCarburantBitmapInit.getPixel(i, j) != 0){
                                contourCarburant.setPixel(i, j, Color.WHITE);
                                contourCarburant.setPixel(i, j-1, Color.WHITE);
                                contourCarburant.setPixel(i, j-2, Color.WHITE);
                            }
                        }
                        if(i!=0 && j!=0 && i!=levelCarburantBitmapInit.getWidth()-1 && j!=levelCarburantBitmapInit.getHeight()-1) {
                            if ((levelCarburantBitmapInit.getPixel(i, j) != 0 && levelCarburantBitmapInit.getPixel(i - 1, j) == 0) || (levelCarburantBitmapInit.getPixel(i, j) != 0 && levelCarburantBitmapInit.getPixel(i + 1, j) == 0)) {
//                                canvas.drawPoint(cvW-2*vehicule.getvehiculePlayerW()/3+i,levelCarburantMinInit+j,contourCarburantPaint);

                                contourCarburant.setPixel(i, j, Color.WHITE);
                                contourCarburant.setPixel(i-1, j, Color.WHITE);
                                contourCarburant.setPixel(i+1, j, Color.WHITE);
                            }


                            if ((levelCarburantBitmapInit.getPixel(i, j) != 0 && levelCarburantBitmapInit.getPixel(i, j + 1) == 0) || (levelCarburantBitmapInit.getPixel(i, j) != 0 && levelCarburantBitmapInit.getPixel(i, j - 1) == 0)) {
 //                               canvas.drawPoint(cvW-2*vehicule.getvehiculePlayerW()/3+i,levelCarburantMinInit+j,contourCarburantPaint);
                                contourCarburant.setPixel(i, j, Color.WHITE);
                                contourCarburant.setPixel(i, j+1, Color.WHITE);
                                contourCarburant.setPixel(i, j-1, Color.WHITE);
                            }
                        }
                    }
                }
//                for(int j=0;j<levelCarburantBitmapInit.getHeight();j++) {
//                    for (int i = 0; i < levelCarburantBitmapInit.getWidth(); i++) {
//                        Log.d("contourCarbu("+i+","+j+")",Integer.toString(contourCarburant.getPixel(i,j)));
//                    }
//                }

                //canvas.drawBitmap(contourCarburant,cvW-2*vehicule.getvehiculePlayerW()/3,levelCarburantMinInit+(levelCarburantMaxInit-levelCarburant),null);

                gestionBitmap=true;
            }
//            cinqdraw.setBounds(bordStartG,bordStartH,bordStartD,bordStartB);
//            quatredraw.setBounds(bordStartG,bordStartH,bordStartD,bordStartB);
//            troisdraw.setBounds(bordStartG,bordStartH,bordStartD,bordStartB);
//            deuxdraw.setBounds(bordStartG,bordStartH,bordStartD,bordStartB);
//            undraw.setBounds(bordStartG,bordStartH,bordStartD,bordStartB);
//            godraw.setBounds(bordStartG,bordStartH,bordStartD,bordStartB);
//            touchtostartdraw.setBounds(bordStartG,bordStartH,bordStartD,bordStartB);


//            if(dureeClignotementOn%15==0 || dureeClignotementOn==0) {
//                // Cliquez pour commencer
//                TextPaint textPaintStart = new TextPaint();
//                textPaintStart.setTextSize(pixels4);
//                textPaintStart.setColor(ContextCompat.getColor(this.getContext(), R.color.colorPrimaryDark));
//                canvas.drawText(textStart, 5*cvW / 16, 11 * cvH / 16, textPaintStart);
//                if(dureeClignotementOff%20==0 && dureeClignotementOff!=0) {
//                    dureeClignotementOn++;
//                    dureeClignotementOff=0;
//                } else {
//                    dureeClignotementOff++;
//                }
//            } else {
//                dureeClignotementOn++;
//            }



            if(start) {
                if(!startDone) {
                    carburant.setMove(true);
                    //vehiculeEnnemi1.setMove(true);
                    initialisation = true;
                    bg.setMove(true);
                    bg.setVector(-vitesseDeplacementBG);
                    vitesseDeplacementBGActuel=-vitesseDeplacementBG;
                    dureeClignotementOn = 0;
                    touchtostartBitmap=null;
                    goBitmap=null;
                    unBitmap=null;
                    deuxBitmap=null;
                    troisBitmap=null;
                    quatreBitmap=null;
                    cinqBitmap=null;
                    startDone=true;
                    pause=false;
                }
            }

        }


        // TODO ancien niveau carburant
//        // Level Carburant
//        TextPaint paintlevelCarburant = new TextPaint();
//        paintlevelCarburant.setTextSize(pixels);
//
//        paintlevelCarburant.setColor(ContextCompat.getColor(this.getContext(),R.color.colorPrimaryDark));
//        canvas.drawRect(levelCarburantMinInit,pixels3,levelCarburant,pixels3+pixels/3,paintlevelCarburant);
//
//        // Image carburant
//        Drawable carburantDraw1 = ContextCompat.getDrawable(this.getContext(),R.drawable.carburantlogo);
//        //d.setHotspot(canvas.getWidth()-2*pixels,canvas.getHeight()-pixels);
//        carburantDraw1.setBounds(levelCarburantMinInit-pixels2/12-carburant.getcarburantH()/2,pixels3,levelCarburantMinInit-pixels2/12,pixels3+pixels/3);
//        carburantDraw1.draw(canvas);


        // Image carburant
//        Drawable carburantDraw = ContextCompat.getDrawable(this.getContext(),R.drawable.carburantlogo);
//        //d.setHotspot(canvas.getWidth()-2*pixels,canvas.getHeight()-pixels);
//        carburantDraw.setBounds(cvW-2*vehicule.getvehiculePlayerW()/3,levelCarburantMinInit,cvW-1*vehicule.getvehiculePlayerW()/3,levelCarburantMaxInit);
//        carburantDraw.draw(canvas);


//        // Level Vie
//        TextPaint paintlevelVie = new TextPaint();
//        paintlevelVie.setTextSize(pixels);
//        paintlevelVie.setColor(Color.RED);
//        canvas.drawRect(levelVieMinInit-(cvW-(levelVieMinInit-pixels2/5-carburant.getcarburantW()/2)),pixels3,levelVie-(cvW-(levelVieMinInit-pixels2/5-carburant.getcarburantW()/2)),pixels3+pixels/2,paintlevelVie);
//
//        // Image vie
//        Drawable vieDraw = ContextCompat.getDrawable(this.getContext(),R.drawable.coeur);
//        //d.setHotspot(canvas.getWidth()-2*pixels,canvas.getHeight()-pixels);
//        vieDraw.setBounds(levelVieMinInit-pixels2/2-carburant.getcarburantW()/2-(cvW-(levelVieMinInit-pixels2/5-carburant.getcarburantW())),pixels3,levelVieMinInit-(pixels3/2)-(cvW-(levelVieMinInit-pixels2/5-carburant.getcarburantW()/2)),pixels3+pixels/2);
//        vieDraw.draw(canvas);


        // TODO /////// CLASSEMENT RECUP CE CODE
//        if(users.size()!=0) {
//            if (users.size() == 1) {
//                if(users.get(0).getHighScore()!=-1) {
//                    // Top 3
////                        TextPaint textPaintClassement = new TextPaint();
////                        textPaintClassement.setTextSize(pixels5);
////                        textPaintClassement.setColor(ContextCompat.getColor(this.getContext(), R.color.primary_dark_material_dark_1));
////                        canvas.drawText(classementText, pixels3, cvH - pixels3, textPaintClassement);
//
//                    TextPaint textPaintTop1 = new TextPaint();
//                    textPaintTop1.setTextSize(pixels5);
//                    textPaintTop1.setColor(ContextCompat.getColor(this.getContext(), R.color.top1));
//                    canvas.drawText(users.get(0).getPseudo() + " : " + users.get(0).highScore, pixels3, cvH - pixels3, textPaintTop1);
//
//                }
//
//            } else {
//                if (users.size() == 2) {
//                    if(users.get(0).getHighScore()!=-1) {
//                        // Top 3
////                        TextPaint textPaintClassement = new TextPaint();
////                        textPaintClassement.setTextSize(pixels5);
////                        textPaintClassement.setColor(ContextCompat.getColor(this.getContext(), R.color.primary_dark_material_dark_1));
////                        canvas.drawText(classementText, pixels3, cvH - pixels3, textPaintClassement);
//
//                        TextPaint textPaintTop1 = new TextPaint();
//                        textPaintTop1.setTextSize(pixels5);
//                        textPaintTop1.setColor(ContextCompat.getColor(this.getContext(), R.color.top1));
//                        canvas.drawText(users.get(0).getPseudo() + " : " + users.get(0).highScore, pixels3, cvH - pixels3, textPaintTop1);
//
//                        if(users.get(1).getHighScore()!=-1) {
//                            TextPaint textPaintTop2 = new TextPaint();
//                            textPaintTop2.setTextSize(pixels5);
//                            textPaintTop2.setColor(ContextCompat.getColor(this.getContext(), R.color.top2));
//                            canvas.drawText(users.get(1).getPseudo() + " : " + users.get(1).highScore, cvW/2, cvH - pixels3, textPaintTop2);
//                        }
//                    }
//                } else {
//
//
//                    if(users.get(0).getHighScore()!=-1) {
//                        // Top 3
////                        TextPaint textPaintClassement = new TextPaint();
////                        textPaintClassement.setTextSize(pixels5);
////                        textPaintClassement.setColor(ContextCompat.getColor(this.getContext(), R.color.primary_dark_material_dark_1));
////                        canvas.drawText(classementText, pixels3, cvH - pixels3, textPaintClassement);
//
//                        TextPaint textPaintTop1 = new TextPaint();
//                        textPaintTop1.setTextSize(pixels5);
//                        textPaintTop1.setColor(ContextCompat.getColor(this.getContext(), R.color.top1));
//                        canvas.drawText(users.get(0).getPseudo() + " : " + users.get(0).highScore, pixels3, cvH - pixels3, textPaintTop1);
//
//                        if(users.get(1).getHighScore()!=-1) {
//                            TextPaint textPaintTop2 = new TextPaint();
//                            textPaintTop2.setTextSize(pixels5);
//                            textPaintTop2.setColor(ContextCompat.getColor(this.getContext(), R.color.top2));
//                            canvas.drawText(users.get(1).getPseudo() + " : " + users.get(1).highScore, cvW/2, cvH - pixels3, textPaintTop2);
//                            if(users.get(2).getHighScore()!=-1){
//                                TextPaint textPaintTop3 = new TextPaint();
//                                textPaintTop3.setTextSize(pixels5);
//                                textPaintTop3.setColor(ContextCompat.getColor(this.getContext(), R.color.top3));
//                                canvas.drawText( users.get(2).getPseudo() + " : " + users.get(2).highScore, 3*cvW/4, cvH -  pixels3, textPaintTop3);
//                            }
//                        }
//                    }
//
//
//                }
//            }
//        }



        // carburant
        if(carburant.getY()<-carburant.getcarburantH()){
            // Inutile de draw en dehors de l'écran
        } else {
            carburant.draw(canvas);
        }
        // vehicule ennemi1
        if(vehiculeEnnemi1.getY()<-vehiculeEnnemi1.getvehiculeEnnemiH()){
            //vehiculeDispo[0]=true;
        } else {
            vehiculeEnnemi1.draw(canvas);
            //Log.d("vehicule1","nondispo");
            //vehiculeDispo[0]=false;
        }
        // vehicule ennemi2
        if(vehiculeEnnemi2.getY()<-vehiculeEnnemi2.getvehiculeEnnemiH()){
            // Inutile de draw en dehors de l'écran
            //vehiculeDispo[1]=true;
        } else {
            vehiculeEnnemi2.draw(canvas);
            //vehiculeDispo[1]=false;
        }
        // vehicule ennemi1
        if(vehiculeEnnemi3.getY()<-vehiculeEnnemi3.getvehiculeEnnemiH()){
            // Inutile de draw en dehors de l'écran
            //vehiculeDispo[2]=true;
        } else {
            vehiculeEnnemi3.draw(canvas);
            //vehiculeDispo[2]=false;
        }
        // vehicule ennemi1
        if(vehiculeEnnemi4.getY()<-vehiculeEnnemi4.getvehiculeEnnemiH()){
            // Inutile de draw en dehors de l'écran
           // vehiculeDispo[3]=true;
        } else {
            vehiculeEnnemi4.draw(canvas);
            //vehiculeDispo[3]=false;
        }
        // vehicule ennemi1
        if(vehiculeEnnemi5.getY()<-vehiculeEnnemi5.getvehiculeEnnemiH()){
            // Inutile de draw en dehors de l'écran
            //vehiculeDispo[4]=true;
        } else {
            vehiculeEnnemi5.draw(canvas);
            //vehiculeDispo[4]=false;
        }
        // vehicule ennemi1
        if(vehiculeEnnemi6.getY()<-vehiculeEnnemi6.getvehiculeEnnemiH()){
            // Inutile de draw en dehors de l'écran
            //vehiculeDispo[5]=true;
        } else {
            vehiculeEnnemi6.draw(canvas);
            //vehiculeDispo[5]=false;
            //Log.d("vehicule6","nondispo");
        }

        // Vehicule player
        if(!collisionVehicule) {
            vehicule.draw(canvas);
        }


        // Image resize
        if(depart){

            dureeAffichagePanneaux++;

            if(dureeAffichagePanneaux<DUREEAFFICHAGETOTALPANNEAUX){
                //Drawable startDraw = ContextCompat.getDrawable(this.getContext(),R.drawable.cinq);
                //startDraw.setBounds(bordStartG,bordStartH,bordStartD,bordStartB);

                //cinqdraw.draw(canvas);
                touchtostartBitmap.recycle();
                //touchtostartBitmap=null;
                canvas.drawBitmap(cinqBitmap,bordStartG,bordStartH,null);

            } else {
                if(dureeAffichagePanneaux<DUREEAFFICHAGETOTALPANNEAUX*2){
                    //Drawable startDraw = ContextCompat.getDrawable(this.getContext(),R.drawable.quatre);
                    //startDraw.setBounds(bordStartG,bordStartH,bordStartD,bordStartB);

                    //quatredraw.draw(canvas);
                    cinqBitmap.recycle();
                    //cinqBitmap=null;
                    canvas.drawBitmap(quatreBitmap,bordStartG,bordStartH,null);
                } else {
                    if(dureeAffichagePanneaux<DUREEAFFICHAGETOTALPANNEAUX*3){
                        // Drawable startDraw = ContextCompat.getDrawable(this.getContext(),R.drawable.trois);
                        //startDraw.setBounds(bordStartG,bordStartH,bordStartD,bordStartB);

                        //troisdraw.draw(canvas);
                        quatreBitmap.recycle();
                        //quatreBitmap=null;
                        canvas.drawBitmap(troisBitmap,bordStartG,bordStartH,null);
                    } else {
                        if(dureeAffichagePanneaux<DUREEAFFICHAGETOTALPANNEAUX*4){
                            //Drawable startDraw = ContextCompat.getDrawable(this.getContext(),R.drawable.deux);
                            //startDraw.setBounds(bordStartG,bordStartH,bordStartD,bordStartB);

                            //deuxdraw.draw(canvas);
                            troisBitmap.recycle();
                            //troisBitmap=null;
                            canvas.drawBitmap(deuxBitmap,bordStartG,bordStartH,null);
                        } else {
                            if(dureeAffichagePanneaux<DUREEAFFICHAGETOTALPANNEAUX*5) {
                                //Drawable startDraw = ContextCompat.getDrawable(this.getContext(), R.drawable.un);
                                //startDraw.setBounds(bordStartG, bordStartH, bordStartD, bordStartB);

                                //undraw.draw(canvas);
                                deuxBitmap.recycle();
                                //deuxBitmap=null;
                                canvas.drawBitmap(unBitmap,bordStartG,bordStartH,null);
                            } else {
                                if(dureeAffichagePanneaux<DUREEAFFICHAGETOTALPANNEAUX*6) {
                                    //Drawable startDraw = ContextCompat.getDrawable(this.getContext(), R.drawable.go);
                                    //startDraw.setBounds(bordStartG, bordStartH, bordStartD, bordStartB);

                                    //godraw.draw(canvas);
                                    unBitmap.recycle();
                                    //unBitmap=null;
                                    canvas.drawBitmap(goBitmap,bordStartG,bordStartH,null);
                                } else {
                                    start = true;
                                    goBitmap.recycle();
                                    //goBitmap=null;
                                    touchtostartBitmap=null;
                                    Game.startMusique();
                                    depart=false;
                                    dureeAffichagePanneaux=0;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if(!start) {
                //Drawable startDraw = ContextCompat.getDrawable(this.getContext(), R.drawable.touchtostart);
                //d.setHotspot(canvas.getWidth()-2*pixels,canvas.getHeight()-pixels);
                //startDraw.setBounds(bordStartG, bordStartH, bordStartD, bordStartB);

                //touchtostartdraw.draw(canvas);
                canvas.drawBitmap(touchtostartBitmap,bordStartG,bordStartH,null);

                // Classement
                Drawable classementDraw = ContextCompat.getDrawable(this.getContext(), R.drawable.classemenths);
                //d.setHotspot(canvas.getWidth()-2*pixels,canvas.getHeight()-pixels);
                classementDraw.setBounds(coinGClassementInit, coinHClassementInit, coinDClassementInit, coinBClassementInit);
                classementDraw.draw(canvas);
            } else {
                // ScoreText
                if(toastHSdone){
                    TextPaint textPaintScorehs = new TextPaint();
                    textPaintScorehs.setTextSize(pixels5);
                    textPaintScorehs.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                    textPaintScorehs.setColor(ContextCompat.getColor(this.getContext(), R.color.newhs));
                    canvas.drawText(Integer.toString(score), pixels3 / 2, 2 * pixels3, textPaintScorehs);
                } else {
                    TextPaint textPaintScore = new TextPaint();
                    textPaintScore.setTextSize(pixels5);
                    textPaintScore.setColor(ContextCompat.getColor(this.getContext(), R.color.colorPrimaryDark));
                    canvas.drawText(Integer.toString(score), pixels3 / 2, 2 * pixels3, textPaintScore);
                }

                canvas.drawBitmap(contourCarburant,cvW-2*vehicule.getvehiculePlayerW()/3,levelCarburantMinInit,null);
                levelCarburantBitmapCroped = Bitmap.createBitmap(levelCarburantBitmapInit,0,levelCarburantMaxInit-levelCarburant,vehicule.getvehiculePlayerW()/3,levelCarburant-levelCarburantMinInit);
                canvas.drawBitmap(levelCarburantBitmapCroped,cvW-2*vehicule.getvehiculePlayerW()/3,levelCarburantMinInit+(levelCarburantMaxInit-levelCarburant),null);



                // Bouton pause
                if(!pause) {
                    Drawable pauseDraw = ContextCompat.getDrawable(this.getContext(), R.drawable.pause);
                    //d.setHotspot(canvas.getWidth()-2*pixels,canvas.getHeight()-pixels);
                    pauseDraw.setBounds(coinGPause, coinHPause, coinDPause, coinBPause);
                    pauseDraw.draw(canvas);
                } else {
                    Drawable pauseDraw = ContextCompat.getDrawable(this.getContext(), R.drawable.lecture);
                    //d.setHotspot(canvas.getWidth()-2*pixels,canvas.getHeight()-pixels);
                    pauseDraw.setBounds(coinGPause, coinHPause, coinDPause, coinBPause);
                    pauseDraw.draw(canvas);

//            TextPaint textPaintPause = new TextPaint();
//            textPaintPause.setTextSize(pixels);
//            textPaintPause.setColor(ContextCompat.getColor(this.getContext(), R.color.colorPrimaryDark));
//            canvas.drawText(textPause,12*cvW/32, 5*cvH/12, textPaintPause);

                    canvas.drawBitmap(tableauPauseBitmap,bordTableauGauche,bordTableauHaut,null);

                    // Classement
                    Drawable classementDraw = ContextCompat.getDrawable(this.getContext(), R.drawable.classemenths);
                    //d.setHotspot(canvas.getWidth()-2*pixels,canvas.getHeight()-pixels);
                    classementDraw.setBounds(coinGClassement, coinHClassement, coinDClassement, coinBClassement);
                    classementDraw.draw(canvas);

                    TextPaint textPaintHS = new TextPaint();
                    textPaintHS.setTextSize(pixels6);
                    textPaintHS.setColor(ContextCompat.getColor(this.getContext(), R.color.colorPrimaryDark));

                    if(userInfo!=null) {
                        if(userInfo.getHighScore()!=-1) {
                            canvas.drawText(textHS + userInfo.getHighScore(), 11*cvW / 40, 29*cvH/64, textPaintHS);
                        } else {
                            canvas.drawText(textHS + "0", 9*cvW / 40, 29*cvH/64, textPaintHS);
                        }
                    }

                }
            }
        }


        if(collisionVehicule){
            Game.pauseMusique();
            if(dureeExplo!=DUREEEXPLOFINAL) {
//                Drawable explo = ContextCompat.getDrawable(this.getContext(), R.drawable.explo);
//                explo.setBounds(vehicule.getX()-vehicule.getvehiculePlayerH()/2,vehicule.getY()-vehicule.getvehiculePlayerH()/2,vehicule.getX()+vehicule.getvehiculePlayerW()+vehicule.getvehiculePlayerH()/2,vehicule.getY()+3*vehicule.getvehiculePlayerH()/2);
//                explo.draw(canvas);
                canvas.drawBitmap(brokenBitmap,vehicule.getX(),vehicule.getY(),null);
                dureeExplo++;
            } else {
                perdu=true;
            }
        }



        if(perdu){
            Game.pauseMusique();


            vehiculeEnnemi1.setMove(false);
            vehiculeEnnemi2.setMove(false);
            vehiculeEnnemi3.setMove(false);
            vehiculeEnnemi4.setMove(false);
            vehiculeEnnemi5.setMove(false);
            vehiculeEnnemi6.setMove(false);

            // Image Game Over
            //Drawable goDraw = ContextCompat.getDrawable(this.getContext(),R.drawable.finish);
            // resize


//            Drawable goDraw = ContextCompat.getDrawable(this.getContext(),R.drawable.finish);
//
//
//            goDraw.setBounds(bordTableauGauche,bordTableauHaut,bordTableauDroit,bordTableauBas);
//            goDraw.draw(canvas);

            canvas.drawBitmap(brokenBitmap,vehicule.getX(),vehicule.getY(),null);

            canvas.drawBitmap(tableauScoreBitmap,bordTableauGauche,bordTableauHaut,null);


            // Texte tableau score
            if(nbVehicules!=0) {
                scorefinal = score + ((nbVehicules - 1) * COEFVEHICULESEVITES);
            } else {
                scorefinal =score;
            }

            if(comptageDistance!=score){
                if(!touched1) {
                    comptageDistance++;
                    comptageScoreFinal++;
                } else {
                    comptageDistance=score;
                    comptageScoreFinal=score;
                }
            } else {
                if(comptageCollision!=nbVehicules-1 && nbVehicules!=0){
                    if(!touched2) {
                        comptageCollision++;
                        comptageScoreFinal += COEFVEHICULESEVITES;
                    } else {
                        comptageCollision=nbVehicules-1;
                        comptageScoreFinal=scorefinal;
                    }
                } else {
                    if (comptageScoreFinal != scorefinal) {
                        comptageScoreFinal++;
                    } else {

                        //Collections.sort(users, new UsersComparator());
                        rangScore=1;
                        for(int i=0; i<users.size();i++){
                            if((scorefinal<users.get(i).getHighScore()) && !(users.get(i).getPseudo()).equals(userInfo.getPseudo())){
                                rangScore++;
                            }
                        }

                        restartEnable = true;
                        if (userInfo != null) { // + registered//true
                            //Log.d("score",Integer.toString(score));
                            //Log.d("HighScore",Integer.toString(highScore));
                            if (scorefinal > userInfo.getHighScore()) {
                                Game.update(scorefinal); // update local
                                userInfo.setHighScore(scorefinal);
                                Game.saveUserInformation(scorefinal);
                                Game.pullHighScore();
                            }
                        }
                    }
                }
            }

            tailleTexte=pixels6;
            int espaceTexte=pixels7;
            TextPaint textPaintTableauAffichage = new TextPaint();
            textPaintTableauAffichage.setTextSize(tailleTexte);
            textPaintTableauAffichage.setColor(ContextCompat.getColor(this.getContext(), R.color.colorPrimaryDark));
            // Distance
            canvas.drawText(textDistance+" : "+comptageDistance,cvW/4+tailleTexte,30*cvH/64-espaceTexte,textPaintTableauAffichage); //cvH/2-espacetexte
            // Collision évités
            canvas.drawText(textVoitureEvites+" : "+Integer.toString(comptageCollision),cvW/4+tailleTexte,30*cvH/64+tailleTexte+espaceTexte-espaceTexte,textPaintTableauAffichage);
            // Score Total
            canvas.drawText(textScoreTotal+" : "+comptageScoreFinal,cvW/4+tailleTexte,30*cvH/64+2*(tailleTexte+tailleTexte)-espaceTexte,textPaintTableauAffichage);


            if(restartEnable){
                TextPaint textPaintRang = new TextPaint();
                textPaintRang.setTextSize(tailleTexte);
                textPaintRang.setColor(ContextCompat.getColor(this.getContext(), R.color.accent_material_dark_1));

                if(rangScore!=0) {
                    if (rangScore == 1) {
                        canvas.drawText(rangScore + "er", cvW / 2 + 3 * tailleTexte, 30*cvH/64 + 2 * (tailleTexte + tailleTexte), textPaintRang);
                    } else {
                        canvas.drawText(rangScore + "ème", cvW / 2 + 3 * tailleTexte, 30*cvH/64 + 2 * (tailleTexte + tailleTexte), textPaintRang);
                    }
                }



                coinGRestart=cvW/2;
                coinHRestart=30*cvH/64+3*(tailleTexte+tailleTexte);
                coinBRestart=30*cvH/64+3*(tailleTexte+tailleTexte)+tailleTexte;
                coinDRestart=cvW/2+4*tailleTexte;

                TextPaint textPaintRestart = new TextPaint();
                textPaintRestart.setTextSize(tailleTexte);
                textPaintRestart.setColor(Color.RED);
                // Distance
                canvas.drawText(textRestart,coinGRestart,coinHRestart,textPaintRestart);

            }
        }

    }

    // Fonction appelée par la boucle principale (gameLoopThread)
    // On gère ici le déplacement des objets
    public void update() {

        if(bg!=null) {
            bg.update();
        }

        if(nbVie==0){
            bg.setVector(0);
        }

        if(start) {
            if(!pause) {
                if(!perdu) {
                    if(!collisionVehicule) {
                        refreshScore++;
                    }
                }
            }
            if (refreshScore == 10) {
                score++;

                if(userInfo!=null) {
                    if ((score + (nbVehicules - 1) * COEFVEHICULESEVITES )> userInfo.getHighScore() && userInfo.getHighScore()!=-1) {
                        if (!toastHSdone) {
                            Game.toastHS();
                            toastHSdone = true;
                        }
                    }
                }


                baisseCarburant = false;
                refreshScore = 0;

                if(-bg.getVitesse()<cvW/10) {
                    Log.d("vehiculeE_vitesse",Integer.toString(vehiculeEnnemi1.getVitesse()));
                   // Log.d("bg_vitesse",Integer.toString(bg.getVitesse()));

                    Log.d("bg_vitesse",Integer.toString(-6*bg.getVitesse()/8));
                    if (vehiculeEnnemi1.getVitesse() < -6 * bg.getVitesse() / 8) {
                        Log.d("sommeIncVitesse",Double.toString(sommeIncrementVitesse));
                        Log.d("increment", Double.toString(incrementVitesse));
                        sommeIncrementVitesse=incrementVitesse+sommeIncrementVitesse;
                        if(sommeIncrementVitesse>=1) {
                            vehiculeEnnemi1.setVitesse(vehiculeEnnemi1.getVitesse() + Integer.valueOf((int)sommeIncrementVitesse));
                            vehiculeEnnemi2.setVitesse(vehiculeEnnemi1.getVitesse() + Integer.valueOf((int)sommeIncrementVitesse));
                            vehiculeEnnemi3.setVitesse(vehiculeEnnemi1.getVitesse() + Integer.valueOf((int)sommeIncrementVitesse));
                            vehiculeEnnemi4.setVitesse(vehiculeEnnemi1.getVitesse() + Integer.valueOf((int)sommeIncrementVitesse));
                            vehiculeEnnemi5.setVitesse(vehiculeEnnemi1.getVitesse() + Integer.valueOf((int)sommeIncrementVitesse));
                            vehiculeEnnemi6.setVitesse(vehiculeEnnemi1.getVitesse() + Integer.valueOf((int)sommeIncrementVitesse));
                            //Log.d("vitesse1", Integer.toString(vitesse1));
                            //Log.d("increment", Integer.toString(incrementVitesse));
                            //Log.d("vitesse1+increment", Integer.toString(vitesse1 + incrementVitesse));
                            //bg.setVector(-vitesseDeplacementBG2);
                            //carburant.setVitesse(vitesseDeplacementBG2);
                            sommeIncrementVitesse=sommeIncrementVitesse-1;
                        }
                    } else {
                        bg.setVector(bg.getVitesse()+bg.getVitesse()/6);
                        vitesseDeplacementBGActuel=bg.getVitesse();
                        carburant.setVitesse(-bg.getVitesse());
//                        if(-bg.getVitesse()==cvW/40) {
//                            bg.setVector(-cvW / 30);
//                            carburant.setVitesse(cvW/30);
//                        }
//                        if(-bg.getVitesse()==cvW/50) {
//                            bg.setVector(-cvW / 40);
//                            carburant.setVitesse(cvW/40);
//                        }
                    }
                }



            }

            if (score % 5 == 0) {
                if (!baisseCarburant) {
                    if (levelCarburant - PERTECARBURANT * longueurBarreCarburant / 100 > levelCarburantMinInit) {
                        levelCarburant = levelCarburant - PERTECARBURANT * longueurBarreCarburant / 100;
                    } else {
                        perdu=true;
                        Log.d("NoMoreCarbu","perdu");
                    }
                    baisseCarburant = true;
                    Game.pullHighScore();
                }
            }

//            if (score ==50){
//                Log.d("score100","faster");
//                vehiculeEnnemi1.setVitesse(vitesse2);
//                vehiculeEnnemi2.setVitesse(vitesse2);
//                vehiculeEnnemi3.setVitesse(vitesse2);
//                vehiculeEnnemi4.setVitesse(vitesse2);
//                vehiculeEnnemi5.setVitesse(vitesse2);
//                vehiculeEnnemi6.setVitesse(vitesse2);
//                bg.setVector(-vitesseDeplacementBG2);
//                carburant.setVitesse(vitesseDeplacementBG2);
//            }
//            if (score == 100){
//                Log.d("score200","faster");
//                vehiculeEnnemi1.setVitesse(vitesse3);
//                vehiculeEnnemi2.setVitesse(vitesse3);
//                vehiculeEnnemi3.setVitesse(vitesse3);
//                vehiculeEnnemi4.setVitesse(vitesse3);
//                vehiculeEnnemi5.setVitesse(vitesse3);
//                vehiculeEnnemi6.setVitesse(vitesse3);
//                bg.setVector(-vitesseDeplacementBG3);
//                carburant.setVitesse(vitesseDeplacementBG2);
//            }
        }

        if(pause || perdu || collisionVehicule){
            carburant.setMove(false);
            vehiculeEnnemi1.setMove(false);
            vehiculeEnnemi2.setMove(false);
            vehiculeEnnemi3.setMove(false);
            vehiculeEnnemi4.setMove(false);
            vehiculeEnnemi5.setMove(false);
            vehiculeEnnemi6.setMove(false);
            if(bg!=null) {
                bg.setMove(false);
            }
        } else {
            carburant.setMove(true);
            if(!vehiculeDispo[0]) {
                vehiculeEnnemi1.setMove(true);
            }
            if(!vehiculeDispo[1]) {
                vehiculeEnnemi2.setMove(true);
            }
            if(!vehiculeDispo[2]) {
                vehiculeEnnemi3.setMove(true);
            }
            if(!vehiculeDispo[3]) {
                vehiculeEnnemi4.setMove(true);
            }
            if(!vehiculeDispo[4]) {
                vehiculeEnnemi5.setMove(true);
            }
            if(!vehiculeDispo[5]) {
                vehiculeEnnemi6.setMove(true);
            }

            if(bg!=null) {
                bg.setMove(true);
            }
        }


        carburant.move();

        if(vehicule.hasBeenTouchedbyCarb(carburant.getX(),carburant.getY(),carburant.getcarburantW(),carburant.getcarburantH())){
            carburant.disparition();
            if(levelCarburant + GAINCARBURANT * longueurBarreCarburant / 100<levelCarburantMaxInit) {
                levelCarburant = levelCarburant + GAINCARBURANT * longueurBarreCarburant / 100;
            } else {
                levelCarburant=levelCarburantMaxInit;
            }
        }

        // Gestion ennemi
        // Disposition


        for (int i = 0; i < stratEnnemiDone.length; i++) {
            if (!stratEnnemiDone[i]) {
                randomStrat = (int) (Math.random() * (100 + 1));
                if (randomStrat < 25) {
                    stratEnnemi[i] = 0;
                } else {
                    if (randomStrat < 50) {
                        stratEnnemi[i] = 1; //1
                    } else {
                        if (randomStrat < 75) {
                            stratEnnemi[i] = 2;//2
                        } else {
                            stratEnnemi[i] = 0;//3
                        }
                    }
                }
                stratEnnemiDone[i] = true;
                Log.d("StratEnnemi["+i+"]",Integer.toString(stratEnnemi[i]));
            }
        }


        if(newColonne) {
            decalageRanded=  (int) (Math.random() * (100 + 1));

            if(decalageRanded<33){
                decalage=vehicule.getvehiculePlayerH()/4;
            } else {
                if(decalageRanded<66){
                    decalage=vehicule.getvehiculePlayerH()/2;
                } else {
                    decalage=3*vehicule.getvehiculePlayerH()/4;
                }
            }



            switch (stratEnnemi[colonneEnnemiAVenir]) {
                //NBCASE = NBSTRATEGIES
                case 0:
                    int cpt = 0;
                    for(int i =0 ; i<vehiculeDispo.length;i++){
                        if(vehiculeDispo[i]){
                            cpt=i;
                        }
                    }

                    vehiculeDispo[cpt]=false;

                    Log.d("vehiculeDispoStr1Numero",Integer.toString(cpt));
                    switch (cpt) {
                        // NB CASE = NB VEHICULES
                        case 0:
                            highestEnnemi=1;
                            distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                            if (distanceEntreVehiculesRand < 25) {
                                vehiculeEnnemi1.setY(distanceEntreVehicules1);
                            } else {
                                if (distanceEntreVehiculesRand < 50) {
                                    vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                } else {
                                    if (distanceEntreVehiculesRand < 75) {
                                        vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                    } else {
                                        vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                    }
                                }
                            }

                            positionnementV1X = (int) (Math.random() * (100 + 1));

                            if (positionnementV1X < 33) {
                                vehiculeEnnemi1.setX(routeB);
                            } else {
                                if (positionnementV1X < 66) {
                                    vehiculeEnnemi1.setX(routeM);
                                } else {
                                    vehiculeEnnemi1.setX(routeH);
                                }
                            }

                            vehiculeEnnemi1.setMove(true);

                            break;
                        case 1:
                            highestEnnemi=2;
                            distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                            if (distanceEntreVehiculesRand < 25) {
                                vehiculeEnnemi2.setY(distanceEntreVehicules1);
                            } else {
                                if (distanceEntreVehiculesRand < 50) {
                                    vehiculeEnnemi2.setY(distanceEntreVehicules2 );
                                } else {
                                    if (distanceEntreVehiculesRand < 75) {
                                        vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                    } else {
                                        vehiculeEnnemi2.setY(distanceEntreVehicules4 );
                                    }
                                }
                            }

                            positionnementV1X = (int) (Math.random() * (100 + 1));

                            if (positionnementV1X < 33) {
                                vehiculeEnnemi2.setX(routeB);
                            } else {
                                if (positionnementV1X < 66) {
                                    vehiculeEnnemi2.setX(routeM);
                                } else {
                                    vehiculeEnnemi2.setX(routeH);
                                }
                            }
                            vehiculeEnnemi2.setMove(true);
                            break;
                        case 2:
                            highestEnnemi=3;
                            distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                            if (distanceEntreVehiculesRand < 25) {
                                vehiculeEnnemi3.setY(distanceEntreVehicules1);
                            } else {
                                if (distanceEntreVehiculesRand < 50) {
                                    vehiculeEnnemi3.setY(distanceEntreVehicules2 );
                                } else {
                                    if (distanceEntreVehiculesRand < 75) {
                                        vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                    } else {
                                        vehiculeEnnemi3.setY(distanceEntreVehicules4 );
                                    }
                                }
                            }

                            positionnementV1X = (int) (Math.random() * (100 + 1));

                            if (positionnementV1X < 33) {
                                vehiculeEnnemi3.setX(routeB);
                            } else {
                                if (positionnementV1X < 66) {
                                    vehiculeEnnemi3.setX(routeM);
                                } else {
                                    vehiculeEnnemi3.setX(routeH);
                                }
                            }
                            vehiculeEnnemi3.setMove(true);
                            break;
                        case 3:
                            highestEnnemi=4;
                            distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                            if (distanceEntreVehiculesRand < 25) {
                                vehiculeEnnemi4.setY(distanceEntreVehicules1);
                            } else {
                                if (distanceEntreVehiculesRand < 50) {
                                    vehiculeEnnemi4.setY(distanceEntreVehicules2 );
                                } else {
                                    if (distanceEntreVehiculesRand < 75) {
                                        vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                    } else {
                                        vehiculeEnnemi4.setY(distanceEntreVehicules4 );
                                    }
                                }
                            }

                            positionnementV1X = (int) (Math.random() * (100 + 1));

                            if (positionnementV1X < 33) {
                                vehiculeEnnemi4.setX(routeB);
                            } else {
                                if (positionnementV1X < 66) {
                                    vehiculeEnnemi4.setX(routeM);
                                } else {
                                    vehiculeEnnemi4.setX(routeH);
                                }
                            }
                            vehiculeEnnemi4.setMove(true);
                            break;
                        case 4:
                            highestEnnemi=5;
                            distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                            if (distanceEntreVehiculesRand < 25) {
                                vehiculeEnnemi5.setY(distanceEntreVehicules1);
                            } else {
                                if (distanceEntreVehiculesRand < 50) {
                                    vehiculeEnnemi5.setY(distanceEntreVehicules2 );
                                } else {
                                    if (distanceEntreVehiculesRand < 75) {
                                        vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                    } else {
                                        vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                    }
                                }
                            }

                            positionnementV1X = (int) (Math.random() * (100 + 1));

                            if (positionnementV1X < 33) {
                                vehiculeEnnemi5.setX(routeB);
                            } else {
                                if (positionnementV1X < 66) {
                                    vehiculeEnnemi5.setX(routeM);
                                } else {
                                    vehiculeEnnemi5.setX(routeH);
                                }
                            }
                            vehiculeEnnemi5.setMove(true);
                            break;
                        case 5:
                            highestEnnemi=6;
                            distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                            if (distanceEntreVehiculesRand < 25) {
                                vehiculeEnnemi6.setY(distanceEntreVehicules1);
                            } else {
                                if (distanceEntreVehiculesRand < 50) {
                                    vehiculeEnnemi6.setY(distanceEntreVehicules2 );
                                } else {
                                    if (distanceEntreVehiculesRand < 75) {
                                        vehiculeEnnemi6.setY(distanceEntreVehicules3);
                                    } else {
                                        vehiculeEnnemi6.setY(distanceEntreVehicules4);
                                    }
                                }
                            }

                            positionnementV1X = (int) (Math.random() * (100 + 1));

                            if (positionnementV1X < 33) {
                                vehiculeEnnemi6.setX(routeB);
                            } else {
                                if (positionnementV1X < 66) {
                                    vehiculeEnnemi6.setX(routeM);
                                } else {
                                    vehiculeEnnemi6.setX(routeH);
                                }
                            }
                            vehiculeEnnemi6.setMove(true);
                            break;
                    }
                    break;
                case 1:
                    // Choix 1ervehicule
                    cpt1=0;
                    cpt2=0;
                    for(int i =0 ; i<vehiculeDispo.length;i++){
                        if(vehiculeDispo[i]){
                            cpt1=i;
                        }
                    }

                    vehiculeDispo[cpt1]=false;

                    switch (cpt1){
                        case 0:
                            highestEnnemi=1;
                            // choix 2ème véhicule
                            for(int i =0 ; i<vehiculeDispo.length;i++){
                                if(vehiculeDispo[i]){
                                    cpt2=i;
                                }
                            }
                            vehiculeDispo[cpt2]=false;

                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt1));
                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt2));

                            switch (cpt2){
                                case 0:
                                    // Vehicule 1 & 1 !

                                    break;
                                case 1:
                                    // Vehicule 1 & 2 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi1.setX(routeB);
                                        vehiculeEnnemi2.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi2.setX(routeB);
                                        } else {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi2.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi1.setMove(true);
                                    vehiculeEnnemi2.setMove(true);

                                    break;
                                case 2:
                                    // Vehicule 1 & 3 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi1.setX(routeB);
                                        vehiculeEnnemi3.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi3.setX(routeB);
                                        } else {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi3.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi1.setMove(true);
                                    vehiculeEnnemi3.setMove(true);


                                    break;
                                case 3:
                                    // Vehicule 1 & 4 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi1.setX(routeB);
                                        vehiculeEnnemi4.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi4.setX(routeB);
                                        } else {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi4.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi1.setMove(true);
                                    vehiculeEnnemi4.setMove(true);

                                    break;
                                case 4:
                                    // Vehicule 1 & 5 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi1.setX(routeB);
                                        vehiculeEnnemi5.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi5.setX(routeB);
                                        } else {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi5.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi1.setMove(true);
                                    vehiculeEnnemi5.setMove(true);

                                    break;
                                case 5:
                                    // Vehicule 1 & 1 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi1.setX(routeB);
                                        vehiculeEnnemi6.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi6.setX(routeB);
                                        } else {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi6.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi1.setMove(true);
                                    vehiculeEnnemi6.setMove(true);

                                    break;

                            }
                            break;
                        case 1:
                            highestEnnemi=2;
                            // choix 2ème véhicule
                            for(int i =0 ; i<vehiculeDispo.length;i++){
                                if(vehiculeDispo[i]){
                                    cpt2=i;
                                }
                            }
                            vehiculeDispo[cpt2]=false;
                            switch (cpt2){
                                case 0:

                                    // Vehicule 2 & 1 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi2.setX(routeB);
                                        vehiculeEnnemi1.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi1.setX(routeB);
                                        } else {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi1.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi2.setMove(true);
                                    vehiculeEnnemi1.setMove(true);


                                    break;
                                case 1:
                                    // Vehicule 2 & 2 !


                                    break;
                                case 2:
                                    // Vehicule 2 & 3 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi2.setX(routeB);
                                        vehiculeEnnemi3.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi3.setX(routeB);
                                        } else {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi3.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi2.setMove(true);
                                    vehiculeEnnemi3.setMove(true);


                                    break;
                                case 3:
                                    // Vehicule 2 & 4 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi2.setX(routeB);
                                        vehiculeEnnemi4.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi4.setX(routeB);
                                        } else {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi4.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi2.setMove(true);
                                    vehiculeEnnemi4.setMove(true);

                                    break;
                                case 4:
                                    // Vehicule 2 & 5 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi2.setX(routeB);
                                        vehiculeEnnemi5.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi5.setX(routeB);
                                        } else {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi5.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi2.setMove(true);
                                    vehiculeEnnemi5.setMove(true);

                                    break;
                                case 5:
                                    // Vehicule 2 & 6 !

                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi2.setX(routeB);
                                        vehiculeEnnemi6.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi6.setX(routeB);
                                        } else {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi6.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi2.setMove(true);
                                    vehiculeEnnemi6.setMove(true);

                                    break;

                            }
                            break;
                        case 2:
                            highestEnnemi=3;
                            // choix 2ème véhicule
                            for(int i =0 ; i<vehiculeDispo.length;i++){
                                if(vehiculeDispo[i]){
                                    cpt2=i;
                                }
                            }
                            vehiculeDispo[cpt2]=false;

                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt1));
                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt2));

                            switch (cpt2){
                                case 0:
                                    // Vehicule 3 & 1 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi3.setX(routeB);
                                        vehiculeEnnemi1.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi1.setX(routeB);
                                        } else {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi1.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi3.setMove(true);
                                    vehiculeEnnemi1.setMove(true);


                                    break;
                                case 1:
                                    // Vehicule 3 & 2 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi3.setX(routeB);
                                        vehiculeEnnemi2.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi2.setX(routeB);
                                        } else {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi2.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi3.setMove(true);
                                    vehiculeEnnemi2.setMove(true);

                                    break;
                                case 2:
                                    // Vehicule 3 & 3 !

                                    break;
                                case 3:
                                    // Vehicule 3 & 4 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi3.setX(routeB);
                                        vehiculeEnnemi4.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi4.setX(routeB);
                                        } else {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi4.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi3.setMove(true);
                                    vehiculeEnnemi4.setMove(true);

                                    break;
                                case 4:
                                    // Vehicule 3 & 5 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi3.setX(routeB);
                                        vehiculeEnnemi5.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi5.setX(routeB);
                                        } else {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi5.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi3.setMove(true);
                                    vehiculeEnnemi5.setMove(true);

                                    break;
                                case 5:
                                    // Vehicule 3 & 6 !

                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi3.setX(routeB);
                                        vehiculeEnnemi6.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi6.setX(routeB);
                                        } else {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi6.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi3.setMove(true);
                                    vehiculeEnnemi6.setMove(true);

                                    break;

                            }



                            break;
                        case 3:
                            highestEnnemi=4;
                            // choix 2ème véhicule
                            for(int i =0 ; i<vehiculeDispo.length;i++){
                                if(vehiculeDispo[i]){
                                    cpt2=i;
                                }
                            }
                            vehiculeDispo[cpt2]=false;

                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt1));
                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt2));

                            switch (cpt2){
                                case 0:
                                    // Vehicule 4 & 1 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi4.setX(routeB);
                                        vehiculeEnnemi1.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi1.setX(routeB);
                                        } else {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi1.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi4.setMove(true);
                                    vehiculeEnnemi1.setMove(true);


                                    break;
                                case 1:
                                    // Vehicule 4 & 2 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi4.setX(routeB);
                                        vehiculeEnnemi2.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi2.setX(routeB);
                                        } else {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi2.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi4.setMove(true);
                                    vehiculeEnnemi2.setMove(true);

                                    break;
                                case 2:
                                    // Vehicule 4 & 3 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi4.setX(routeB);
                                        vehiculeEnnemi3.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi3.setX(routeB);
                                        } else {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi3.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi4.setMove(true);
                                    vehiculeEnnemi3.setMove(true);


                                    break;
                                case 3:
                                    // Vehicule 4 & 4 !


                                    break;
                                case 4:
                                    // Vehicule 4 & 5 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi4.setX(routeB);
                                        vehiculeEnnemi5.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi5.setX(routeB);
                                        } else {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi5.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi4.setMove(true);
                                    vehiculeEnnemi5.setMove(true);

                                    break;
                                case 5:
                                    // Vehicule 5 & 6 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi4.setX(routeB);
                                        vehiculeEnnemi6.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi6.setX(routeB);
                                        } else {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi6.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi4.setMove(true);
                                    vehiculeEnnemi6.setMove(true);

                                    break;

                            }


                            break;
                        case 4:
                            highestEnnemi=5;
                            // choix 2ème véhicule
                            for(int i =0 ; i<vehiculeDispo.length;i++){
                                if(vehiculeDispo[i]){
                                    cpt2=i;
                                }
                            }
                            vehiculeDispo[cpt2]=false;

                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt1));
                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt2));

                            switch (cpt2){
                                case 0:
                                    // Vehicule 5 & 1 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi5.setX(routeB);
                                        vehiculeEnnemi1.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi1.setX(routeB);
                                        } else {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi1.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi5.setMove(true);
                                    vehiculeEnnemi1.setMove(true);


                                    break;
                                case 1:
                                    // Vehicule 5 & 2 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi5.setX(routeB);
                                        vehiculeEnnemi2.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi2.setX(routeB);
                                        } else {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi2.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi5.setMove(true);
                                    vehiculeEnnemi2.setMove(true);

                                    break;
                                case 2:
                                    // Vehicule 5 & 3 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi5.setX(routeB);
                                        vehiculeEnnemi3.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi3.setX(routeB);
                                        } else {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi3.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi5.setMove(true);
                                    vehiculeEnnemi3.setMove(true);


                                    break;
                                case 3:
                                    // Vehicule 5 & 4 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi5.setX(routeB);
                                        vehiculeEnnemi4.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi4.setX(routeB);
                                        } else {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi4.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi5.setMove(true);
                                    vehiculeEnnemi4.setMove(true);

                                    break;
                                case 4:
                                    // Vehicule 5 & 5 !


                                    break;
                                case 5:
                                    // Vehicule 5 & 6 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi5.setX(routeB);
                                        vehiculeEnnemi6.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi6.setX(routeB);
                                        } else {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi6.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi5.setMove(true);
                                    vehiculeEnnemi6.setMove(true);

                                    break;

                            }

                            break;
                        case 5:
                            highestEnnemi=6;
                            // choix 2ème véhicule
                            for(int i =0 ; i<vehiculeDispo.length;i++){
                                if(vehiculeDispo[i]){
                                    cpt2=i;
                                }
                            }
                            vehiculeDispo[cpt2]=false;

                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt1));
                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt2));

                            switch (cpt2){
                                case 0:
                                    // Vehicule 6 & 1 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi1.setX(routeB);
                                        vehiculeEnnemi6.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi6.setX(routeB);
                                        } else {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi6.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi1.setMove(true);
                                    vehiculeEnnemi6.setMove(true);


                                    break;
                                case 1:
                                    // Vehicule 6 & 2 !

                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi6.setX(routeB);
                                        vehiculeEnnemi2.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi2.setX(routeB);
                                        } else {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi2.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi6.setMove(true);
                                    vehiculeEnnemi2.setMove(true);

                                    break;
                                case 2:
                                    // Vehicule 6 & 3 !

                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi6.setX(routeB);
                                        vehiculeEnnemi3.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi3.setX(routeB);
                                        } else {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi3.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi6.setMove(true);
                                    vehiculeEnnemi3.setMove(true);


                                    break;
                                case 3:
                                    // Vehicule 6 & 4 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi6.setX(routeB);
                                        vehiculeEnnemi4.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi4.setX(routeB);
                                        } else {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi4.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi6.setMove(true);
                                    vehiculeEnnemi4.setMove(true);

                                    break;
                                case 4:
                                    // Vehicule 6 & 5 !

                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                            } else {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi6.setX(routeB);
                                        vehiculeEnnemi5.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi5.setX(routeB);
                                        } else {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi5.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi6.setMove(true);
                                    vehiculeEnnemi5.setMove(true);

                                    break;
                                case 5:
                                    // Vehicule 6 & 6 !
                                    break;

                            }


                            break;

                    }
                    break;
                case 2:
                    // Choix 1ervehicule
                    cpt1=0;
                    cpt2=0;
                    for(int i =0 ; i<vehiculeDispo.length;i++){
                        if(vehiculeDispo[i]){
                            cpt1=i;
                        }
                    }

                    vehiculeDispo[cpt1]=false;

                    switch (cpt1){
                        case 0:
                            highestEnnemi=1;
                            // choix 2ème véhicule
                            for(int i =0 ; i<vehiculeDispo.length;i++){
                                if(vehiculeDispo[i]){
                                    cpt2=i;
                                }
                            }
                            vehiculeDispo[cpt2]=false;

                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt1));
                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt2));

                            switch (cpt2){
                                case 0:
                                    // Vehicule 1 & 1 !

                                    break;
                                case 1:
                                    // Vehicule 1 & 2 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi1.setX(routeB);
                                        vehiculeEnnemi2.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi2.setX(routeB);
                                        } else {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi2.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi1.setMove(true);
                                    vehiculeEnnemi2.setMove(true);

                                    break;
                                case 2:
                                    // Vehicule 1 & 3 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi1.setX(routeB);
                                        vehiculeEnnemi3.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi3.setX(routeB);
                                        } else {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi3.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi1.setMove(true);
                                    vehiculeEnnemi3.setMove(true);


                                    break;
                                case 3:
                                    // Vehicule 1 & 4 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi1.setX(routeB);
                                        vehiculeEnnemi4.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi4.setX(routeB);
                                        } else {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi4.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi1.setMove(true);
                                    vehiculeEnnemi4.setMove(true);

                                    break;
                                case 4:
                                    // Vehicule 1 & 5 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi1.setX(routeB);
                                        vehiculeEnnemi5.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi5.setX(routeB);
                                        } else {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi5.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi1.setMove(true);
                                    vehiculeEnnemi5.setMove(true);

                                    break;
                                case 5:
                                    // Vehicule 1 & 1 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi1.setX(routeB);
                                        vehiculeEnnemi6.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi6.setX(routeB);
                                        } else {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi6.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi1.setMove(true);
                                    vehiculeEnnemi6.setMove(true);

                                    break;

                            }
                            break;
                        case 1:
                            highestEnnemi=2;
                            // choix 2ème véhicule
                            for(int i =0 ; i<vehiculeDispo.length;i++){
                                if(vehiculeDispo[i]){
                                    cpt2=i;
                                }
                            }
                            vehiculeDispo[cpt2]=false;
                            switch (cpt2){
                                case 0:

                                    // Vehicule 2 & 1 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi2.setX(routeB);
                                        vehiculeEnnemi1.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi1.setX(routeB);
                                        } else {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi1.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi2.setMove(true);
                                    vehiculeEnnemi1.setMove(true);


                                    break;
                                case 1:
                                    // Vehicule 2 & 2 !


                                    break;
                                case 2:
                                    // Vehicule 2 & 3 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi2.setX(routeB);
                                        vehiculeEnnemi3.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi3.setX(routeB);
                                        } else {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi3.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi2.setMove(true);
                                    vehiculeEnnemi3.setMove(true);


                                    break;
                                case 3:
                                    // Vehicule 2 & 4 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi2.setX(routeB);
                                        vehiculeEnnemi4.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi4.setX(routeB);
                                        } else {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi4.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi2.setMove(true);
                                    vehiculeEnnemi4.setMove(true);

                                    break;
                                case 4:
                                    // Vehicule 2 & 5 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi2.setX(routeB);
                                        vehiculeEnnemi5.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi5.setX(routeB);
                                        } else {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi5.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi2.setMove(true);
                                    vehiculeEnnemi5.setMove(true);

                                    break;
                                case 5:
                                    // Vehicule 2 & 6 !

                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi2.setX(routeB);
                                        vehiculeEnnemi6.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi6.setX(routeB);
                                        } else {
                                            vehiculeEnnemi2.setX(routeH);
                                            vehiculeEnnemi6.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi2.setMove(true);
                                    vehiculeEnnemi6.setMove(true);

                                    break;

                            }
                            break;
                        case 2:
                            highestEnnemi=3;
                            // choix 2ème véhicule
                            for(int i =0 ; i<vehiculeDispo.length;i++){
                                if(vehiculeDispo[i]){
                                    cpt2=i;
                                }
                            }
                            vehiculeDispo[cpt2]=false;

                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt1));
                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt2));

                            switch (cpt2){
                                case 0:
                                    // Vehicule 3 & 1 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi3.setX(routeB);
                                        vehiculeEnnemi1.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi1.setX(routeB);
                                        } else {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi1.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi3.setMove(true);
                                    vehiculeEnnemi1.setMove(true);


                                    break;
                                case 1:
                                    // Vehicule 3 & 2 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi3.setX(routeB);
                                        vehiculeEnnemi2.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi2.setX(routeB);
                                        } else {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi2.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi3.setMove(true);
                                    vehiculeEnnemi2.setMove(true);

                                    break;
                                case 2:
                                    // Vehicule 3 & 3 !

                                    break;
                                case 3:
                                    // Vehicule 3 & 4 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi3.setX(routeB);
                                        vehiculeEnnemi4.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi4.setX(routeB);
                                        } else {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi4.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi3.setMove(true);
                                    vehiculeEnnemi4.setMove(true);

                                    break;
                                case 4:
                                    // Vehicule 3 & 5 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi3.setX(routeB);
                                        vehiculeEnnemi5.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi5.setX(routeB);
                                        } else {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi5.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi3.setMove(true);
                                    vehiculeEnnemi5.setMove(true);

                                    break;
                                case 5:
                                    // Vehicule 3 & 6 !

                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi3.setX(routeB);
                                        vehiculeEnnemi6.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi6.setX(routeB);
                                        } else {
                                            vehiculeEnnemi3.setX(routeH);
                                            vehiculeEnnemi6.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi3.setMove(true);
                                    vehiculeEnnemi6.setMove(true);

                                    break;

                            }



                            break;
                        case 3:
                            highestEnnemi=4;
                            // choix 2ème véhicule
                            for(int i =0 ; i<vehiculeDispo.length;i++){
                                if(vehiculeDispo[i]){
                                    cpt2=i;
                                }
                            }
                            vehiculeDispo[cpt2]=false;

                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt1));
                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt2));

                            switch (cpt2){
                                case 0:
                                    // Vehicule 4 & 1 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi4.setX(routeB);
                                        vehiculeEnnemi1.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi1.setX(routeB);
                                        } else {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi1.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi4.setMove(true);
                                    vehiculeEnnemi1.setMove(true);


                                    break;
                                case 1:
                                    // Vehicule 4 & 2 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi4.setX(routeB);
                                        vehiculeEnnemi2.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi2.setX(routeB);
                                        } else {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi2.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi4.setMove(true);
                                    vehiculeEnnemi2.setMove(true);

                                    break;
                                case 2:
                                    // Vehicule 4 & 3 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi4.setX(routeB);
                                        vehiculeEnnemi3.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi3.setX(routeB);
                                        } else {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi3.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi4.setMove(true);
                                    vehiculeEnnemi3.setMove(true);


                                    break;
                                case 3:
                                    // Vehicule 4 & 4 !


                                    break;
                                case 4:
                                    // Vehicule 4 & 5 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi4.setX(routeB);
                                        vehiculeEnnemi5.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi5.setX(routeB);
                                        } else {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi5.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi4.setMove(true);
                                    vehiculeEnnemi5.setMove(true);

                                    break;
                                case 5:
                                    // Vehicule 5 & 6 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi4.setX(routeB);
                                        vehiculeEnnemi6.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi6.setX(routeB);
                                        } else {
                                            vehiculeEnnemi4.setX(routeH);
                                            vehiculeEnnemi6.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi4.setMove(true);
                                    vehiculeEnnemi6.setMove(true);

                                    break;

                            }


                            break;
                        case 4:
                            highestEnnemi=5;
                            // choix 2ème véhicule
                            for(int i =0 ; i<vehiculeDispo.length;i++){
                                if(vehiculeDispo[i]){
                                    cpt2=i;
                                }
                            }
                            vehiculeDispo[cpt2]=false;

                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt1));
                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt2));

                            switch (cpt2){
                                case 0:
                                    // Vehicule 5 & 1 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi5.setX(routeB);
                                        vehiculeEnnemi1.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi1.setX(routeB);
                                        } else {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi1.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi5.setMove(true);
                                    vehiculeEnnemi1.setMove(true);


                                    break;
                                case 1:
                                    // Vehicule 5 & 2 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi5.setX(routeB);
                                        vehiculeEnnemi2.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi2.setX(routeB);
                                        } else {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi2.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi5.setMove(true);
                                    vehiculeEnnemi2.setMove(true);

                                    break;
                                case 2:
                                    // Vehicule 5 & 3 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi5.setX(routeB);
                                        vehiculeEnnemi3.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi3.setX(routeB);
                                        } else {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi3.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi5.setMove(true);
                                    vehiculeEnnemi3.setMove(true);


                                    break;
                                case 3:
                                    // Vehicule 5 & 4 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi5.setX(routeB);
                                        vehiculeEnnemi4.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi4.setX(routeB);
                                        } else {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi4.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi5.setMove(true);
                                    vehiculeEnnemi4.setMove(true);

                                    break;
                                case 4:
                                    // Vehicule 5 & 5 !


                                    break;
                                case 5:
                                    // Vehicule 5 & 6 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi5.setX(routeB);
                                        vehiculeEnnemi6.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi6.setX(routeB);
                                        } else {
                                            vehiculeEnnemi5.setX(routeH);
                                            vehiculeEnnemi6.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi5.setMove(true);
                                    vehiculeEnnemi6.setMove(true);

                                    break;

                            }

                            break;
                        case 5:
                            highestEnnemi=6;
                            // choix 2ème véhicule
                            for(int i =0 ; i<vehiculeDispo.length;i++){
                                if(vehiculeDispo[i]){
                                    cpt2=i;
                                }
                            }
                            vehiculeDispo[cpt2]=false;

                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt1));
                            Log.d("vehiculeDispoStr2Numero",Integer.toString(cpt2));

                            switch (cpt2){
                                case 0:
                                    // Vehicule 6 & 1 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi1.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi1.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi1.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi1.setX(routeB);
                                        vehiculeEnnemi6.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi6.setX(routeB);
                                        } else {
                                            vehiculeEnnemi1.setX(routeH);
                                            vehiculeEnnemi6.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi1.setMove(true);
                                    vehiculeEnnemi6.setMove(true);


                                    break;
                                case 1:
                                    // Vehicule 6 & 2 !

                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi2.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi2.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi2.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi6.setX(routeB);
                                        vehiculeEnnemi2.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi2.setX(routeB);
                                        } else {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi2.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi6.setMove(true);
                                    vehiculeEnnemi2.setMove(true);

                                    break;
                                case 2:
                                    // Vehicule 6 & 3 !

                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi3.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi3.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi3.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi6.setX(routeB);
                                        vehiculeEnnemi3.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi3.setX(routeB);
                                        } else {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi3.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi6.setMove(true);
                                    vehiculeEnnemi3.setMove(true);


                                    break;
                                case 3:
                                    // Vehicule 6 & 4 !
                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi4.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi4.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi4.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi6.setX(routeB);
                                        vehiculeEnnemi4.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi4.setX(routeB);
                                        } else {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi4.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi6.setMove(true);
                                    vehiculeEnnemi4.setMove(true);

                                    break;
                                case 4:
                                    // Vehicule 6 & 5 !

                                    distanceEntreVehiculesRand = (int) (Math.random() * (100 + 1));

                                    if (distanceEntreVehiculesRand < 25) {
                                        vehiculeEnnemi6.setY(distanceEntreVehicules1);
                                        vehiculeEnnemi5.setY(distanceEntreVehicules1+decalage);
                                    } else {
                                        if (distanceEntreVehiculesRand < 50) {
                                            vehiculeEnnemi6.setY(distanceEntreVehicules2);
                                            vehiculeEnnemi5.setY(distanceEntreVehicules2+decalage);
                                        } else {
                                            if (distanceEntreVehiculesRand < 75) {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules3);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules3+decalage);
                                            } else {
                                                vehiculeEnnemi6.setY(distanceEntreVehicules4);
                                                vehiculeEnnemi5.setY(distanceEntreVehicules4+decalage);
                                            }
                                        }
                                    }

                                    positionnementV1X = (int) (Math.random() * (100 + 1));

                                    if (positionnementV1X < 33) {
                                        vehiculeEnnemi6.setX(routeB);
                                        vehiculeEnnemi5.setX(routeM);

                                    } else {
                                        if (positionnementV1X < 66) {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi5.setX(routeB);
                                        } else {
                                            vehiculeEnnemi6.setX(routeH);
                                            vehiculeEnnemi5.setX(routeM);

                                        }
                                    }

                                    vehiculeEnnemi6.setMove(true);
                                    vehiculeEnnemi5.setMove(true);

                                    break;
                                case 5:
                                    // Vehicule 6 & 6 !
                                    break;

                            }


                            break;

                    }
                    break;
                case 3:

                    break;
                default:
                    break;
            }
            stratEnnemiDone[colonneEnnemiAVenir]=false;
            newColonne=false;
        }


        switch (highestEnnemi){
            case 1:
                if(vehiculeEnnemi1.getY()>vehiculeEnnemi1.getvehiculeEnnemiH()){
                    newColonne=true;
                    colonneEnnemiAVenir++;
                }
                break;
            case 2:
                if(vehiculeEnnemi2.getY()>vehiculeEnnemi2.getvehiculeEnnemiH()){
                    newColonne=true;
                    colonneEnnemiAVenir++;
                }
                break;
            case 3:
                if(vehiculeEnnemi3.getY()>vehiculeEnnemi3.getvehiculeEnnemiH()){
                    newColonne=true;
                    colonneEnnemiAVenir++;
                }
                break;
            case 4:
                if(vehiculeEnnemi4.getY()>vehiculeEnnemi4.getvehiculeEnnemiH()){
                    newColonne=true;
                    colonneEnnemiAVenir++;
                }
                break;
            case 5:
                if(vehiculeEnnemi5.getY()>vehiculeEnnemi5.getvehiculeEnnemiH()){
                    newColonne=true;
                    colonneEnnemiAVenir++;
                }
                break;
            case 6:
                if(vehiculeEnnemi6.getY()>vehiculeEnnemi6.getvehiculeEnnemiH()){
                    newColonne=true;
                    colonneEnnemiAVenir++;
                }
                break;
            default:
                break;
        }

        if(colonneEnnemiAVenir==NBENNEMIPARCOLONNEMAX){
            colonneEnnemiAVenir=0;
        }


        if(vehiculeEnnemi1.getY()>cvH){
            nbVehicules++;
            comptageVehiculeDone=true;
            vehiculeEnnemi1.setY(-2*vehiculeEnnemi1.getvehiculeEnnemiH());
            vehiculeEnnemi1.setMove(false);
            vehiculeDispo[0]=true;
        }
        if(vehiculeEnnemi2.getY()>cvH){
            nbVehicules++;
            comptageVehiculeDone=true;
            vehiculeEnnemi2.setY(-2*vehiculeEnnemi2.getvehiculeEnnemiH());
            vehiculeEnnemi2.setMove(false);
            vehiculeDispo[1]=true;
        }
        if(vehiculeEnnemi3.getY()>cvH){
            nbVehicules++;
            comptageVehiculeDone=true;
            vehiculeEnnemi3.setY(-2*vehiculeEnnemi3.getvehiculeEnnemiH());
            vehiculeEnnemi3.setMove(false);
            vehiculeDispo[2]=true;
        }
        if(vehiculeEnnemi4.getY()>cvH){
            nbVehicules++;
            comptageVehiculeDone=true;
            vehiculeEnnemi4.setY(-2*vehiculeEnnemi4.getvehiculeEnnemiH());
            vehiculeEnnemi4.setMove(false);
            vehiculeDispo[3]=true;
        }
        if(vehiculeEnnemi5.getY()>cvH){
            nbVehicules++;
            comptageVehiculeDone=true;
            vehiculeEnnemi5.setY(-2*vehiculeEnnemi5.getvehiculeEnnemiH());
            vehiculeEnnemi5.setMove(false);
            vehiculeDispo[4]=true;
        }
        if(vehiculeEnnemi6.getY()>cvH){
            nbVehicules++;
            comptageVehiculeDone=true;
            vehiculeEnnemi6.setY(-2*vehiculeEnnemi6.getvehiculeEnnemiH());
            vehiculeEnnemi6.setMove(false);
            vehiculeDispo[5]=true;
        }

        vehiculeEnnemi1.moveHautBas();
        vehiculeEnnemi2.moveHautBas();
        vehiculeEnnemi3.moveHautBas();
        vehiculeEnnemi4.moveHautBas();
        vehiculeEnnemi5.moveHautBas();
        vehiculeEnnemi6.moveHautBas();

        if(vehicule.hasBeenTouched(vehiculeEnnemi1.getX(),vehiculeEnnemi1.getY(),vehiculeEnnemi1.getvehiculeEnnemiW(),vehiculeEnnemi1.getvehiculeEnnemiH(),vehiculeEnnemi1.getBD())){
            collisionVehicule=true;
            Log.d("collision1","perdu");
        }
        if(vehicule.hasBeenTouched(vehiculeEnnemi2.getX(),vehiculeEnnemi2.getY(),vehiculeEnnemi2.getvehiculeEnnemiW(),vehiculeEnnemi2.getvehiculeEnnemiH(),vehiculeEnnemi2.getBD())){
            collisionVehicule=true;
            Log.d("collision2","perdu");
        }
        if(vehicule.hasBeenTouched(vehiculeEnnemi3.getX(),vehiculeEnnemi3.getY(),vehiculeEnnemi3.getvehiculeEnnemiW(),vehiculeEnnemi3.getvehiculeEnnemiH(),vehiculeEnnemi3.getBD())){
            collisionVehicule=true;
            Log.d("collision3","perdu");
        }
        if(vehicule.hasBeenTouched(vehiculeEnnemi4.getX(),vehiculeEnnemi4.getY(),vehiculeEnnemi4.getvehiculeEnnemiW(),vehiculeEnnemi4.getvehiculeEnnemiH(),vehiculeEnnemi4.getBD())){
            collisionVehicule=true;
            Log.d("collision4","perdu");
        }
        if(vehicule.hasBeenTouched(vehiculeEnnemi5.getX(),vehiculeEnnemi5.getY(),vehiculeEnnemi5.getvehiculeEnnemiW(),vehiculeEnnemi5.getvehiculeEnnemiH(),vehiculeEnnemi5.getBD())){
            collisionVehicule=true;
            Log.d("collision5","perdu");
        }
        if(vehicule.hasBeenTouched(vehiculeEnnemi6.getX(),vehiculeEnnemi6.getY(),vehiculeEnnemi6.getvehiculeEnnemiW(),vehiculeEnnemi6.getvehiculeEnnemiH(),vehiculeEnnemi6.getBD())){
            collisionVehicule=true;
            Log.d("collision6","perdu");
        }


//        if (diffToAdd - bg.getX() < 0) {
//            diffToAdd = diffToAdd - bg.getX();
//        }
//        //Log.d("diffToAdd",Integer.toString(diffToAdd));
//        distancebackground = distancebackground + diffToAdd;
//        //Log.d("distancebg",Integer.toString(distancebackground));
//        diffToAdd = bg.getX();



    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée immédiatement après la création de l'objet SurfaceView
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // création du processus GameLoopThread si cela n'est pas fait
//        Bitmap bgBm = BitmapFactory.decodeResource(getResources(), R.drawable.espace3);
//        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bgBm, getWidth(), getHeight(), false);
//        WIDTHBG = resizedBitmap.getWidth();
//        bg = new SpaceBackground(resizedBitmap);
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.bg));;
        bg.setVector(0);

        if(gameLoopThread.getState()==Thread.State.TERMINATED) {
            gameLoopThread=new GameLoopThread(this);
        }
        gameLoopThread.setRunning(true);
        gameLoopThread.start();


    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée juste avant que l'objet ne soit détruit.
    // on tente ici de stopper le processus de gameLoopThread
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameLoopThread.setRunning(false);
        while (retry) {
            try {
                gameLoopThread.join();
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
                if(!start){
                    if(currentX>bordStartG && currentX<bordStartD && currentY<bordStartB && currentY>bordStartH) {
                        depart=true;
                        toastHSdone = false;
                    }
                    if (currentX < coinDClassementInit + cvW / 32 && currentX > coinGClassementInit - cvW / 32 && currentY < coinBClassementInit + cvW / 32 && currentY > coinHClassementInit - cvW / 32) {
                        Game.seeClassement();
                    }
                } else {
                    if(!perdu) {
                        if(!collisionVehicule) {
                            if (currentX < coinDPause+cvW/32 && currentX > coinGPause-cvW/32 && currentY < coinBPause+cvW/32 && currentY > coinHPause-cvW/32) {
                                if (!pause) {
                                    Log.d("Pause","true");
                                    pause = true;
                                    Game.pauseMusique();
                                } else {
                                    pause = false;
                                    bg.setMove(true);
                                    bg.setVector(vitesseDeplacementBGActuel);
                                    carburant.setVitesse(vitesseDeplacementBGActuel);
                                    Log.d("Pause","false");
                                    //bg.setVector(-vitesseDeplacementBG);
                                    Game.startMusique();
                                }
                            } else {
                                if(pause) {
                                    if (currentX < coinDClassement + cvW / 32 && currentX > coinGClassement - cvW / 32 && currentY < coinBClassement + cvW / 32 && currentY > coinHClassement - cvW / 32) {
                                        Game.seeClassement();
                                    }
                                } else {
                                    if (currentX >= vehicule.getX() - vehicule.getvehiculePlayerW() / 8 && currentX <= vehicule.getX() + vehicule.getvehiculePlayerW() + vehicule.getvehiculePlayerW() / 8) {
                                        canMoveVehicule = true;
                                        distanceDoigtVoiture = currentX - vehicule.getX();
                                    } else {
                                        canMoveVehicule = false;
                                    }
                                    break;
                                }
                            }
                        }
                    } else {
                        if(touched1){
                            touched2=true;
                        }
                        touched1=true;

                        if(restartEnable) {
                            if (currentX < coinDRestart +cvW/32 && currentX > coinGRestart-cvW/32 && currentY> coinHRestart-cvW/32 && currentY<coinBRestart+cvW/32){
                                bg=null;
                                ////alertDialogDone=false;
                                Game.restart();
                            }
                        }
                    }
                }



                // code exécuté lorsque le doight glisse sur l'écran.
            case MotionEvent.ACTION_MOVE:


                // Comportement normal
                //if(!pause) {
                if(!perdu) {
                    if (!collisionVehicule) {
                        //if (currentX + vehicule.getvehiculePlayerW() / 2 < cvW - cvW / 8 && currentX - vehicule.getvehiculePlayerW() / 2 > cvW / 8) {


                        if(currentX - distanceDoigtVoiture < routeB+vehicule.getvehiculePlayerW() && currentX -distanceDoigtVoiture > routeH-vehicule.getvehiculePlayerW()+vehicule.getvehiculePlayerW()/4){
                        //if (currentX + vehicule.getvehiculePlayerW() / 2 < routeB+2*vehicule.getvehiculePlayerW() && currentX - vehicule.getvehiculePlayerW() / 2 > routeH-vehicule.getvehiculePlayerW()+vehicule.getvehiculePlayerW()/4) {
                            if (canMoveVehicule) {
                                vehicule.setX(currentX - distanceDoigtVoiture);
                            }
                        }
                    }
                }
                // }
                break;

            // lorsque le doigt quitte l'écran
            case MotionEvent.ACTION_UP:
                // on reprend le déplacement de la vehicule
                vehicule.setMove(false);
                canMoveVehicule=false;

        }

        return true;  // On retourne "true" pour indiquer qu'on a géré l'évènement
    }


    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée à la CREATION et MODIFICATION et ONRESUME de l'écran
    // nous obtenons ici la largeur/hauteur de l'écran en pixels
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {
        vehicule.resize(w,h); // on définit la taille de la vehicule selon la taille de l'écran
        carburant.resize(w,h);
        vehiculeEnnemi1.resize(w,h);
        vehiculeEnnemi2.resize(w,h);
        vehiculeEnnemi3.resize(w,h);
        vehiculeEnnemi4.resize(w,h);
        vehiculeEnnemi5.resize(w,h);
        vehiculeEnnemi6.resize(w,h);

    }

} // class GameView

