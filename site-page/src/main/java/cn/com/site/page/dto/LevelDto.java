package cn.com.site.page.dto;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: LevelDto
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2020-10-27 22:20
 */
public class LevelDto<T, A> {

    private String company;

    private Map<String, T> map;

    private A o;

    private int gap;

    private double min;

    private double max;

    private String color;

    private int tableHeight;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Map<String, T> getMap() {
        return map;
    }

    public void setMap(Map<String, T> map) {
        this.map = map;
    }

    public LevelDto(String company, Map<String, T> map) {
        this.company = company;
        this.map = map;
    }

    public LevelDto() {
    }

    public A getO() {
        return o;
    }

    public void setO(A o) {
        this.o = o;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTableHeight() {
        return tableHeight;
    }

    public void setTableHeight(int tableHeight) {
        this.tableHeight = tableHeight;
    }
}
