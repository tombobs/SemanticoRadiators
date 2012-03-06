package com.semantico.radiators.svn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HTMLMaker {
	
	Template htmlPage;
	Collection<FormattedLogEntry> dataModel;
	
	//should take in a collection (the model) and it should take in a template
	public HTMLMaker(Collection<FormattedLogEntry> entries) throws IOException {
		
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("/Users/panda/SemanticoRadiators/Alex/SubversionRadiator/"));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		htmlPage = cfg.getTemplate("OpenProjects.ftl");
		dataModel = entries;
	}

	//This takes the data-model and the template and merges them together - writes to the System output.
	public void fillPage() throws TemplateException, IOException {
		Writer out = new OutputStreamWriter(System.out);
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