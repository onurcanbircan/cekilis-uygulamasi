import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.ActionEvent;

public class Cekilis extends JFrame {

	private String dosyaYolu = "";
	private ArrayList<String> katilanlar = new ArrayList<String>();
	private Set<String> kazananlar = new TreeSet<String>();
	private DefaultListModel model = new DefaultListModel();
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfAramaCubugu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cekilis frame = new Cekilis();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void cekilisYap()
	{
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dosyaYolu),"ISO-8859-9")))
		{
			
			String kisi;
			
			while ((kisi = reader.readLine()) != null)
			{
				
				katilanlar.add(kisi);
				
			}
			
		} 
		
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(kazananlar.size() != 10)
		{
			Random random = new Random();
			int kazananIndex = random.nextInt(katilanlar.size());
			
			kazananlar.add(katilanlar.get(kazananIndex));
		}
		
	}
	
	
	public void alkisEkle() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
	    AudioInputStream stream = AudioSystem.getAudioInputStream(new File("alkış.wav"));
	    Clip clip = AudioSystem.getClip();
	    clip.open(stream);
	    clip.start();
	}

	
	/**
	 * Create the frame.
	 */
	public Cekilis() {
		
		setTitle("Çekiliş Uygulaması");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 255));
		panel.setBounds(0, 0, 434, 323);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tfAramaCubugu = new JTextField();
		tfAramaCubugu.setEditable(false);
		tfAramaCubugu.setBounds(10, 11, 322, 20);
		panel.add(tfAramaCubugu);
		tfAramaCubugu.setColumns(10);
		
		JButton btnGozat = new JButton("Gözat");
		btnGozat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				JFileChooser fileChooser = new JFileChooser();
				int i = fileChooser.showOpenDialog(btnGozat);
				
				if (i == JFileChooser.APPROVE_OPTION)
				{
					
					dosyaYolu = fileChooser.getSelectedFile().getPath();
					tfAramaCubugu.setText(dosyaYolu);
					
				}
				
			}
		});
		btnGozat.setBounds(342, 10, 89, 23);
		panel.add(btnGozat);
		
		JList listKazananlar = new JList();
		listKazananlar.setBounds(10, 79, 404, 208);
		panel.add(listKazananlar);
		
		listKazananlar.setModel(model);
		
		JLabel lblKazananlar = new JLabel("Kazananlar");
		lblKazananlar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKazananlar.setForeground(new Color(255, 255, 255));
		lblKazananlar.setBounds(10, 54, 72, 14);
		panel.add(lblKazananlar);
		
		
		JButton btnCekilisYap = new JButton("Çekiliş Yap");
		btnCekilisYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				if (dosyaYolu.equals(""))
				{
					
					JOptionPane.showMessageDialog(btnCekilisYap, "Lütfen çekiliş dosyası seçiniz.!");
					
				}
				
				else
				{
					
					cekilisYap();
					
					for(String kazanan : kazananlar)
					{
						
						model.addElement(kazanan);
						
					}
					
					try
					{
						alkisEkle();
					}
					
					catch (UnsupportedAudioFileException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					catch (LineUnavailableException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnCekilisYap.setBounds(310, 45, 121, 23);
		panel.add(btnCekilisYap);
	}
}
