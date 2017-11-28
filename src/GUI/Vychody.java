/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Collection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logika.IHra;
import logika.Prostor;
import utils.Observer;

/**
 *
 * @author davn00
 */
public class Vychody extends VBox implements Observer {
    
    private IHra hra;
    private TextField zadejPrikazRozhrani;
    Text titulek;

    /**
     * Konstruktor panelu
     * @param hra prave hrana hra
     * @param zadejPrikazRozhrani rozhrani pro volani prikazu
     */
    public Vychody(IHra hra, TextField zadejPrikazRozhrani) {
        this.hra = hra;
        this.zadejPrikazRozhrani = zadejPrikazRozhrani;
        hra.registerObserver(this);
        init();
    }
    
    /**
     * Nastaveni nadpisu panelu
     */
     private void init() {
        titulek = new Text("Kam jít");
        titulek.setFont(Font.font("Arial", FontWeight.BOLD,14));
        this.setPadding(new Insets(10));
        this.setSpacing(8);
        this.setMinWidth(200.0);
        update();
    }
     /**
      * Nastaveni nove hry po restartu
      * @param novaHra nova hra
      */
     public void newGame(IHra novaHra) {
         hra.removeObserver(this);
         hra = novaHra;
         hra.registerObserver(this);
         update();
     }
     
     /**
      * Aktualizace seznamu míst po prechodu do jineho prostoru.
      */
     
    @Override
    public void update(){
        this.getChildren().clear();
        this.getChildren().add(titulek);
        Collection<Prostor> vychody = hra.getHerniPlan().getAktualniProstor().getVychody();
        for (Prostor vychod : vychody) {
            Hyperlink moznost = new Hyperlink(vychod.getNazev());
            moznost.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    zadejPrikazRozhrani.setText("jdi " + vychod.getNazev());
                    zadejPrikazRozhrani.fireEvent(new ActionEvent());
                }
            });
            this.getChildren().add(moznost);
        }
    }

   
}
