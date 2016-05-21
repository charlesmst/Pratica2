/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.websocket;

import br.com.empresa.rh.filter.SecurityFilter;
import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.model.Evento;
import com.sun.javafx.scene.traversal.Hueristic2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author charles
 */
@ServerEndpoint(value = "/hub/folha")

public class FolhaHub {

    private static final Queue<Session> usuariosConectados = new ConcurrentLinkedQueue<Session>();
    public static final FolhaHubResolve folhaHub = new FolhaHubResolve();

    @OnOpen
    public void open(Session session) {
        try {
            String auth = session.getQueryString().substring("Authorization=".length());
            SecurityFilter.Authorizer a = SecurityFilter.decodeAuthorize(auth, true);
            if (a.isUserInRole(NivelAcesso.ADMIN) || a.isUserInRole(NivelAcesso.RH)) {
                usuariosConectados.add(session);
            }
        } catch (Exception e) {
            Logger.getLogger(FolhaHub.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @OnError
    public void error(Session session, Throwable t) {
        usuariosConectados.remove(session);
    }

    @OnClose
    public void closedConnection(Session session) {
        usuariosConectados.remove(session);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        final Session s = session;
        final String mes = message;
        new Thread(new Runnable() {

            @Override
            public void run() {
                  folhaHub.resolve(s,mes);
            }
        }).start();
    }

    public static void sendToAll(String message) throws IOException {
        ArrayList<Session> closedSessions = new ArrayList<Session>();
        for (Session session : usuariosConectados) {
            if (!session.isOpen()) {
                closedSessions.add(session);
            } else {
                session.getBasicRemote().sendText(message);
            }
        }
        usuariosConectados.removeAll(closedSessions);
    }
}
