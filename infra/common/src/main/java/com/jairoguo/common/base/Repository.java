package com.jairoguo.common.base;

/**
 * @author Jairo Guo
 */
public interface Repository<A extends AggregateRoot<I>, I extends Id> {
    Boolean save(A aggregate);
    A findById(I id);
    Boolean delete(I id);
    Boolean update(A aggregate);
}
