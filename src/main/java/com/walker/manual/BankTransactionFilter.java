package com.walker.manual;

/**
 * <p>
 * 负责选择逻辑
 * </p>
 *
 * @author mu qin
 * @date 2021/1/25
 */
@FunctionalInterface
public interface BankTransactionFilter {

    boolean test(BankTransaction transaction);

}
