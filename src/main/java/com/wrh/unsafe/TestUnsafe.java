package com.wrh.unsafe;

import org.junit.Test;
import sun.misc.Unsafe;
import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

/**
 * @author wuruohong
 * @Classname TestUnsafe
 * @Description TODO
 * @Date 2021/10/19 10:47
 */
public class TestUnsafe {
    @Test
    public void Test11() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);//16 个字节
        byteBuffer.putInt(1);
        byteBuffer.putInt(2);
        byteBuffer.putInt(3);
        byteBuffer.putInt(4);
        byteBuffer.flip();

        System.out.println("byteBuffer.getInt() = " + byteBuffer.getInt());
        System.out.println("byteBuffer.getInt() = " + byteBuffer.getInt());
        System.out.println("byteBuffer.getInt() = " + byteBuffer.getInt());
        System.out.println("byteBuffer.getInt() = " + byteBuffer.getInt());

    }

    @Test
    public void Test31() {
        // 上面的操作使用unsafe进行
        Unsafe unsafe = UnsafeUtils.getUnsafe();
        long address = unsafe.allocateMemory(16);
        unsafe.putInt(address, 1);
        unsafe.putInt(address+4, 2);
        unsafe.putInt(address+8, 3);
        unsafe.putInt(address+12, 4);
        System.out.println("unsafe.getInt(address) = " + unsafe.getInt(address));
        System.out.println("unsafe.getInt(address+4) = " + unsafe.getInt(address+4));
        System.out.println("unsafe.getInt(address+8) = " + unsafe.getInt(address+8));
        System.out.println("unsafe.getInt(address+12) = " + unsafe.getInt(address+12));

    }
    
    @Test
    public void Test48() {
        ByteBuffer heapBuffer = ByteBuffer.allocate(4);
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(4);
        heapBuffer.putInt(1234);
        long address = ((DirectBuffer)directBuffer).address();

        UnsafeUtils.unsafe.copyMemory(heapBuffer.array(), 16, null, address, 4);

        directBuffer.position(0);
        directBuffer.limit(4);

        System.out.println(directBuffer.getInt());

        
    }
}
