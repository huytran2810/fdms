package org.SWP391_FUHL_SE1825.FDMS.dto.respone;


import java.time.Instant;

public interface RequestRespone {
    Long getId();
    String getType();
    String getName();
    String getBedName();
    String getContent();
    String getStatus();
    String getRespone();
    Instant getCreateAt();
}
