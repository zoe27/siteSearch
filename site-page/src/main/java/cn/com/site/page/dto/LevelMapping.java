package cn.com.site.page.dto;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: LevelMapping
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2020-12-25 20:32
 */
public class LevelMapping {

    private String company;

    private String level;

    private Double monthBase;

    private Double yearBase;

    private String compareCompany;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Double getMonthBase() {
        return monthBase;
    }

    public void setMonthBase(Double monthBase) {
        this.monthBase = monthBase;
    }

    public Double getYearBase() {
        return yearBase;
    }

    public void setYearBase(Double yearBase) {
        this.yearBase = yearBase;
    }

    public String getCompareCompany() {
        return compareCompany;
    }

    public void setCompareCompany(String compareCompany) {
        this.compareCompany = compareCompany;
    }
}
