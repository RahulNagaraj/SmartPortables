public class CustomerOrder {
    private int orderId;
    private String userName;
    private String orderName;
    private double orderPrice;
    private String streetAddress;
    private String cityAddress;
    private String stateAddress;
    private String zipcode;
    private String customerName;
    private String hasWarranty;
    private String creditCardNo;
    private String deliveryMethod;
    private String orderDate;
    private String deliveryDate;
    private String maxOrderCancellationDate;

    public CustomerOrder() {
    }

    public CustomerOrder(
            int orderId,
            String userName,
            String orderName,
            double orderPrice,
            String streetAddress,
            String cityAddress,
            String stateAddress,
            String zipcode,
            String customerName,
            String hasWarranty,
            String creditCardNo,
            String deliveryMethod,
            String orderDate,
            String deliveryDate,
            String maxOrderCancellationDate
    ) {
        this.orderId = orderId;
        this.userName = userName;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.streetAddress = streetAddress;
        this.cityAddress = cityAddress;
        this.stateAddress = stateAddress;
        this.zipcode = zipcode;
        this.customerName = customerName;
        this.hasWarranty = hasWarranty;
        this.creditCardNo = creditCardNo;
        this.deliveryMethod = deliveryMethod;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.maxOrderCancellationDate = maxOrderCancellationDate;
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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }

    public String getStateAddress() {
        return stateAddress;
    }

    public void setStateAddress(String stateAddress) {
        this.stateAddress = stateAddress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String isHasWarranty() {
        return hasWarranty;
    }

    public void setHasWarranty(String hasWarranty) {
        this.hasWarranty = hasWarranty;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getMaxOrderCancellationDate() {
        return maxOrderCancellationDate;
    }

    public void setMaxOrderCancellationDate(String maxOrderCancellationDate) {
        this.maxOrderCancellationDate = maxOrderCancellationDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderId=" + orderId +
                ", userName='" + userName + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderPrice=" + orderPrice +
                ", streetAddress='" + streetAddress + '\'' +
                ", cityAddress='" + cityAddress + '\'' +
                ", stateAddress='" + stateAddress + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", customerName='" + customerName + '\'' +
                ", hasWarranty=" + hasWarranty +
                ", creditCardNo='" + creditCardNo + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", maxOrderCancellationDate='" + maxOrderCancellationDate + '\'' +
                '}';
    }
}
