package com.exam.jvm.domain;

public enum TradeType {
	Pay(0), Refund(1);

	final int value;

	private TradeType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
