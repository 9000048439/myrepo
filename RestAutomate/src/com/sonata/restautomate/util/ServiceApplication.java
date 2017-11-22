package com.sonata.restautomate.util;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServiceApplication extends Frame implements ActionListener {
	String msg="";
	Button b1 = new Button("Generate XML");
	Label l11 = new Label("Enter Services Details", Label.CENTER);
	Label l0 = new Label("ServiceURL:", Label.LEFT);
	Label l1 = new Label("ServiceName:", Label.LEFT);
	Label l2 = new Label("StatusCode:", Label.LEFT);
	Label l31 = new Label("Username:", Label.LEFT);
	Label l32 = new Label("Password:", Label.LEFT);
	Label l4 = new Label("RequestBody:", Label.LEFT);
	Label l41 = new Label("ResponseBody:", Label.LEFT);
	Label l5 = new Label("RequestType:", Label.LEFT);
	Label l6 = new Label("Semester:", Label.LEFT);
	Label l7 = new Label("", Label.RIGHT);
	TextField serviceURL = new TextField(25);
	TextField serviceName = new TextField();
	TextField username = new TextField();
	TextField password = new TextField();
	TextArea requestBody = new TextArea("", 180, 150, TextArea.SCROLLBARS_VERTICAL_ONLY);
	TextArea responseBody = new TextArea("", 180, 150, TextArea.SCROLLBARS_VERTICAL_ONLY);
	Choice requestType = new Choice();
	Choice listOfServices = new Choice();
	Choice statusCode = new Choice();

	public ServiceApplication() {
		addWindowListener(new myWindowAdapter());
		setBackground(Color.cyan);
		setForeground(Color.black);
		setLayout(null);
		add(l0);
		add(l11);
		add(l1);
		add(l2);
		add(l31);
		add(l32);
		add(l4);
		add(l41);
		add(l5);
		add(l6);
		add(l7);
		add(serviceURL);
		add(serviceName);
		add(requestBody);
		add(username);
		add(password);
		add(requestType);
		add(listOfServices);
		add(statusCode);
		add(responseBody);
		add(b1);
		b1.addActionListener(this);
		add(b1);
		requestType.add("GET");
		requestType.add("POST");
		requestType.add("PUT");
		requestType.add("DELETE");
		listOfServices.add("CART");
		listOfServices.add("USERLOGIN");
		listOfServices.add("WISHLIST");
		listOfServices.add("USERCREATION");
		listOfServices.add("ADDUSER");
		listOfServices.add("DELETEUSER");
		statusCode.add("200");
		statusCode.add("201");
		statusCode.add("404");
		statusCode.add("500");
		statusCode.add("415");
		l0.setBounds(25, 40, 90, 20);
		l1.setBounds(25, 65, 90, 20);
		l2.setBounds(25, 90, 90, 20);
		l31.setBounds(25, 140, 90, 20);
		l32.setBounds(25, 160, 90, 20);
		l4.setBounds(25, 185, 90, 20);
		l5.setBounds(25, 260, 90, 20);
		l41.setBounds(25, 330, 90, 20);
		l6.setBounds(25, 290, 90, 20);
		l7.setBounds(25, 260, 90, 20);

		serviceURL.setBounds(120, 40, 170, 20);
		serviceName.setBounds(120, 65, 170, 20);
		requestBody.setBounds(120, 185, 170, 60);
		username.setBounds(120, 140, 170, 20);
		password.setBounds(120, 160, 170, 20);
		requestType.setBounds(120, 260, 100, 20);
		listOfServices.setBounds(120, 290, 100, 20);
	//	listOfServices.setBounds(120, 290, 100, 20);
		statusCode.setBounds(120, 90, 50, 20);
		responseBody.setBounds(120, 330, 170, 60);
		b1.setBounds(120, 400, 120, 30);
	
	}

	public void paint(Graphics g) {
		g.drawString(msg, 200, 450);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Generate XML")) {
			Service service=new Service();
			service.setServiceURL(serviceURL.getText());
			service.setServiceName(serviceName.getText());
			service.setRequestBody(requestBody.getText());
			service.setUsername(username.getText());
			service.setPassword(password.getText());
			service.setStatusCode(statusCode.getItem(statusCode.getSelectedIndex()));
			service.setRequestType(requestType.getItem(requestType.getSelectedIndex()));
			service.setListOfServices(listOfServices.getItem(listOfServices.getSelectedIndex()));
			service.setResponse(responseBody.getText());
			try {
				Main.generateXML(service);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msg = "Services details saved in "+service.getListOfServices()+"!";
			setForeground(Color.red);
		}
	}
 
	public static void main(String g[]) {
		ServiceApplication stu = new ServiceApplication();
		
		Image icon = Toolkit.getDefaultToolkit().getImage("E:\\CodingPractice\\RestAutomate\\icon.gif");
		stu.setIconImage(icon);
		stu.setSize(new Dimension(500, 500));
		stu.setTitle("Enter Services Details");
		stu.setVisible(true);
	}

}

class myWindowAdapter extends WindowAdapter {
	public void windowClosing(WindowEvent we) {
		System.exit(0);
	}
	
}
