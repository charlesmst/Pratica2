/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.websocket;

import br.com.empresa.rh.resources.JAXRSServerConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Queue;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author charles
 */
public class FolhaHubResolve {

    private final ObjectMapper objectMapper;
    private final ConcurrentHashMap<String, ReportProgressHub> items = new ConcurrentHashMap<>();

    public FolhaHubResolve() {
        objectMapper = JAXRSServerConfig.getObjectMapper();
    }

    public void add(ReportProgressHub progress) {
        items.put(progress.getId(), progress);
        progress.setResolver(this);

        HubMessage h = new HubMessage();
        h.setAction("add");
        h.setData(progress);
        sendMessage(h);
    }

    public void changeStatusText(ReportProgressHub progress, boolean remove) {
        HubMessage h = new HubMessage();
        h.setAction("statusText");
        h.setData(progress);

        if (remove) {
            items.remove(progress.getId());
            progress.setFinalizado(true);
        }
        sendMessage(h);
    }

    public void changeProgress(ReportProgressHub progress) {
        HubMessage h = new HubMessage();
        h.setAction("progress");
        h.setData(progress);
        sendMessage(h);
    }

    public void resolve(Session session, String message) {
        try {
            HubMessage messageObject = objectMapper.readValue(message, HubMessage.class);
            switch (messageObject.getAction()) {
                case "receiveAll":
                    messageObject.setData(items);
                    sendMessage(session, messageObject);
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(FolhaHubResolve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendMessage(Session session, HubMessage message) {
        try {
            String ser = objectMapper.writeValueAsString(message);
            session.getBasicRemote().sendText(ser);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(FolhaHubResolve.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FolhaHubResolve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendMessage(HubMessage message) {
        try {
            String ser = objectMapper.writeValueAsString(message);
            FolhaHub.sendToAll(ser);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(FolhaHubResolve.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FolhaHubResolve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
