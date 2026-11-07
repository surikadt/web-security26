/*
 * Author: Oleksii Hnidan
 * Project: web-security26 | Lab: 4 | Class: Auditable
 */
package chnu.edu.websecurity26.model;

import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class Auditable {
    private Instant createdAt;
    private String createdBy;
    private Instant modifiedAt;
    private String modifiedBy;
    private long version;

    public void markCreated(String username) {
        Instant now = Instant.now();
        createdAt = now;
        createdBy = username;
        modifiedAt = now;
        modifiedBy = username;
        version = 1;
    }

    public void copyAuditFrom(Auditable source, String username) {
        createdAt = source.createdAt;
        createdBy = source.createdBy;
        modifiedAt = Instant.now();
        modifiedBy = username;
        version = source.version + 1;
    }
}
