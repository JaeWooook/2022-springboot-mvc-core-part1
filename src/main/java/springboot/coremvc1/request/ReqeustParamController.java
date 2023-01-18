package springboot.coremvc1.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springboot.coremvc1.HelloData;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class ReqeustParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody //이렇게 하면 String으로 "ok"라는 문자를 http응답 메세지에 넣어서 그대로 반환한다. RestController랑 같은 기능이다.
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        log.info("username={}, age={}", memberName, memberAge);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(//RequestParam의 required의 기본값은 true이다.
            @RequestParam(required = true) String username,//required = true인데 username을 안보내면 요청파라미터 스펙이 안맞아서 bad request를 내려준다.
            @RequestParam(required = false) Integer age) {
        //int는 null값을 넣을 수 없지만 Integer는 null값을 넣을 수 있다.
        //username에 ""빈문자가 들어오면 필수값이 통과가 된다.
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") int age) {
        //defaultValue는 값이 있건 없건 default값이 들어가게 된다.
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        //modelAttribute가 있으면 헬로우데이터를 생성해서 helloData의 getter, setter를 찾는다.
        //파라미터의 값을 알아서 바인딩 해준다.
        //다른 타입의 값을 넣으면, 바인딩익셉션이 나온다.
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData); //toString자동으로 된다. @Data 어노테이션에서는
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        //@ModelAttribute 생략이 가능하다.
        //@RequestParam은 생략이 가능하다. STring, int, Integer같은 단순 타입은 @RequestParam이고,
        // 나머지는 @ModelAttribute를 생략한다. agrument resolver로 지정해둔 타입은 제외다.
        // agrument resolver는 미리 예약된것들이다. HttpRequest같은 것들..
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData); //toString자동으로 된다. @Data 어노테이션에서는
        return "ok";
    }
}
