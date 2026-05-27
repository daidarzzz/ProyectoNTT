package com.learnhub.order.application.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record OrderRequest(
    @NotEmpty List<Long> cursoIds
) {}
