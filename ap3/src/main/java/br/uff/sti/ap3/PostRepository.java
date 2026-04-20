package br.uff.sti.ap3;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    // Busca os 5 posts mais recentes de um usuário específico
    @Query("SELECT * FROM post WHERE usuario_id = :usuarioId ORDER BY data_postagem DESC LIMIT :num_posts")
    List<Post> findLastPostsByUsuarioId(int num_posts, Long usuarioId);
}
