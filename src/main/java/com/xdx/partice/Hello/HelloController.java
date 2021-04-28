package com.xdx.partice.Hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
/**
 * @author xdxstart
 * @create 2021-04-28 9:15
 */
@Controller
public class HelloController {
        @RequestMapping(value = "say")
        public @ResponseBody String say(){
            return "hollow,springboot";
        }
}
