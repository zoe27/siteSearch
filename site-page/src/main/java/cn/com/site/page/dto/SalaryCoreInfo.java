package cn.com.site.page.dto;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: SalaryCoreInfo
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2020-10-09 23:19
 */
public class SalaryCoreInfo {

    private String bounsComp;
    private String baseComp;
    private String totalComp;
    private String baseOfMont;
    private String stockComp;

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

    public SalaryCoreInfo(String bounsComp, String baseComp, String totalComp, String baseOfMont, String stockComp) {
        this.bounsComp = bounsComp;
        this.baseComp = baseComp;
        this.totalComp = totalComp;
        this.baseOfMont = baseOfMont;
        this.stockComp = stockComp;
    }

    public SalaryCoreInfo() {
    }
}
