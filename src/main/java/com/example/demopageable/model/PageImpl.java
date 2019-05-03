package com.example.demopageable.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PageImpl<T> implements Page<T> {
    private List<T> content;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private int numberOfElements;
    private SortImpl sort;
    private boolean first;
    private int size;
    private int number;

    @Override
    public boolean hasContent() {
        return content != null && !content.isEmpty();
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return number + 1 < totalPages;
    }

    @Override
    public boolean hasPrevious() {
        return number > 0;
    }

    @Override
    public Pageable nextPageable() {
        return null;
    }

    @Override
    public Pageable previousPageable() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }

    @Override
    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
        return null;
    }

    @Data
    private static class SortImpl {
        private boolean sorted;
        private boolean unsorted;
        private boolean empty;
    }
}
