package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PortScanner {
    private static final int PORTS_RANGE = 3307;
    private static final Map<Integer, String> POTENTIAL_SERVICES = Map.of(
        80, "HTTP",
        21, "FTP",
        25, "SMTP",
        22, "SSH",
        443, "HTTPS",
        53, "DNS",
        3306, "MySQL Database",
        5432, "PostgreSQL Database",
        3389, "Remote Desktop Protocol (RDP)",
        27017, "MongoDB Database"
    );

    private PortScanner() {
    }

    public static void print() {
        Logger logger = LogManager.getLogger();
        String logPattern = "%-8s %-5s %-40s";

        List<List<String>> found = scan();

        logger.info(String.format(logPattern, "Протокол", "Порт", "Сервис"));

        for (List<String> row : found) {
            String type = row.get(0);
            String port = row.get(1);
            String user = row.get(2);
            logger.info(String.format(logPattern, type, port, user));
        }
    }

    public static List<List<String>> scan() {
        List<List<String>> found = new ArrayList<>();
        for (int port = 0; port < PORTS_RANGE; port++) {
            String user = POTENTIAL_SERVICES.get(port);
            if (isTCPOpen(port)) {
                found.add(List.of("TCP", String.valueOf(port), user != null ? user : ""));
            }
            if (isUDPOpen(port)) {
                found.add(List.of("UDP", String.valueOf(port), user != null ? user : ""));
            }
        }

        return found;
    }

    private static boolean isUDPOpen(int port) {
        try (DatagramSocket upd = new DatagramSocket(port)) {
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }

    private static boolean isTCPOpen(int port) {
        try (ServerSocket tcp = new ServerSocket(port)) {
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }
}
