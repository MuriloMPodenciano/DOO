package ifsp.doo.atas.UI;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

@SpringBootApplication
public class AtasReuniaoApplication extends Application {
    private static Stage stage;

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        applicationContext = SpringApplication.run(AtasReuniaoApplication.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        AtasReuniaoApplication.stage = stage;
        loader.setControllerFactory(applicationContext::getBean);
        AnchorPane view = loader.load(getClass().getResource("ata/redator.fxml").openStream());

        Scene scene = new Scene(view);
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScene(String url) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(applicationContext::getBean);

        Parent view = loader.load(AtasReuniaoApplication.class.getResource(url).openStream());

        Scene scene = new Scene(view);
        AtasReuniaoApplication.stage.setScene(scene);
        AtasReuniaoApplication.stage.show();
    }

    @Override
    public void stop() {
        applicationContext.close();
    }
}
