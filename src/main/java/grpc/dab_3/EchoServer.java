package grpc.dab_3;

import io.grpc.*;

public class EchoServer extends EchoServiceGrpc.EchoServiceImplBase {

    public String gameStatus = "nStarted";
    @Override
    public void echo(EchoRequest request,
                     io.grpc.stub.StreamObserver<EchoResponse> responseObserver) {
        System.out.println("receive:" + request.getMessage());
        EchoResponse response = EchoResponse
                .newBuilder()
                .setMessage("hello from server:" + request.getMessage())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder
                .forPort(7080)
                .addService(new EchoServer()).build();
        server.start();
        System.out.println("Server started");
        server.awaitTermination();
    }
}