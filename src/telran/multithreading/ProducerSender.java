package telran.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class ProducerSender extends Thread {
	// HW #44 definition
	// dispatching functionality
	// two message boxes
	// even messages should be put to even message box
	// odd messages should be put to odd message box
	private BlockingQueue<String> evenMessageBox;
	private BlockingQueue<String> oddMessageBox;
	private int nMessages;

	public ProducerSender(BlockingQueue<String> OddMessageBox, BlockingQueue<String> EvenMessageBox, int nMessages) {
		this.evenMessageBox = EvenMessageBox;
		this.oddMessageBox = OddMessageBox;
		this.nMessages = nMessages;
	}

	public void run() {
		IntStream.rangeClosed(1, nMessages).mapToObj(i -> "message" + i).forEach(m -> {
			sendMessage(m, m.charAt(m.length() - 1));
		});
	}

	private void sendMessage(String m, char lastChar) {
		if (Character.getNumericValue(lastChar) % 2 == 0) {
			try {
				evenMessageBox.put(m);
			} catch (InterruptedException e) {

			}

		} else {
			try {
				oddMessageBox.put(m);
			} catch (InterruptedException e) {

			}
		}

	}

}
