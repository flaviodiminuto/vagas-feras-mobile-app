package enumeradores;

public enum IntentExtras {
    USER_ID("USER_ID");

    private final String extra;

    IntentExtras(String extra) {
        this.extra = extra;
    }

    public String get() {
        return extra;
    }
}
