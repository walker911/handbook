package com.walker.manual;

/**
 * <p>
 * 导出json
 * </p>
 *
 * @author mu qin
 * @date 2021/2/1
 */
public class JsonExporter implements Exporter{

    @Override
    public String export(SummaryStatistics statistics) {
        String result = "{";
        result += "\"sum\":" + statistics.getSum() + ",";
        result += "\"max\":" + statistics.getSum() + ",";
        result += "\"min\":" + statistics.getSum() + ",";
        result += "\"average\":" + statistics.getSum() + "}";

        return result;
    }
}
