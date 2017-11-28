/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * Instance třídy {@code PrikazVyhod} představují ...
 *
 * @author    Narek Davtyan
 * @version   ZS 2016/17
 */
public class PrikazVyhod implements IPrikaz
{
    //== DATOVÉ ATRIBUTY =============================================
    private static final String NAZEV = "vyhod";
    private HerniPlan plan;
    private Igelit igelit;

    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /***************************************************************************
     * Konstruktor
     * @param plan
     */
    public PrikazVyhod(HerniPlan plan, Igelit igelit)
    {
        this.plan = plan;
        this.igelit = igelit;
    }

    /**
     *  Provádí příkaz "vyhod". Vyhazuje věci z igelitky na zem.
     *  Pokud věc není v igelitce, vypíše se chybové hlášení
     *
     *@param parametry - jako  parametr obsahuje název veci
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Co mám vyhodit? Zadej jméno věci";
        }
        String nazevVeci = parametry[0];
        Vec vec = igelit.vyberVecZIgelitu(nazevVeci);
        if(vec == null){
            return "Taková věc v batohu není";
        }
        igelit.vyberVecZIgelitu(nazevVeci);
        return "Věc " +nazevVeci+ " si vyhodil na zem.";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
