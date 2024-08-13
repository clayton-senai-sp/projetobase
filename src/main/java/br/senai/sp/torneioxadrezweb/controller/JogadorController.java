package br.senai.sp.torneioxadrezweb.controller;

import br.senai.sp.torneioxadrezweb.model.Jogador;
import br.senai.sp.torneioxadrezweb.repository.JogadorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/jogador")
public class JogadorController {

    @Autowired
    private JogadorRepository jogadorRepository;

    @GetMapping
    public String listar(Model model){
        model.addAttribute("jogadores", jogadorRepository.findAll());
        return "cadastros/jogador/listagem";
    }

    // Carrega o form-inserir.html
    @GetMapping("/form-inserir")
    public String formInserir(Model model){

        model.addAttribute("jogador", new Jogador());
        return "cadastros/jogador/form-inserir";
    }

    // Método para salvar o jogador
    @PostMapping("/inserir")
    public String inserir(
            @Valid Jogador jogador,
            BindingResult result,
            RedirectAttributes redirectAttributes){

        // Verifica se há erros de validação
        if(result.hasErrors()){
            return "cadastros/jogador/form-inserir";
        }

        jogadorRepository.save(jogador);
        redirectAttributes.addFlashAttribute("mensagem", "Jogador salvo com sucesso!");
        return "redirect:/jogador";
    }

    // Formulário de Alteração dos Jogadores
    @GetMapping("/form-alterar/{id}")
    public String formAlterar(@PathVariable("id") Long id, Model model){

        Jogador jogador = jogadorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Jogador inválido: " + id));

        model.addAttribute("jogador", jogador);
        return "cadastros/jogador/form-alterar";
    }

    // Método para alterar o jogador
    @PostMapping("/alterar")
    public String alterar(
            @Valid Jogador jogador,
            BindingResult result,
            RedirectAttributes redirectAttributes){

        // Verifica se há erros de validação
        if(result.hasErrors()){
            return "cadastros/jogador/form-alterar";
        }

        jogadorRepository.save(jogador);
        redirectAttributes.addFlashAttribute("mensagem", "Jogador alterado com sucesso!");
        return "redirect:/jogador";
    }

    // Método para excluir o jogador
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Jogador jogador = jogadorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Jogador inválido: " + id));
        jogadorRepository.delete(jogador);
        redirectAttributes.addFlashAttribute("mensagem", "Jogador excluído com sucesso!");
        return "redirect:/jogador";
    }
}
