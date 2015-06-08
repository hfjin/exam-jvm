package com.exam.jvm.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.exam.jvm.domain.Trade;
import com.exam.jvm.domain.TradeStatus;

/**
 * <p>
 * TradeDao class.
 * </p>
 * 
 * @author hfjin
 * @version $Id: $Id
 */
@Repository
public class TradeDao {

	/**
	 * 存储交易记录
	 */
	public static Map<String, Trade> tradeStore = new HashMap<String, Trade>();

	/**
	 * 保存交易信息
	 * 
	 * @param trade
	 *            交易信息
	 * @return 交易实体
	 */
	public Trade saveTrade(Trade trade) {
		UUID uuid = UUID.randomUUID();
		trade.setTradeID(uuid.toString());
		trade.setCreateTime(new Date());
		tradeStore.put(trade.getTradeID(), trade);
		return trade;
	}

	/**
	 * 通过交易状态查询交易列表
	 * 
	 * @param tradeStatus
	 *            交易状态
	 * @return 查询交易列表
	 */
	public List<Trade> queryTradesByStatus(final TradeStatus tradeStatus) {
		List<Trade> trades = new ArrayList<>();
		for (Trade item : tradeStore.values()) {
			if (item.getStatus().equals(tradeStatus))
				trades.add(item);
		}
		return trades;
	}
	
	/**
	 * 获取所有交易记录
	 * @return
	 */
	public List<Trade> getAllTrades(){
		List<Trade> trades = new ArrayList<>();
		trades.addAll(tradeStore.values());
		return trades;
	}
}
