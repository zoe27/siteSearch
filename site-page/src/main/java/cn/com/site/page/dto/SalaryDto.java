package cn.com.site.page.dto;


/**
 * Created with IntelliJ IDEA.
 *
 * @title: SalaryDto
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2020-09-26 22:24
 */
public class SalaryDto {

    @Override
	public String toString() {
		return "SalaryDto [company=" + company + ", title=" + title + ", level=" + level + ", yearOfExp=" + yearOfExp
				+ ", yearInCome=" + yearInCome + ", bounsComp=" + bounsComp + ", baseComp=" + baseComp + ", totalComp="
				+ totalComp + ", baseOfMont=" + baseOfMont + ", stockComp=" + stockComp + ", degree=" + degree
				+ ", location=" + location + ", hireType=" + hireType + ", hours=" + hours + ", college=" + college
				+ "]";
	}

	private String company;
    private String title;
    private String level;
    private String yearOfExp;
    private String yearInCome;
    private String bounsComp;
    private String baseComp;
    private String totalComp;
    private String baseOfMont;
    private String stockComp;
    private String degree;
    private String location;
    private String hireType;
    private String hours;
    private String college;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getYearOfExp() {
        return yearOfExp;
    }

    public void setYearOfExp(String yearOfExp) {
        this.yearOfExp = yearOfExp;
    }

    public String getYearInCome() {
        return yearInCome;
    }

    public void setYearInCome(String yearInCome) {
        this.yearInCome = yearInCome;
    }

    public String getBounsComp() {
        return bounsComp;
    }

    public void setBounsComp(String bounsComp) {
        this.bounsComp = bounsComp;
    }

    public String getBaseComp() {
        return baseComp;
    }

    public void setBaseComp(String baseComp) {
        this.baseComp = baseComp;
    }

    public String getTotalComp() {
        return totalComp;
    }

    public void setTotalComp(String totalComp) {
        this.totalComp = totalComp;
    }

    public String getBaseOfMont() {
        return baseOfMont;
    }

    public void setBaseOfMont(String baseOfMont) {
        this.baseOfMont = baseOfMont;
    }

    public String getStockComp() {
        return stockComp;
    }

    public void setStockComp(String stockComp) {
        this.stockComp = stockComp;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHireType() {
        return hireType;
    }

    public void setHireType(String hireType) {
        this.hireType = hireType;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
