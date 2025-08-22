package com.example.jlmart;

import java.sql.Date;

public class Payment {
    public int payId;
    public int payAmount;
    public String payCurrency;
    public Date payDate;

    public Payment(int payId, int payAmount, String payCurrency, Date payDate) {
        this.payId = payId;
        this.payAmount = payAmount;
        this.payCurrency = payCurrency;
        this.payDate = payDate;
    }

    public int getPayId() {
        return payId;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public String getPayCurrency() {
        return payCurrency;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public void setPayCurrency(String payCurrency) {
        this.payCurrency = payCurrency;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}
