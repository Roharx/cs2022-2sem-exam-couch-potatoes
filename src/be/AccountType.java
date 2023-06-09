package be;

public enum AccountType {
    CEO(0),
    TECHNICIAN(1),
    PROJECTMANAGER(2),
    SALESPERSON(3);

    private int id;
    private final int value;
    AccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public static AccountType fromValue(int id) {
        for (AccountType type : values()) {
            if (type.getValue() == id) {
                return type;
            }
        }
        return null;
    }
    private int getId() {
        return id;
    }
}
