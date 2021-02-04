package data;

public class DrinkResponse {
    int orderID;
    int status;
    int errorCode;
    String errorDesc;

    public DrinkResponse(int orderID, int status) {
        this.orderID = orderID;
        this.status = status;
        this.errorCode = 0;
        this.errorDesc = "";
    }

    public DrinkResponse(int orderID, int status, int errorCode, String errorDesc) {
        this.orderID = orderID;
        this.status = status;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public int getOrderID() {
        return this.orderID;	
    }

    public int getStatus() {
        return this.status;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorDesc() {
        return this.errorDesc;
    }
}
