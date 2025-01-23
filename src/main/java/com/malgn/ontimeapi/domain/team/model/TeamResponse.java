package com.malgn.ontimeapi.domain.team.model;

import com.malgn.common.model.CommonResponse;

public interface TeamResponse extends CommonResponse {

    Long id();

    String name();

    String description();

}
