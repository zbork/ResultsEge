import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class ResultsWeb extends Applet implements KeyListener{
	
	TextField txtFam;
	TextField txtSer;
	TextField txtNum;
	TextArea txtArea;
	Button btnGet;
	
	public void init() {
		
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		setSize(350, 400);
		this.addKeyListener(this);
		
		Label lbFam;
		Label lbSer;
		Label lbNum;
		
		lbFam = new Label ("Фамилия: ");
		lbSer = new Label ("Серия:   ");
		lbNum = new Label ("Номер:   ");
		
		txtArea = new TextArea(10, 28);
		txtArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtArea.setEditable(false);
		txtArea.setBackground(Color.WHITE);
		
		txtFam = new TextField("", 30);
		txtFam.addKeyListener(this);
		txtSer = new TextField("", 30);
		txtSer.addKeyListener(this);
		txtNum = new TextField("", 30);
		txtNum.addKeyListener(this);
		
		btnGet = new Button("Узнать");
		btnGet.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayResult();
			}
			
		});
		
		add(lbFam);
		add(txtFam);
		add(lbSer);
		add(txtSer);
		add(lbNum);
		add(txtNum);
		
		add(btnGet);
		
		add(txtArea);
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			displayResult();
		}
		
	}
	
	public void displayResult() {
		String fam = txtFam.getText();
		String ser = txtSer.getText();
		String num = txtNum.getText();
		String result = "";
		try {
			result = new Requester(fam, ser, num).getResult();
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtArea.setText(result);
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
