package com.ashline.senses.faction;

import java.util.Locale;
import java.util.Optional;

/** Six ASHLINE factions — ids match datapack + quest commands. */
public enum FactionId {
    MILITIA("militia"),
    CUT("cut"),
    WARD("ward"),
    PARISH("parish"),
    HOLDS("holds"),
    CHOIR("choir");

    private final String id;

    FactionId(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    public static Optional<FactionId> parse(String raw) {
        if (raw == null || raw.isBlank()) {
            return Optional.empty();
        }
        String key = raw.toLowerCase(Locale.ROOT).trim();
        for (FactionId f : values()) {
            if (f.id.equals(key)) {
                return Optional.of(f);
            }
        }
        return Optional.empty();
    }

    public static String listIds() {
        StringBuilder sb = new StringBuilder();
        for (FactionId f : values()) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(f.id);
        }
        return sb.toString();
    }
}
