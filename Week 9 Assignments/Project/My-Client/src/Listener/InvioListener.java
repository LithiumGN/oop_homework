package Listener;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class InvioListener implements ActionListener {

	private JTextField textField;
	private ObjectOutputStream oos;

	public InvioListener(JTextField textField, ObjectOutputStream oos) {
		this.textField = textField;
		this.oos = oos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Ci provo");
		String text = textField.getText();
		if (!text.equals("")) {
			try {
				oos.writeObject(text);
				oos.flush();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Connessione con il server persa, applicazione dismessa");
				Window frame = SwingUtilities.getWindowAncestor(textField);
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}

			textField.setText("");
		}
	}
}
