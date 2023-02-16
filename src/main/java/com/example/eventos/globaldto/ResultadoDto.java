package com.example.eventos.globaldto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ResultadoDto <T> {
    private boolean status;
    private String error;
    private T data;

    public static <T> ResultadoDto<T> ok(T data) {
        return ResultadoDto.<T>builder().data(data).status(true).build();
    }

    public static <T> ResultadoDto<T> fail() {
        return ResultadoDto.<T>builder().status(false).build();
    }

    public static <T> ResultadoDto<T> fail(Exception ex) {
        return ResultadoDto.<T>builder().error(ex.getMessage()).status(true).build();
    }
}
