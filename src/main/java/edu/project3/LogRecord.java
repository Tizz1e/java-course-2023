package edu.project3;

import java.time.LocalDate;

public record LogRecord(
    String remoteAddr,
    String remoteUser,
    LocalDate localTime,
    String request,
    String status,
    int bodyBytes,
    String httpReferer,
    String httpUserAgent) {
}
