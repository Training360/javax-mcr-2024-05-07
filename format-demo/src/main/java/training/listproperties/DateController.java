package training.listproperties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@Slf4j
public class DateController {

    @RequestMapping("/")
    public ModelAndView get() {
        log.info("Get page");
        return new ModelAndView("sample", Map.of("number", 1_234_567,"now", LocalDateTime.now()));
    }
}
