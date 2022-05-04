package encryptdecrypt;

import java.io.Writer;

public class CryptoFactory {
    private String mode;
    private String alg;

    public CryptoFactory(String mode, String alg) {
        this.mode = mode;
        this.alg = alg;
    }

    public Cryptography getCryptographyInstance(int shift, Writer writer) {
        switch (mode) {
            case "enc": break;
            case "dec": shift *= -1;
                break;
            default: Main.exit(mode);
        }
        switch (alg) {
            case "shift": return new Shift(shift, writer);
            case "unicode": return new Unicode(shift, writer);
            default: Main.exit(alg);
        }
        return null;
    }
}