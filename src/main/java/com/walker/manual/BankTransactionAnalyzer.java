package com.walker.manual;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/1/23
 */
public class BankTransactionAnalyzer {

    private static final String RESOURCE = "src/main/resources/";

    public void analyze(String filename, BankStatementParser parser) throws IOException {
        final Path path = Paths.get(RESOURCE + filename);
        List<String> lines = Files.readAllLines(path);

        List<BankTransaction> transactions = parser.parseLinesFrom(lines);

        BankStatementProcessor processor = new BankStatementProcessor(transactions);
        collectSummary(processor);
    }

    private void collectSummary(BankStatementProcessor processor) {
        System.out.println("The total for all transaction is " + processor.calculateTotalAmount());

        System.out.println("Transactions in January " + processor.calculateTotalInMonth(Month.JANUARY));

        System.out.println("The total salary received is " + processor.calculateTotalForCategory("Salary"));

        List<BankTransaction> transactions = processor.findTransactions(transaction ->
                transaction.getDate().getMonth() == Month.FEBRUARY && transaction.getAmount() >= 1000);
    }

}
