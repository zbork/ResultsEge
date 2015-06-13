
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.http.client.ClientProtocolException;


public class Main extends JFrame implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	public JPanel jp = new JPanel();
	public JPanel jp1 = new JPanel();
	public JPanel jp2 = new JPanel();
	public JLabel jl1 = new JLabel("Фамилия");
	public JLabel jl2 = new JLabel("Серия");
	public JLabel jl3 = new JLabel("Номер");	
	public JTextField jt1 = new JTextField("", 20);
	public JTextField jt2 = new JTextField("", 20);
	public JTextField jt3 = new JTextField("", 20);
	public JButton jb = new JButton("Узнать результат");
	public JTextArea jta = new JTextArea("");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main() .setVisible(true);
	}
	
	private Main() {
		super("Результаты");
		setSize(300, 350);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		jp1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		jp1.add(jl1);
		jp1.add(jt1);
		jt1.setActionCommand("input");
		jp1.add(jl2);
		jp1.add(jt2);
		jt2.setActionCommand("input");
		jp1.add(jl3);
		jp1.add(jt3);
		jt3.setActionCommand("input");
		jp1.add(jb);
		
		jb.addActionListener(this);
		jt1.addKeyListener(this);
		jt2.addKeyListener(this);
		jt3.addKeyListener(this);
		
		add(jp1);
	
		jp2.add(jta);
		add(jp2);
	}

	public void actionPerformed(ActionEvent e) {
		displayResults();
	}
	
	public void displayResults () {
		String fam = jt1.getText();
		String ser = jt2.getText();
		String num = jt3.getText();
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
		jta.setText(result);
		jta.setFont(new Font("Monospaced", Font.PLAIN, 18));		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("keyreleased");
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			displayResults();
	}
}
