package zzz.api.query;

public interface QueryHandler<Q extends Query<R>, R extends QueryResult> {

    R handle(Q query);

    Class<Q> queryType();

}
