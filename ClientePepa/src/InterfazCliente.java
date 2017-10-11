
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Junior Garcia
 */
public class InterfazCliente extends JFrame implements ActionListener,Runnable{
    
    JTextArea textField;
    JButton btnSend;
    public InterfazCliente(){
        super("PepaCliente");
        setSize(300, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        createContent();
    }
    
    public void createContent(){
        JPanel panelChat = new JPanel();
        panelChat.setLayout(new BorderLayout());
        textField = new JTextArea();
        btnSend = new JButton("Enviar");
        btnSend.addActionListener(this);
        panelChat.add(textField, BorderLayout.CENTER);
        panelChat.add(btnSend, BorderLayout.SOUTH);
        
        add(panelChat, BorderLayout.CENTER);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSend){            
            try {
                Socket socketSalida=new Socket("10.1.11.125",5555);
                DataOutputStream salida=new DataOutputStream(socketSalida.getOutputStream());
                
                salida.writeUTF(textField.getText());
                salida.close();
                socketSalida.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error en el puerto");                  
            }           
        }
    }

    @Override
    public void run() {
       String menj;
        while (true){
        try {
            ServerSocket entrada=new ServerSocket(5556);
            Socket aux=entrada.accept();
            DataInputStream flujoEntrada=new DataInputStream(aux.getInputStream());
            menj=flujoEntrada.readUTF();
            textField.append(menj+"\n");
            flujoEntrada.close();
            aux.close();
            entrada.close();
        } catch (IOException ex) {
            System.out.println("Error en el puerto del Servidor cliente"+ex);
        }
        }
    }
    
    
    
    
    
}
