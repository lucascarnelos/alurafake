package br.com.alura.AluraFake.infra.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping
    public String home() {
        return """
            <h1>Bem vindo ao teste de java Alura</h1>
            <ul>
                <li><a href="/user/all">Usuários cadastrados</a></li>
                <li><a href="/status/all">Cursos cadastrados</a></li>
            </ul>
            """;
    }
}
