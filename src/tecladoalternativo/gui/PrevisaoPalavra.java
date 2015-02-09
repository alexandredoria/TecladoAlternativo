package tecladoalternativo.gui;

/**Classe para objeto do tipo PrevisaoPalavra, onde serão definidos elementos alfa-numéricos, valores e métodos para o mesmo.
 * @author Alexandre José Dória Batista
 * @version 1.0
 * @since Release 01 da aplicação
 * */

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import tecladoalternativo.gui.*;


public class PrevisaoPalavra extends JPanel{

    private static final long serialVersionUID = 1L;
	private GridBagLayout layout;
	private GridBagConstraints constantes;
    private JButton[][] button;
    private static final String COMMIT_ACTION = "commit";
    private final static int n_linhas = 3;
    private final static int n_colunas = 3;
    private final static String espaco = " ";
    private final static int n_sugestoes = n_linhas*n_colunas;
    private String text;
    
    private String[][] sugestao = {
            {"", "", ""}, 
            {"", "", ""},
            {"", "", ""}
        };
    
    public PrevisaoPalavra() {
        super(new GridBagLayout());

        layout = new GridBagLayout();
        setLayout(layout);
        constantes = new GridBagConstraints();
        
        for (int linha = 0; linha < sugestao.length; linha++) {
            button = new JButton[n_linhas][n_colunas];
            for (int coluna = 0; coluna < sugestao[linha].length; coluna++) {
                button[linha][coluna] = new JButton(sugestao[linha][coluna]);
                
                button[linha][coluna].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
			            CaixaEntrada ce = new CaixaEntrada();
			            String txt = e.getActionCommand();
			            ce.getCaixadeVisualizacao().append(text + espaco);
			            //textField.selectAll();
			            ce.getCaixadeVisualizacao().setCaretPosition(ce.getCaixadeVisualizacao().getDocument().getLength());
			            
			            //ce.setCaixaDigitacao(txt);
					}
				});
                //button[linha][coluna].setFocusable(false);
                //button[linha][coluna].setPreferredSize(new Dimension(30, 30));
                //button[linha][coluna].setBorder(null);
                constantes.fill = GridBagConstraints.BOTH;
                addComponente(button[linha][coluna], linha, coluna, 10, 10);
            }
     
        }        
    }
    
	
    /**Método para adicionar componentes ao layout  
     * @param componente JComponent - Componente a ser adicionado ao layout
     * @param linha int - Posição da linha deste componente no layout
     * @param coluna int - Posição da coluna deste componente no layout
     * @param comprimento int - Valor do comprimento deste elemento no layout
     * @param altura int - Valor do comprimento deste elemento no layout
     * @return 
     * */
    
    public void definirBotoes(ArrayList<Lexico> keywords){
    	String[][] sugestaoArr = new String[n_linhas][n_colunas];
    	int i = 0;
    	
    	
		for (int linha = 0; linha < n_linhas-1; linha++) {
    	    for (int coluna = 0; coluna < n_colunas-1; coluna++) {
    	    	if(i<keywords.size()){
    	    		if(!keywords.get(i).equals(null)){
        	    		sugestaoArr[linha][coluna] = String.valueOf(keywords.get(i++));
            	    }
    	    	}
    	    	        	    	
    	    }
    	}
    	this.sugestao = sugestaoArr;
    	//System.out.println(Arrays.deepToString(this.sugestao));
    }
    
    public int getN_sugestoes(){
		return n_sugestoes;
    }
    
    private void addComponente(JComponent componente, int linha, int coluna, int comprimento, int altura){
    	constantes.gridx = coluna;
    	constantes.gridy = linha;
    	constantes.ipadx = 3;
    	constantes.ipady = 3;
    	constantes.insets = new Insets(4,16,4,16); //Adiciona bordas 
    	//constantes.gridwidth = comprimento; 
    	//constantes.gridheight = altura; //Está causando sobreposição de elementos
    	layout.setConstraints(componente, constantes);
    	add(componente);
    }
}