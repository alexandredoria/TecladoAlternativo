package tecladoalternativo.gui;

/**Classe para objeto do tipo Teclado, onde serão definidos elementos alfa-numéricos, valores e métodos para o mesmo.
 * @author Alexandre José Dória Batista
 * @version 1.0
 * @since Release 01 da aplicação
 * */

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Teclado extends JPanel{

    private static final long serialVersionUID = 1L;
	private GridBagLayout layout;
	private GridBagConstraints constantes;
    private JButton[][] button;
    private final static int n_linhas = 5;
    private final static int n_colunas = 10;
    private final static int n_teclas = n_linhas*n_colunas;
    private String [] placeholder;
    private JLabel displayLabel;
    private static final String[][] tecla = {
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"}, 
        {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"}, 
        {"A", "S", "D", "F", "G", "H", "J", "K", "L", "Ç"}, 
        {"Z", "X", "C", "V", "B", "N", "M", "<", ">", ","},
        {"(", ")", "?", "!", "@", "^", "OK", "DEL", "ESP", "ENT"}
    };

    public Teclado() {
        super(new GridBagLayout());
        layout = new GridBagLayout();
        placeholder = new String[15];
        displayLabel = new JLabel("");
        setLayout(layout);
        constantes = new GridBagConstraints();
        for (int linha = 0; linha < tecla.length; linha++) {
            button = new JButton[n_linhas][n_colunas];
            for (int coluna = 0; coluna < tecla[linha].length; coluna++) {
                button[linha][coluna] = new JButton(tecla[linha][coluna]);
                
                
    			/*if (coluna < 5  ){
    				button[linha][coluna].setBackground(Color.blue);
    			} else {
    				button[linha][coluna].setBackground(Color.yellow);
    			}*/
        				        		
                
                button[linha][coluna].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
			            switch (e.getActionCommand()){
			            /*	case "OK":   numberAction(e.getActionCommand());
			            	case "DEL":   numberAction(e.getActionCommand());
			            	case "ESP":   numberAction(e.getActionCommand());
			            	case "ENT":   numberAction(e.getActionCommand());*/
			            	default: {
			            		JButton btn = (JButton) e.getSource();
								CaixaEntrada ce = new CaixaEntrada();
								String txt = btn.getText();
					            ce.setCaixaDigitacao(e.getActionCommand());
					            ce.getCaixadeDigitacao().selectAll();
			            	} 
			            }
			            
			        }
				});
                button[linha][coluna].setFocusable(false);
                
                button[linha][coluna].setPreferredSize(new Dimension(30, 30));
                button[linha][coluna].setBorder(null);
                constantes.fill = GridBagConstraints.BOTH;
                addComponente(button[linha][coluna], linha, coluna, 10, 10);
            }
     
        }        
    }
    
/* // Method used to display button value on the JLabel and add value to array.
    private void numberAction(){
        makePlaceholderNumber();
        displayLabel.setText(Long.toString(placeholderBuilder));
    }   

    // Method used to take a value of a button or key number entered by the user and add it to the placeholder array.
    private void numberAction(String string){
        for (int i = 0; i < placeholder.length; i++) {
            if (placeholder[i] == null) {
                placeholder[i]= string;
                break;
            }
        }
        makePlaceholderNumber();
        displayLabel.setText(Long.toString(placeholderBuilder));
        
    }
    
    
    // Method converts to numbers in the placeholder [] array into a placeholderBuilder string to be displayed in the display JLabel later.
    private void makePlaceholderNumber(){
        StringBuilder number = new StringBuilder();
        for (Integer num : placeholder){
            if (num != null){
                number.append(num);
            }
        }

        if (positiveNumberSwitch == false){
            long displayNumber = Long.parseLong(number.toString());
            displayNumber = -displayNumber;
            placeholderBuilder = displayNumber;
        }else{
            placeholderBuilder = Long.parseLong(number.toString());
        }

        testingStringOutputToConsole();
    }


*/    
    public int getN_sugestoes(){
		return n_teclas;
    }
    
    
    
	
    /**Método para adicionar componentes ao layout  
     * @param componente JComponent - Componente a ser adicionado ao layout
     * @param linha int - Posição da linha deste componente no layout
     * @param coluna int - Posição da coluna deste componente no layout
     * @param comprimento int - Valor do comprimento deste elemento no layout
     * @param altura int - Valor do comprimento deste elemento no layout
     * */

    private void addComponente(JComponent componente, int linha, int coluna, int comprimento, int altura){
    	constantes.gridx = coluna;
    	constantes.gridy = linha;
    	constantes.ipadx = 3;
    	constantes.ipady = 3;
    	//constantes.insets = new Insets(3,3,3,3); //Adiciona bordas 
    	//constantes.gridwidth = comprimento; 
    	//constantes.gridheight = altura; //Está causando sobreposição de elementos
    	layout.setConstraints(componente, constantes);
    	add(componente);
    }
}