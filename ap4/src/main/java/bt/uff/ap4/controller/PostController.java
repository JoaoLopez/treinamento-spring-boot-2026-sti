package bt.uff.ap4.controller;


import bt.uff.ap4.modelo.Post;
import bt.uff.ap4.modelo.PostComUsuario;
import bt.uff.ap4.modelo.Usuario;
import bt.uff.ap4.repository.UsuarioRepository;
import bt.uff.ap4.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final UsuarioRepository usuarioRepository;


    @GetMapping("{id}")
    public ModelAndView get(@PathVariable Long id){
        val mv = new ModelAndView("post/get");

        mv.addObject("post", postService.findObyComUsuarioById(id));

        return mv;
    }

    @GetMapping("new")
    public ModelAndView novo(){
        val mv = new ModelAndView("post/edit");

        mv.addObject("post", new PostComUsuario(
                new Post(null,null,null,null, List.of()),
                new Usuario(null,null,null,0, null)));

        return mv;
    }

    @PostMapping
    public String save(Post post){
        log.info("Olha o post a ser salvo: {}", post);
        val usuario = usuarioRepository.save(new Usuario(
                null,
                "Teste",
                "Teste",
                10,
                "35frg5/f5rf6"
        ));
        val postSalvo = postService.save(post.withUsuarioId(usuario.id()));

        return "redirect:/post/" + postSalvo.id();
    }

//    @GetMapping("{id}/edit")
//    public ModelAndView edit(@PathVariable Long id){
//        val mv = new ModelAndView("post/edit");
//
//        mv.addObject("post", postService.findObyComUsuarioById(id));
//
//        return mv;
//    }


    @GetMapping
    public ModelAndView list(){
        val mv = new ModelAndView("post/list");
        mv.addObject("posts", postService.findAll(Pageable.unpaged()));
        return mv;
    }


}
