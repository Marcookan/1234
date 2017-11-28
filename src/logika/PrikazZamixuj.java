/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Instance třídy {@code PrikazZamixuj} představují ...
 *
 * @author    Narek Davtyan
 * @version   ZS 2016/17
 */
public class PrikazZamixuj implements IPrikaz
{
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
    private static final String NAZEV = "zamixuj";
    private HerniPlan plan;
    private Igelit igelit;
    private Hra hra;
    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    /***************************************************************************
     *  
     */
    public PrikazZamixuj(HerniPlan plan, Igelit igelit, Hra hra)
    {
        this.plan = plan;
        this.igelit = igelit;
        this.hra = hra;
    }
    /**
     *  Provádí příkaz "uvař". Pokud jsou v hrnci všechny správné ingredience, hra skončí vítězstvím
     *  Pokud jsou v hrnci nesprávné ingredience, hra skončí prohrou
     *  Pokud je v hrnci málo ingrediencí, vypíše se chybové hlášení.
     *
     *@param parametry - názvy ingrediencí
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String provedPrikaz(String... parametry) {
        Prostor aktualniProstor = plan.getAktualniProstor();
        if (aktualniProstor.getNazev().equals("nápadná_chatka")) {           
            if (igelit.obsahujeVecVIgelitu("hruška") && igelit.obsahujeVecVIgelitu("mango")
            && igelit.obsahujeVecVIgelitu("pitahaya")&& igelit.obsahujeVecVIgelitu("sladké_brambory")
            && igelit.obsahujeVecVIgelitu("žluté_rajče"))
            {
              plan.setVyhra(true);
              System.out.println("Gratuluji\n");
            }
            
            if (igelit.obsahujeVecVIgelitu("červené_rajče")){
                hra.setKonecHry(true);
                return "\n Sorry, špatná volba. Prohrál si.\n";
            }
            return "\nK zamixování potřebuješ ještě další ingredience.\n";
            

        }

        return "Nemůžeš to udělat, ještě nejsi v chatce.";
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


    //== ABSTRAKTNÍ METODY =====================================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =================================
    //== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ====================================
    //== SOUKROMÉ A POMOCNÉ METODY TŘÍDY =======================================
    //== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ====================================
    //== INTERNÍ DATOVÉ TYPY ===================================================
    //== TESTOVACÍ METODY A TŘÍDY ==============================================
    //
    //     /********************************************************************
    //      * Testovací metoda.
    //      */
    //     public static void test()
    //     {
    //         PrikazZamixuj instance = new PrikazZamixuj();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
