package hello;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
@AllArgsConstructor
public class HelloController {

    private HelloService helloService;

//    private static Logger log = LoggerFactory.getLogger(HelloController.class);


//    public HelloController(HelloService helloService) {
//        this.helloService = helloService;
//    }

    @RequestMapping("/api/hello")
    public String hello() {
        log.info("Hello");
        log.debug("Hello from HelloController");
        return helloService.sayHello();
    }
}
