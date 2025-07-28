package Enum;

public enum DollarNominalValue {
    USD_1(1),
    USD_2(2),
    USD_5(5),
    USD_10(10),
    USD_20(20),
    USD_50(50),
    USD_100(100);

    private int value;
    private DollarNominalValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
