package ifsp.doo.atas.UI;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@SpringBootApplication
public class AtasReuniaoApplication extends Application {
    private Stage stage;
    private Object controller;

    public static void main(String[] args) {
        SpringApplication.run(AtasReuniaoApplication.class, args);

        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane pane = loader.load(getClass().getResource("./ata/redator.fxml").openStream());
        Object controller = loader.getController();

        stage.setScene(new Scene(pane));

        stage.show();

        this.stage = stage;
    }
}
