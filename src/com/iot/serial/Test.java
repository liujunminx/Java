package com.iot.serial;

// An highlighted block

import java.util.TooManyListenersException;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import javax.tools.Tool;

public class Test
{
    static SerialPort sp=null;
    public static void main(String[] args){
        try{
            sp=SerialTool.openPort("COM5", 9600);
            if(sp!=null)
            {
                try {
                    //给串口添加事件监听
                    sp.addEventListener(new SerialPortEventListener() {
                        @Override
                        public void serialEvent(SerialPortEvent arg0) {
                            if(arg0.getEventType() == SerialPortEvent.DATA_AVAILABLE) {//数据通知
                                byte[] bytes = SerialTool.read(sp);//从串口读取数据
                                //System.out.println("收到的数据长度："+bytes.length);
                                String data=new String(bytes);
                                System.out.println("收到的数据："+data);
                                SerialTool.sendToPort(sp, data.getBytes());//向串口写入数据
                            }
                        }
                    });
                } catch (TooManyListenersException e) {
                    e.printStackTrace();
                }
                sp.notifyOnDataAvailable(true);//串口有数据监听
                sp.notifyOnBreakInterrupt(true);//中断事件监听
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}


