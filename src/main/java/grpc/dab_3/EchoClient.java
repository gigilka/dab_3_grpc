package grpc.dab_3;

import io.grpc.*;

public class EchoClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:7080").usePlaintext().build();
        EchoServiceGrpc.EchoServiceBlockingStub stub = EchoServiceGrpc.newBlockingStub(channel);

        EchoRequest request = EchoRequest.newBuilder().setMessage("test").build();
        EchoResponse response = stub.echo(request);
        System.out.println("response = "+response.getMessage());
    }
}
