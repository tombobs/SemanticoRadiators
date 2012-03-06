package com.semantico.radiators.svn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
	public HTMLMaker(Collection<FormattedLogEntry> entries) throws IOException {
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("/Users/panda/SemanticoRadiators/Alex/SubversionRadiator/"));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		htmlPage = cfg.getTemplate("OpenProjects.ftl");
		
		HashMap<String, Object> model = new HashMap<String,Object>();
		model.put("entries", entries);
		
		dataModel = model;
	}

	//This takes the data-model and the template and merges them together - writes to the System output.
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