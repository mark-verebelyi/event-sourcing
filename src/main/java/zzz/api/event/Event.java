package zzz.api.event;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Event {

    private final UUID uuid = UUID.randomUUID();
    private final LocalDateTime timestamp = LocalDateTime.now();

    public final UUID uuid() {
        return uuid;
    }

    public final LocalDateTime timestamp() {
        return timestamp;
    }

}
