package com.exam.jvm.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


public class Trades implements Serializable {
    private static final long serialVersionUID = -5070487415443208853L;
    private List<Trade> tradeList;

    public Trades() {
        super();
    }

    public Trades(final List<Trade> tradeList) {
        super();
        this.tradeList = tradeList;
    }

    @XmlElement(name = "trade")
    @XmlElementWrapper
    public List<Trade> getTradeList() {
        return tradeList;
    }

    public void setTradeList(final List<Trade> tradeList) {
        this.tradeList = tradeList;
    }

}