/*
 * Author: Oleksii Hnidan
 * Project: web-security26 | Lab: 1 | Class: Track
 */
package chnu.edu.websecurity26.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Track extends Auditable {
    private String id;
    private String title;
    private String artist;
    private String album;
    private Integer durationSeconds;
}
