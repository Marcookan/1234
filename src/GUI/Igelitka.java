/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Collection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
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
 * Trida predstavuje panel igelitky postavy.
 * Predmety jsou zobrazeny jako obrazky.
 * Umoznuje kliknutim na obrazek vyhodit predmet z igelitky.
 * @author davn00
 */
public class Igelitka extends AnchorPane implements Observer {
    
    private IHra hra;
    private Text titulek;
    private TextField zadejPrikazRozhrani;
    
/**
 * Konstruktor panelu
 * @param hra prave hrana hra
 * @param zadejPrikazRozhrani rozhrani pro volani prikazu
 */
    
public Igelitka(IHra hra, TextField zadejPrikazRozhrani) {
    this.hra = hra;
    this.zadejPrikazRozhrani = zadejPrikazRozhrani;
    hra.registerObserver(this);
    init();
    
}

    /**
     * Nastaveni nadpisu panelu.
     */
    private void init() {
        titulek = new Text("Igelitka");
        titulek.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        this.setTopAnchor(titulek, 10.0);
        this.setLeftAnchor(titulek, 10.0);
        
        update();
    }

    /**
     * Nastaveni nove hry po restartu.
     * @param novaHra nova hra
     */
    public void newGame(IHra novaHra){
        hra.removeObserver(this);
        hra = novaHra;
        hra.registerObserver(this);
        update();
    }
    
    @Override
    public void update() {
        this.getChildren().clear();
        this.getChildren().add(titulek);
        Collection<Vec> veci = hra.getIgelit().getVeci();
        int pocet = 0;
        for (Vec vec : veci) {
            ImageView obrazekPredmetu = new ImageView(new Image(Main.class.getResourceAsStream(vec.getObrazek()),50,50,false,true));
            obrazekPredmetu.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    zadejPrikazRozhrani.setText("vyhod " + vec.getNazev());
                    zadejPrikazRozhrani.fireEvent(new ActionEvent());
                }
                
            });
            this.setTopAnchor(obrazekPredmetu, 40.0);
            this.setLeftAnchor(obrazekPredmetu, 10.0 + pocet * 50.0);
            this.getChildren().add(obrazekPredmetu);
            pocet++;
        }
        
        if (pocet > 0) {
            Hyperlink moznost = new Hyperlink("Zamixuj ingredience");
            moznost.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    zadejPrikazRozhrani.setText("zamixuj");
                    zadejPrikazRozhrani.fireEvent(new ActionEvent());
                }
                
            });
            this.setTopAnchor(moznost, 100.0);
            this.setLeftAnchor(moznost, 10.0);
            this.getChildren().add(moznost);
        }
        }
    }
