package com.wrh.collection.set;

import lombok.Data;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:37 2019/5/7 0007
 * @Modified By:
 */
@Data
public class Students implements Comparable<Students>{
    private String name;
    private int id;

    @Override
    public int compareTo(Students o) {
        return Integer.compare(o.id,this.id);
    }
}
