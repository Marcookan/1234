/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;

/**
 * Hlavni lista menu
 * @author davn00
 */
public class MenuLista extends MenuBar{
    
    private IHra hra;
    private Main main;
    
    /**
     * Konstruktor hlavniho menu.
     * @param hra hrana hra
     * @param main hlavni trida
     */
    public MenuLista(IHra hra, Main main){
        this.hra = hra;
        this.main = main;
        init();
    }
    
    private void init(){
        Menu novySoubor = new Menu("Adventura");
        Menu napoveda = new Menu("Help");
    
        MenuItem novaHra = new MenuItem("Nova hra"); 
    
        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));        
        MenuItem konecHry = new MenuItem("Konec hry");
    
        novySoubor.getItems().addAll(novaHra, konecHry);
        
        MenuItem oProgramu = new MenuItem("O programu");         
        MenuItem napovedaItem = new MenuItem("Napoveda");
        
        
        napoveda.getItems().addAll(oProgramu, napovedaItem);
        
        this.getMenus().addAll(novySoubor, napoveda);
        
        konecHry.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        novaHra.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                hra = new Hra();
                main.getMapa().newGame(hra);
                main.setHra(hra);
                main.getCentralText().setText(hra.vratUvitani());
                main.getOblastPredmety().newGame(hra);
                main.getVychody().newGame(hra);
                main.getZadejPrikazTextArea().setEditable(true);
                main.getIgelitka().newGame(hra);
            }
        });
        
        oProgramu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
             
                Alert oProgramuAlert = new Alert(Alert.AlertType.INFORMATION);
                
                oProgramuAlert.setTitle("O programu");
                oProgramuAlert.setHeaderText("Adventura DobroDružství™");
                oProgramuAlert.setContentText("Program vytvořen, za pomocí VŠE profesorů a osobních kamarádů, Narkem Davtyanem.\n"+
                         "Vznik v roce 2016-2017.\n"+
                        "Program je spustitelný přes přiložený archiv nebo ručním otevřením v dané platformě"
                        + "");
                oProgramuAlert.initOwner(main.getStage());
                
                oProgramuAlert.showAndWait();
            }
        });
        
        napovedaItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               
                Stage stage = new Stage();
                stage.setTitle("Napoveda");
                
                WebView webView = new WebView();
                
                webView.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
                
                stage.setScene(new Scene(webView, 500, 500));
                stage.show();
            }
        });
        
    }
    
}
