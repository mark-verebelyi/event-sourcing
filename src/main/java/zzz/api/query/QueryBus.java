package zzz.api.query;

public interface QueryBus {

    <Q extends Query<R>, R extends QueryResult> R dispatch(Q query);

}
