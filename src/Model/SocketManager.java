
package Model;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//tạo ra socket để tái sử dụng
public class SocketManager {   
     private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public SocketManager(String serverHost, int serverPort) throws IOException {
        socket = new Socket(serverHost, serverPort);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public String sendRequest(String requestType, String email, String password) throws IOException {
        outputStream.writeUTF(requestType);
        outputStream.writeUTF(email);
        outputStream.writeUTF(password);
        outputStream.flush();

        return inputStream.readUTF();
    }

    public void close() throws IOException {
        socket.close();
    }
}
