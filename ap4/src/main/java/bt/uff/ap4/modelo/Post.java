package bt.uff.ap4.modelo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Table(name="post", schema = "ap4")
@With
public record Post(
        @Id Long id,

        @NotNull(message = "Data de postagem é obrigatória")
        LocalDateTime dataPostagem,

        @NotBlank(message = "Mensagem é obrigatória")
        @Size(max=2000)
        String mensagem,

        @NotNull(message = "Usuário é obrigatório")
        Long usuarioId,

        @MappedCollection(idColumn = "post_id", keyColumn = "ordem")
        List<Tag> tags
) {
}
