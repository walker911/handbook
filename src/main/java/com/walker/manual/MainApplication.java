package com.walker.manual;

import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/1/25
 */
public class MainApplication {

    public static void main(String[] args) throws IOException {
        String filename = args[0];

        BankTransactionAnalyzer analyzer = new BankTransactionAnalyzer();
        BankStatementParser parser = new BankStatementCsvParser();
        analyzer.analyze(filename, parser);
    }
}
