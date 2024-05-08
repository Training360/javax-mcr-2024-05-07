package hello;

import java.util.HashMap;
import java.util.Map;

public class EnVagyokASpring {

    public static void main(String[] args) {
        var service = new HelloService();
        var controller = new HelloController(service);

        Map<String, Object> context = new HashMap<>();
        context.put("helloService", service);
        context.put("helloContoller", controller);
    }
}
