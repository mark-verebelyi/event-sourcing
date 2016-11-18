package zzz.framework.event;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import zzz.api.event.Event;
import zzz.api.event.EventListener;
import zzz.api.event.EventListenerProvider;
import zzz.api.event.EventListenerRegistry;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

public class EventListenerRepository implements EventListenerProvider, EventListenerRegistry {

    private volatile Multimap<Class<?>, EventListener<?>> repository = ImmutableMultimap.of();

    @Override
    public void register(final EventListener<?> eventListener) {
        checkArgument(eventListener != null, "eventListener cannot be null");
        final Class<?> eventType = eventListener.eventType();
        repository = add(repository, eventType, eventListener);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E extends Event> Collection<EventListener<E>> provide(final E event) {
        checkArgument(event != null, "event cannot be null");
        final Collection<EventListener<?>> eventListeners = repository.get(event.getClass());
        return eventListeners.stream().map(listener -> (EventListener<E>) listener).collect(Collectors.toList());
    }

    private static ImmutableMultimap<Class<?>, EventListener<?>> add(Multimap<Class<?>, EventListener<?>> repository, final Class<?> eventType, final EventListener<?> eventListener) {
        final ImmutableMultimap.Builder<Class<?>, EventListener<?>> builder = ImmutableMultimap.builder();
        builder.putAll(repository);
        builder.put(eventType, eventListener);
        return builder.build();
    }

}
