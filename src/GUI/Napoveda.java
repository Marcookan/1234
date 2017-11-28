/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Panel s odkazy pro vypsani napovedy.
 * @author davn00
 */
public class Napoveda extends VBox {
    
    private TextField zadejPrikazRozhrani;
    Text titulek;
    
    /**
     * Konstruktor panelu.
     * @param zadejPrikazRozhrani rozhrani pro volani prikazu
     */     
    public Napoveda(TextField zadejPrikazRozhrani) {
        this.zadejPrikazRozhrani = zadejPrikazRozhrani;
        init();
    }

    /**
     * Zobrazí odkazy pro napovedu.
     */
    
    private void init() {
        titulek = new Text("Ostatní");
        titulek. setFont(Font.font("Arial", FontWeight.BOLD, 14));
        this.setPadding(new Insets(10));
        this.setSpacing(8);
        this.setMinWidth(200.0);
        this.getChildren().add(titulek);
        Hyperlink moznost = new Hyperlink("Nápověda");
        moznost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                zadejPrikazRozhrani.setText("napoveda");
                zadejPrikazRozhrani.fireEvent(new ActionEvent());
            }
        });
        this.getChildren().add(moznost);
        moznost = new Hyperlink("Ingredience");
        moznost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                zadejPrikazRozhrani.setText("ingredience");
                zadejPrikazRozhrani.fireEvent(new ActionEvent());
            }
        });
        this.getChildren().add(moznost);  
    }
}