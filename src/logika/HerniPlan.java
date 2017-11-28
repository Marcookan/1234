package logika;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Narek Davtyan
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan {

    private Prostor aktualniProstor;    
    private boolean vyhra = false;
    private boolean prohra = false;

    // private List<Observer> listObserveru = new ArrayList<Observer>();       //vytvořili jsme list všech observerů
    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }

    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor rozcesti = new Prostor("rozcestí","rozcestí, na kterém se Dobroslav probudí", 190, 393);
        Prostor hrusen = new Prostor("hrušeň", "hrušeň, na kterém jsou hrušky", 320, 363);
        Prostor mangovnik = new Prostor("mangovník","mangovník s mangem", 75, 363);
        Prostor kaktus = new Prostor("kaktus","kaktus s podivuhodným plodem", 63, 269);
        Prostor rybnik = new Prostor("rybník", "rybník, na kterém tě čeká smrt", 70, 160); 
        Prostor udoli = new Prostor("údolí", "údolí které vede ke konečným chatkám", 200, 175);
        Prostor zahrada = new Prostor("zahrada", "zahrada s bramborama", 313, 269);
        Prostor napadnaChatka = new Prostor("nápadná_chatka", "nápadná chatka, kde uviděl muže v černém na židli.\n" 
        +"\nMuž v černém: 'Jsi tady správně, ale je tu ještě poslední úkol,'" + "\n'Chceš červené nebo žluté rajče?'\n", 300, 80);
        Prostor nenapadnaChatka = new Prostor("nenápadná_chatka", "nenápadná chatka, kde tě čeká smrt", 144, 100);

        // přiřazují se průchody mezi prostory (sousedící prostory)

        rozcesti.setVychod(mangovnik);
        rozcesti.setVychod(hrusen);
        hrusen.setVychod(kaktus);
        hrusen.setVychod(zahrada);
        hrusen.setVychod(rozcesti);
        mangovnik.setVychod(rozcesti);
        mangovnik.setVychod(kaktus);
        kaktus.setVychod(rybnik);
        kaktus.setVychod(udoli);
        kaktus.setVychod(hrusen);
        kaktus.setVychod(mangovnik);
        kaktus.setVychod(zahrada);
        zahrada.setVychod(udoli);
        zahrada.setVychod(hrusen);
        zahrada.setVychod(hrusen);
        udoli.setVychod(kaktus);
        udoli.setVychod(kaktus);
        udoli.setVychod(napadnaChatka);
        udoli.setVychod(nenapadnaChatka);

        Vec hruska = new Vec("hruška", true, "/zdroje/hruska.jpg");
        Vec sladke_brambory = new Vec("sladké_brambory", true, "/zdroje/sladkebrambory.jpg");
        Vec rane_brambory = new Vec("rané_brambory", false, "/zdroje/ranebrambory.jpg");
        Vec zluta_koule = new Vec("žluté_rajče", true, "/zdroje/zluterajcee.jpg");
        Vec cervena_koule = new Vec("červené_rajče", true, "/zdroje/cervenerajce.jpg");
        Vec mango = new Vec("mango", true, "/zdroje/mango.jpg");
        Vec pitahaya = new Vec("pitahaya", true, "/zdroje/pitahaya.jpg");
        Vec pomeranc = new Vec("pomeranč", true, "/zdroje/pomeranc.jpg");


        hrusen.vlozVec(hruska);
        zahrada.vlozVec(sladke_brambory);
        zahrada.vlozVec(rane_brambory);
        mangovnik.vlozVec(mango);
        kaktus.vlozVec(pitahaya);
        napadnaChatka.vlozVec(zluta_koule);
        napadnaChatka.vlozVec(cervena_koule);
        udoli.vlozVec(pomeranc);

        aktualniProstor = rozcesti;  
    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }

    /**
     * Metoda zjišťující stav booleanu vyhry.
     * 
     * @return      vrací true pokud je Vyhra
     */
    public boolean jeVyhra(){
        return vyhra;    
    }

    /**
     *  Metoda vrací odkaz na prohru.
     *
     *@return     prohra
     */
    public boolean jeProhra() {
        return prohra;
    }

    /*
     * metody nastavující stavy booleanu výhry a prohry
     */
    public void setProhra(boolean stav){
        this.prohra = stav;
    }

    public void setVyhra(boolean stav){
        this.vyhra = stav;
    }
}
