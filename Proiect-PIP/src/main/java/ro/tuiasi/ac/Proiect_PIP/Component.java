package ro.tuiasi.ac.Proiect_PIP;

import java.time.Duration;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Clasa abstracta de baza pentru componente care folosesc logare si functii auxiliare
 */
public abstract class Component {
    /**
     * Instanta de Logger care va fi folosita pentru afisarea de mesaje
     */
    private static final Logger logger = Logger.getLogger("Pi4J Components");
    /**
     * Bloc static care seteaza nivelul de logare si formatarea mesajelor
     */
    static {
        Level appropriateLevel = Level.INFO;
        //Level appropriateLevel = Level.FINE; //use if 'debug'

        // Formatul personalizat al mesajelor afisate in consola
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%4$s: %5$s [%1$tl:%1$tM:%1$tS %1$Tp]%n");

        logger.setLevel(appropriateLevel);		// Seteaza nivelul pentru logger
        logger.setUseParentHandlers(false);		// Dezactiveaza handlerul implicit
        ConsoleHandler handler = new ConsoleHandler();// Creeaza un handler pentru consola
        handler.setLevel(appropriateLevel);		// Seteaza acelasi nivel pentru handler
        logger.addHandler(handler);				// Adauga handlerul la logger
    }

    protected Component(){
    }
 
    /**
     * Logare mesaj de tip informativ
     * @param msg mesajul de afisat
     * @param args argumente pentru formatare
     */
    protected void logInfo(String msg, Object... args) {
        logger.info(() -> String.format(msg, args));
    }
    /**
     * Logare mesaj de eroare
     * @param msg mesajul de afisat
     * @param args argumente pentru formatare
     */
    protected void logError(String msg, Object... args) {
        logger.severe(() -> String.format(msg, args));
    }
    /**
     * Logare mesaj pentru debugging 
     * @param msg mesajul de afisat
     * @param args argumente pentru formatare
     */
    protected void logDebug(String msg, Object... args) {
        logger.fine(() -> String.format(msg, args));
    }
    /**
     * Logare exceptie cu mesaj
     * @param msg mesajul care insoteste exceptia
     * @param exception obiectul exceptiei
     */
    protected void logException(String msg, Throwable exception){
        logger.log(Level.SEVERE, msg, exception);
    }

    /**
     * Functie utilitara care pune in pauza executia pentru o durata specificata
     * @param duration durata de pauza
     */
    protected void delay(Duration duration) {
        try {
            long nanos = duration.toNanos();
            long millis = nanos / 1_000_000;
            int remainingNanos = (int) (nanos % 1_000_000);
            Thread.currentThread().sleep(millis, remainingNanos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Metoda auxiliara pentru a transforma un obiect generic intr-un anumit tip
     * @param type clasa dorita
     * @param instance instanta ce urmeaza a fi convertita
     * @param <T> tipul rezultat
     * @return obiectul convertit
     */
    protected <T> T asMock(Class<T> type, Object instance) {
        return type.cast(instance);
    }
}

