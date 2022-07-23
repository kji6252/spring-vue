package io.github.kji6252.springvue.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Embeddable
public class Blog {
    private String title;
    private String description;
    private String url;
    private String blogName;
    private LocalDate createdDate;
}
