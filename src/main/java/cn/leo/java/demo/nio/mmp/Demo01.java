package cn.leo.java.demo.nio.mmp;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Demo01 {
    /**
     * mmap
     */
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\mmap_demo.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileChannel channel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        String string = "Hello";
        mappedByteBuffer.put(string.getBytes());
    }
}
