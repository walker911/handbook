package com.walker.manual;

/**
 * <p>
 * 导出 xml
 * </p>
 *
 * @author mu qin
 * @date 2021/2/1
 */
public class XmlExporter implements Exporter{

    @Override
    public String export(SummaryStatistics statistics) {
        String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        result += "<sum>The sum is: " + statistics.getSum() + "</sum>";
        result += "<max>The max is: " + statistics.getMax() + "</max>";
        result += "<min>The min is: " + statistics.getMin() + "</min>";
        result += "<average>The average is: " + statistics.getAverage() + "</average>";

        return result;
    }
}
