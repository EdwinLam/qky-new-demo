package cn.ripple.config.security;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.*;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 没有登录时候拦截用
 */
public class MacLoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint,
        InitializingBean {
    // ~ Static fields/initializers
    // =====================================================================================

    // ~ Instance fields
    // ================================================================================================

    private PortMapper portMapper = new PortMapperImpl();

    private PortResolver portResolver = new PortResolverImpl();

    private String loginFormUrl;

    private boolean forceHttps = false;

    private boolean useForward = false;

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     *
     * @param loginFormUrl URL where the login page can be found. Should either be
     * relative to the web-app context path (include a leading {@code /}) or an absolute
     * URL.
     */
    public MacLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        Assert.notNull(loginFormUrl, "loginFormUrl cannot be null");
        this.loginFormUrl = loginFormUrl;
    }

    // ~ Methods
    // ========================================================================================================

    public void afterPropertiesSet() throws Exception {
        Assert.isTrue(
                StringUtils.hasText(loginFormUrl)
                        && UrlUtils.isValidRedirectUrl(loginFormUrl),
                "loginFormUrl must be specified and must be a valid redirect URL");
        if (useForward && UrlUtils.isAbsoluteUrl(loginFormUrl)) {
            throw new IllegalArgumentException(
                    "useForward must be false if using an absolute loginFormURL");
        }
        Assert.notNull(portMapper, "portMapper must be specified");
        Assert.notNull(portResolver, "portResolver must be specified");
    }


    /**
     * Performs the redirect (or forward) to the login form URL.
     */
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // redirect to login page. Use https if forceHttps true
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        StringBuffer sb = new StringBuffer();
        String test="12312";
        sb.append("{\"code\":").append(test)
                .append(",\"message\":\"");
        sb.append("token无效");
        sb.append("\",\"time\":");
        sb.append(new Date().getTime());
        sb.append("}");
        out.write(sb.toString());
        out.flush();
        out.close();
    }

}
