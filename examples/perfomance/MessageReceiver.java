package perfomance;

import io.socket.*;
import org.json.JSONException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Keesun Baik
 */
public class MessageReceiver {

	int connectionCount = 0;
	int messageCount = 0;

	public static void main(String[] args) throws MalformedURLException, JSONException, InterruptedException {
		MessageReceiver client = new MessageReceiver();
		List<SocketIO> sockets = new ArrayList<SocketIO>();

		for(int i = 0 ; i < 10 ; i++) {
			SocketIO socket = new SocketIO();
			CallBack callback = new CallBack(socket, client);
			socket.connect("http://10.96.250.207:19191/", callback);
			sockets.add(socket);
			System.out.printf("[%d] sockets tried to connect.\n", sockets.size());
			if(i%100 == 0) {
				Thread.sleep(500l);
			}
		}

		while (true) {
			Thread.sleep(1000l);
			System.out.printf("[%d] connections, got [%d] messages.\n", client.getConnectionCount(), client.getMessageCount());
		}
	}

	public int getConnectionCount() {
		return connectionCount;
	}

	public int getMessageCount() {
		return messageCount;
	}

	public static class CallBack extends DeafultIoCallback {

		MessageReceiver client;

		public CallBack(SocketIO socket, MessageReceiver sampleClient) {
			super(socket);
			this.client = sampleClient;
		}

		@Override
		public void onConnect() {
			this.client.plusConnectionCount();
		}

		@Override
		public void on(String event, IOAcknowledge ack, Object... args) {
		 	this.client.plusMessageCount();
		}

		@Override
		public void onDisconnect() {
			this.client.minusConnectionCount();
		}

	}

	private synchronized void plusMessageCount() {
		this.messageCount++;
	}

	private synchronized void minusConnectionCount() {
		this.connectionCount--;
	}

	private synchronized void plusConnectionCount() {
		this.connectionCount++;
	}

}
