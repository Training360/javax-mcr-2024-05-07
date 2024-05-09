package hello.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class EmployeeTemplateController {

    @GetMapping("/hello")
    public ModelAndView sayHello() {
//        var model = new HashMap<String, Object>();
//        model.put("name", "John Doe");
//        model.put("salary", 100000);
        var model = Map.of("name", "John Doe", "salary", 100_000);
        return new ModelAndView("hello", model);
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
