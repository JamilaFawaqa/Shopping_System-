package com.example.jlmart;

import java.sql.Date;
import java.time.LocalDate;

public class Purchase {
    public int pNum;
    public int pAmount;
    public int cID;
    public String cName;

    public String productName;
    public Date pDate;

    public Purchase(int pNum, int pAmount, int cID, String cName) {
        this.pNum = pNum;
        this.pAmount = pAmount;
        this.cID = cID;
        this.cName = cName;
    }
    public Purchase(int pNum, int pAmount, String productName, Date pDate) {
        this.pNum = pNum;
        this.pAmount = pAmount;
        this.productName = productName;
        this.pDate = pDate;
    }

    public int getPNum() {
        return pNum;
    }

    public int getPAmount() {
        return pAmount;
    }

    public int getCID() {
        return cID;
    }

    public String getCName() {
        return cName;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
    }

    public void setpAmount(int pAmount) {
        this.pAmount = pAmount;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getProductName() {
        return productName;
    }

    public Date getPDate() {
        return pDate;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPDate(Date pDate) {
        this.pDate = pDate;
    }
}
