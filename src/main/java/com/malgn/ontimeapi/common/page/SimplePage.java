package com.malgn.ontimeapi.common.page;

import java.util.List;

import lombok.Builder;

@Builder
public record SimplePage<T>(List<T> content,
                            SimplePageable pageable,
                            long total) {
}
