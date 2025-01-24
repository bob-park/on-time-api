package com.malgn.ontimeapi.domain.user.model;

import lombok.Builder;

@Builder
public record UserResponse(String uniqueId,
                           String userId,
                           String username) {
}
