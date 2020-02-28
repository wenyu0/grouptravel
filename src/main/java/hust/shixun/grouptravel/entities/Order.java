package hust.shixun.grouptravel.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


public class Order {
    private int orderId;
    private Date createTime;
    private int productId;
    private int userId;
    private double orderPrice;
    private Date payTime;
    private int status;
    private int PTid;
    private int PNum;
    private double currentDiscount;
    private Date travelTime;//产品出发时间
    private int notesId;

    public Order() {

    }

    public Order(int orderId, Date createTime, int productId, int userId, double orderPrice, Date payTime, int status, int PTid, int pNum, double currentDiscount, Date travelTime, int notesId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.productId = productId;
        this.userId = userId;
        this.orderPrice = orderPrice;
        this.payTime = payTime;
        this.status = status;
        this.PTid = PTid;
        this.PNum = pNum;
        this.currentDiscount = currentDiscount;
        this.travelTime = travelTime;
        this.notesId = notesId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPTid() {
        return PTid;
    }

    public void setPTid(int PTid) {
        this.PTid = PTid;
    }

    public int getPNum() {
        return PNum;
    }

    public void setPNum(int PNum) {
        this.PNum = PNum;
    }

    public double getCurrentDiscount() {
        return currentDiscount;
    }

    public void setCurrentDiscount(double currentDiscount) {
        this.currentDiscount = currentDiscount;
    }

    public Date getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Date travelTime) {
        this.travelTime = travelTime;
    }

    public int getNotesId() {
        return notesId;
    }

    public void setNotesId(int notesId) {
        this.notesId = notesId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", createTime=" + createTime +
                ", productId=" + productId +
                ", userId=" + userId +
                ", orderPrice=" + orderPrice +
                ", payTime=" + payTime +
                ", status=" + status +
                ", PTid=" + PTid +
                ", PNum=" + PNum +
                ", currentDiscount=" + currentDiscount +
                ", travelTime=" + travelTime +
                ", notesId=" + notesId +
                '}';
    }
}
