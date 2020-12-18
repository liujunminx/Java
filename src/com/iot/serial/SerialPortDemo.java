package com.iot.serial;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 串口demo
 */
public class SerialPortDemo extends Thread implements SerialPortEventListener {

    /**
     * 串口通信管理类
     */
    private static CommPortIdentifier portId;

    /**
     * 有效连接枚举
     */
    private static Enumeration<?> portList;

    /**
     * 串口输入流
     */
    private InputStream inputStream;

    /**
     * 串口输出流
     */
    private static OutputStream outputStream;

    /**
     * 串口引用
     */
    private static SerialPort serialPort;

    /**
     * 阻塞队列用来存放读到的数据
     */
    private BlockingQueue<String> msgQueue = new LinkedBlockingDeque<>();

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

        switch (serialPortEvent.getEventType()){
            case SerialPortEvent.BI:
                case SerialPortEvent.OE:
                    case SerialPortEvent.FE:
                        case SerialPortEvent.PE:
                            case SerialPortEvent.CD:
                                case SerialPortEvent.CTS:
                                    case SerialPortEvent.DSR:
                                        case SerialPortEvent.RI:
                                            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                                                break;
                                            case SerialPortEvent.DATA_AVAILABLE:
                                                byte[] readBuffer = null;
                                                int availableBytes = 0;
                                                try {
                                                    availableBytes = inputStream.available();
                                                    while (availableBytes > 0){
                                                        readBuffer = SerialPortDemo.readFromPort(serialPort);
                                                        String needData = printHexString(readBuffer);
                                                        System.out.println(new Date() + "真实收到的数据为：====" + needData);
                                                        availableBytes = inputStream.available();
                                                        msgQueue.add(needData);
                                                    }
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
            default:
                break;

        }
    }

    // 字节数组转字符串
    private String printHexString(byte[] b) {

        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sbf.append(hex.toUpperCase() + "  ");
        }
        return sbf.toString().trim();
    }

    /**
     * 从串口读数据
     * @param serialPort
     * @return
     */
    public static byte[] readFromPort(SerialPort serialPort){
        InputStream in = null;
        byte[] bytes = {};
        try {
            in = serialPort.getInputStream();
            byte[] readerBuffer = new byte[1];
            int bytesNum = in.read(readerBuffer);
            while (bytesNum > 0){
                bytes = MyUtils.concat(bytes, readerBuffer);
                bytesNum = in.read(readerBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null){
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    public int startComPort(){
        // 获取相应串口通信管理类获取当前连接上的串口列表
        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()){
            portId = (CommPortIdentifier) portList.nextElement();

            System.out.println("设备类型：===》" + portId.getPortType());
            System.out.println("设备名称：====》" + portId.getName());
            // 判断端口类型是否是串口
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL){
                    // 判断如果COM4串口存在，就打开串口
                    if (portId.getName().equals(portId.getName())){
                        try {
                            serialPort = (SerialPort) portId.open(portId.getName(), 1000);
                        }catch (PortInUseException e){
                            System.out.println("打开端口失败");
                            e.printStackTrace();
                            return 0;
                        }

                        // 设置当前串口的输入输出流
                        try {
                            inputStream = serialPort.getInputStream();
                            outputStream = serialPort.getOutputStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return 0;
                        }

                        //给当前串口添加一个监听器
                        try {
                            serialPort.addEventListener(this);
                        } catch (TooManyListenersException e) {
                            e.printStackTrace();
                            return 0;
                        }

                        // 设置监听器有效，即：当有数据时通知
                        serialPort.notifyOnBreakInterrupt(true);

                        // 设置串口读写参数
                        try {
                            serialPort.setSerialPortParams(9600,
                                    SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                                    SerialPort.PARITY_NONE);
                        } catch (UnsupportedCommOperationException e) {
                            e.printStackTrace();
                            return 0;
                        }

                        return 1;
                    }
                }

        }
        return 1;
    }
}
