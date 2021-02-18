package com.walker.manual.twoot.adapter;

import com.walker.manual.twoot.TwootRepository;
import com.walker.manual.twoot.Twootr;
import com.walker.manual.twoot.database.DatabaseTwootRepository;
import com.walker.manual.twoot.database.DatabaseUserRepository;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/18
 */
public class TwootrServer extends WebSocketServer {

    public static final int STATIC_PORT = 8000;
    public static final int WEBSOCKET_PORT = 9000;

    private static final String USERNAME = "Joe";
    private static final String PASSWORD = "ahc5ez2aiV";
    private static final String OTHER_USERNAME = "John";

    private final TwootRepository twootRepository = new DatabaseTwootRepository();
    private final Twootr twootr = new Twootr(new DatabaseUserRepository(), twootRepository);
    private final Map<WebSocket, WebSocketEndPoint> socketToEndPoint = new HashMap<>();

    public TwootrServer(final InetSocketAddress address) {
        super(address, 1);

        twootr.onRegisterUser(USERNAME, PASSWORD);
        twootr.onRegisterUser(OTHER_USERNAME, PASSWORD);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        socketToEndPoint.put(webSocket, new WebSocketEndPoint(twootr, webSocket));
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        socketToEndPoint.remove(webSocket);
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        WebSocketEndPoint endPoint = socketToEndPoint.get(webSocket);
        try {
            endPoint.onMessage(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onStart() {

    }

    public static void main(String[] args) throws Exception {
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", WEBSOCKET_PORT);
        TwootrServer twootrServer = new TwootrServer(socketAddress);
        twootrServer.start();

        System.setProperty("org.eclipse.jetty.LEVEL", "INFO");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setResourceBase(System.getProperty("user.dir") + "/src/main/webapp");
        context.setContextPath("/");

        ServletHolder staticContentServlet = new ServletHolder("staticContentServlet", DefaultServlet.class);
        staticContentServlet.setInitParameter("dirAllowed", "true");
        context.addServlet(staticContentServlet, "/");

        Server server = new Server(STATIC_PORT);
        server.setHandler(context);
        server.start();
        server.dumpStdErr();
        server.join();
    }
}
