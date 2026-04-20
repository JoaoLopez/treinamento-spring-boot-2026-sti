package bt.uff.ap4.service;

import bt.uff.ap4.modelo.Post;
import bt.uff.ap4.modelo.PostComUsuario;
import bt.uff.ap4.repository.PostRepository;
import bt.uff.ap4.repository.UsuarioRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final Validator validator;

    private final PostRepository postRepository;

    private final UsuarioRepository usuarioRepository;


    @Transactional
    public Post save(Post post){
        assert post.id() == null;

        post = post.withDataPostagem(LocalDateTime.now());
        post = post.withTags(post.tags().stream()
                                 .filter(tag -> tag != null)
                                 .filter(tag -> tag.nome() != null && !tag.nome().isBlank())
                                 .toList());

        final Set<ConstraintViolation<Post>> violations = validator.validate(post);
        if (!violations.isEmpty()) {
            String message = violations.stream()
                                        .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                                        .collect(Collectors.joining(", "));

            throw new IllegalArgumentException(message);
        }

        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post findObjById(long id) {
        return postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Post com id %d não encontrado".formatted(id)));
    }

    @Transactional(readOnly = true)
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public PostComUsuario findObyComUsuarioById(long id){
        val post = findObjById(id);

        val usuario = usuarioRepository.findById(post.usuarioId()).orElseThrow();

        return new PostComUsuario(
                post,
                usuario
        );
    }
}
