package br.com.alura.AluraFake.util;

import java.util.List;

public record ListErrorItemDTO(
        String statusCode,
        List<ErrorItemDTO> data
) {


}
