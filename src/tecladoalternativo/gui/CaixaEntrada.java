package tecladoalternativo.gui;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CaixaEntrada extends JPanel{
    /**
	 * Cria as caixas de digita��o e visualiza��o.  
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextArea textArea;
    private final static String espaco = " ";
    private static final String fileName = "dict/pt-BR.dic";
    private JScrollPane scrollPane;
    private GridBagConstraints c;
    private String text;
    

    public CaixaEntrada() {
        super(new GridBagLayout());

        textField = new JTextField(20);
        textField.addActionListener(new ActionListener(){
        	/**Evento para definir valor da caixa de visualiza��o*/
	        public void actionPerformed(ActionEvent evt) {
	            text = getCaixadeDigitacao().getText();
	            textArea.append(text + espaco);
	            textField.selectAll();
	            textArea.setCaretPosition(textArea.getDocument().getLength());
	        }
        	}
        );
        
        textField.getDocument().addDocumentListener(new AtualizaCaixaDigitacao(textField));
        
        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        
        scrollPane = new JScrollPane(textArea);
        
        //Adiciona componentes a este painel.
        c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
        
    }
    
    
    
    private static void readFile(String fileName, JTextField textField) {
    	ArrayList<Lexico> sugestao = new ArrayList<Lexico>();
        try {
          File file = new File(fileName);
          Scanner scanner = new Scanner(file);
          PrevisaoPalavra previsao = new PrevisaoPalavra();
          int i = 0;
          int n_sugestoes = previsao.getN_sugestoes();

          while(scanner.hasNextLine()){
            if(scanner.nextLine().startsWith(textField.getText())){           	
            	
            	Pattern p = Pattern.compile("(.*?)(/)(.*)", Pattern.DOTALL);
        		Matcher m = p.matcher(scanner.nextLine());
        		while ((m.find()) && (i<n_sugestoes)) {
        			sugestao.add(new Lexico(m.group(1), m.group(3)));
        			i++;
        		}
        	}
          }          
          previsao.definirBotoes(sugestao);
          scanner.close();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        sugestao.clear();
    }
    
    
    /**M�todo para definir o conte�do da caixa de vizualiza��o
     * @param txt String - Conte�do da caixa de digita��o*/
    public void setCaixaDigitacao(String txt){
    	this.textField.setText(txt);
    	System.out.println("->"+this.textField.getText());
    }
    
    /** M�todo para retorno da caixa de digita��o
 	@return JTextField - Caixa de digita��o*/
	public JTextField getCaixadeDigitacao() {
		return this.textField;		
	}

    /** M�todo para retorno da caixa de digita��o
 	@return JTextField - Caixa de digita��o*/
	public JTextArea getCaixadeVisualizacao() {
		return this.textArea;		
	}	
	      
    /**Classe que implementa altera��es de documento da caixa de digita��o*/
	private class AtualizaCaixaDigitacao implements DocumentListener {
		private JTextField textField;
		
		public AtualizaCaixaDigitacao(JTextField textField) {
            this.textField = textField;
        }
        @Override
        public void insertUpdate(DocumentEvent e) {
        	if (textField.getText().length() > 3){
                readFile(fileName, textField);
        	}
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
        	if (textField.getText().length() > 3){          
                readFile(fileName, textField);
        	}
        	
       
        }
        @Override
        public void changedUpdate(DocumentEvent e) {
        	if (textField.getText().length() > 3){          
                readFile(fileName, textField);
        	}
        	System.out.println("Hi, " + textField.getText());
            
        }
    }
}
