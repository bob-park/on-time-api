package com.malgn.ontimeapi.common.result;

import java.time.LocalDateTime;

public interface CommonResponse {

    static LocalDateTime createdDate() {
        return null;
    }

    static String createdBy() {
        return null;
    }

    static LocalDateTime lastModifiedDate() {
        return null;
    }

    static String lastModifiedBy() {
        return null;
    }

}
