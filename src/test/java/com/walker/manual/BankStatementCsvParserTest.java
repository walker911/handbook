package com.walker.manual;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/1/25
 */
public class BankStatementCsvParserTest {

    private BankStatementParser parser = new BankStatementCsvParser();

    @Test
    public void shouldParseOneCorrectLine() {
        String line = "2021-01-25,-50,Tesco";

        BankTransaction result = parser.parseFrom(line);
        BankTransaction expected = new BankTransaction(LocalDate.of(2021, Month.JANUARY, 25), -50, "Tesco");
        double tolerance = 0.0d;

        Assertions.assertEquals(expected.getDate(), result.getDate());
        Assertions.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assertions.assertEquals(expected.getDescription(), result.getDescription());
    }
}
