package com.walker.manual.twoot;

import java.util.Optional;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/12
 */
public interface AbstractRepository<T> {

    void add(T value);

    Optional<T> get(String id);

    void update(T value);

    void delete(T value);

}
