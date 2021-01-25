package com.walker.manual;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/1/25
 */
@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarize(double accumulator, BankTransaction transaction);
}
