package com.sonata.restautomate.framework;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class PdfUtils {
	public static void main(String[] args) throws Exception {
		// step 1
		Document document = new Document(new Rectangle(792,850));
		// step 2
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("pdf.pdf"));
		// step 3
		document.open();
		// step 4
		XMLWorkerHelper.getInstance().parseXHtml(writer, document,
				new FileInputStream("F:\\RestAssured\\RestAutomate\\Test-Report\\TestNGreport\\customized-emailable-report.html"));
		// step 5
		document.close();

		System.out.println("PDF Created!");
	}
}
