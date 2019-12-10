package com.wrh.config.importConfig.ImportSelectorTest;

import com.wrh.config.importConfig.importTest.Circle;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:24 2019/12/2 0002
 * @Modified By:
 */
@Import({Circle.class, MyImportSelector.class})
@Configuration
public class MainConfigTwo {

}
