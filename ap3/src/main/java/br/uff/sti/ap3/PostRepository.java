package br.uff.sti.ap3;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    @Query("SELECT * FROM post WHERE usuario_id = :usuarioId ORDER BY data_postagem DESC LIMIT :num_posts")
    List<Post> findLastPostsByUsuarioId(int num_posts, Long usuarioId);

    @Query("SELECT * FROM post WHERE LOWER(mensagem) LIKE LOWER(:pattern)")
    List<Post> findByMensagemContaining(String pattern);

    @Query("""
        SELECT p.*
        FROM post p JOIN post_tag t ON p.id = t.post_id
        WHERE t.nome = :tagName
        ORDER BY p.data_postagem DESC
        LIMIT :num_posts
    """)
    List<Post> findLastPostsByTagName(int num_posts, String tagName);

    @Query("SELECT * FROM post ORDER BY data_postagem DESC LIMIT :num_posts")
    Stream<Post> streamLastPosts(int num_posts);
}
