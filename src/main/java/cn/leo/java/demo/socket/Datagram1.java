package cn.leo.java.demo.socket;

import lombok.Getter;

public class Datagram1 {
    @Getter
    public static class Head{
        private int len; //报文长度 ,四个字节
    }
    @Getter
    public static class Body{
        private String body; // n个字节

        public Body(String body) {
            this.body = body;
        }
    }

    private Head head;

    private Body body;

    public Datagram1(Body body) {
        this.body = body;
    }

    public byte[] toByteArr(){
        byte[] bodyBytes = this.body.getBody().getBytes();

        int byteLen = 4 + bodyBytes.length;
        byte[] bytes = new byte[byteLen];

        bytes[0] = (byte)((byteLen >> 24) & 0xFF);
        bytes[1] = (byte)((byteLen >> 16) & 0xFF);
        bytes[2] = (byte)((byteLen >> 8) & 0xFF);
        bytes[3] = (byte)(byteLen & 0xFF);

        int bodyBytesP = 0;
        for (int bytesP = 4; bytesP < byteLen; bytesP++) {
            bytes[bytesP] = bodyBytes[bodyBytesP++];
        }
        return bytes;
    }

    public static void main(String[] args) {
        Body hello = new Body("hello");
        Datagram1 datagram1 = new Datagram1(hello);
        byte[] bytes = datagram1.toByteArr();
        System.out.println(bytes);
    }
}
