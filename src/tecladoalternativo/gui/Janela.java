
package tecladoalternativo.gui;

/**Classe para objeto do tipo Janela, onde ir� agrupar os objetos e mostrar-los na tela principal
 * @author Alexandre Jos� D�ria Batista
 * @version 1.0
 * @since Release 01 da aplica��o

 * */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

public class Janela extends JFrame{

	private static final long serialVersionUID = 1L;
	private Teclado teclado;
	private CaixaEntrada caixaEntrada;
	private PrevisaoPalavra previsaoPalavra;
	private GridBagLayout layout;
	private GridBagConstraints constantes;
	private JPanel painel;
	
	/**
	 * @param titulo String - Nome principal da janela criada
	 */
    public Janela(String titulo) {
        super(titulo);
        
        layout = new GridBagLayout();
        setLayout(layout);
        constantes = new GridBagConstraints();
        
        teclado = new Teclado();
        caixaEntrada = new CaixaEntrada();
        previsaoPalavra = new PrevisaoPalavra();
        
        constantes.fill = GridBagConstraints.BOTH;
        addComponente(caixaEntrada, 0, 0, 15, 10);
        
        constantes.fill = GridBagConstraints.BOTH;
        addComponente(previsaoPalavra, 1, 0, 15, 10);
        
        constantes.fill = GridBagConstraints.BOTH;
        addComponente(teclado, 2, 0, 15, 10);
        
        painel = new JPanel(new BorderLayout());
		this.add(painel);
		
		//Adiciona atalho de teclado
		InputMap inputMap = painel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "Ativa");
		ActionMap actionMap = painel.getActionMap();
		actionMap.put("Ativa", new AtivaTeclado());
    }
    
    private class AtivaTeclado extends AbstractAction {
		/**
		 * M�todo para ativar e alternar o foco do teclado
		 */
		private static final long serialVersionUID = 1L;

		//Em constru��o...
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println("A tecla down foi acionada!");
		}
	}
    
    /**M�todo para adicionar componentes ao layout  
     * @param componente JComponent - Componente a ser adicionado ao layout
     * @param linha int - Posi��o da linha deste componente no layout
     * @param coluna int - Posi��o da coluna deste componente no layout
     * @param comprimento int - Valor do comprimento deste elemento no layout
     * @param altura int - Valor do comprimento deste elemento no layout
     * */
    private void addComponente(JComponent componente, int linha, int coluna, int comprimento, int altura){
    	constantes.gridx = coluna;
    	constantes.gridy = linha;
    	constantes.gridwidth = comprimento;
    	//constantes.gridheight = altura;
    	layout.setConstraints(componente, constantes);
    	add(componente);
    }
   
    /**M�todo que cria e configura a janela*/
    private static void criaMostraGUI() {
        ////Create and set up the window.
        JFrame frame = new Janela("Teclado Alternativo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        /* Usa um design apropriado para a aplica��o. */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Retira o asp�cto met�lico de fontes em negrito */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Cria e exibe a aplica��o GUI.
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                criaMostraGUI();  
            }
        });
    }
}
