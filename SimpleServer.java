
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        DataInputStream input = null;
        DataOutputStream output = null;

        try {
            // 1. Open server socket
            int port = 5000;
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            // 2. Accept client
            clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            // 3. Create input stream (server side)
            input = new DataInputStream(clientSocket.getInputStream());

            // 4. Create output stream (server side)
            output = new DataOutputStream(clientSocket.getOutputStream());

            // 5. Read data from client
            String clientMessage = input.readUTF();
            System.out.println("Server received: " + clientMessage);

            // 6. Send response to client
            output.writeUTF("Hello Client");
            output.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 7. Close resources (manual)
            try {
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
