package com.wrh.collection.list.iteratorTest;

import com.wrh.copy.DeepCopyVo;
import lombok.Data;

import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 12:19 2019/10/11 0011
 * @Modified By:
 */
@Data
public class IteratorVo {

    private String brand;

    private Integer num;

    private List<DeepCopyVo> cars;
}
