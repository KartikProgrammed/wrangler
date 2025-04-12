package io.cdap.wrangler.api.parser;

import com.google.gson.JsonObject;

public class ByteSize implements Token {
    private final long bytes;

    public ByteSize(String value) {
        this.bytes = parseByteSize(value);
    }

    @Override
    public Object value() {
        return bytes;
    }

    @Override
    public TokenType type() {
        return TokenType.BYTE_SIZE; // Assuming TokenType is the enum used in Token interface
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("type", "byte-size");
        object.addProperty("value", bytes);
        return object;
    }

    private long parseByteSize(String value) {
        value = value.trim().toUpperCase();
        if (value.endsWith("KB")) return (long) (Double.parseDouble(value.replace("KB", "").trim()) * 1024);
        if (value.endsWith("MB")) return (long) (Double.parseDouble(value.replace("MB", "").trim()) * 1024 * 1024);
        if (value.endsWith("GB")) return (long) (Double.parseDouble(value.replace("GB", "").trim()) * 1024 * 1024 * 1024);
        return Long.parseLong(value); // bytes
    }

    public long getBytes() {
        return bytes;
    }
}
