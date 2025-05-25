package ro.tuiasi.ac.Proiect_PIP;

import java.time.Duration;

import com.pi4j.context.Context;
import com.pi4j.io.i2c.I2C;
import ro.tuiasi.ac.Proiect_PIP.I2CDevice;


/**
 * Implementarea unui LCDDisplay folosind GPIO cu Pi4J
 * <p>
 * Functioneaza doar cu modulul PCF8574T.
 */
public class LcdDisplay extends I2CDevice {
    /** Flags pentru comenzile display-ului */
    private static final byte LCD_CLEAR_DISPLAY   = (byte) 0x01;
    private static final byte LCD_RETURN_HOME     = (byte) 0x02;
    private static final byte LCD_SCROLL_RIGHT    = (byte) 0x1E;
    private static final byte LCD_SCROLL_LEFT     = (byte) 0x18;
    private static final byte LCD_ENTRY_MODE_SET  = (byte) 0x04;
    private static final byte LCD_DISPLAY_CONTROL = (byte) 0x08;
    private static final byte LCD_CURSOR_SHIFT    = (byte) 0x10;
    private static final byte LCD_FUNCTION_SET    = (byte) 0x20;
    private static final byte LCD_SET_CGRAM_ADDR  = (byte) 0x40;
    private static final byte LCD_SET_DDRAM_ADDR  = (byte) 0x80;
    // flags pentru display entry mode
    private static final byte LCD_ENTRY_RIGHT           = (byte) 0x00;
    private static final byte LCD_ENTRY_LEFT            = (byte) 0x02;
    private static final byte LCD_ENTRY_SHIFT_INCREMENT = (byte) 0x01;
    private static final byte LCD_ENTRY_SHIFT_DECREMENT = (byte) 0x00;
    // flags pentru control display on/off 
    private static final byte LCD_DISPLAY_ON  = (byte) 0x04;
    private static final byte LCD_DISPLAY_OFF = (byte) 0x00;
    private static final byte LCD_CURSOR_ON   = (byte) 0x02;
    private static final byte LCD_CURSOR_OFF  = (byte) 0x00;
    private static final byte LCD_BLINK_ON    = (byte) 0x01;
    private static final byte LCD_BLINK_OFF   = (byte) 0x00;
    // flags pentru display/cursor shift
    private static final byte LCD_DISPLAY_MOVE = (byte) 0x08;
    private static final byte LCD_CURSOR_MOVE  = (byte) 0x00;
    // flags pentru functia set
    private static final byte LCD_8BIT_MODE = (byte) 0x10;
    private static final byte LCD_4BIT_MODE = (byte) 0x00;
    private static final byte LCD_2LINE     = (byte) 0x08;
    private static final byte LCD_1LINE     = (byte) 0x00;
    private static final byte LCD_5x10DOTS  = (byte) 0x04;
    private static final byte LCD_5x8DOTS   = (byte) 0x00;
    // flags pentru control backlight 
    private static final byte LCD_BACKLIGHT     = (byte) 0x08;
    private static final byte LCD_NO_BACKLIGHT  = (byte) 0x00;
    private static final byte En                = (byte) 0b000_00100; // Enable bit
    private static final byte Rw                = (byte) 0b000_00010; // Read/Write bit
    private static final byte Rs                = (byte) 0b000_00001; // Register select bit
  
    /**
     * Offset-ul liniilor display-ului. Offset-ul pentru pana la 4 linii.
     */
    private static final byte[] LCD_ROW_OFFSETS = {0x00, 0x40, 0x14, 0x54};

    private static final int DEFAULT_DEVICE = 0x27;

    /**
     * Numar de linii de pe display
     */
    private final int rows;
    /**
     * Numar de coloane de pe display
     */
    private final int columns;
    /**
     * Este lunima de fundal pornita on/off
     */
    private boolean backlight;

    /**
     * Creeaza un nou component LCDDisplay cu valori implicite
     *
     * @param pi4j Contextul Pi4J
     */
    public LcdDisplay(Context pi4j){
        this(pi4j, 2, 16, DEFAULT_DEVICE);
    }

    /**
     * Creeaza un nou component LCDDisplay cu un numar personalizat de linii si coloane
     *
     * @param pi4j      Contextul Pi4J
     * @param rows      numarul de linii ale display-ului
     * @param columns   numarul de caractere pe fiecare linie
     */
    public LcdDisplay(Context pi4j, int rows, int columns){
        this(pi4j, rows, columns, DEFAULT_DEVICE);
    }

    /**
     * Creeaza un nou component LCDDisplay cu un numar personalizat de linii si coloane
     *
     * @param pi4j      Contextul Pi4J
     * @param rows      numarul de linii ale display-ului
     * @param columns   numarul de caractere pe fiecare linie
     * @param device    Adresa dispozitivului I2C
     */
    public LcdDisplay(Context pi4j, int rows, int columns, int device) {
        super(pi4j, device, "PCF8574AT backed LCD");
        this.rows    = rows;
        this.columns = columns;
    }


