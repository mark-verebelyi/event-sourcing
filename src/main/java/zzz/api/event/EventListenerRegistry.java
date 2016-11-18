package zzz.api.event;

public interface EventListenerRegistry {

    void register(EventListener<?> eventListener);

}
