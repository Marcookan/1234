/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.Igelitka;
import GUI.Mapa;
import GUI.MenuLista;
import GUI.Napoveda;
import GUI.OblastPredmety;
import GUI.Vychody;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 *
 * @author davn00
 */
public class Main extends Application {

    private TextArea centralText;
    private IHra hra;

    public void setHra(IHra hra) {
        this.hra = hra;
    }
    private TextField zadejPrikazTextArea;
    
    private Igelitka igelitka;
    private Vychody vychody;
    private OblastPredmety predmety;
    private Napoveda napoveda;
    private Mapa mapa;
    private MenuLista menuLista;
    
    private Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        hra = new Hra();
        
        mapa = new Mapa(hra);
        menuLista = new MenuLista(hra, this);
        
        BorderPane borderPane = new BorderPane();

        centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        borderPane.setCenter(centralText);
        
        Label zadejPrikazLabel = new Label("Zadej prikaz: ");
        zadejPrikazLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        
        zadejPrikazTextArea = new TextField("...");
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
              String vstupniPrikaz = zadejPrikazTextArea.getText();
              String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
              
              centralText.appendText("\n" + vstupniPrikaz + "\n");
              centralText.appendText("\n" + odpovedHry + "\n");
              
              zadejPrikazTextArea.setText("");
              
              if (hra.konecHry()) {
                      zadejPrikazTextArea.setEditable(false);
                      centralText.appendText(hra.vratEpilog());
            }
        }
     });
        
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextArea);
        
        predmety = new OblastPredmety(hra, zadejPrikazTextArea);
        igelitka = new Igelitka(hra, zadejPrikazTextArea);
        vychody = new Vychody(hra, zadejPrikazTextArea);
        napoveda = new Napoveda(zadejPrikazTextArea);
            
        //obrazek s mapou, predmety v oblasti, igelitka         
        VBox leveMenu = new VBox();
        leveMenu.getChildren().add(mapa);
        leveMenu.getChildren().add(predmety);
        leveMenu.getChildren().add(igelitka);
        borderPane.setLeft(leveMenu);
        
        // východy z prostoru a nápověda
        BorderPane praveMenu = new BorderPane();
        praveMenu.setCenter(vychody);
        praveMenu.setBottom(napoveda);
        borderPane.setRight(praveMenu);
        borderPane.setBottom(dolniLista);
        borderPane.setTop(menuLista);
        


        Scene scene = new Scene(borderPane, 1180, 800);
        primaryStage.setTitle("Adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
    }

    public TextField getZadejPrikazTextArea() {
        return zadejPrikazTextArea;
    }
    public TextArea getCentralText() {
        return centralText;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public Igelitka getIgelitka() {
        return igelitka;
    }
    
    public OblastPredmety getOblastPredmety() {
        return predmety;
    }
    
    public Vychody getVychody() {
        return vychody;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            } 
            else {
                System.out.println("Neplatný parametr");
                System.exit(1);
            }
        }
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }
}