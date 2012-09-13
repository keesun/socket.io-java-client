package basic;

import io.socket.DeafultIoCallback;
import io.socket.SocketIO;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

/**
 * @author Keesun Baik
 */
public class MessageSender {

	public static void main(String[] args) throws MalformedURLException, JSONException, InterruptedException {
		int sendingMessageCount = 0;
		SocketIO socket = new SocketIO();
		socket.connect("http://localhost:19090/", new DeafultIoCallback(socket));
		while (true) {
			JSONObject message = new JSONObject();
			message.put("hello", "socket.io");
			socket.emit("send", message);
			sendingMessageCount++;
			System.out.printf("sent [%d] messages\n", sendingMessageCount);
			Thread.sleep(10000l);
		}
	}
}
