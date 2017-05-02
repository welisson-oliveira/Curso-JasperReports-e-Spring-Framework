package br.com.devmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.devmedia.curso.repository.ContatoRepository;

@Controller
@RequestMapping("/jasper")
public class RelatorioController {
	
	@Autowired
	private ContatoRepository repository;
	
	@RequestMapping(value = "/findAllPdf", method = RequestMethod.GET)
	public String getAllPdf(ModelMap model){
		model.addAttribute("JRCollection", repository.findAll());
		model.addAttribute("format","pdf");
//		model.addAttribute("SUB_REPORT_DIR", "./Relatorios/");
//		return "findAll";
		return "findContatos";
	}
	
	@RequestMapping(value = "/findAllHtml", method = RequestMethod.GET)
	public String getAllHtml(ModelMap model){
		model.addAttribute("JRCollection", repository.findAll());
		model.addAttribute("format","html");
//		model.addAttribute("SUB_REPORT_DIR", "./Relatorios/");
//		return "findAll";
		return "findContatos";
	}
	
	@RequestMapping(value = "/findAllCsv", method = RequestMethod.GET)
	public String getAllCsv(ModelMap model){
		model.addAttribute("JRCollection", repository.findAll());
		model.addAttribute("format","csv");
//		model.addAttribute("SUB_REPORT_DIR", "./Relatorios/");
//		return "findAll";
		return "findContatos";
	}
	
	
	
}
