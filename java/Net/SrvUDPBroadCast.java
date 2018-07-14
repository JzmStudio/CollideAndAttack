package Net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class SrvUDPBroadCast {
    private static final String BROADCAST_IP = "224.224.224.225";
    private static final int BROADCAST_PORT = 8681;
    private  byte[] sendData;
    private boolean isStop = false;
    private MulticastSocket mSocket = null;
    private InetAddress address = null;
    private DatagramPacket dataPacket;

    /**
     * 开始广播
     * @param ip 将要发送给客户端的ip
     * @param port 将要发送给客户端的port
     */
    public void startUDP(String ip, int port) {
        sendData = ("xxxx" + "-" + ip + "-" + port).getBytes();
        new Thread(UDPRunning).start();
    }

    /**
     * 停止广播
     */
    public void stopUDP() {
        isStop = true;
        destroy();
    }

    /**
     * 销毁缓存的数据
     */
    private void destroy() {
        mSocket = null;
        address = null;
        dataPacket = null;
        sendData = null;
    }

    /**
     * 创建udp数据
     */
    private void CreateUDP() {
        try {
            mSocket = new MulticastSocket(BROADCAST_PORT);
            mSocket.setTimeToLive(1);// 广播生存时间0-255，为1时，指定数据报发送到本地局域网网
            address = InetAddress.getByName(BROADCAST_IP);
            mSocket.joinGroup(address);//加入广播接收组
            dataPacket = new DatagramPacket(sendData, sendData.length, address, BROADCAST_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 5秒发送一次广播
     */
    private Runnable UDPRunning = new Runnable() {

        @Override
        public void run() {
            CreateUDP();
            while (!isStop) {
                if (mSocket != null) {
                    try {
                        mSocket.send(dataPacket);
                        Thread.sleep(5 * 1000);// 发送一次停5秒
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };
}
