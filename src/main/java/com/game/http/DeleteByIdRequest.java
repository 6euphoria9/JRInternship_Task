package com.game.http;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteByIdRequest {
    private Long id;
}
