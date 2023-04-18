package br.senac.sp.projeto.cineticketoficial.view.aplication;

import br.senac.sp.projeto.cineticketoficial.controller.AcessoService;
import br.senac.sp.projeto.cineticketoficial.model.UserSession;
import br.senac.sp.projeto.cineticketoficial.model.entity.Acesso;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/app")
public class LoginController {

    private AcessoService service;

    @Autowired
    public LoginController(AcessoService service) {
        this.service = service;
    }

    @GetMapping
    public String loginArea(Model model) {
        model.addAttribute("login", new Acesso());
        return "acessos/login";
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestParam("email") String email,
                                        @RequestParam("senha") String senha,
                                        HttpServletRequest request, Model model) {
        String result;
        UserSession loginSession = this.service.getAcesso(email,senha);

        if (loginSession == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        HttpSession session = request.getSession();
        session.setAttribute("loggedCliente", loginSession);


        return ResponseEntity.ok(loginSession);


//        if (acesso != null) {
//            result = "redirect:/api/clientes";
//        } else {
//            result = "não foi possivel logar";












//        String result;
//        Optional<Acesso> acesso = this.service.getAcesso(email,senha);
//        if (acesso != null) {
//            result = "redirect:/api/clientes";
//        } else {
//            result = "não foi possivel logar";
//        }
//        return result;
    }


}
