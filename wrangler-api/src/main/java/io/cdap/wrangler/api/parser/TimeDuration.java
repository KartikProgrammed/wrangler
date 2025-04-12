package io.cdap.wrangler.api.parser;

import com.google.gson.JsonObject;

public class TimeDuration implements Token {
    private final long milliseconds;

    public TimeDuration(String value) {
        this.milliseconds = parseTime(value);
    }

    @Override
    public Object value() {
        return milliseconds;
    }

    @Override
    public TokenType type() {
        return TokenType.TIME_DURATION;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("type", "time-duration");
        object.addProperty("value", milliseconds);
        return object;
    }

    private long parseTime(String value) {
        value = value.trim().toLowerCase();
        if (value.endsWith("ms")) return (long) Double.parseDouble(value.replace("ms", "").trim());
        if (value.endsWith("s")) return (long) (Double.parseDouble(value.replace("s", "").trim()) * 1000);
        if (value.endsWith("m")) return (long) (Double.parseDouble(value.replace("m", "").trim()) * 60 * 1000);
        if (value.endsWith("h")) return (long) (Double.parseDouble(value.replace("h", "").trim()) * 3600 * 1000);
        return Long.parseLong(value); // fallback
    }

    public long getMilliseconds() {
        return milliseconds;
    }
}
