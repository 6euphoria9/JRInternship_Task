package com.game.http;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FetchPlayerByIdRequest {
    private Long id;
}
