package com.wrh.config.importConfig.importTest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:18 2019/12/2 0002
 * @Modified By:
 */
@Import(Circle.class)
@Configuration
public class MainConfig {

}
