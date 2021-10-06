public class CustomerOrder {
    private int orderId;
    private String userName;
    private String orderName;
    private double orderPrice;
    private boolean isWarrantyIncluded;
    private double discountPrice;
    private double orderTotal;
    private double warrantyPrice;

    public CustomerOrder() {
    }

    public CustomerOrder(int orderId, String userName, String orderName, double orderPrice, boolean isWarrantyIncluded, double discountPrice, double orderTotal, double warrantyPrice) {
        this.orderId = orderId;
        this.userName = userName;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.isWarrantyIncluded = isWarrantyIncluded;
        this.discountPrice = discountPrice;
        this.orderTotal = orderTotal;
        this.warrantyPrice = warrantyPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public boolean getIsWarrantyIncluded() {
        return isWarrantyIncluded;
    }

    public void setIsWarrantyIncluded(boolean isWarrantyIncluded) {
        this.isWarrantyIncluded = isWarrantyIncluded;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public double getWarrantyPrice() {
        return warrantyPrice;
    }

    public void setWarrantyPrice(double warrantyPrice) {
        this.warrantyPrice = warrantyPrice;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderId=" + orderId +
                ", userName='" + userName + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderPrice=" + orderPrice +
                ", isWarrantyIncluded=" + isWarrantyIncluded +
                ", discountPrice=" + discountPrice +
                ", orderTotal=" + orderTotal +
                ", warrantyPrice=" + warrantyPrice +
                '}';
    }
}
