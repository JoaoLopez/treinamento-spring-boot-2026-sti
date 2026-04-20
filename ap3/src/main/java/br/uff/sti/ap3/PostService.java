package br.uff.sti.ap3;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.val;
import net.datafaker.Faker;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class PostService {
    private final ObjectProvider<PostService> selfProvider;
    private static final Faker faker = new Faker();
    private final Validator validator;
    private final PostRepository postRepository;

    public List<Post> criaPosts(int num_posts, List<Usuario> usuarios) {
        val posts = new ArrayList<Post>();

        for (int i = 0; i < num_posts; i++)
            posts.add(criaUmPost(ListUtils.escolherAleatorio(usuarios)));

        return posts;
    }

    public Post criaUmPost(Usuario usuario) {
        val post = new Post(null,
                faker.timeAndDate()
                     .past(30, TimeUnit.DAYS)
                     .atZone(ZoneId.systemDefault())
                     .toLocalDateTime(),
                faker.lorem().paragraph(2),
                usuario.id(),
                Tag.of(faker.verb().base(), 
                       faker.hacker().adjective(), 
                       faker.color().name()));

        return selfProvider.getObject().save(post);
    }

    @Transactional
    public Post save(Post post){
        assert post.id() == null;

        final Set<ConstraintViolation<Post>> violations = validator.validate(post);

        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Objeto inválido: " + violations);
        }

        return postRepository.save(post);
    }

    public List<Post> getUltimosPostsUsuario(int num_posts, Usuario usuario){
        return postRepository.findLastPostsByUsuarioId(num_posts, usuario.id());
    }

    public void imprimirPosts(List<Post> posts) {
        for (Post p : posts)
            System.out.println(p);
    }
}
