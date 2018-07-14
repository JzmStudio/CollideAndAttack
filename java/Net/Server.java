package Net;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import android.net.wifi.*;
import android.content.Context;

public class Server {
    private Context context;
    private int PORT = 8888;
    private String serverIp = null;

    public Server(Context c){
        context = c;
        sendIpPortToClient();
        Thread thread = new Thread(new UDPSendMessage(PORT));
        thread.start();
    }

    /**
     * 获取wifi状态下当前手机的ip
     * @return 返回的ip类型为String
     */
    public String getIp(){
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }
    /**
     * 将int型地址转换成String
     */
    private String intToIp(int i) {
        return (i & 0xFF ) + "." + ((i >> 8 ) & 0xFF) + "." + ((i >> 16 ) & 0xFF) + "." + ( i >> 24 & 0xFF) ;
    }

    /**
     * 广播发送本地ip与port
     */
    public void sendIpPortToClient(){
        SrvUDPBroadCast srvUDPBroadCast = new SrvUDPBroadCast();
        serverIp = getIp();
        srvUDPBroadCast.startUDP(serverIp,PORT);
    }


}

class UDPSendMessage implements Runnable {
    private int PORT;
    private ArrayList<InetAddress> cltIp = new ArrayList<InetAddress>();
    private ArrayList<Integer> cltPort = new ArrayList<Integer>();
    private byte[] recvBuf = new byte[1000];
    private DatagramPacket recvPacket = new DatagramPacket(recvBuf , recvBuf.length);

    public UDPSendMessage(int port){
        PORT = port;
    }

    /**
     * 循环接收并转发数据
     */
    public void run(){
        while (true) {
            try{
                DatagramSocket server = new DatagramSocket(PORT);
                server.receive(recvPacket);
                InetAddress addr = recvPacket.getAddress();
                int port = recvPacket.getPort();
                if (!exist(addr)){
                    cltIp.add(addr);
                    cltPort.add(port);
                }
                for (InetAddress ip : cltIp) {
                    if (ip != addr) {
                        byte[] sendBuf;
                        sendBuf = recvPacket.getData();
                        DatagramPacket sendPacket = new DatagramPacket(sendBuf , sendBuf.length , addr , port );
                        server.send(sendPacket);
                    }
                }
            }catch (IOException e){e.printStackTrace();}
        }
    }

    /**
     * 检查当前客户端地址是否已保存在cltIp中
     */
    private boolean exist(InetAddress ip){
        for (InetAddress cIp : cltIp) {
            if(cIp == ip) return true;
        }
        return false;
    }

}
