package pipProiect;

public class SenzorUmiditateMock implements SenzorUmiditateInterface {
    private final boolean valoare;

    public SenzorUmiditateMock(boolean valoare) {
        this.valoare = valoare;
    }

    @Override
    public boolean esteUmed() {
        return valoare;
    }
}
