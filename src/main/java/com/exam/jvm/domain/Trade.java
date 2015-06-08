package com.exam.jvm.domain;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>Order class.</p>
 *
 * @author hfjin
 * @version $Id: $Id
 */
@XmlRootElement
public class Trade implements Serializable {
    private static final long serialVersionUID = 3749670031307574543L;
    
    private String tradeID;
    private Date createTime;
    /**
     * 交易状态
     * -1：交易失败
     * 0 ：等待付款
     * 1 ：交易成功
     */
    private TradeStatus status = TradeStatus.TradeFailed;
    /**
     * 交易类型
     * 1: 支付
     * 2： 退款
     */
    private TradeType type;
    private Double amounts;
    /**
     * 币种
     */
    private String currency;
    private Integer customerID;
    private String customerName;

    public Trade() {
        super();
    }

    
    @XmlAttribute(name = "tradeID")
    public String getTradeID() {
		return tradeID;
	}



	public void setTradeID(String tradeID) {
		this.tradeID = tradeID;
	}


	@XmlAttribute(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	/**
     * 交易状态
     * -1：交易失败
     * 0 ：等待付款
     * 1 ：交易成功
	 * @return 交易状态
	 */
	@XmlAttribute(name = "status")
	public TradeStatus getStatus() {
		return status;
	}



	public void setStatus(TradeStatus status) {
		this.status = status;
	}


	@XmlAttribute(name = "type")
	public TradeType getType() {
		return type;
	}



	public void setType(TradeType type) {
		this.type = type;
	}


	@XmlAttribute(name = "amounts")
	public Double getAmounts() {
		return amounts;
	}



	public void setAmounts(Double amounts) {
		this.amounts = amounts;
	}


	@XmlAttribute(name = "currency")
	public String getCurrency() {
		return currency;
	}



	public void setCurrency(String currency) {
		this.currency = currency;
	}


	@XmlAttribute(name = "customerID")
	public Integer getCustomerID() {
		return customerID;
	}



	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}


	@XmlAttribute(name = "customerName")
	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
