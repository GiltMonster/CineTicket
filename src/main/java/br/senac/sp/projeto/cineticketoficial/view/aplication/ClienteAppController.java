package br.senac.sp.projeto.cineticketoficial.view.aplication;

import br.senac.sp.projeto.cineticketoficial.controller.ClienteService;
import br.senac.sp.projeto.cineticketoficial.model.entity.Acesso;
import br.senac.sp.projeto.cineticketoficial.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/app/clientes")
public class ClienteAppController {
    private ClienteService service;

    @Autowired
    public ClienteAppController(ClienteService service) {
        this.service = service;
    }

//    @GetMapping("/search-cliente")
    @GetMapping("/cadastro")
    public String registerCli(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/cadastro";
    }


    @PostMapping("/cadastro")
    public String register(@ModelAttribute Cliente cliente,
                           @RequestParam("senha") String senha) {
        try {
            cliente.setAcesso(new Acesso());
            cliente.getAcesso().setEmailCliente(cliente.getEmail());
            cliente.getAcesso().setSenha(senha);
            this.service.add(cliente);
            return "redirect:/api/clientes";
        } catch (Exception ex) {
            return "redirect:/api/clientes";
        }
    }

    @GetMapping("/search-cliente")
    public String searchCli(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/search-cliente";
    }

    @PostMapping("/search-cliente")
    public String findByEmail(@RequestParam("email") String email, Model model) {
        String result;
        Optional<Cliente> cliente = this.service.getCliente(email);
        if (cliente.isPresent()) {
            model.addAttribute("clienteSearch", cliente);
            result = "clientes/cliente-result.html";
        } else {
            result = "redirect:error.html";
        }
        return result;
    }

}
