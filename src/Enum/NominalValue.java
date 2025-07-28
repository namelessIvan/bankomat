package Enum;

public enum NominalValue {
    RUB_50(50),
    RUB_100(100),
    RUB_500(500),
    RUB_1000(1000),
    RUB_5000(5000);

    private final int value;
    private NominalValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
