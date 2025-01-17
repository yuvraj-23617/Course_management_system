public class GenericPair <T,U>{
    private T a;
    private U b;

    public GenericPair(T a, U b) {
        this.a = a;
        this.b = b;
    }

    public T getValue() {
        return a;
    }

    public U getotherrValue() {
        return b;
    }

    public void setStringValue(T stringValue) {
        this.a = stringValue;
    }

    public void setIntValue(U intValue) {
        this.b = intValue;
    }

    @Override
    public String toString() {
        return a + " " + b + "/5" + "\n";
    }
}
