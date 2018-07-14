package Net;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Client {
    private String serverIp = null;
    private int serverPort;
    UDPRecieveMessage udpRecieveMessage = null;
    private ArrayList<String> recieveMsg = new ArrayList<String>();

    public Client(){
        getSrvIpPort();
        udpRecieveMessage = new UDPRecieveMessage(serverIp ,serverPort);
        Thread thread = new Thread(udpRecieveMessage);
        thread.start();
    }

    public ArrayList<String> recieveMsg(){
        recieveMsg = udpRecieveMessage.getRecieveMsg();
        return recieveMsg;
    }

    private void getSrvIpPort(){
        CltUDPBroadCast cast = new CltUDPBroadCast();
        cast.startUDP();
        serverIp = cast.getIp();
        serverPort = cast.getPort();
    }

    public void sendMsg(byte[] message){
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress addr = InetAddress.getByName(serverIp);
            DatagramPacket sendPacket = new DatagramPacket(message ,message.length , addr , serverPort);
            client.send(sendPacket);
            client.close();
        }catch (IOException e){e.printStackTrace();}
    }
}


class UDPRecieveMessage implements Runnable {
    private InetAddress srvIp = null;
    private int srvPort;
    private byte[] recvBuf = new byte[1000];
    DatagramSocket server = null;
    private DatagramPacket recvPacket = new DatagramPacket(recvBuf , recvBuf.length);
    private ArrayList<String> recieveMsg = new ArrayList<String>();

    public UDPRecieveMessage(String ip, int port){
        try{
            DatagramSocket server = new DatagramSocket(srvPort);
            srvIp = InetAddress.getByName(ip);
            srvPort = port;
        }catch (UnknownHostException e){e.printStackTrace();}
        catch (SocketException e){e.printStackTrace();}
    }

    public ArrayList<String> getRecieveMsg(){
        return recieveMsg;
    }

    /**
     * 循环接收数据
     */
    public void run(){
        while (true) {
            try{
                server.receive(recvPacket);
                recieveMsg.add(new String(recvPacket.getData()));
            }catch (IOException e){e.printStackTrace();}
        }
    }
}