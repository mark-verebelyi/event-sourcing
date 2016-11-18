package zzz.api.query;

public interface QueryHandlerRegistry {

    void register(QueryHandler<?, ?> queryHandler);

}
