package br.uff.sti.ap3;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class Ap3Service {
    private final UsuarioService usuarioService;
    private final PostService postService;

    public void exercicio_1(){
        val usuarios = usuarioService.criaUsuarios(5);
        val posts = postService.criaPosts(30, usuarios);
        imprimir_exercicio_1(usuarios, posts);
    }

    public void imprimir_exercicio_1(List<Usuario> usuarios, List<Post> posts){
        System.out.println("==== Exercício 1: Insira 30 posts aleatórios de 5 pessoas diferentes na base. ====");
        System.out.println("==== Usuários inseridos ====");
        usuarioService.imprimirUsuarios(usuarios);
        System.out.println("==== Posts inseridos ====");
        postService.imprimirPosts(posts);
        System.out.println("==================================================================================");
    }
}
