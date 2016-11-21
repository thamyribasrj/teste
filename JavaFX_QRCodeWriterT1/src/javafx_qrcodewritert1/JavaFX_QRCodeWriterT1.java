/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_qrcodewritert1;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import com.google.zxing.qrcode.QRCodeWriter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author thamiris.ribas
 */
public class JavaFX_QRCodeWriterT1 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setMinSize(300, 300);
        grid.setVgap(5);
        grid.setHgap(5);
 
        Text textoSite = new Text("Site:");
        grid.add(textoSite, 0, 0);
 
        TextField site = new TextField();
        site.setPrefColumnCount(10);
        grid.add(site, 1, 0);
 
        Text password = new Text("Password:");
        grid.add(password, 0, 1);
 
        TextField text2 = new TextField();
        text2.setPrefColumnCount(10);
        grid.add(text2, 1, 1);        
        
        Button btn = new Button();
        btn.setText("Gerar QRCode");
        btn.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Gerando QRCode!!!");
                    
                    QRCodeWriter qrCodeWriter = new QRCodeWriter();
        
                    String myWeb = site.getText().toString();
                    int width = 200;
                    int height = 200;
                    String fileType = "png";

                    BufferedImage bufferedImage = null;

                    try {
                        BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
                        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                        bufferedImage.createGraphics();

                        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
                        graphics.setColor(Color.WHITE);
                        graphics.fillRect(0, 0, width, height);
                        graphics.setColor(Color.BLACK);

                        for (int i = 0; i < height; i++) {
                            for (int j = 0; j < width; j++) {
                                if (byteMatrix.get(i, j)) {
                                    graphics.fillRect(i, j, 1, 1);
                                }
                            }
                        }

                        System.out.println("Sucesso...");

                    } catch (WriterException ex) {
                        Logger.getLogger(JavaFX_QRCodeWriterT1.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    ImageView qrView = new ImageView();
                    qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
                    
                    grid.add(qrView,1,3);
                }
            }
        );
        
        grid.add(btn, 1, 2);
        
        Scene scene = new Scene(grid, 400, 350);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
