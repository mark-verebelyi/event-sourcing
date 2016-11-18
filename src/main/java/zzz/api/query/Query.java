package zzz.api.query;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Query<R extends QueryResult> {

    private final UUID uuid = UUID.randomUUID();
    private final LocalDateTime timestamp = LocalDateTime.now();

    public final UUID uuid() {
        return uuid;
    }

    public final LocalDateTime timestamp() {
        return timestamp;
    }

}
