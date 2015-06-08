package com.exam.jvm.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.exam.jvm.domain.Trade;
import com.exam.jvm.domain.TradeStatus;
import com.exam.jvm.domain.Trades;
import com.exam.jvm.service.TradeService;

/**
 * 
 * 交易资源类.
 * 
 * @author hfjin
 * @version $Id: $Id
 */
@Path("trades")
public class TradeResource {

	private static final Logger LOGGER = Logger.getLogger(TradeResource.class);

	@Autowired
	private TradeService tradeService;
	
	/**
	 * <p>
	 * getAllTrades.
	 * </p>
	 *获取所有交易记录
	 * 
	 * @return trades 交易列表.
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Trades getAllTrades() {
		final Trades trades = tradeService.getAllTrades();
		return trades;
	}

	/**
	 * <p>
	 * getTradesByStatus.
	 * </p>
	 * 根据交易状态查询交易记录
	 * 
	 * @param tradeStatus
	 *            交易状态.
	 * @return trades 交易列表.
	 */
	@Path("/{tradeStatus}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Trades getTradesByStatus(
			@PathParam("tradeStatus") final TradeStatus tradeStatus) {
		//TradeStatus status = TradeStatus.valueOf(tradeStatus);
		final Trades trades = tradeService.getTradesByStatus(tradeStatus);
		return trades;
	}

	/**
	 * <p>
	 * buildTrade.
	 * </p>
	 * 创建一笔交易
	 * 
	 * @param trade
	 *            交易信息.
	 * @return trade 创建成功的交易.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.TEXT_XML })
	public Trade buildTrade(final Trade trade) {
		return tradeService.buildTrade(trade);
	}
}
