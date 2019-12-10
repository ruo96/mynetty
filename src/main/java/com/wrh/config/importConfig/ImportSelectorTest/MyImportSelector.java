package com.wrh.config.importConfig.ImportSelectorTest;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:23 2019/12/2 0002
 * @Modified By:
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.wrh.config.importConfig.ImportSelectorTest.Triangle"};
    }
}
