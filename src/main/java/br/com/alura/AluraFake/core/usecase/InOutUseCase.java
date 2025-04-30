package br.com.alura.AluraFake.core.usecase;

public interface InOutUseCase <IN, OUT> {

    OUT execute(IN in);

}
