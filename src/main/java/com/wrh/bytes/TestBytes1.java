package com.wrh.bytes;

import com.google.common.primitives.Bytes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:44 2018/10/29 0029
 * @Modified By:
 */
@Slf4j
public class TestBytes1 {

    public static void main(String[] args) {

        String A010 = "0x01, 0x02, 0x03, 0x84, 0x45, 0xA6, 0x17, 0x58";

        byte[] bytes = convertToBytes(A010);
        log.info("bytes:{}",Arrays.toString(bytes));


        byte[] bytes1 = ArrayUtils.subarray(bytes, 0, 4);
        log.info("bytes1:{}",Arrays.toString(bytes1));

        byte[] bytes2 = ArrayUtils.subarray(bytes, 4, bytes.length);
        log.info("bytes2:{}",Arrays.toString(bytes2));

    }

    private static byte[] convertToBytes(String string) {
        String[] strs = StringUtils.split(string, ",");
        List<Byte> list = new ArrayList<>();
        for (String item : strs) {
            String regulate = StringUtils.trimToNull(item);
            if (StringUtils.isNotEmpty(regulate)) {
                regulate = regulate.replace("0x", "");
                list.add((byte) Integer.parseInt(regulate, 16));
            }
        }

        return ArrayUtils.toPrimitive(list.toArray(new Byte[list.size()]));
    }
}
