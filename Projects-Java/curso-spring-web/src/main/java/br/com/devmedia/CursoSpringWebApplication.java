package br.com.devmedia;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
public class CursoSpringWebApplication {

	@Autowired
	private ResourceLoader resource;

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringWebApplication.class, args);
	}

	@Configuration
	public static class MvcConfig extends WebMvcConfigurerAdapter {

		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			// TODO Auto-generated method stub
			super.addViewControllers(registry);

			registry.addRedirectViewController("/", "/devmedia");

		}

	}

	@Bean
	public JasperReportsViewResolver jasperReportsViewResolver() {
		JasperReportsViewResolver resolver = new JasperReportsViewResolver();
		resolver.setPrefix("classpath:/Relatorios/");
		resolver.setViewNames("find*");
		resolver.setSuffix(".jasper");
		resolver.setReportDataKey("JRCollection");
		resolver.setViewClass(JasperReportsMultiFormatView.class);
		resolver.setOrder(0);
		resolver.setExporterParameters(exportParameters());
		resolver.setAttributesMap(jasperParams());
		return resolver;
	}

	private Map<String, Object> jasperParams() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("SUB_REPORT_DIR", "./Relatorios/");
		return map;
	}

	public Map<String, Object> exportParameters(){
		Map<String, Object> map = new HashMap<>();
		map.put("net.sf.jasperreports.engine.export.JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN", "false");
		return map;
	}

	// @Bean
	public ResourceBundleViewResolver propertyViewResolver() {
		ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
		resolver.setBasename("jasper-view");
		resolver.setOrder(0);
		return resolver;
	}

	// @Bean
	public XmlViewResolver xmlViewResolver() {
		XmlViewResolver resolver = new XmlViewResolver();
		resolver.setLocation(resource.getResource("/WEB-INF/jasper-views.xml"));
		resolver.setOrder(0);
		return resolver;
	}

	@Bean
	public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return objectMapper;
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
}
