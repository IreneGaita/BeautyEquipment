package bd21.progetto;
import java.sql.*;
import java.util.Scanner;

public class App {
	
	public static void main (String[] args) {
		Connection con = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/progetto" + "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "useLegacyDatetimeCode=false&servverTimezone=UTC";
			String username = "root";
			String pwd = "Nutella.01";
			con = DriverManager.getConnection(url, username, pwd);
			System.out.println("Mi sono connesso!\n");
		} catch (Exception e) {
			System.out.println("Connessione fallita");
			e.printStackTrace();
		}
		
		
		String a;
	
		
	    System.out.println( "\n"
	    			  		+ "\n"
	    			  		+ "1 - Registrazione di un cliente\n"
	                        + "2 - Acquisto di un macchinario\n"
	                        + "3 - Consegna di un macchinario ad un corriere\n"
	                        + "4 - Acquisto di un macchinario di tipo accessorio\n"
	                        + "5 - Avvio di un intervento di manutenzione di un macchinario\n"
	                        + "6 - Verifica della possibilità di assegnare un operaio ad un intervento di manutenzione\n"
	                        + "7 - Coinvolgimento di un dipendente in un intervento\n"
	                        + "8 - Visualizzazione per ogni dipendente del numero di macchinari che ha riparato\n"
	                        + "9 -  Visualizzazione dei codici dei macchinari affidati ad un corriere o per i quali è in atto un intervento di manutenzione con stato'in lavorazione'\n"
	                        + "10 - Visualizzazione dei macchinari per i quali non è stato mai richiesto un intervento di manutenzione\n"
	                        + "11 - Stampa dei dati degli ingegneri,compreso il numero di richieste di sostituzione che ha definito\n"
	                        + "12 - Stampa dei dati degli ingegneri che non hanno mai progettato macchinari di tipo accessorio\n"
	                        + "13 - Stampa di un report che mostri i dati delle categorie di tipo accessorio, inclusa la quantità totale di macchinari di tipo 'base' a cui sono stati associati\n"
	                        + "14 - Stampa di un report che mostri i dati degli operai, compreso il numero totale di ore dedicate agli interventi di manutenzione\n"
	                        + "15 - Stampa di un report che mostri i dati dei clienti, incluso il numero totale di prodotti acquistati \n"                 
	                        + "Per terminare l'esecuzione digitare 0"
	                        +"\n"
	                        +"\n"
	                            );
	    
	    Scanner scan = new Scanner(System.in);
	    
		
		int num;
		num=scan.nextInt();
		
		
		switch(num) {
		case 0:
			
			break;
			
		case 1:
			operazioni.query1(con);
			break;
	
		case 2:
			operazioni.query2(con);
			break;
		case 3:
			operazioni.query3(con);
			break;
		case 4:
			operazioni.query4(con);
			break;
		case 5:	
			operazioni.query5(con);
			break;
		case 6:
			operazioni.query6(con);
			break;
		case 7:
			operazioni.query7(con);
			break;
		case 8:
			operazioni.query8(con);
			break;
		case 9:
			operazioni.query9(con);
			break;
		
		case 10:
			operazioni.query10(con);
			break;
		case 11:
			operazioni.query11(con);
			break;
		case 12:
			operazioni.query12(con);
			break;
		case 13:
			operazioni.query13(con);
			break;
		case 14:
			operazioni.query14(con);
			break;
		case 15:
			operazioni.query15(con);
			break;
			
		}
		while(true) {
			System.out.println("\nVuoi fare un'altra operazione? Y/N");
			
			char a1 = scan.next().charAt(0);
			if(a1=='Y') {
				 System.out.println( "\n"
	    			  		+ "\n"
	    			  		+ "1 - Registrazione di un cliente\n"
	                        + "2 - Acquisto di un macchinario\n"
	                        + "3 - Consegna di un macchinario ad un corriere\n"
	                        + "4 - Acquisto di un macchinario di tipo accessorio\n"
	                        + "5 - Avvio di un intervento di manutenzione di un macchinario\n"
	                        + "6 - Verifica della possibilità di assegnare un operaio ad un intervento di manutenzione\n"
	                        + "7 - Coinvolgimento di un dipendente in un intervento\n"
	                        + "8 - Visualizzazione per ogni dipendente del numero di macchinari che ha riparato\n"
	                        + "9 -  Visualizzazione dei codici dei macchinari affidati ad un corriere o per i quali è in atto un intervento di manutenzione con stato'in lavorazione'\n"
	                        + "10 - Visualizzazione dei macchinari per i quali non è stato mai richiesto un intervento di manutenzione\n"
	                        + "11 - Stampa dei dati degli ingegneri,compreso il numero di richieste di sostituzione che ha definito\n"
	                        + "12 - Stampa dei dati degli ingegneri che non hanno mai progettato macchinari di tipo accessorio\n"
	                        + "13 - Stampa di un report che mostri i dati delle categorie di tipo accessorio, inclusa la quantità totale di macchinari di tipo 'base' a cui sono stati associati\n"
	                        + "14 - Stampa di un report che mostri i dati degli operai, compreso il numero totale di ore dedicate agli interventi di manutenzione\n"
	                        + "15 - Stampa di un report che mostri i dati dei clienti, incluso il numero totale di prodotti acquistati \n"                 
	                        +"\n"
	                        +"\n"
	                            );
				 
				 System.out.println("Effettua una scelta!\n");
					num = scan.nextInt();
					
					
					switch(num) {
					case 0:
						
						break;
						
					case 1:
						operazioni.query1(con);
						break;
				
					case 2:
						operazioni.query2(con);
						break;
					case 3:
						operazioni.query3(con);
						break;
					case 4:
						operazioni.query4(con);
						break;
					case 5:	
						operazioni.query5(con);
						break;
					case 6:
						operazioni.query6(con);
						break;
					case 7:
						operazioni.query7(con);
						break;
					case 8:
						operazioni.query8(con);
						break;
					case 9:
						operazioni.query9(con);
						break;
					
					case 10:
						operazioni.query10(con);
						break;
					case 11:
						operazioni.query11(con);
						break;
					case 12:
						operazioni.query12(con);
						break;
					case 13:
						operazioni.query13(con);
						break;
					case 14:
						operazioni.query14(con);
						break;
					case 15:
						operazioni.query15(con);
						break;
						
					}
					
			}else {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("connessione chiusa");
				
				scan.close();
				System.exit(0);
				
				
			}
			
		}
	}
}
