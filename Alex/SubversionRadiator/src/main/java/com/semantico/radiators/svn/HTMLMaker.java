package com.semantico.radiators.svn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HTMLMaker {
	
	Template htmlPage;
	HashMap<String,Object> dataModel;
	
	//should take in a collection (the model) and it should take in a template
	public HTMLMaker(Collection<FormattedLogEntry> entries) throws IOException, URISyntaxException {
		//A configuration instance for Freemarker.
		Configuration cfg = new Configuration();
		//I am letting it know where the template is stored.
		// move ftl to src/main/resources
		// getClass().getResource("OpenProjects.ftl")
		
		URL resource = getClass().getClassLoader().getResource("OpenProjects.ftl");
		File parentFile = new File(resource.toURI()).getParentFile();
		cfg.setDirectoryForTemplateLoading(parentFile);
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		//Specify an *.ftl template file.
		htmlPage = cfg.getTemplate("OpenProjects.ftl");
		//htmlPage = cfg.getTemplate(new String(getClass().getResource("/resources/OpenProjects.flt").toString()));
		
		//Add the collection of entries to a Map as a data model.
		HashMap<String, Object> model = new HashMap<String,Object>();
		model.put("entries", entries);
		
		dataModel = model;
	}

	//This takes the data-model and the template and merges them together - writes to the output stream specified as an argument. 
	public void fillPage(OutputStream stream) throws TemplateException, IOException {
		Writer out = new OutputStreamWriter(stream);
		htmlPage.process(dataModel,out);
		out.flush();
	}
	
	//This takes the data-model and the template and merges them together - writes to a file.
	public void makeFile() throws IOException, TemplateException {
		File newHTML = new File("/Users/panda/SemanticoRadiators/Alex/SubversionRadiator/svnRad.html");
		FileWriter writer = new FileWriter(newHTML);
		htmlPage.process(dataModel,writer);
		writer.flush();
	}
}