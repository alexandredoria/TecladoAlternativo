package tecladoalternativo.gui;

import java.util.Comparator;

/* lmcpc_dec.txt
 * Léxico Multifuncional Computorizado do Português Contemporâneo
 * Arquivo disponível em http://www.clul.ul.pt/pt/investigacao/88-multifunctional-computational-lexicon-of-contemporary-portuguese-r
 * RegEx lmcpc (.*?) \\((.*?)\\)( # )([0-9]+)
 *
 *Quebrando os grupos da RegEx:
 *(.*?) -> Palavra léxica
 * \\((.*?)\\) -> Classificação morfossintática do léxico (obs.: espaço no início do grupo)
 *( # ) -> caracter de escape
 *([0-9]+) -> Informação quantitativa
*/


public class Lexico implements Comparable {
	private String palavra;
	private String morfo;
	
	public Lexico(String palavra, String morfo){
		this.palavra = palavra;
		this.morfo = morfo;		
	}
	
	 /*Comparator for sorting the list by Lexico Name*/
    public static Comparator<Lexico> comparadorPalavra = new Comparator<Lexico>() {

	public int compare(Lexico s1, Lexico s2) {
	   String Palavra1 = s1.getPalavra().toUpperCase();
	   String Palavra2 = s2.getPalavra().toUpperCase();

	   //ordem crescente
	   return Palavra1.compareTo(Palavra2);

	   //descending order
	   //return Palavra2.compareTo(Palavra1);
    }};

    /*Comparator for sorting the list by roll no*/
    /*public static Comparator<Lexico> comparadorQuant = new Comparator<Lexico>() {

	public int compare(Lexico s1, Lexico s2) {

	   int quant1 = s1.getQuant();
	   int quant2 = s2.getQuant();

	   Ordem crescente
	   //return quant1-quant2;

	   Ordem decrescente
	   return quant2-quant1;
   }};
*/
	
	/*public int compareTo(Lexico comparaLexico) {
        int comparaQuant=((Lexico)comparaLexico).getQuant();
         For Ascending order
    //    return this.quant-comparaQuant;

         For Descending order do like this 
        return comparaQuant-this.quant;
    }
*/	
    public String toString() {
		return String.valueOf(palavra);
	}

	
	public String getPalavra() {
		return palavra;
	}	
	
	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}
   
	public String getMorfo() {
		return morfo;
	}
   
	public void setMorfo(String morfo) {
		this.morfo = morfo;
	}
	
	/*public int getQuant() {
		return quant;
	}
   
	public void setQuant(int quant) {
		this.quant = quant;
	}*/

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}	
}