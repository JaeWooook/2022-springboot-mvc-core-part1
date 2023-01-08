package springboot.coremvc1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //이거 하나 넣으면 log팩토리를 불러주지 않아도 된다.
@RestController//레스트컨트롤러로 하면 문자를 반환하면 해당 문자를 바로 body에 넣어서 보여준다.
public class LogTestControlller {

//    private final Logger log = LoggerFactory.getLogger(getClass());//현재 클래스를 지정한다.

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

//        log.info(" info log="+ name); 이런식으로 + 으로 사용하면 안된다.
        // info인 경우에는 상관 없지만 트레이스인 경우 debug레벨로 해놓은 경우에는 쓰지도 않는데 문자의 더하기가 일어나면서
        // 문자의 더하기 연산을 사용한다. 쓸데없는 리소스가 사용된다.
        // {} 이렇게 하면 param만 넘기기 때문에 메서드를 사용하는 것이라서 상관없다.


        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);// {}이것 안에 name값이 들어간다.
        log.warn(" warn log={}", name);
        log.error("error log={}", name);
//로그를 찍을 때 정보를 주게 된다.
        return "ok";
    }
}
