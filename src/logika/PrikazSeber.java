/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy PrikazSeber představují ...
 *
 * @author    Narek Davtyan
 * @version   ZS 2016/17
 */
public class PrikazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Igelit igelit;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazSeber(HerniPlan plan, Igelit igelit)
    {
        this.plan = plan;
        this.igelit = igelit;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Provádí příkaz "seber". Sbírá věci, které jdou sebrat a dává je do igelitky.
     * Pokud vybrat nejdou, vypíše chybovou hlášku.
     *
     */
    @Override
    public String provedPrikaz(String... parametry) {
        String text = "";
        if(parametry.length == 0) {
            return "\nCo mám sebrat?\n";
        }

        String nazevSbiraneVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();

        if (aktualniProstor.obsahujeVec(nazevSbiraneVeci)!= null){
            Vec sbiranaVec = aktualniProstor.odeberVec(nazevSbiraneVeci);
            if (sbiranaVec == null || !sbiranaVec.jePrenositelna()){  
                aktualniProstor.vlozVec(sbiranaVec);  
                text =  "\nTuto věc nelze sebrat.\n";
            }
            else{
                if (igelit.vlozVecDoIgelitu(sbiranaVec) && sbiranaVec.jePrenositelna()){
                    text = "\nByla sebrána věc " + sbiranaVec.getNazev();

                }                             
                else{
                    aktualniProstor.vlozVec(sbiranaVec);                       
                    text =  "Igelit je plný.\n";
                }
            }
        }
        else {
            text = "\nTo tady není.\n";
        }
        return text;

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

    //== Soukromé metody (instancí i třídy) ========================================

}