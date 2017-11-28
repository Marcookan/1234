package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Narek Davtyan
 *@version    pro školní rok 2016/2017
 */

public class Hra implements IHra, Subject {
    private SeznamPrikazu platnePrikazy;   
    private HerniPlan plan;
    private boolean konecHry = false;
    private Igelit igelit;
    private List<Observer> listObserveru = new ArrayList<Observer>();

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        plan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        igelit = new Igelit();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(plan, this));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(plan, igelit));        
        platnePrikazy.vlozPrikaz(new PrikazZamixuj(plan, igelit , this));
        platnePrikazy.vlozPrikaz(new PrikazIngredience(plan));
        platnePrikazy.vlozPrikaz(new PrikazVyhod(plan, igelit));
        platnePrikazy.vlozPrikaz(new PrikazIgelitka(igelit, plan));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "*************************Vítejte**************************!\n" +
               "*****Toto je příběh o Dobroslavovi a jeho ztracení v neznámu*****\n" +
               "*****Napište 'napoveda', pokud si nevíte rady, jak hrát dál.*****\n" +
               "\n" +
               plan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Díky, že jste si zahráli.  Na viděnou.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám.\n ";
        }
        
        if (plan.jeVyhra()){
            konecHry = true;
            textKVypsani = "\nDobroslav se konečně probudil a zjistil, že to byl všechno jenom sen.\n";
        }
        
        if (plan.jeProhra()){
            konecHry = true;
            textKVypsani += "\nProhrál jsi.\n";
        }
        
        if (konecHry) {
            Prostor aktualniMisto = this.getHerniPlan().getAktualniProstor();
            this.getHerniPlan().setAktualniProstor(new Prostor("", "", aktualniMisto.getPosLeft(), aktualniMisto.getPosTop()));
            this.igelit = new Igelit();
        }
        this.notifyObservers();
        
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return plan;
     }

     /**
     * Vrat igelitku
     * @return igelitka
     */
    public Igelit getIgelit() {
        return igelit;
    }

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
    
}

