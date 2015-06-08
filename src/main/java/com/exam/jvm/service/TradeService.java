package com.exam.jvm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.jvm.dao.TradeDao;
import com.exam.jvm.domain.Trade;
import com.exam.jvm.domain.TradeStatus;
import com.exam.jvm.domain.Trades;

@Service
/**
 * <p>TradeService class.</p>
 *
 * @author hfjin
 * @version $Id: $Id
 */
public class TradeService {

	private static final Logger LOGGER = Logger.getLogger(TradeService.class);

	@Autowired
	private TradeDao tradeDao;

	/**
	 * <p>
	 * buildTrade.
	 * </p>
	 * 创建一笔交易
	 * 
	 * @param trade
	 *            交易信息.
	 * @return trade 创建完成的交易.
	 */
	public Trade buildTrade(final Trade trade) {
		return tradeDao.saveTrade(trade);
	}

	/**
	 * 通过交易状态查询交易列表
	 * 
	 * @param tradeStatus
	 *            交易状态
	 * @return 查询交易列表
	 */
	public Trades getTradesByStatus(final TradeStatus tradeStatus) {
		try {
			List<Trade> tradeList = tradeDao.queryTradesByStatus(tradeStatus);
			return new Trades(tradeList);
		} catch (final Exception e) {
			LOGGER.error(e);
			return null;
		}
	}
	
	/**
	 * 获取所有交易记录
	 * @return
	 */
	public Trades getAllTrades(){
		List<Trade> tradeList = tradeDao.getAllTrades();
		return new Trades(tradeList);
	}
}
