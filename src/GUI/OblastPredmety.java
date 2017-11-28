/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Collection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 * Trida panelu pro zobrazeni predmetu v aktualni oblasti.
 * @author davn00
 */
public class OblastPredmety extends AnchorPane implements Observer {
    
    private IHra hra;
    private Text titulek;
    private TextField zadejPrikazRozhrani;


    /**
     * Konstruktor panelu
     * @param hra prave hrana hra
     * @param zadejPrikazRozhrani rozhrani pro volani prikazu
     */
    public OblastPredmety(IHra hra, TextField zadejPrikazRozhrani) {
        this.hra = hra;
        this.zadejPrikazRozhrani = zadejPrikazRozhrani;
        hra.registerObserver(this);
        init();
    }
    
    /**
     * Nastaveni nadpisu panelu.
     */
    private void init() {
        
        titulek = new Text("Předměty v oblasti");
        titulek.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        this.setTopAnchor(titulek, 10.0);
        this.setLeftAnchor(titulek, 10.0);
        
        update();
    }
    /**
     * Nastaveni nove hry po restartu
     * @param novaHra nova hra
     */
    void newGame(IHra novaHra) {
        hra.removeObserver(this);
        hra = novaHra;
        hra.registerObserver(this);
        update();
    }
    
    /**
     * Aktualizace zobrazení po změně místa nebo sebrání předmětu.
     */
    @Override
    public void update() {
       this.getChildren().clear();
       this.getChildren().add(titulek);
        Collection<Vec> veci = hra.getHerniPlan().getAktualniProstor().getVeci();
        int pocet = 0;
        for (Vec vec : veci) {
            ImageView obrazekPredmetu = new ImageView(new Image(Main.class.getResourceAsStream(vec.getObrazek()),50,50,false,true));
        obrazekPredmetu.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                zadejPrikazRozhrani.setText("seber " + vec.getNazev());
                zadejPrikazRozhrani.fireEvent(new ActionEvent());
            }
        });
        this.setTopAnchor(obrazekPredmetu, 40.0);
        this.setLeftAnchor(obrazekPredmetu, 10.0 + pocet * 50.0);
        this.getChildren().add(obrazekPredmetu);
        pocet++;
        }
    }

    
}
