package com.walker.manual;

import java.time.Month;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/1/25
 */
public class BankTransactionIsInFebruaryAndExpensive implements BankTransactionFilter {
    @Override
    public boolean test(BankTransaction transaction) {
        return transaction.getDate().getMonth() == Month.FEBRUARY && transaction.getAmount() >= 1000;
    }
}
