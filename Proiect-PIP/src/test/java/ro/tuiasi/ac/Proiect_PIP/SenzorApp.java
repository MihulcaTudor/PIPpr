package ro.tuiasi.ac.Proiect_PIP;

/**
 * Clasa ajutatoare pentru testare.
 * Contine logica simpla pentru interpretarea starii solului.
 */
public class SenzorApp {

    /**
     * Returneaza un mesaj in functie de starea solului.
     * @param esteUscat true daca senzorul detecteaza sol uscat
     * @return mesaj pentru afisare pe LCD
     */
    public String getMesajAfisaj(boolean esteUscat) {
        if (esteUscat) {
            return "Solul este uscat";
        } else {
            return "Solul este umed";
        }
    }
}

