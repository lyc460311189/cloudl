package com.lyc.test.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liyuchun
 * @version 1.0
 * @description: nio 学习 非阻塞io
 * @date 2022/5/13 17:59
 */
public class NioTest {
    //io   输入、输出流   单向  从文件、网络读写数据！
    //nio
    // channel 既可以输入数据、也可以输出数据
    // buffer-->>内存缓冲区，暂存从channel读入的数据！  桥梁


    public static void testNio(){
        //文件 通道   非阻塞
        FileChannel fileChannel ;
        //udp
        DatagramChannel datagramChannel ;
        //tcp  socket都可以用
        SocketChannel socketChannel;
        //专用于 服务器的 channel
        ServerSocketChannel serverSocketChannel;
        //buffer  有很多实现类
        ByteBuffer byteBuffer;
        //选择器----到底选择了啥
        Selector selector;
        //早期采用多线程版   每个socket  开一个线程、、、则cpu消耗大内存占用高
        //线程上线问  切换  成本高；  只适合少数的连接！！！

        //改为线程池 版本
        //阻塞模式下，线程只能处理一个连接  、、、、仅适合短连接的场景！！！

        //连接少的时候用
        selector =null ;
        //channel  通道 是连接，，，  selector是监听器，监听channel的风吹草动
        //channel -> selector -> thread   再到 channel
        //https://www.bilibili.com/video/BV1py4y1E7oA?p=5&spm_id_from=pageDriver  视频讲解网址

        //可连接、可读、可写

    }


    //测试 byteBuffer
    public static void testByteBuffer(){
        //1、读取 输入流  2、RandomAccessFile
        try {
            FileChannel fileChannel= new FileInputStream("d:\\test.txt").getChannel();
            //buffer  准备缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            List<Byte> bytes = new ArrayList<>();
            while (true){
                //从channel读取数据  返回值  意思是读取到的字节数  所以可以吧这个放在while里
                int len = fileChannel.read(byteBuffer);
                if(len == -1){//没有内容了
                    break;
                }
                //打印buffer内容  必须的这行
                byteBuffer.flip();//切换至读模式！！！
                //第一次尝试  这样只能读取10个字节！！！
                while (byteBuffer.hasRemaining()){
                    byte b = byteBuffer.get();//无参每次都读一个字节
                    bytes.add(b);
                    System.out.println((char)b);
                }
                //切换为写模式
                byteBuffer.clear();
            }
            byte[] byteArr = new byte[bytes.size()];
            int i = 0;
            for (byte b:bytes) {
                byteArr[i++] = b ;
            }
            System.out.println(new String(byteArr));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
