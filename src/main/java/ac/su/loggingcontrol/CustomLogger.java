package ac.su.loggingcontrol;

import ac.su.loggingcontrol.controller.LogController;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLogger {
    // 선언된 로그 타입에 맞는 로거 생성
    public static Logger logger = LogManager.getLogger(LogController.class);

    public static void logRequest(
            String logType,     // logType : 요청 유형 (이니셜로 쓰면 좋음)
            String logTime,
            String url,         // url : 요청 엔드포인트
            String method,      // method : HTTP METHODS (GET/POST/PUT/PATCH/DELETE)
            String userId,      // userId : -
            String transactionId,   // transactionId : 요청 고유값 (nullable)
            String productId,   // productId : 상품 고유번호 (not null)
            String cartId,      // cartId : 카트 번호
            String orderId,     // orderId : 주문 번호
            String payload,     // payload : 수량 등 기타 데이터
            HttpServletRequest request
            /*String clientIp,    // clientIp : 고객 IP
            String userAgent,   // userAgent : 브라우저 등 요청에 사용된 SW 정보
            String referrer     // referrer : 직전 페이지 URL*/
    ) {
        logger.info(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s", // 컬럼 개수 선언
                        logType,
                        logTime,
                        url,
                        method,
                        userId,
                        transactionId,
                        productId,
                        cartId,
                        orderId,
                        payload,
                        request.getRemoteAddr(),
                        request.getHeader("User-Agent"),
                        request.getHeader("Referrer")
                        /*clientIp,
                        userAgent,
                        referrer*/
                )
        );
    }
}
