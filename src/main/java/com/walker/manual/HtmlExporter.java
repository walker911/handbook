package com.walker.manual;

/**
 * <p>
 * 导出html
 * </p>
 *
 * @author mu qin
 * @date 2021/1/26
 */
public class HtmlExporter implements Exporter{

    @Override
    public String export(SummaryStatistics statistics) {
        String result = "<!doctype html>";
        result += "<li><strong>The sum is</strong>: " + statistics.getSum() + "</li>";
        result += "<li><strong>The average is</strong>: " + statistics.getAverage() + "</li>";
        result += "<li><strong>The max is</strong>: " + statistics.getMax() + "</li>";
        result += "<li><strong>The min is</strong>: " + statistics.getMin() + "</li>";

        return result;
    }
}
