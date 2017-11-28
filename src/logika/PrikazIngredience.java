/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Instance třídy {@code PrikazIngredience} představují ...
 *
 * @author    Narek Davtyan
 * @version   ZS 2016/17
 */
public class PrikazIngredience implements IPrikaz
{
    //== DATOVÉ ATRIBUTY =============================================
    private static final String NAZEV = "ingredience";
    private HerniPlan plan;
    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /***************************************************************************
     *
     */
    public PrikazIngredience(HerniPlan plan)
    {   
        this.plan = plan;
    }
    /**
     * Provádí příkaz "ingredience", vrací seznam potřebných ingrediencí
     */
    public String provedPrikaz(String... parametry)
    {
        return "Potřebuješ následující ingredience: hruška, mango,\n"
        + "pomeranč, sladké brambory, žluté rajče.\n";
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
