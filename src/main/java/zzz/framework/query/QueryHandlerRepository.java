package zzz.framework.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zzz.api.query.Query;
import zzz.api.query.QueryHandler;
import zzz.api.query.QueryHandlerProvider;
import zzz.api.query.QueryHandlerRegistry;
import zzz.api.query.QueryResult;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.google.common.base.Preconditions.checkArgument;

public final class QueryHandlerRepository implements QueryHandlerProvider, QueryHandlerRegistry {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ConcurrentMap<Class<?>, QueryHandler<?, ?>> repository = new ConcurrentHashMap<>();

    @Override
    public void register(final QueryHandler<?, ?> queryHandler) {
        checkArgument(queryHandler != null, "query handler cannot be null");
        final Class<?> queryType = queryHandler.queryType();
        final QueryHandler<?, ?> prev = repository.put(queryType, queryHandler);
        if (prev != null) {
            throw new IllegalStateException(String.format("query handler already registered for [%s]; previous [%s], current [%s]",
                    queryType.getName(),
                    prev.getClass().getName(),
                    queryHandler.getClass().getName()));
        }
        logger.debug("registered query handler for query type [{}] -> {}", queryType.getName(), queryHandler.getClass().getName());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <Q extends Query<R>, R extends QueryResult> Optional<QueryHandler<Q, R>> provide(final Q query) {
        checkArgument(query != null, "query cannot be null");
        final QueryHandler<?, ?> queryHandler = repository.get(query.getClass());
        return Optional.ofNullable((QueryHandler<Q, R>) queryHandler);
    }

}
