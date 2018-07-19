package me.jtx.robinia.payment.wxpay.vo;

import java.math.BigDecimal;

public class Coupon {
    private String id;
    private String type;
    private BigDecimal fee;

    public Coupon(String id, String type, String fee) {
        super();
        this.id = id;
        this.type = type;
        if(fee!=null){
            this.fee = BigDecimal.valueOf(Long.valueOf(fee)).divide(new BigDecimal(100));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

}
