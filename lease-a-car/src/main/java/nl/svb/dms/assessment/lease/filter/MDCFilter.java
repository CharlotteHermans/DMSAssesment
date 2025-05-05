package nl.svb.dms.assessment.lease.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MDCFilter implements Filter {

    static final String CORRELATION_ID = "CID";
    static final String KEY_URI = "URI";

    @Override
    public void destroy() {
        // no implementation needed
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpServletRequest) {
            zetKeyInMDC(CORRELATION_ID, httpServletRequest.getHeader(CORRELATION_ID));
            String method = httpServletRequest.getMethod();
            zetKeyInMDC(KEY_URI, (StringUtils.isNotBlank(method) ? method + " " : "") + httpServletRequest.getRequestURI());

            try {
                chain.doFilter(request, response);
            } finally {
                leegMDC();
            }
        }
    }

    protected void zetKeyInMDC(String key, String value) {
        MDC.put(key, value);
    }

    protected void leegMDC() {
        MDC.clear();
    }

    @Override
    public void init(final FilterConfig filterConfig) {
        // no implementation needed
    }
}
