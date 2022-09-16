import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Servidor {
	public static void main(String[] args) throws IOException {
		
		System.out.println("Conexão Iniciada");
		
		JFrame jFrame = new JFrame("Tela do Servidor");
		jFrame.setSize(1000,1000);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel jLabel = new JLabel("Esperando Conexão");
		
		jFrame.add(jLabel, BorderLayout.SOUTH);

		jFrame.setVisible(true);

		ServerSocket servidor = new ServerSocket(1234);

		Socket cliente = servidor.accept();

		InputStream entrada = cliente.getInputStream();
		BufferedInputStream buffEntrada = 
		new BufferedInputStream(entrada);

		BufferedImage imagemBuff = ImageIO.read(buffEntrada);
		
		ImageIO.write(imagemBuff, "png",
				new File("D:\\Trabalhos\\P6\\Sistemas Distribuidos"
						+ "\\Atividade Socket\\Imagem do Servidor\\"
						+ "Imagem de Teste 2.png"));

		buffEntrada.close();
		servidor.close();

		JLabel jLabelImagem = new JLabel(new ImageIcon(imagemBuff));
		jLabel.setText("Imagem Recebida");
		jFrame.add(jLabelImagem, BorderLayout.CENTER);
		
		System.out.println("Conexão Encerrada");
	}
}

