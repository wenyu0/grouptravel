package hust.shixun.grouptravel.entities;

public class Kanjia {
    private String PTid;
    private String discountList;

    public String getPTid() {
        return PTid;
    }

    public void setPTid(String PTid) {
        this.PTid = PTid;
    }

    public String getDiscountList() {
        return discountList;
    }

    public void setDiscountList(String discountList) {
        this.discountList = discountList;
    }

    public Kanjia(String PTid, String discountList) {
        this.PTid = PTid;
        this.discountList = discountList;
    }

    public Kanjia() {
    }

    @Override
    public String toString() {
        return "Kanjia{" +
                "PTid='" + PTid + '\'' +
                ", discountList='" + discountList + '\'' +
                '}';
    }
}
