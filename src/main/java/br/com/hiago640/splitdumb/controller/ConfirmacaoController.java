package br.com.hiago640.splitdumb.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.hiago640.splitdumb.service.PessoaService;

@Controller
public class ConfirmacaoController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/confirmar")
    @ResponseBody
    public ModelAndView confirmarConta(@RequestParam("token") UUID token, RedirectAttributes redirectAttributes) {
        // Verifica se o token é válido e ativa a conta do usuário correspondente
        boolean contaAtivada = pessoaService.ativarConta(token);
        
        if (contaAtivada) {
        	return new ModelAndView("redirect:/");
        } else {
    		redirectAttributes.addFlashAttribute("mensagem", "Não foi possível autenticar usuário");
    		return new ModelAndView("redirect:/mostrarmensagem");
        }
    }
}
