package com.exam.jvm.domain;

public enum TradeStatus {
	TradeFailed(-1),
	WaitingPay(0),
	TradeSuccess(1);
	
    final int value;

    private TradeStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
