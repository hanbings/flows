package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Request;
import lombok.RequiredArgsConstructor;

import javax.net.SocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

@RequiredArgsConstructor
public class OAuthSocketFactory extends SocketFactory {
    final Request.Proxy proxy;

    @Override
    public Socket createSocket() {
        return new Socket(new Proxy(proxy.type(), new InetSocketAddress(proxy.host(), proxy.port())));
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        Socket socket = createSocket();
        try {
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            socket.close();
            throw e;
        }
        return socket;
    }

    @Override
    public Socket createSocket(InetAddress address, int port) throws IOException {
        Socket socket = createSocket();
        try {
            socket.connect(new InetSocketAddress(address, port));
        } catch (IOException e) {
            socket.close();
            throw e;
        }
        return socket;
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress clientAddress, int clientPort) throws IOException {
        Socket socket = createSocket();
        try {
            socket.bind(new InetSocketAddress(clientAddress, clientPort));
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            socket.close();
            throw e;
        }
        return socket;
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress clientAddress, int clientPort)
            throws IOException {
        Socket socket = createSocket();
        try {
            socket.bind(new InetSocketAddress(clientAddress, clientPort));
            socket.connect(new InetSocketAddress(address, port));
        } catch (IOException e) {
            socket.close();
            throw e;
        }
        return socket;
    }
}
