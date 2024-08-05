package ac.su.loggingcontrol.controller;

import ac.su.loggingcontrol.CustomLogger;
import ac.su.loggingcontrol.domain.CartForm;
import ac.su.loggingcontrol.domain.OrderForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



@RestController("/")
public class LogController {
    // 로그 config에 등록된 클래스 단위에서
    // 실제 로그 기록 매서드 호출 실행 -> 로그 기록
    // 로그를 확인하기 위한 단순한 API (DB 사용 안함)

    @GetMapping
    public void redirectProducts(HttpServletResponse response) throws IOException {
        response.sendRedirect("/products");
    }

    // GET /products    (상품 리스트 조회)
    @GetMapping("/products")
    public ResponseEntity<List<Integer>> getProducts(
            @RequestParam(name = "userId", required = false) String userId,
            HttpServletRequest request
    ) {
        // 랜덤 상품 ID
        List<Integer> products = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            products.add(random.nextInt(100)+1);
        }
        // 로그 기록하기 (상품 하나하나에 대한 노출이 발생했을을 기록해야 함)
        // 로그 스키마는 로그 한 줄의 구성을 가지고 특별한 의미 (로직 및 데이터 분석용) 부여할 수 있도록 구현
        // csv 형태로 다루면 한줄에 여러개의 칼럼 표현 가능
        for (Integer product : products) {
            CustomLogger.logRequest(
                    "l",
                    LocalDateTime.now().toString(),
                    "/products",
                    "GET",
                    userId != null ? userId : "-",
                    "-",
                    product.toString(),
                    "-",
                    "-",
                    "-",
                    // HttpServletRequest에서 추출하는 로직 필요
                    request
            );
        }
        return ResponseEntity.ok(products);
    }

    // GET /products/{productId}    (상품 상세 조회)
    @GetMapping("products/{productId}")
    public ResponseEntity<Integer> getProduct(@PathVariable int productId) {
        // 로그 기록하기 ()
        return ResponseEntity.ok(productId);
    }

    // POST /cart   (상품을 장바구니에 추가)
    @PostMapping("/cart")
    public ResponseEntity<CartForm> addCart(@RequestBody CartForm cartForm) {
        // 로그 기록하기 (상품을 장바구니에 추가)
        return ResponseEntity.ok(cartForm);
    }

    // POST /order  (장바구니에 담긴 상품 주문 또는 상품 즉시 주문)
    @PostMapping("/order")
    public ResponseEntity<OrderForm> addOrder(@RequestBody OrderForm orderForm) {
        // 로그 기록하기 (주문이 발생했을을 기록해야 함)
        return ResponseEntity.ok(orderForm);
    }
}
