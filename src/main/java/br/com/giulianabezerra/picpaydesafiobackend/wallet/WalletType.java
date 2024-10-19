package br.com.giulianabezerra.picpaydesafiobackend.wallet;

public enum WalletType {
    COMUN(1),     LOGISTA(2);

    private int value;

    private WalletType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    
}
