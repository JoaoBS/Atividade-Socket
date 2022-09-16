import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import java.net.UnknownHostException;

public class Cliente {
	public static void main(String[] args) throws IOException {
		
		Socket cliente = new Socket("localhost", 1234);
		System.out.println("Cliente conectado");
		
		JFrame jFrame = new JFrame("Tela do Cliente");
		jFrame.setSize(1000,1000);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon iconeImagem = new ImageIcon
		("D:\\Trabalhos\\P6\\Sistemas Distribuidos\\Atividade Socket"
		+ "\\Imagem do Cliente\\Imagem de Teste.png");
		
		JLabel jLabel = new JLabel(iconeImagem);
		JButton jButton = new JButton("Enviar Imagem para o Servidor");
		
		jFrame.add(jLabel, BorderLayout.CENTER);
		jFrame.add(jButton, BorderLayout.SOUTH);
		
		jFrame.setVisible(true);
		
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evento) {
				try {
					OutputStream saida = cliente.getOutputStream();
					BufferedOutputStream buffSaida =
					new BufferedOutputStream(saida);
					
					Image imagem = iconeImagem.getImage();
					
					BufferedImage imagemBuff = new BufferedImage(
					imagem.getWidth(null), imagem.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
					
					Graphics grafico = imagemBuff.createGraphics();
					grafico.drawImage(imagem, 0, 0, null);
					grafico.dispose();
					
					ImageIO.write(imagemBuff, "png", buffSaida);
					
					buffSaida.close();
					cliente.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		System.out.println("Cliente Desconectado");
	}
}
