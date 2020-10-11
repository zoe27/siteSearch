package cn.com.site.page.vo;

import java.io.Serializable;

public class Salary implements Serializable {
    private Integer id;

    private String company;

    private String title;

    private String level;

    private Float yearOfExp;

    private Float yearInCome;

    private Float bounsComp;

    private Float baseComp;

    private Float totalComp;

    private Float baseOfMonth;

    private Integer stockComp;

    private String degree;

    private String location;

    private Integer hireType;

    private String hours;

    private String college;

    private String coreInfo;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Float getYearOfExp() {
        return yearOfExp;
    }

    public void setYearOfExp(Float yearOfExp) {
        this.yearOfExp = yearOfExp;
    }

    public Float getYearInCome() {
        return yearInCome;
    }

    public void setYearInCome(Float yearInCome) {
        this.yearInCome = yearInCome;
    }

    public Float getBounsComp() {
        return bounsComp;
    }

    public void setBounsComp(Float bounsComp) {
        this.bounsComp = bounsComp;
    }

    public Float getBaseComp() {
        return baseComp;
    }

    public void setBaseComp(Float baseComp) {
        this.baseComp = baseComp;
    }

    public Float getTotalComp() {
        return totalComp;
    }

    public void setTotalComp(Float totalComp) {
        this.totalComp = totalComp;
    }

    public Float getBaseOfMonth() {
        return baseOfMonth;
    }

    public void setBaseOfMonth(Float baseOfMonth) {
        this.baseOfMonth = baseOfMonth;
    }

    public Integer getStockComp() {
        return stockComp;
    }

    public void setStockComp(Integer stockComp) {
        this.stockComp = stockComp;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getHireType() {
        return hireType;
    }

    public void setHireType(Integer hireType) {
        this.hireType = hireType;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours == null ? null : hours.trim();
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    public String getCoreInfo() {
        return coreInfo;
    }

    public void setCoreInfo(String coreInfo) {
        this.coreInfo = coreInfo == null ? null : coreInfo.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Salary other = (Salary) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompany() == null ? other.getCompany() == null : this.getCompany().equals(other.getCompany()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getYearOfExp() == null ? other.getYearOfExp() == null : this.getYearOfExp().equals(other.getYearOfExp()))
            && (this.getYearInCome() == null ? other.getYearInCome() == null : this.getYearInCome().equals(other.getYearInCome()))
            && (this.getBounsComp() == null ? other.getBounsComp() == null : this.getBounsComp().equals(other.getBounsComp()))
            && (this.getBaseComp() == null ? other.getBaseComp() == null : this.getBaseComp().equals(other.getBaseComp()))
            && (this.getTotalComp() == null ? other.getTotalComp() == null : this.getTotalComp().equals(other.getTotalComp()))
            && (this.getBaseOfMonth() == null ? other.getBaseOfMonth() == null : this.getBaseOfMonth().equals(other.getBaseOfMonth()))
            && (this.getStockComp() == null ? other.getStockComp() == null : this.getStockComp().equals(other.getStockComp()))
            && (this.getDegree() == null ? other.getDegree() == null : this.getDegree().equals(other.getDegree()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getHireType() == null ? other.getHireType() == null : this.getHireType().equals(other.getHireType()))
            && (this.getHours() == null ? other.getHours() == null : this.getHours().equals(other.getHours()))
            && (this.getCollege() == null ? other.getCollege() == null : this.getCollege().equals(other.getCollege()))
            && (this.getCoreInfo() == null ? other.getCoreInfo() == null : this.getCoreInfo().equals(other.getCoreInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompany() == null) ? 0 : getCompany().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getYearOfExp() == null) ? 0 : getYearOfExp().hashCode());
        result = prime * result + ((getYearInCome() == null) ? 0 : getYearInCome().hashCode());
        result = prime * result + ((getBounsComp() == null) ? 0 : getBounsComp().hashCode());
        result = prime * result + ((getBaseComp() == null) ? 0 : getBaseComp().hashCode());
        result = prime * result + ((getTotalComp() == null) ? 0 : getTotalComp().hashCode());
        result = prime * result + ((getBaseOfMonth() == null) ? 0 : getBaseOfMonth().hashCode());
        result = prime * result + ((getStockComp() == null) ? 0 : getStockComp().hashCode());
        result = prime * result + ((getDegree() == null) ? 0 : getDegree().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getHireType() == null) ? 0 : getHireType().hashCode());
        result = prime * result + ((getHours() == null) ? 0 : getHours().hashCode());
        result = prime * result + ((getCollege() == null) ? 0 : getCollege().hashCode());
        result = prime * result + ((getCoreInfo() == null) ? 0 : getCoreInfo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", company=").append(company);
        sb.append(", title=").append(title);
        sb.append(", level=").append(level);
        sb.append(", yearOfExp=").append(yearOfExp);
        sb.append(", yearInCome=").append(yearInCome);
        sb.append(", bounsComp=").append(bounsComp);
        sb.append(", baseComp=").append(baseComp);
        sb.append(", totalComp=").append(totalComp);
        sb.append(", baseOfMonth=").append(baseOfMonth);
        sb.append(", stockComp=").append(stockComp);
        sb.append(", degree=").append(degree);
        sb.append(", location=").append(location);
        sb.append(", hireType=").append(hireType);
        sb.append(", hours=").append(hours);
        sb.append(", college=").append(college);
        sb.append(", coreInfo=").append(coreInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Salary( String company, String title, String level, Float yearOfExp, Float yearInCome, Float bounsComp, Float baseComp, Float totalComp, Float baseOfMonth, Integer stockComp, String degree, String location, Integer hireType, String hours, String college, String coreInfo) {
        this.company = company;
        this.title = title;
        this.level = level;
        this.yearOfExp = yearOfExp;
        this.yearInCome = yearInCome;
        this.bounsComp = bounsComp;
        this.baseComp = baseComp;
        this.totalComp = totalComp;
        this.baseOfMonth = baseOfMonth;
        this.stockComp = stockComp;
        this.degree = degree;
        this.location = location;
        this.hireType = hireType;
        this.hours = hours;
        this.college = college;
        this.coreInfo = coreInfo;
    }

    public Salary() {
    }
}