package com.exam.jvm.resource;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exam.jvm.domain.Trade;
import com.exam.jvm.domain.TradeStatus;
import com.exam.jvm.domain.TradeType;
import com.exam.jvm.domain.Trades;

public class TestTradeResource {
	private final static Logger LOGGER = Logger
			.getLogger(TestTradeResource.class);
	public static final String BASE_URI = "http://localhost:8080/webapi/";
	private HttpServer server;
	private WebTarget target;

	@Before
	public void setUp() throws Exception {
		final ResourceConfig rc = new ResourceConfig().packages("com.exam.jvm");
		URI uri = URI.create(TestTradeResource.BASE_URI);
		server = GrizzlyHttpServerFactory.createHttpServer(uri, rc);
		server.start();
		final ClientConfig cc = new ClientConfig();
		final Client client = ClientBuilder.newClient(cc);
		target = client.target(TestTradeResource.BASE_URI).path("trades");
	}

	@After
	public void tearDown() throws Exception {
		if (server != null)
			server.stop();
	}

	Trade buildTrade(final Trade trade) {
		LOGGER.debug("Test build trade");
		final Entity<Trade> tradeEntity = Entity.entity(trade,
				MediaType.APPLICATION_JSON_TYPE);
		final Trade newTrade = target.request(MediaType.APPLICATION_JSON_TYPE)
				.post(tradeEntity, Trade.class);
		Assert.assertNotNull(newTrade.getTradeID());
		LOGGER.debug("build trade " + newTrade.getTradeID());
		return newTrade;
	}

	// public

	/**
	 * unit1: 使用用户张三创建一条86元5角人民币的交易记录，验证交易状态为等待付款，并且交易的金额是正确的。
	 */
	@Test
	public void testBuildTrade() {
		Trade trade = new Trade();
		trade.setCurrency("rmb");
		trade.setAmounts(86.5D);
		trade.setCustomerID(1);
		trade.setCustomerName("张三");
		trade.setType(TradeType.Pay);
		trade.setStatus(TradeStatus.WaitingPay);
		Trade newTrade = buildTrade(trade);
		Assert.assertEquals(newTrade.getStatus(), TradeStatus.WaitingPay);
		Assert.assertEquals(newTrade.getAmounts(), new Double(86.5D));
	}

	/**
	 * unit2: 使用你所习惯的测试框架，初始化3条交易成功信息，2条交易失败信息。验证查询接口按全部状态查询共5条交易，只查询成功的交易是3条，
	 * 只查询失败的交易是2条。
	 */
	@Test
	public void testQueryTrades() {

		// 构造5笔交易
		for (int i = 0; i < 5; i++) {
			Trade trade1 = new Trade();
			trade1.setCurrency("rmb");
			trade1.setAmounts(10D + i);
			trade1.setCustomerID(i + 1);
			trade1.setCustomerName("张" + (i + 1));
			trade1.setType(TradeType.Pay);
			TradeStatus status;
			status = (i % 2 == 0) ? TradeStatus.TradeSuccess
					: TradeStatus.TradeFailed;
			trade1.setStatus(status);
			buildTrade(trade1);
		}

		LOGGER.debug("Test Get All");
		final Invocation.Builder invocationBuilder1 = target.request();
		final Trades allTrades = invocationBuilder1.get(Trades.class);
		LOGGER.debug(allTrades.getTradeList());
		Assert.assertEquals(allTrades.getTradeList().size(), 5);
		LOGGER.debug("size:" + allTrades.getTradeList().size());
		LOGGER.debug("=========");

		LOGGER.debug("Test Query Success Trade");
		final WebTarget querySuccTarget = target.path(TradeStatus.TradeSuccess
				.toString());
		final Invocation.Builder invocationBuilder2 = querySuccTarget
				.request(MediaType.APPLICATION_JSON_TYPE);
		final Trades successTrades = invocationBuilder2.get(Trades.class);
		LOGGER.debug(successTrades);
		Assert.assertEquals(successTrades.getTradeList().size(), 3);
		LOGGER.debug("size:" + successTrades.getTradeList().size());
		LOGGER.debug("=========");

		LOGGER.debug("Test Query Failed Trade");
		final WebTarget queryFailedTarget = target.path(TradeStatus.TradeFailed
				.toString());
		final Invocation.Builder invocationBuilder3 = queryFailedTarget
				.request(MediaType.APPLICATION_JSON_TYPE);
		final Trades failedTrades = invocationBuilder3.get(Trades.class);
		LOGGER.debug(failedTrades);
		Assert.assertEquals(failedTrades.getTradeList().size(), 2);
		LOGGER.debug("size:" + failedTrades.getTradeList().size());
		LOGGER.debug("=========");

	}
}
