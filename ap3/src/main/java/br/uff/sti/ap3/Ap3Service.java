package br.uff.sti.ap3;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class Ap3Service {
    private static final Faker faker = new Faker();
    private final UsuarioService usuarioService;
    private final PostService postService;

    public void exercicio_1(){
        val usuarios = usuarioService.criaUsuarios(5);
        val posts = postService.criaPosts(30, usuarios);
        imprimir_exercicio_1(usuarios, posts);
    }

    private void imprimir_exercicio_1(List<Usuario> usuarios, List<Post> posts){
        System.out.println("\n==== Exercício 1: Insira 30 posts aleatórios de 5 pessoas diferentes na base. ====");
        System.out.println("==== Usuários inseridos ====");
        usuarioService.imprimirUsuarios(usuarios);
        System.out.println("==== Posts inseridos ====");
        postService.imprimirPosts(posts);
        System.out.println("==================================================================================\n");
    }

    public void exercicio_2(){
        long idUsuario = faker.number().numberBetween(1, 6);
        val usuario = usuarioService.find(idUsuario);
        val posts = postService.getUltimosPostsUsuario(5, usuario);

        imprimir_exercicio_2(usuario, posts);
    }

    private void imprimir_exercicio_2(Usuario usuario, List<Post> posts){
        System.out.println("\n==== Exercício 2: Retorne os últimos 5 posts de um usuário. ====");
        System.out.println("==== Usuário selecionado aleatoriamente ====");
        usuarioService.imprimirUmUsuario(usuario);
        System.out.println("==== Últimos 5 posts inseridos ====");
        postService.imprimirPosts(posts);
        System.out.println("==================================================================================\n");
    }

    public void exercicio_3(){
        val posts = postService.getUltimosPostsPorTag(10, "rpg");
        imprimir_exercicio_3(posts);
    }

    private void imprimir_exercicio_3(List<Post> posts){
        System.out.println("\n==== Exercício 3: Retorne os últimos 10 posts que tenham a tag \"rpg\". ====");
        System.out.println("==== Últimos posts com a tag \"rpg\" ====");
        postService.imprimirPosts(posts);
        System.out.println("==================================================================================\n");
    }

    public void exercicio_4(){
        val posts = postService.getPostsPorPalavraChave("orc");
        imprimir_exercicio_4(posts);
    }

    private void imprimir_exercicio_4(List<Post> posts){
        System.out.println("\n==== Exercício 4: Busque todos os posts que tenham as palavras “orc”. ====");
        System.out.println("==== Posts com o pattern \"%orc%\" ====");
        postService.imprimirPosts(posts);
        System.out.println("==================================================================================\n");
    }
}
