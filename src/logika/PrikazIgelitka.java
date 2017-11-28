/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Instance třídy {@code PrikazInventar} představují ...
 *
 * @author    Narek Davtyan
 * @version   ZS 2016/17
 */
public class PrikazIgelitka implements IPrikaz
{
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
    private static final String NAZEV = "igelitka";
    private HerniPlan plan;
    private Igelit igelit;
    
    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /***************************************************************************
     *
     */
    
    public PrikazIgelitka(Igelit igelit, HerniPlan plan)
    {
        this.plan = plan;
        this.igelit = igelit;
    }
    
    /**
     *  Provádí příkaz "igelitka". Sbírá věci, které se nacházejí v prostoru a které jdou sebrat, 
     *  vkádá je potom do batohu
     *  Pokud nejsou parametry, vypíše se chybové hlášení
     *
     */ 
    @Override
    public String provedPrikaz(String... parametry){
        return igelit.nazvyVeciVIgelitu();
    
    }
     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev(){
            return NAZEV;
    }
}
