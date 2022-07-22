package io.github.kji6252.springvue.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Entity
@Table(indexes = @Index(columnList = "searchCount"))
public class HotKeyword {

    public HotKeyword(String keyword) {
        this.keyword = keyword;
        this.searchCount = 0L;
    }

    @Id
    private String keyword;

    private Long searchCount;

    public Long incrementSearchCount() {
        return searchCount++;
    }
}
