import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class Ventana extends JFrame {
	
	private Ventana instance;
	private List<JButton> botones;
	private HashMap<JButton,ImageIcon> cartasBarajeadas;
	private Thread t,game;
	private int time,numPares;
	private List<JButton> pares;
	private volatile boolean isRunning;

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Ventana() {
		instance = this;
		botones = new ArrayList<JButton>();
		cartasBarajeadas = new HashMap<JButton,ImageIcon>();
		pares = new ArrayList<JButton>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 255, 255));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("0");
		lblNewLabel.setFont(new Font("Microsoft JhengHei", Font.BOLD, 30));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 255, 255));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 2, 2, 2));
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pares.add(btnNewButton_1);
				mostrarUna(btnNewButton_1);
			}
		});
		panel_1.add(btnNewButton_1);
		botones.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton();
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pares.add(btnNewButton_3);
				mostrarUna(btnNewButton_3);
			}
		});
		panel_1.add(btnNewButton_3);
		botones.add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton();
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pares.add(btnNewButton_5);
				mostrarUna(btnNewButton_5);
			}
		});
		panel_1.add(btnNewButton_5);
		botones.add(btnNewButton_5);
		
		JButton btnNewButton_7 = new JButton();
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pares.add(btnNewButton_7);
				mostrarUna(btnNewButton_7);
			}
		});
		panel_1.add(btnNewButton_7);
		botones.add(btnNewButton_7);
		
		JButton btnNewButton_6 = new JButton();
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pares.add(btnNewButton_6);
				mostrarUna(btnNewButton_6);
			}
		});
		panel_1.add(btnNewButton_6);
		botones.add(btnNewButton_6);
		
		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pares.add(btnNewButton_2);
				mostrarUna(btnNewButton_2);
			}
		});
		panel_1.add(btnNewButton_2);
		botones.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton();
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pares.add(btnNewButton_4);
				mostrarUna(btnNewButton_4);
			}
		});
		panel_1.add(btnNewButton_4);
		botones.add(btnNewButton_4);
		
		JButton btnNewButton_8 = new JButton();
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pares.add(btnNewButton_8);
				mostrarUna(btnNewButton_8);
			}
		});
		panel_1.add(btnNewButton_8);
		botones.add(btnNewButton_8);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 204, 255));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("REINICIAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opc = JOptionPane.showConfirmDialog(instance,"¿Seguro que quieres reiniciar?","Memorama",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				if(opc == 0) {
					stopTimer(t);
					t = startTimer(lblNewLabel);
					t.start();
					restart();
					revalidate();
					repaint();
					pares.clear();
				}
			}
		});
		btnNewButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
		panel_2.add(btnNewButton);
		
		/////////////////////////
		t = startTimer(lblNewLabel);
		t.start();
		restart();
		
		game = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					System.out.print("");
					if(pares.size() >= 2) {
						System.out.println("Comprobando...");
						if(!sonIguales(pares.get(0).getIcon(), pares.get(1).getIcon())) {
							JOptionPane.showMessageDialog(instance,"¡No son pares!","Memorama",JOptionPane.PLAIN_MESSAGE);
							voltearUna(pares.get(0));
							voltearUna(pares.get(1));
							pares.clear();
						} else {
							JOptionPane.showMessageDialog(instance,"¡Pares!","Memorama",JOptionPane.PLAIN_MESSAGE);
							pares.get(0).setEnabled(false);
							pares.get(1).setEnabled(false);
							pares.clear();
							numPares++;
						}
					}
					
					if(numPares >= 4) {
						stopTimer(t);
						JOptionPane.showMessageDialog(instance,"Has descubierto todos los pares en un tiempo de " + lblNewLabel.getText() + " segundos","Memorama",JOptionPane.PLAIN_MESSAGE);
						t = startTimer(lblNewLabel);
						t.start();
						restart();
						revalidate();
						repaint();
					}
				}
			}
			
		});
		game.start();
	}
	
	public Thread startTimer(JLabel timer) {
		time = 0;
		timer.setText("0");
		isRunning = true;
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(isRunning) {
					timer.setText("" + time);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					time++;
				}
			}
		});
		return thread;
	}
	
	public void stopTimer(Thread timer) {
		isRunning = false;
		timer.interrupt();
	}
	
	public void restart() {
		Collections.shuffle(botones);
		int i = 0;
		for(JButton boton : botones) {
			switch(i) {
				case 0:
				case 1:
					cartasBarajeadas.put(boton,new ImageIcon(resizeImage(150, 150, getStream("resources/1.png"))));
					break;
				case 2:
				case 3:
					cartasBarajeadas.put(boton,new ImageIcon(resizeImage(150, 150, getStream("resources/2.png"))));
					break;
				case 4:
				case 5:
					cartasBarajeadas.put(boton,new ImageIcon(resizeImage(150, 150, getStream("resources/3.png"))));
					break;
				case 6:
				case 7:
					cartasBarajeadas.put(boton,new ImageIcon(resizeImage(150, 150, getStream("resources/4.png"))));
					break;
			}
			i++;
		}
		voltear();
		for(JButton boton : botones) {
			boton.setEnabled(true);
		}
		numPares = 0;
		time = 0;
	}
	
	public void voltear() {
		for(JButton boton : cartasBarajeadas.keySet()) {
			boton.setIcon(new ImageIcon(resizeImage(110, 150, getStream("resources/unknown.png"))));
		}
	}
	
	public void voltearUna(JButton boton) {
		boton.setIcon(new ImageIcon(resizeImage(110, 150, getStream("resources/unknown.png"))));
	}
	
	public void mostrarUna(JButton boton) {
		boton.setIcon(cartasBarajeadas.get(boton));
	}
	
    public Image resizeImage(int width, int height, InputStream inputStream) {
        Image img;

        try {
            img = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return img.getScaledInstance(width,height,Image.SCALE_SMOOTH);
    }

    public InputStream getStream(String path) {
        return Ventana.class.getClassLoader().getResourceAsStream(path);
    }
    
    public boolean sonIguales(Icon image1, Icon image2) {
    	ImageIcon icono1 = (ImageIcon) image1;
        ImageIcon icono2 = (ImageIcon) image2;
        
        BufferedImage imagen1 = new BufferedImage(icono1.getIconWidth(), icono1.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        imagen1.getGraphics().drawImage(icono1.getImage(), 0, 0, null);
        BufferedImage imagen2 = new BufferedImage(icono2.getIconWidth(), icono2.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        imagen2.getGraphics().drawImage(icono2.getImage(), 0, 0, null);
        
        if (imagen1.getWidth() == imagen2.getWidth() && imagen1.getHeight() == imagen2.getHeight()) {
            for (int y = 0; y < imagen1.getHeight(); y++) {
                for (int x = 0; x < imagen1.getWidth(); x++) {
                    if (imagen1.getRGB(x, y) != imagen2.getRGB(x, y)) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
