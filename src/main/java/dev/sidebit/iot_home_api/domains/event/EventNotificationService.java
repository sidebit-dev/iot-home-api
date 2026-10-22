package dev.sidebit.iot_home_api.domains.event;

import org.postgresql.PGNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EventNotificationService {

    private final List<SseEmitter> emitters = Collections.synchronizedList(new ArrayList<>());
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Autowired
    public EventNotificationService(DataSource dataSource) {
        // Inicia a thread que ficará escutando o PostgreSQL
        executor.submit(() -> {
            try (Connection conn = dataSource.getConnection()) {
                // Desenvelopa a conexão para acessar recursos específicos do PostgreSQL
                org.postgresql.PGConnection pgConn = conn.unwrap(org.postgresql.PGConnection.class);

                try (Statement stmt = conn.createStatement()) {
                    stmt.execute("LISTEN event_insert");
                }

                while (!Thread.currentThread().isInterrupted()) {
                    // Executa uma query dummy ou checa notificações (timeout de 2 segundos)
                    PGNotification[] notifications = pgConn.getNotifications(2000);

                    if (notifications != null) {
                        for (PGNotification notification : notifications) {
                            String jsonPayload = notification.getParameter(); // row_to_json enviado pela trigger
                            broadcast(jsonPayload);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SseEmitter addEmitter() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE); // Sem timeout para demonstração
        this.emitters.add(emitter);

        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> this.emitters.remove(emitter));
        emitter.onError((e) -> this.emitters.remove(emitter));

        return emitter;
    }

    private void broadcast(String data) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        synchronized (emitters) {
            for (SseEmitter emitter : emitters) {
                try {
                    emitter.send(SseEmitter.event().data(data));
                } catch (Exception e) {
                    deadEmitters.add(emitter);
                }
            }
            emitters.removeAll(deadEmitters);
        }
    }}
