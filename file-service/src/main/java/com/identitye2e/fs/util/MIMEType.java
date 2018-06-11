package com.identitye2e.fs.util;

public enum MIMEType {
    CSV("text/csv"),
    XLS("application/vnd.ms-excel"),
    XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    UNKNOWN("unknown"),
    OCTET_STREAM("application/octet-stream");

    final String mimeType;

    MIMEType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }
}
