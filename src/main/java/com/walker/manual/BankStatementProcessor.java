package com.walker.manual;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/1/25
 */
public class BankStatementProcessor {

    private final List<BankTransaction> transactions;

    public BankStatementProcessor(List<BankTransaction> transactions) {
        this.transactions = transactions;
    }

    public double calculateTotalAmount() {
        double total = 0d;

        for (BankTransaction transaction : transactions) {
            total += transaction.getAmount();
        }

        return total;
    }

    public double calculateTotalInMonth(Month month) {
        double total = 0d;

        for (BankTransaction transaction : transactions) {
            if (transaction.getDate().getMonth() == month) {
                total += transaction.getAmount();
            }
        }

        return total;
    }

    public double calculateTotalForCategory(String category) {
        double total = 0d;

        for (BankTransaction transaction : transactions) {
            if (transaction.getDescription().equals(category)) {
                total += transaction.getAmount();
            }
        }

        return total;
    }

    /**
     * 查找超过一定数额的银行交易
     *
     * @param amount 金额
     * @return 交易列表
     */
    public List<BankTransaction> findTransactionsGreaterThanEqual(double amount) {
        List<BankTransaction> result = new ArrayList<>();

        for (BankTransaction transaction : transactions) {
            if (transaction.getAmount() >= amount) {
                result.add(transaction);
            }
        }

        return result;
    }

    public List<BankTransaction> findTransactionsInMonth(Month month) {
        List<BankTransaction> result = new ArrayList<>();

        for (BankTransaction transaction : transactions) {
            if (transaction.getDate().getMonth() == month) {
                result.add(transaction);
            }
        }

        return result;
    }

    public List<BankTransaction> findTransactions(BankTransactionFilter filter) {
        List<BankTransaction> result = new ArrayList<>();

        for (BankTransaction transaction : transactions) {
            if (filter.test(transaction)) {
                result.add(transaction);
            }
        }

        return result;
    }
}
