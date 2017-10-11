/**
 *
 * @author Junior Garcia
 */
public class Main {
    public static void main(String[] args) {
        
        InterfazCliente interfaz = new InterfazCliente();
        interfaz.setVisible(true);
        
        Thread hilo=new Thread(interfaz);
        hilo.start();
    }
}
