
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SimpleClient {

    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;

        try {
            // 1. Open socket (client side)
            String serverAddress = "localhost";
            int port = 5000;
            socket = new Socket(serverAddress, port);
            System.out.println("Connected to server");

            // 2. Create output stream (client side)
            output = new DataOutputStream(socket.getOutputStream());

            // 3. Create input stream (client side)
            input = new DataInputStream(socket.getInputStream());

            // 4. Send data to server
            output.writeUTF("Hello Server");
            output.flush();

            // 5. Read response from server
            String serverResponse = input.readUTF();
            System.out.println("Client received: " + serverResponse);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6. Close resources
            try {
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
