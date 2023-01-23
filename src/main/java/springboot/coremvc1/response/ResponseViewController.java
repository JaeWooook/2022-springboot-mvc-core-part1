package springboot.coremvc1.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
//@Controller이면서 스트링을 반환하면 이게 view의 논리적이름이된다.
        //@ResponseBody하면 view로 안가고 문자가 그대로 보여진다.
        return "response/hello";
    }

    //권장하지 않음 구분하기 어렵기 때문에
    //컨트롤러의 url이름과 view의 경로이름 같으면 이렇게 할 수 있다.
    @RequestMapping("/response/hello")//경로이름이 requestmapping url과 같으면 return이 없어도된다.
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
    //@ResponseBody가 있으면 뷰 리졸버를 실행하지 않고, HTTP메시지 바디에 직접 "response/hello" 문자가 입력된다.
}
