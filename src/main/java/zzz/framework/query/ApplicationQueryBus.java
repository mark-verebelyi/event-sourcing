package zzz.framework.query;

import zzz.api.query.Query;
import zzz.api.query.QueryBus;
import zzz.api.query.QueryHandler;
import zzz.api.query.QueryHandlerProvider;
import zzz.api.query.QueryResult;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public final class ApplicationQueryBus implements QueryBus {

    private final QueryHandlerProvider provider;

    public ApplicationQueryBus(final QueryHandlerProvider provider) {
        checkArgument(provider != null, "provider cannot be null");
        this.provider = provider;
    }

    @Override
    public <Q extends Query<R>, R extends QueryResult> R dispatch(final Q query) {
        checkArgument(query != null, "query cannot be null");
        final Optional<QueryHandler<Q, R>> queryHandler = provider.provide(query);
        checkState(queryHandler.isPresent(), "no handler registered for query type [%s]", query.getClass().getName());
        return queryHandler.get().handle(query);
    }

}
