package training.listproperties;


import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class RemoteAddressFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var remoteAddress = servletRequest.getRemoteAddr();

        MDC.put("remoteAddress", remoteAddress);
        log.info("Remote address: {}", remoteAddress);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
