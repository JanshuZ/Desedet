package cn.kmbeast;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *Project initiation category
 */
@MapperScan("cn.kmbeast.mapper")
@SpringBootApplication
public class PersonalHealthApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonalHealthApplication.class, args);
    }
}
