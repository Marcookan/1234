/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * Instance třídy Vec představují ...
 *
 * @author    Narek Davtyan
 * @version   ZS 2016/17
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private String obrazek;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     */
    public Vec(String nazev, boolean prenositelnost, String obrazek)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.obrazek = obrazek;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    
    /*****
     * Metoda vrací název věci
     */
     public String getNazev(){
        return nazev;
    
    }
    /**
     * Metoda vrací true, pokud je věc přenositelná
     */
    public boolean jePrenositelna(){
        return prenositelnost;
    
    }

     /*****
     * Metoda vrací název souboru obrazku
     */
     public String getObrazek(){
        return obrazek;
    
    }
    
    //== Soukromé metody (instancí i třídy) ========================================

}
