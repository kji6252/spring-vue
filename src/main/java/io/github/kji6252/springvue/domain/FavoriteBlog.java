package io.github.kji6252.springvue.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Data
@Entity
@Table(indexes = @Index(columnList = "createdDateTime"))
public class FavoriteBlog {

    @EmbeddedId
    private FavoriteBlogID favoriteBlogID;

    @Embedded
    private Blog blog;

    private LocalDateTime createdDateTime;

}
