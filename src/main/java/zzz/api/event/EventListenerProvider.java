package zzz.api.event;

import java.util.Collection;

public interface EventListenerProvider {

    <E extends Event> Collection<EventListener<E>> provide(E event);

}
