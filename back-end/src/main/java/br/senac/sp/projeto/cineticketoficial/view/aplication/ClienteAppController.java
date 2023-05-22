package br.senac.sp.projeto.cineticketoficial.view.aplication;

import br.senac.sp.projeto.cineticketoficial.DTO.CadastroDTO;
import br.senac.sp.projeto.cineticketoficial.entity.Cliente;
import br.senac.sp.projeto.cineticketoficial.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/app/clientes")
public class ClienteAppController {
    private final ClienteService service;

    @GetMapping()
    public String registerCli(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/cadastro";
    }


    @PostMapping("/cadastro")
    public String formRegistro(@ModelAttribute CadastroDTO cadastroDTO) {
        this.service.inserirCliente(cadastroDTO);
        return "redirect:/api/clientes";
    }

    @GetMapping("/search-cliente")
    public String searchCli(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/search-cliente";
    }

    @PostMapping("/search-cliente")
    public String findByEmail(@RequestParam("email") String email, Model model) {
        String result;
        Cliente cliente = this.service.buscarClientePorEmail(email);
        if (cliente != null) {
            model.addAttribute("cliente", cliente);
            result = "clientes/cliente-result.html";
        } else {
            result = "redirect:error.html";
        }
        return result;
    }

}
