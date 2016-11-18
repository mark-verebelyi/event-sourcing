package zzz.api.query;

import java.util.Optional;

public interface QueryHandlerProvider {

    <Q extends Query<R>, R extends QueryResult> Optional<QueryHandler<Q, R>> provide(Q query);

}
