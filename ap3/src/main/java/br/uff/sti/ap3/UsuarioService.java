package br.uff.sti.ap3;

import lombok.AllArgsConstructor;
import lombok.val;
import net.datafaker.Faker;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {
    private static final Faker faker = new Faker();
    private final UsuarioDAO usuarioDAO;

    public List<Usuario> criaUsuarios(int num) {
        val usuarios = new ArrayList<Usuario>();

        for (int i = 0; i < num; i++)
            usuarios.add(criaUmUsuario());

        return usuarios;
    }
        
    @Transactional
    public Usuario criaUmUsuario(){
        return usuarioDAO.save(
            new Usuario(null,
                        faker.credentials().username(),
                        faker.name().fullName(),
                        faker.number().numberBetween(18, 80),
                        faker.avatar().image())
        );
    }

    public void imprimirUsuarios(List<Usuario> usuarios) {
        for (Usuario u : usuarios)
            System.out.println(u);
    }
}
