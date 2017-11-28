/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

/*******************************************************************************
 * Instance třídy {@code Inventar} představují ...
 *
 * @author    Narek Davtyan
 * @version   ZS 2016/17
 */
public class Igelit
{
    //== DATOVÉ ATRIBUTY =============================================
    private static final int KAPACITA = 5 ;
    private Map<String, Vec> seznamVeci ; 

    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /***************************************************************************
     *  Konstruktor
     */
    public Igelit()
    {
        seznamVeci = new HashMap<>();
    }

    //== METODY =====================================================
    /**
     * Metoda k vložení věcí do igelitky.
     */
    public boolean vlozVecDoIgelitu (Vec vec) {
        if (seznamVeci.size() < KAPACITA && (vec.jePrenositelna())) {
            seznamVeci.put(vec.getNazev(), vec);
            return true;
        }
        return false;
    }

    /**
     * Metoda vrací, jestli v igelitu něco je
     */
    public boolean obsahujeVecVIgelitu (String jmenoVeci) {
        return seznamVeci.containsKey(jmenoVeci);
    }

    /**
     * Metoda vrací vybranou věc z igelitu
     */
    public Vec vyberVecZIgelitu (String jmenoVeci) {
        Vec nalezenaVec;
        if (seznamVeci.containsKey(jmenoVeci)) {
            nalezenaVec = seznamVeci.get(jmenoVeci);
            seznamVeci.remove(jmenoVeci);
            return nalezenaVec;
        }
        return null;
    }

    /**
     * Metoda vrací věcí, co jsou v igelitce
     */
    public String nazvyVeciVIgelitu() {
        String nazvy = "V igelitce máš následující: ";
        for (String jmenoVeci : seznamVeci.keySet()){
            nazvy += jmenoVeci + " ";
        }
        return nazvy;

    }

    /**
     * Vraci seznam veci v igelitce
     * @return seznam veci
     */
    public Collection<Vec> getVeci() {
        return Collections.unmodifiableCollection(seznamVeci.values());
    }

}
