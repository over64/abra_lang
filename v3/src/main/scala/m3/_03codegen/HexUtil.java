package m3._03codegen;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class HexUtil {
    public static class EncodeResult {
        public final String str;
        public final int bytesLen;

        public EncodeResult(String str, int bytesLen) {
            this.str = str;
            this.bytesLen = bytesLen;
        }
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    static void encodeByte(byte b, OutputStream out) throws IOException {
        int v = b & 0xFF;
        out.write(92); // \
        out.write((byte) hexArray[v >>> 4]);
        out.write((byte) hexArray[v & 0x0F]);
    }

    final static HashMap<Byte, Byte> escapeMap = new HashMap<Byte, Byte>() {{
        put((byte) 98, (byte) 7);   // \b -> BEL
        put((byte) 116, (byte) 9);  // \t -> TAB
        put((byte) 110, (byte) 10); // \n -> NL
        put((byte) 102, (byte) 12); // \f -> FF
        put((byte) 114, (byte) 13); // \r -> CR
        put((byte) 39, (byte) 39);  // \' -> '
        put((byte) 92, (byte) 92);  // \\ -> \
    }};

    static boolean mkEscape(byte[] src, int srcPtr, OutputStream out) throws IOException {
        if (srcPtr < src.length - 1)
            if (src[srcPtr] == 92 /* '\' */) {
                byte b = src[srcPtr + 1];
                Byte forEscape = escapeMap.get(b);
                if (forEscape != null) {
                    encodeByte(forEscape, out);
                    return true;
                }
            }
        return false;
    }

    public static EncodeResult singleByteNullTerminated(byte[] bytes) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = 0;
        for (int j = 0; j < bytes.length; ) {
            if (mkEscape(bytes, j, out)) {
                j += 2;
                len++;
            } else {
                if ((bytes[j] & 0xFF) < 128) {
                    out.write(bytes[j]);
                    len++;
                    j++;
                } else {
                    // ok if correct unicode
                    encodeByte(bytes[j], out);
                    encodeByte(bytes[j + 1], out);
                    len += 2;
                    j += 2;
                }
            }
        }

        // Null termination
        out.write(92); // \
        out.write((byte) 48);
        out.write((byte) 48);
        len++;

        return new EncodeResult(new String(out.toByteArray()), len);
    }
}