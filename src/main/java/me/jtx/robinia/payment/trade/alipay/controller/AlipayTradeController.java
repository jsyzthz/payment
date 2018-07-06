package me.jtx.robinia.payment.trade.alipay.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.jtx.robinia.payment.trade.alipay.model.ExtendParams;
import me.jtx.robinia.payment.trade.alipay.model.GoodsDetail;
import me.jtx.robinia.payment.trade.alipay.model.builder.AlipayTradePayRequestBuilder;
import me.jtx.robinia.payment.trade.alipay.model.result.AlipayF2FPayResult;
import me.jtx.robinia.payment.trade.alipay.service.AlipayTradeService;

@RestController
@RequestMapping("/v1/trade/alipay/f2f")
public class AlipayTradeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlipayTradeController.class);

	@Autowired
	private AlipayTradeService tradeService;

	/**
	 * 
	 * undiscountableAmount
	 * 订单可打折金额，可以配合商家平台配置折扣活动，如果订单部分商品参与打折，可以将部分商品总价填写至此字段，默认全部商品可打折,如果该值未传入,但传入了【订单总金额】,【不可打折金额】
	 * 则该值默认为【订单总金额】- 【不可打折金额】
	 * 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段如果该值未传入，但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
	 * 
	 * @param tradeNo
	 *            (必填)
	 *            商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，需保证商户系统端不能重复，建议通过数据库sequence生成，
	 * @param subject
	 *            (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店消费”
	 * @param totalAmount
	 *            (必填) 订单总金额，单位为元，不能超过1亿元
	 *            ，如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
	 * @param authCode
	 *            (必填) 付款条码，用户支付宝钱包手机app点击“付款”产生的付款条码，条码示例，286648048691290423
	 * @param undiscountableAmount
	 *            (可选，根据需要决定是否使用) 订单可打折金额/订单不可打折金额
	 * @param sellerId
	 *            卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
	 * @param body
	 *            订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品3件共20.00元"
	 * @param operatorId
	 *            商户操作员编号，添加此参数可以为商户操作员做销售统计
	 * @param storeId
	 *            (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
	 * @param providerId
	 *            业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
	 * @return
	 */
	@RequestMapping(value = "/pay", method = { RequestMethod.POST })
	public @ResponseBody String tradePay(@RequestParam("tradeNo") String tradeNo,
			@RequestParam("subject") String subject, @RequestParam("totalAmount") String totalAmount,
			@RequestParam("authCode") String authCode,
			@RequestParam("undiscountableAmount") String undiscountableAmount,
			@RequestParam("sellerId") String sellerId, @RequestParam("body") String body,
			@RequestParam("operatorId") String operatorId, @RequestParam("storeId") String storeId,
			@RequestParam("providerId") String providerId) {
		ExtendParams extendParams = new ExtendParams();
		extendParams.setSysServiceProviderId(providerId);

		// 支付超时，线下扫码交易定义为5分钟
		String timeoutExpress = "5m";

		// 商品明细列表，需填写购买商品详细信息，
		List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
		// 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
		GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxx面包", 1000, 1);
		// 创建好一个商品后添加至商品明细列表
		goodsDetailList.add(goods1);

		// 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件
		GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
		goodsDetailList.add(goods2);

		String appAuthToken = "应用授权令牌";// 根据真实值填写

		// 创建条码支付请求builder，设置请求参数
		AlipayTradePayRequestBuilder builder = new AlipayTradePayRequestBuilder()
				// .setAppAuthToken(appAuthToken)
				.setOutTradeNo(tradeNo).setSubject(subject).setAuthCode(authCode).setTotalAmount(totalAmount)
				.setStoreId(storeId).setUndiscountableAmount(undiscountableAmount).setBody(body)
				.setOperatorId(operatorId).setExtendParams(extendParams).setSellerId(sellerId)
				.setGoodsDetailList(goodsDetailList).setTimeoutExpress(timeoutExpress);

		// 调用tradePay方法获取当面付应答
		AlipayF2FPayResult result = tradeService.tradePay(builder);
		switch (result.getTradeStatus()) {
		case SUCCESS:
			LOGGER.info("支付宝支付成功: )");
			break;

		case FAILED:
			LOGGER.error("支付宝支付失败!!!");
			break;

		case UNKNOWN:
			LOGGER.error("系统异常，订单状态未知!!!");
			break;

		default:
			LOGGER.error("不支持的交易状态，交易返回异常!!!");
			break;
		}
		return null;
	}
}
