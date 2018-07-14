package Net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class CltUDPBroadCast {

    private String serverIp = null;
    private int serverPort = 0;
    private final String BROADCAST_IP = "224.224.224.225";
    private final int BROADCAST_PORT = 8681;
    private byte[] getData = new byte[1024];
    private boolean isStop = false;
    private MulticastSocket mSocket = null;
    private InetAddress address = null;
    private DatagramPacket dataPacket;
    private Thread mUDPThread = null;

    public String getIp(){ return serverIp;}
    public int getPort(){ return serverPort;}

    /**
     * 开始接收广播
     *
     * @param
     */
    public void startUDP() {
        mUDPThread = new Thread(UDPRunning);
        mUDPThread.start();
    }
    /**
     * 停止广播
     * @throws IOException
     */
    public void stopUDP() throws IOException {
        mSocket.leaveGroup(address);
        isStop = true;
        mUDPThread.interrupt();
    }

    /**
     * 创建udp数据
     */
    private void CreateUDP() {
        try {
            mSocket = new MulticastSocket(BROADCAST_PORT);
            mSocket.setTimeToLive(1);// 广播生存时间0-255
            address = InetAddress.getByName(BROADCAST_IP);
            mSocket.joinGroup(address);
            dataPacket = new DatagramPacket(getData, getData.length, address, BROADCAST_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Runnable UDPRunning = new Runnable() {

        @Override
        public void run() {
            CreateUDP();
            while (!isStop) {
                if (mSocket != null) {
                    try {
                        mSocket.receive(dataPacket);
                        String mUDPData = new String(getData, 0, dataPacket.getLength());
                        /**
                         * 确定是否是这个客户端发过来的数据
                         */
                       if (mUDPData != null && "xxxx".equals(mUDPData.split("-")[0])) {
                            serverIp = mUDPData.split("-")[1];
                            serverPort = Integer.parseInt(mUDPData.split("-")[2]);
                            isStop = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };
}