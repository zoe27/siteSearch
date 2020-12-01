package cn.com.site.page.dto;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: LevelSalaryDto
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2020-10-31 17:20
 */
public class LevelSalaryDto {
    private String level;

    private Double high;

    private Double low;

    private Double median;

    private Double average;

    private String height;

    private String color;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getMedian() {
        return median;
    }

    public void setMedian(Double median) {
        this.median = median;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public LevelSalaryDto(String level, Double high, Double low, Double median, Double average) {
        this.level = level;
        this.high = high;
        this.low = low;
        this.median = median;
        this.average = average;
    }

    public LevelSalaryDto() {
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LevelSalaryDto(String level, Double high, Double low, Double median, Double average, String height, String color) {
        this.level = level;
        this.high = high;
        this.low = low;
        this.median = median;
        this.average = average;
        this.height = height;
        this.color = color;
    }
}
