package me.jtx.robinia.payment.trade.alipay.controller;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;

import me.jtx.robinia.payment.trade.alipay.config.Configs;
import me.jtx.robinia.payment.trade.alipay.model.builder.AlipayTradeQueryRequestBuilder;
import me.jtx.robinia.payment.trade.alipay.model.result.AlipayF2FPayResult;
import me.jtx.robinia.payment.trade.alipay.model.result.AlipayF2FPrecreateResult;
import me.jtx.robinia.payment.trade.alipay.model.result.AlipayF2FQueryResult;
import me.jtx.robinia.payment.trade.alipay.model.result.AlipayF2FRefundResult;
import me.jtx.robinia.payment.trade.alipay.service.AlipayTradeService;
import me.jtx.robinia.payment.trade.alipay.service.impl.AlipayTradeServiceImpl;
import me.jtx.robinia.payment.trade.alipay.utils.Utils;
import me.jtx.robinia.payment.trade.alipay.vo.PayPrecreateResponseMessage;
import me.jtx.robinia.payment.trade.alipay.vo.PayPrecreateVO;
import me.jtx.robinia.payment.trade.alipay.vo.PayRefundResponseMessage;
import me.jtx.robinia.payment.trade.alipay.vo.PayRefundVO;
import me.jtx.robinia.payment.trade.alipay.vo.PayResponseMessage;
import me.jtx.robinia.payment.trade.alipay.vo.PayVO;

@RestController
@RequestMapping("/v1/trade/alipay/f2f")
public class AlipayTradeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlipayTradeController.class);

    private static AlipayTradeService tradeService;

    
    static{
        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("zfbinfo.properties");

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
    }
    /**
     * 当面付2.0
     * 
     * @param payVo
     * @return
     */
    @RequestMapping(value = "/pay", method = {RequestMethod.POST})
    public @ResponseBody PayResponseMessage tradePay(@ModelAttribute @Valid PayVO pay, BindingResult validResult) {
        PayResponseMessage responseMessage = new PayResponseMessage();
        responseMessage.setTradeNo(pay.getTradeNo());
        if (validResult.hasErrors()) {
            responseMessage.setErrorMessage(validResult.getFieldError().getDefaultMessage());
            return responseMessage;
        }
        // 调用tradePay方法获取当面付应答
        AlipayF2FPayResult payResult = tradeService.tradePay(pay.getBuilder());
        switch (payResult.getTradeStatus()) {
            case SUCCESS:
                LOGGER.info("支付宝支付成功: )");
                break;

            case FAILED:
                responseMessage.setErrorMessage("FAILED!");
                LOGGER.error("支付宝支付失败!!!");
                break;

            case UNKNOWN:
                responseMessage.setErrorMessage("UNKNOWN!");
                LOGGER.error("系统异常，订单状态未知!!!");
                break;

            default:
                LOGGER.error("不支持的交易状态，交易返回异常!!!");
                responseMessage.setErrorMessage("UNKNOWN!");
                break;
        }
        return responseMessage;
    }

    /**
     * 当面付2.0查询订单
     * 
     * @param tradeNo
     */
    @RequestMapping(value = "/{tradeNo}", method = {RequestMethod.GET})
    public @ResponseBody void tradePay(@PathVariable String tradeNo) {
        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder().setOutTradeNo(tradeNo);

        AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                LOGGER.info("查询返回该订单支付成功: )");

                AlipayTradeQueryResponse response = result.getResponse();
                dumpResponse(response);

                LOGGER.info(response.getTradeStatus());
                if (Utils.isListNotEmpty(response.getFundBillList())) {
                    for (TradeFundBill bill : response.getFundBillList()) {
                        LOGGER.info(bill.getFundChannel() + ":" + bill.getAmount());
                    }
                }
                break;

            case FAILED:
                LOGGER.error("查询返回该订单支付失败或被关闭!!!");
                break;

            case UNKNOWN:
                LOGGER.error("系统异常，订单支付状态未知!!!");
                break;

            default:
                LOGGER.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
    }

    @RequestMapping(value = "/refund", method = {RequestMethod.POST})
    public @ResponseBody PayRefundResponseMessage tradeRefund(@ModelAttribute @Valid PayRefundVO refund,
        BindingResult validResult) {
        PayRefundResponseMessage refundResponseMessage = new PayRefundResponseMessage();
        AlipayF2FRefundResult result = tradeService.tradeRefund(refund.getBuilder());
        switch (result.getTradeStatus()) {
            case SUCCESS:
                LOGGER.info("支付宝退款成功: )");
                break;

            case FAILED:
                LOGGER.error("支付宝退款失败!!!");
                refundResponseMessage.setErrorMessage("支付宝退款失败!!!");
                break;

            case UNKNOWN:
                LOGGER.error("系统异常，订单退款状态未知!!!");
                refundResponseMessage.setErrorMessage("系统异常，订单退款状态未知!!!");
                break;

            default:
                LOGGER.error("不支持的交易状态，交易返回异常!!!");
                refundResponseMessage.setErrorMessage("不支持的交易状态，交易返回异常!!!");
                break;
        }

        return refundResponseMessage;
    }

    @RequestMapping(value = "/precreate", method = {RequestMethod.POST})
    public @ResponseBody PayPrecreateResponseMessage tradePrecreate(@ModelAttribute @Valid PayPrecreateVO pay,
        BindingResult validResult) {
        PayPrecreateResponseMessage precreateResponseMessage = new PayPrecreateResponseMessage();
        precreateResponseMessage.setTradeNo(pay.getTradeNo());
        if (validResult.hasErrors()) {
            precreateResponseMessage.setErrorMessage("Error!");
            return precreateResponseMessage;
        }
        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(pay.getBuilder());
        switch (result.getTradeStatus()) {
            case SUCCESS:
                LOGGER.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);

                // 需要修改为运行机器上的路径
                String filePath = String.format("/Users/sudo/Desktop/qr-%s.png", response.getOutTradeNo());
                LOGGER.info("filePath:" + filePath);
                precreateResponseMessage.setBarcodeFilePath(filePath);
                // ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
                break;

            case FAILED:
                LOGGER.error("支付宝预下单失败!!!");
                precreateResponseMessage.setErrorMessage("支付宝预下单失败!!!");
                break;

            case UNKNOWN:
                LOGGER.error("系统异常，预下单状态未知!!!");
                precreateResponseMessage.setErrorMessage("系统异常，预下单状态未知!!!");
                break;

            default:
                LOGGER.error("不支持的交易状态，交易返回异常!!!");
                precreateResponseMessage.setErrorMessage("不支持的交易状态，交易返回异常!!!");
                break;
        }
        return precreateResponseMessage;
    }

    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            LOGGER.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                LOGGER.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(), response.getSubMsg()));
            }
            LOGGER.info("body:" + response.getBody());
        }
    }

}
