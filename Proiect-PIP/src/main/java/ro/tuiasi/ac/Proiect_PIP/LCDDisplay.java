package ro.tuiasi.ac.Proiect_PIP;
import com.pi4j.io.i2c.I2C;

public class LCDDisplay {
	 private final I2C lcd;

	    public LCDDisplay(I2C lcd) {
	        this.lcd = lcd;
	        init();
	    }

	    private void init() {
	        // Trimite comenzi de inițializare LCD
	        sendCmd(0x33); // Initialization
	        sendCmd(0x32); // Initialization
	        sendCmd(0x06); // Cursor move direction
	        sendCmd(0x0C); // Display On, Cursor Off
	        sendCmd(0x28); // 2 line, 5x7 matrix
	        sendCmd(0x01); // Clear display
	    }

	    public void write(String line1, String line2) {
	        sendCmd(0x80); // Prima linie
	        writeLine(line1);
	        sendCmd(0xC0); // A doua linie
	        writeLine(line2);
	    }

	    private void writeLine(String text) {
	        for (char c : String.format("%-16s", text).toCharArray()) {
	            sendData(c);
	        }
	    }

	    private void sendCmd(int cmd) {
	        try {
	            lcd.write((byte) 0x00, (byte) cmd);
	        } catch (Exception e) {
	            System.err.println("Eroare comanda LCD: " + e.getMessage());
	        }
	    }

	    private void sendData(int data) {
	        try {
	            lcd.write((byte) 0x40, (byte) data);
	        } catch (Exception e) {
	            System.err.println("Eroare data LCD: " + e.getMessage());
	        }
	    }
}
