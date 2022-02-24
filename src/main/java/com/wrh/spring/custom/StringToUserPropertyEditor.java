package com.wrh.spring.custom;

import com.wrh.spring.custom.service.User;
import org.junit.Test;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

/**
 * @author wuruohong
 * @Classname StringToUserPropertyEditor
 * @Description 这个都可以
 * @Date 2022/2/22 18:03
 */
public class StringToUserPropertyEditor extends PropertyEditorSupport implements PropertyEditor {
    @Override
    public void setAsText(String text) {
        User user = new User();
        user.setName(text);
        this.setValue(user);
    }

    /** 这个类的使用可以不依赖spring*/
    @Test
    public void Test23() {
        StringToUserPropertyEditor propertyEditor = new StringToUserPropertyEditor();
        propertyEditor.setAsText("wrh");

        User user = (User) propertyEditor.getValue();
        System.out.println("user = " + user);
        System.out.println("user.getName() = " + user.getName());
    }
}
