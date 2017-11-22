package com.sonata.restautomate.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class RootMain {

	public static void main(String[] args) throws Exception {
		InputStream is = null;
		try {
			is = new FileInputStream("test.xml");
		} catch (FileNotFoundException fnfe) {
			createFile("test.xml");
			is = new FileInputStream("test.xml");
		}
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document oldDoc = builder.parse(is);
		Node oldRoot = oldDoc.getDocumentElement();
		Document newDoc = builder.newDocument();
		Element newRoot = newDoc.createElement("newroot");
		newDoc.appendChild(newRoot);
		newRoot.appendChild(newDoc.importNode(oldRoot, true));

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DOMSource domSource = new DOMSource(newDoc);
		Writer writer = new OutputStreamWriter(out);
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.transform(domSource, result);
		writer.flush();

		InputStream isNewXML = new ByteArrayInputStream(out.toByteArray());
	}

	private static void createFile(String fileName) {
		try {

			File file = new File(fileName);

			if (file.createNewFile()) {
				System.out.println("File is created!");
				FileWriter fw = new FileWriter(file);
				fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><services></services>");
				fw.close();
			} else {
				System.out.println("File already exists.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}