package springboot.coremvc1.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
//    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException { 이게 기본?
    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException {
        //httpEntity를 사용하면 스프링이 HTTPbody에 있는것을 문자로 바꿔서 너한테 넣어줄께 이런 코드를 실행 해준다.
        //HttpEntity 컨버터가 동작한다.
        //Http header, body의 정보를 편하게 조회한다. 요청 파라미터(@RequestParam, @MdoelAttribute랑 관계없음)를 조회하는 기능과 관계없다.
        //이걸 사용하면 view를 조회하지 않는다.
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
//        responseWriter.write("ok"); // 반환도 HttpEntity를 이용해서 반환한다.
//        return new HttpEntity<>("ok");//첫번째것에는 body를 넣을 수 있다.
        return new ResponseEntity<>("ok", HttpStatus.CREATED);//HTTP의 상태값을 넣을 수 있다.
        //둘다 모두 HttpEntity를 상속받는다.
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws  IOException {
        // @RequestBody 이것 하나 사용하면 위에것 사용하지 않아도 된다.
        // RequestBdoy가 있으면 ResponseBody도 있다.
        // @ResponseBody를 사용하면 반환 타입을 String으로 해도 http body에 들어간다.
        log.info("messageBody={}", messageBody);

        return "ok";
    }

    //요청 파라미터 조회 기능 : @RequestParam, @ModelAttribute
    //HTTP 메시지 바디를 직접 조회하는 기능 : @RequestBody를 사용한다.
}
