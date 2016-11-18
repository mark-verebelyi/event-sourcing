package zzz.api.command;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Command {

    private final UUID uuid = UUID.randomUUID();
    private final LocalDateTime timestamp = LocalDateTime.now();

    public final UUID uuid() {
        return uuid;
    }

    public final LocalDateTime timestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "uuid=" + uuid +
                ", timestamp=" + timestamp +
                '}';
    }
}
