package br.senac.sp.projeto.cineticketoficial.view.aplication;

import br.senac.sp.projeto.cineticketoficial.entity.Acesso;
import br.senac.sp.projeto.cineticketoficial.services.AcessoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/app/login")
public class LoginController {
    private final AcessoService service;

    @GetMapping
    public String loginArea(Model model) {
        model.addAttribute("login", new Acesso());
        return "acessos/login";
    }


//    @PostMapping("/login")
//    public ResponseEntity<Object> login(@RequestParam("email") String email,
//                                        @RequestParam("senha") String senha,
//                                        HttpServletRequest request, Model model) {
//        String result;
//
//        if (loginSession == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        HttpSession session = request.getSession();
//        session.setAttribute("loggedCliente", loginSession);
//
//
//        return ResponseEntity.ok(loginSession);
//
//
////        if (acesso != null) {
////            result = "redirect:/api/clientes";
////        } else {
////            result = "não foi possivel logar";

//
//
////        String result;
////        Optional<Acesso> acesso = this.service.getAcesso(email,senha);
////        if (acesso != null) {
////            result = "redirect:/api/clientes";
////        } else {
////            result = "não foi possivel logar";
////        }
////        return result;
//    }


}
