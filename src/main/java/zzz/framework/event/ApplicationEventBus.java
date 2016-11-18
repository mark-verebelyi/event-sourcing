package zzz.framework.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zzz.api.event.Event;
import zzz.api.event.EventBus;
import zzz.api.event.EventListener;
import zzz.api.event.EventListenerProvider;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkArgument;

public final class ApplicationEventBus implements EventBus {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final EventListenerProvider provider;

    public ApplicationEventBus(final EventListenerProvider provider) {
        checkArgument(provider != null, "provider cannot be null");
        this.provider = provider;
    }

    @Override
    public void publish(final Event event) {
        checkArgument(event != null, "event cannot be null");
        final Collection<EventListener<Event>> eventListeners = provider.provide(event);
        if (eventListeners.isEmpty()) {
            logger.debug("no event listeners registered for event type [{}]", event.getClass().getName());
        }
        eventListeners.forEach(eventListener -> eventListener.onEvent(event));
    }

}
