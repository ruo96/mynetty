package com.wrh.reference;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wuruohong
 * @Classname TestBoolTest
 * @Description TODO
 * @Date 2021/11/24 17:23
 */
@RunWith(MockitoJUnitRunner.class)
public class TestBoolTest {

    @InjectMocks
    private TestBool testBool;

    @Test
    public void testMethod() {
        testBool.show();
    }

}