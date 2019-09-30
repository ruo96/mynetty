package com.wrh.instanceoftest;

import com.alibaba.fastjson.JSON;
import com.wrh.instanceoftest.vo.BoundDTO;
import com.wrh.instanceoftest.vo.WarehouseBoundDTO;
import com.wrh.qrcode.CodeInfo;
import com.wrh.qrcode.CodeParserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:38 2019/9/26 0026
 * @Modified By:
 */
@Slf4j
public class TestInstance1 {
    public static void main(String[] args) {

        WarehouseBoundDTO warehouseBoundDTO = new WarehouseBoundDTO();
        warehouseBoundDTO.setName("wrh");
        warehouseBoundDTO.setConnectionSet(new HashSet<BoundDTO>());

        HashSet<BoundDTO> boundDTOS = new HashSet<>();
        BoundDTO b1 = new BoundDTO();
        b1.setName("w1");
        HashSet<String> s1 = new HashSet<>();
        s1.add("s1");
        s1.add("s2");
        s1.add("s3");
        s1.add("s4");
        s1.add("s5");
        b1.setCode(s1);


        BoundDTO b2 = new BoundDTO();
        b2.setName("w2");
        HashSet<String> s2 = new HashSet<>();
        s2.add("s1");
        s2.add("s2");
        s2.add("s3");
        s2.add("s4");
        s2.add("s5");
        b2.setCode(s2);

        boundDTOS.add(b1);
        boundDTOS.add(b2);

        warehouseBoundDTO.setConnectionSet(boundDTOS);

        log.info("===>before warehouseBoundDTO: {}", JSON.toJSONString(warehouseBoundDTO));
        handleNotPackCode(warehouseBoundDTO);
        log.info("===>after warehouseBoundDTO: {}", JSON.toJSONString(warehouseBoundDTO));
    }

    private static void handleNotPackCode(WarehouseBoundDTO req) {

        for(BoundDTO codeInfo : req.getConnectionSet()){
            Iterator<String> it = codeInfo.getCode().iterator();
            while (it.hasNext()) {
                if(!"s6".equals(it.next())){
                    it.remove();
                }
            }
        }

        HashSet<BoundDTO> connectionSet = (HashSet<BoundDTO>) req.getConnectionSet()
                .stream()
                .filter(e->e.getCode().size()>0)
                .collect(Collectors.toSet());
        req.setConnectionSet(connectionSet);
    }
}
