/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 *
 * @author davn00
 */
public class Mapa extends AnchorPane implements Observer{
    
    private IHra hra;
    private Circle tecka;
    
     /**
     * Konstruktor mapy.
     * @param hra prave hrana hra
     */
    public Mapa(IHra hra){
            this.hra = hra;
            hra.registerObserver(this);
            init();
    }
    
     /**
     * Nastaveni nadpisu a podkladu mapy.
     */
    private void init(){
        
        Text titulek = new Text("Mapa");
        titulek.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        
        this.setTopAnchor(titulek, 10.0);
        this.setLeftAnchor(titulek, 10.0);

        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.gif"),400,400,false,true));
        
        this.setTopAnchor(obrazekImageView, 40.0);
        
        tecka = new Circle(10, Paint.valueOf("red"));
        
        this.getChildren().addAll(titulek, obrazekImageView, tecka);
        
        update();
    }
    
    /**
     * Nastaveni nove hry po restaru.
     * @param novaHra nova hra
     */
    
    public void newGame(IHra novaHra){
        hra.removeObserver(this);
        hra = novaHra;
        hra.registerObserver(this);
        update();
    }
    
    /**
     * Aktualizace pozice hrace.
     */
    @Override
    public void update(){
        this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosTop());
        this.setLeftAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }
}