    /**
     * Initializeaza LCD-ul cu lumina de fundal oprita
     */
    @Override
    protected void init(I2C i2c) {
        sendLcdTwoPartsCommand((byte) 0x03);
        sendLcdTwoPartsCommand((byte) 0x03);
        sendLcdTwoPartsCommand((byte) 0x03);
        sendLcdTwoPartsCommand((byte) 0x02);

        // Initialize display settings
        sendLcdTwoPartsCommand((byte) (LCD_FUNCTION_SET | LCD_2LINE | LCD_5x8DOTS | LCD_4BIT_MODE));
        sendLcdTwoPartsCommand((byte) (LCD_DISPLAY_CONTROL | LCD_DISPLAY_ON | LCD_CURSOR_OFF | LCD_BLINK_OFF));
        sendLcdTwoPartsCommand((byte) (LCD_ENTRY_MODE_SET | LCD_ENTRY_LEFT | LCD_ENTRY_SHIFT_DECREMENT));

        clearDisplay();

        // Enable backlight
        setDisplayBacklight(true);
    }

    /**
     * Porneste sau opreste lumina de fundal
     */
    public void setDisplayBacklight(boolean backlightEnabled) {
        backlight = backlightEnabled;
        try {
			sendCommand(backlight ? LCD_BACKLIGHT : LCD_NO_BACKLIGHT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		    System.out.println("eroare la setDisplayBacklight");
			e.printStackTrace();
		}
    }

    /**
     * Curata LCD-ul si muta cursorul la pozitia initiala
     */
    public void clearDisplay() {
        moveCursorHome();
        sendLcdTwoPartsCommand(LCD_CLEAR_DISPLAY);
    }

    /**
     * Muta cursorul la pozitia de start (prima linie, primul caracter)
     */
    public void moveCursorHome() {
        sendLcdTwoPartsCommand(LCD_RETURN_HOME);
    }

    /**
     * Inchide display-ul
     */
    public void off() {
        try {
			sendCommand(LCD_DISPLAY_OFF);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("eroare la functia de oprire lcd");
			e.printStackTrace();
		}
    }

    /**
     * Afiseaza o linie de text pe LCD
     *
     * @param text Textul care trebuie afisat
     * @param line numarul liniei pe display, interval: 0 .. rows-1
     */
    public void displayLineOfText(String text, int line) {
        displayLineOfText(text, line, 0);
    }

    /**
     * Centreaza textul specificat pe linia specificata
     * @param text Textul care trebuie afișat
     * @param line numărul liniei pe display, interval: 0 .. rows-1
     */
    public void centerTextInLine(String text, int line){
        displayLineOfText(text, line, (int) ((columns - text.length()) * 0.5));
    }

    /**
     * Afișează o linie de text pe LCD
     *
     * @param text     textul care trebuie afișat
     * @param line     numărul liniei pe display, interval: 0..rows-1
     * @param position poziția de start, interval: 0..columns-1
     */
    public void displayLineOfText(String text, int line, int position) {
        if (text.length() + position > columns) {
        	System.out.println("Text " + text + "too long, cut to"+position +"characters"  );
            text = text.substring(0, (columns - position));
        }

        if (line > rows || line < 0) {
        	System.out.println("Wrong line id "+ line +". Only " + rows + " lines possible");
        }
        else {
            setCursorToPosition(line, 0);
            for(int i= 0; i < position; i++){
                writeCharacter(' ');
            }
            for (char character : text.toCharArray()) {
                writeCharacter(character);
            }
            for(int i = 0; i < columns - text.length() - position; i++){
                writeCharacter(' ');
            }
        }
    }

    /**
     * Afișează textul pe LCD începând de la poziția de start
     *
     * @param text Textul care trebuie afișat
     */
    public void displayText(String text) {
    	System.out.println("Display in LCD: "+ text);
        var currentLine = 0;

        StringBuilder[] texts = new StringBuilder[rows];
        for (int j = 0; j < rows; j++) {
            texts[j] = new StringBuilder(rows);
        }

        for (int i = 0; i < text.length(); i++) {
            if (currentLine > rows - 1) {
            	System.out.println("Text too long, remaining " + text.substring(i)+" will not be displayed");
                break;
            }
            if (text.charAt(i) == '\n') {
                currentLine++;
                continue;
            }
            else if(texts[currentLine].length() >= columns){
                currentLine++;
                if (text.charAt(i) == ' ') {
                    i++;
                }
            }
            // append character to line
            if(currentLine < rows){
                texts[currentLine].append(text.charAt(i));
            }
        }

        //display the created texts
        for (int j = 0; j < rows; j++) {
            displayLineOfText(texts[j].toString(), j);
        }
    }

    /**
     * Scrie un caracter pe LCD la poziția curentă a cursorului
     *
     * @param character caracterul care va fi scris
     */
    public void writeCharacter(char character) {
        sendLcdTwoPartsCommand((byte) character, Rs);
    }

    /**
     * Scrie un caracter pe LCD la o poziție specifică
     *
     * @param character caracterul care va fi scris
     * @param line   poziția pe linie, interval: 0 .. rows-1
     * @param pos    poziția pe coloană, interval: 0 .. columns-1
     */
    public void writeCharacter(char character, int line, int pos) {
        setCursorToPosition(line, pos);
        sendLcdTwoPartsCommand((byte) character, Rs);
    }

    /**
     * Afișează o linie la o poziție specifică
     *
     * @param text textul care trebuie afișat
     * @param pos  poziția de start a textului
     */
    private void displayLine(String text, int pos) {
        sendLcdTwoPartsCommand((byte) (0x80 + pos));

        for (int i = 0; i < text.length(); i++) {
            writeCharacter(text.charAt(i));
        }
    }

    /**
     * Curăță o linie a display-ului
     *
     * @param line numărul liniei care trebuie ștearsă
     */
    public void clearLine(int line) {
        if (line > rows || line < 1) {
            throw new IllegalArgumentException("Wrong line id. Only " + rows + " lines possible");
        }
        displayLine(" ".repeat(columns), LCD_ROW_OFFSETS[line - 1]);
    }

    /**
     * Setează cursorul la o poziție țintă
     *
     * @param line Selectează linia display-ului. Interval: 0 - ROWS-1
     * @param pos  Selectează caracterul de pe linie. Interval: 0 - Columns-1
     */
    public void setCursorToPosition(int line, int pos) {
        if (line > rows-1 || line < 0) {
            throw new IllegalArgumentException("Line out of range. Display has only " + rows + "x" + columns + " Characters!");
        }

        if (pos < 0 || pos > columns-1) {
            throw new IllegalArgumentException("Line out of range. Display has only " + rows + "x" + columns + " Characters!");
        }
        sendLcdTwoPartsCommand((byte) (LCD_SET_DDRAM_ADDR | pos + LCD_ROW_OFFSETS[line]));
    }

    /**
     * Creează un caracter personalizat oferind starea fiecărui pixel. Trebuie trimis un array de bytes
     * care va fi transformat într-un caracter.
     *
     * @param location  Setează locația din memorie a caracterului. Valori permise: 1 - 7.
     * @param character Array de bytes care reprezintă pixelii caracterului
     */
    public void createCharacter(int location, byte[] character) {
        if (character.length != 8) {
            throw new IllegalArgumentException("Array has invalid length. Character is only 5x8 Digits. Only a array with length" +
                    " 8 is allowed");
        }

        if (location > 7 || location < 1) {
            throw new IllegalArgumentException("Invalid memory location. Range 1-7 allowed. Value: " + location);
        }
        sendLcdTwoPartsCommand((byte) (LCD_SET_CGRAM_ADDR | location << 3));

        for (int i = 0; i < 8; i++) {
            sendLcdTwoPartsCommand(character[i], (byte) 1);
        }
    }

    /**
     * Scroll  întregul display la dreapta cu o coloană
     */
    public void scrollRight(){
        sendLcdTwoPartsCommand(LCD_SCROLL_RIGHT);
    }

    /**
     * Scroll întregul display la stanga cu o coloană
     */
    public void scrollLeft(){
        sendLcdTwoPartsCommand(LCD_SCROLL_LEFT);
    }

    /**
     * Resetează display-ul (curăță și oprește)
     */
    public void reset() {
        clearDisplay();
        off();
    }

    /**
     * Trimite o comandă către LCD
     */
    private void sendLcdTwoPartsCommand(byte cmd) {
        sendLcdTwoPartsCommand(cmd, (byte) 0);
    }

    /**
     * Trimite o comandă în două părți către LCD
     */
    private void sendLcdTwoPartsCommand(byte cmd, byte mode) {
        //bitwise AND with 11110000 to remove last 4 bits
        writeFourBits((byte) (mode | (cmd & 0xF0)));
        //bitshift and bitwise AND to remove first 4 bits
        writeFourBits((byte) (mode | ((cmd << 4) & 0xF0)));
    }

    /**
     * Scrie cele patru biți ai unui byte către LCD
     *
     * @param data byte-ul care este trimis
     */
    private void writeFourBits(byte data) {
        byte backlightStatus = backlight ? LCD_BACKLIGHT : LCD_NO_BACKLIGHT;

        write((byte) (data | En | backlightStatus));
        write((byte) ((data & ~En) | backlightStatus));

        try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
