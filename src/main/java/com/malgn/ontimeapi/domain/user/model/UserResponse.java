package com.malgn.ontimeapi.domain.user.model;

import lombok.Builder;

@Builder
public record UserResponse(String id,
                           String userId,
                           String username) {
}
