package com.genius.webfluxfuncional;

import com.genius.webfluxfuncional.matcher.CustomMatcher;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class WebfluxFuncionalApplicationTests {

    @Test
    public void comparePAth() {
        String requestPath = "/plugin-mobato/servico/resumo/34";
        String contextPath1 = "/plugin-mobato/servico/resumo/{COD_EMPRESA}";
        String contextPath2 = "/plugin-mobato/servico/{SLA}/{COD_EMPRESA}";
        var path = getAffinity(requestPath, contextPath1, contextPath2);
        var a = "";
    }

    private String getAffinity(String requestPath, String contextPath1, String contextPath2) {
//        var comparator = new AntPathMatcher().getPatternComparator(requestPath);
        var comparator = new CustomMatcher(requestPath);
        var c = comparator.compare(contextPath1, contextPath2);
        String path = c > 0 ? contextPath1 : contextPath2;
        return path;
    }
}
