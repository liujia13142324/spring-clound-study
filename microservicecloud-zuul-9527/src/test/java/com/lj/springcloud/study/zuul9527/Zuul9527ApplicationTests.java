package com.lj.springcloud.study.zuul9527;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

@SpringBootTest
class Zuul9527ApplicationTests {

    @Test
    void contextLoads() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'");
        String message = (String) exp.getValue();
        System.out.println(message);
        System.out.println(parser.parseExpression("'OK'").getValue());
    }

    @Component
    enum Status{
        OK;
    }


}
