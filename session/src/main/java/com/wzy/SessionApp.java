package com.wzy;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Controller
@Slf4j
@MapperScan({"com.com.com.com.wzy"})
public class SessionApp {

    public static void main( String[] args )
    {
        SpringApplication.run(SessionApp.class);
    }

}
