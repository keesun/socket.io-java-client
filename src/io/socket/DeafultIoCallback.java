package io.socket;

import org.json.JSONObject;

/**
 * @author Keesun Baik
 */
public class DeafultIoCallback implements IOCallback {

	protected SocketIO socket;

	public DeafultIoCallback() {
	}

	public DeafultIoCallback(SocketIO socket) {
		this.socket = socket;
	}

	@Override
	public void onDisconnect() {
	}

	@Override
	public void onConnect() {
	}

	@Override
	public void onMessage(String data, IOAcknowledge ack) {
	}

	@Override
	public void onMessage(JSONObject json, IOAcknowledge ack) {
	}

	@Override
	public void on(String event, IOAcknowledge ack, Object... args) {
	}

	@Override
	public void onError(SocketIOException socketIOException) {
	}

}
