package grpc.dab_3;

import io.grpc.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EchoClient extends Application {
    public GameController mainCont;
    public int id = -1;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(EchoClient.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 513, 304);
        stage.setTitle("Dots&boxes");
        stage.setScene(scene);
        stage.show();
        mainCont = fxmlLoader.getController();
        mainCont.cl = this;
        for (int i = 0; i < 10; i++) {
            ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:7080").usePlaintext().build();
            EchoServiceGrpc.EchoServiceBlockingStub stub = EchoServiceGrpc.newBlockingStub(channel);

            EchoRequest request = EchoRequest.newBuilder().setMessage("test " + i).build();
            EchoResponse response = stub.echo(request);
            System.out.println("response = " + response.getMessage());
        }
    }
}
