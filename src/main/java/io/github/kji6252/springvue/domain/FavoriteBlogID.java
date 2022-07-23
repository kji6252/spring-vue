package io.github.kji6252.springvue.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Data
@Embeddable
public class FavoriteBlogID implements Serializable {

    private String userName;
    private int blogHashCode;
}
