package zzz.api.event;

public interface EventListener<E extends Event> {

    void onEvent(E event);

    Class<E> eventType();

}
