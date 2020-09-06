package sample;

import java.io.UnsupportedEncodingException;
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
    CryptMethods cryptMethods = new CryptMethods();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea publicKey;

    @FXML
    private TextField privateKey;

    @FXML
    private Button encodeButton;

    @FXML
    private Button decodeButton;

    @FXML
    private TextArea resultArea;

    @FXML
    void initialize() {
//        assert publicKey != null : "fx:id=\"publicKey\" was not injected: check your FXML file 'sample.fxml'.";
//        assert privateKey != null : "fx:id=\"privateKey\" was not injected: check your FXML file 'sample.fxml'.";
//        assert encodeButton != null : "fx:id=\"encodeButton\" was not injected: check your FXML file 'sample.fxml'.";
//        assert decodeButton != null : "fx:id=\"decodeButton\" was not injected: check your FXML file 'sample.fxml'.";
//        assert resultArea != null : "fx:id=\"resultArea\" was not injected: check your FXML file 'sample.fxml'.";

        encodeButton.setOnAction(event -> {

            if (privateKey.getText().length() == 16) {
                resultArea.setDisable(false);
                resultArea.setEditable(false);
                try {
                    resultArea.setText(cryptMethods.ecrypt(publicKey.getText(), privateKey.getText()));
                } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                resultArea.setText("Key length must be 16");
                resultArea.setDisable(true);
            }
        });

        decodeButton.setOnAction(event -> {
            if (privateKey.getText().length() == 16) {
                resultArea.setDisable(false);
                resultArea.setEditable(false);
                try {
                    resultArea.setText(cryptMethods.decrypt(publicKey.getText().getBytes(), privateKey.getText()));
                } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
            } else {
                resultArea.setText("Key length must be 16");
                resultArea.setDisable(true);
            }
        });
    }

}
