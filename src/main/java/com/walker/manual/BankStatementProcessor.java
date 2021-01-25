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
        return summarizeTransactions(((accumulator, transaction) -> transaction.getDate().getMonth() == month ? accumulator + transaction.getAmount() : accumulator));
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
        return findTransactions(transaction -> transaction.getAmount() >= amount);
    }

    public List<BankTransaction> findTransactionsInMonth(Month month) {
        return findTransactions(transaction -> transaction.getDate().getMonth() == month);
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

    public double summarizeTransactions(BankTransactionSummarizer summarizer) {
        double result = 0d;

        for (BankTransaction transaction : transactions) {
            result = summarizer.summarize(result, transaction);
        }

        return result;
    }

}
