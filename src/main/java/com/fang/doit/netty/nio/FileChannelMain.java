package com.fang.doit.netty.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/5/25 15:55
 */
public class FileChannelMain {

    public static void main(String[] args) {
        FileChannel channel = null;
        try {
            RandomAccessFile file = new RandomAccessFile("D:\\bk.txt", "r");
            channel = file.getChannel();

            //ByteBuffer buffer = ByteBuffer.allocate(1024);
            //int noOfByteRead = channel.read(buffer);
            //String content = new String(buffer.array());
            //System.out.println(noOfByteRead);
            //System.out.println(content);

            // ���ļ����ּ��ص��ڴ�
            MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, 1024);
            if (mappedByteBuffer.hasRemaining()) {
                byte[] bytes = new byte[mappedByteBuffer.remaining()];
                mappedByteBuffer.get(bytes);
                String content = new String(bytes);
                System.out.println(content);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
