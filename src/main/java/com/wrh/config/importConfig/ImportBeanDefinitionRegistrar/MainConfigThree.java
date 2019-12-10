package com.wrh.config.importConfig.ImportBeanDefinitionRegistrar;

import com.wrh.config.importConfig.ImportSelectorTest.MyImportSelector;
import com.wrh.config.importConfig.importTest.Circle;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:32 2019/12/2 0002
 * @Modified By:
 */
@Import({Circle.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
@Configuration
public class MainConfigThree {
}
