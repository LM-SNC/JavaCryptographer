package sample;

import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Controller {
    private CryptMethods cryptMethods = new CryptMethods();
    private RandomExample randomExample = new RandomExample();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea publicKey;

    @FXML
    private TextField privateKey;

    @FXML
    private Button generateButton;

    @FXML
    private Button encodeButton;

    @FXML
    private Button decodeButton;

    @FXML
    private TextArea resultArea;

    @FXML
    void initialize() {

        generateButton.setOnAction(event -> {
            privateKey.setText(randomExample.generateRandomString(16));
        });

        encodeButton.setOnAction(event -> {

            if (privateKey.getText().length() == 16) {
                resultArea.setStyle("-fx-background-color: #fafafa");
                resultArea.setDisable(false);
                resultArea.setEditable(false);
                try {
                    resultArea.setText(cryptMethods.crypt(publicKey.getText(), privateKey.getText()));
                } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
            } else {
                resultArea.setText("Key length must be 16");
                resultArea.setStyle("-fx-background-color: #FF0000");
                resultArea.setDisable(true);
            }
        });

        decodeButton.setOnAction(event -> {
            if (privateKey.getText().length() == 16) {
                resultArea.setStyle("-fx-background-color: #fafafa");
                resultArea.setDisable(false);
                resultArea.setEditable(false);

                try {
                    resultArea.setText(cryptMethods.decrypt(publicKey.getText().getBytes(), privateKey.getText()));
                } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | IllegalArgumentException e) {
//                    e.printStackTrace();
                    resultArea.setStyle("-fx-background-color: #FF0000");
                    resultArea.setText("Invalid argument -- Key and ciphertext do not match: \n" + e);
                    resultArea.setDisable(true);
                    resultArea.setDisable(false);
                }
            } else {
                resultArea.setStyle("-fx-background-color: #FF0000");
                resultArea.setText("Key length must be 16");
                resultArea.setDisable(true);
            }
        });
    }


}
