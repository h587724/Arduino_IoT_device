package no.hvl.dat110.aciotdevice.ui;

import no.hvl.dat110.aciotdevice.pins.Constants;
import no.hvl.dat110.aciotdevice.pins.IOPins;
import javafx.scene.control.Button;

public class PushButton extends Button {

	private IOPins iopins;
	int pin;

	public PushButton(String label, IOPins iopins, int pin) {
		super(label);
		this.iopins = iopins;
		this.pin = pin;
		
		setOnAction(e -> {
			iopins.write(pin, Constants.HIGH);
			System.out.println("PUSHBOTTON pin " + pin);
		});
	}
}
