package br.com.hiago640.splitdumb.controller.config;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class ControllersSetup {

	private static final Logger logger = LoggerFactory.getLogger(ControllersSetup.class);
	
	@InitBinder
	private void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	} 
	
    public String handleAllExceptions(HttpServletRequest request, Exception exception) {
        // ESQUEMA DO ID DO ERRO
        logger.error("A requisição {} lançou uma {}", request.getRequestURL(), exception);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        logger.error("Stack trace da exceção: {}", sw.toString());
        return "error";
    }
	
}