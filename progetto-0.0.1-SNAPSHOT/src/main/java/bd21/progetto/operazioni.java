package bd21.progetto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class operazioni {

	static void query1(Connection con) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Inserisci il codice fiscale del cliente");
		String cf = scan.nextLine();
		System.out.println("Inserisci il nome del cliente");
		String nome = scan.nextLine();
		System.out.println("Inserisci il cognome del cliente");
		String cognome = scan.nextLine();
		System.out.println("Inserisci la via di residenza del cliente");
		String via = scan.nextLine();
		System.out.println("Inserisci il cap");
		String cap = scan.nextLine();
		System.out.println("Inserisci il numero di telefono del cliente");
		String num_telefono = scan.nextLine();
		System.out.println("Inserisci l'email del cliente");
		String email = scan.nextLine();
		System.out.println("Inserisci il numero civico della residenza");
		int num_civico = scan.nextInt();

		try {

			PreparedStatement query = con.prepareStatement("INSERT INTO cliente VALUES (?,?,?,?,?,?,?,?,?)");

			query.setString(1, cf);
			query.setString(2, nome);
			query.setString(3, cognome);
			query.setString(4, via);
			query.setString(5, cap);
			query.setInt(6, num_civico);
			query.setString(7, num_telefono);
			query.setInt(8, 0);
			query.setString(9, email);

			int result = query.executeUpdate();
			if (result == 1)
				System.out.println("\n Cliente registrato correttamente \n");
			else
				System.out.println("\n Errore nella registrazione di un cliente \n");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void query2(Connection con) {

		ArrayList<String> macchinario = new ArrayList<>();
		ArrayList<String> clienti = new ArrayList<>();
		int count1 = 1;
		int count2 = 1;
		int acquisti = 0;
		ResultSet result;
		ResultSet result3;

		try {
			System.out.println("Elenco dei macchinari/o disponibili per essere acquistati");
			Statement query = con.createStatement();
			String acquisto_macc = "select  macchinario.cod_seriale FROM macchinario WHERE macchinario.cliente_cf IS null";
			result = query.executeQuery(acquisto_macc);

			while (result.next()) {
				String codice_seriale = result.getString("cod_seriale");
				System.out.println("Macchinario " + count1 + ": " + codice_seriale);

				macchinario.add(codice_seriale);
				count1++;
			}

			Scanner scan = new Scanner(System.in);
			System.out.println("Scegli il numero corrispondente al macchinario che vuoi acquistare in base all'elenco");
			int risp = scan.nextInt();

			System.out.println("E' il primo acquisto? Y / N");
			char risp1 = scan.next().charAt(0);
			if (risp1 == 'Y') {

				System.out.println("Inserisci il codice fiscale ");
				String cf = scan.next();
				System.out.println("Inserisci il nome ");
				String nome = scan.next();
				System.out.println("Inserisci il cognome ");
				String cognome = scan.next();
				System.out.println("Inserisci la via di residenza ");
				String via = scan.next();
				System.out.println("Inserisci il cap della residenza");
				String cap = scan.next();
				System.out.println("Inserisci il numero di telefono ");
				String num_telefono = scan.next();
				System.out.println("Inserisci l'email ");
				String email = scan.next();
				System.out.println("Inserisci il numero civico ");
				int num_civico = scan.nextInt();

				try {

					PreparedStatement queryp = con.prepareStatement("INSERT INTO cliente VALUES (?,?,?,?,?,?,?,?,?)");

					queryp.setString(1, cf);
					queryp.setString(2, nome);
					queryp.setString(3, cognome);
					queryp.setString(4, via);
					queryp.setString(5, cap);
					queryp.setInt(6, num_civico);
					queryp.setString(7, num_telefono);
					queryp.setInt(8, 1);
					queryp.setString(9, email);

					int result1 = queryp.executeUpdate();

					if (result1 == 1)
						System.out.println("Cliente registrato correttamente \n");
					else {
						System.out.println("\n Errore nella registrazione di un cliente \n");
					}
					String codseriale = macchinario.get(risp - 1);

					PreparedStatement query1 = con
							.prepareStatement("UPDATE macchinario set cliente_cf=? WHERE cod_seriale=?");
					query1.setString(1, cf);
					query1.setString(2, codseriale);
					query1.executeUpdate();

					System.out.println("L'acquisto è andato a buon fine");

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Inserisci il tuo codice fiscale");
				String cf = scan.next();

				String codseriale = macchinario.get(risp - 1);

				PreparedStatement query1 = con
						.prepareStatement("UPDATE macchinario set cliente_cf=? WHERE cod_seriale=?");
				query1.setString(1, cf);
				query1.setString(2, codseriale);
				query1.executeUpdate();

				System.out.println("L'acquisto è andato a buon fine");

				PreparedStatement query2 = con.prepareStatement(
						"UPDATE cliente set acquisti_effettuati=acquisti_effettuati+1 WHERE codice_fiscale=?");
				query2.setString(1, cf);
				query2.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void query3(Connection con) {

		String codice_seriale = null;
		ResultSet result;
		String consegna_macc;
		String corriere;
		ArrayList<String> macchinario = new ArrayList<>();
		ArrayList<String> corrieri = new ArrayList<>();
		int count1 = 1;
		int count2 = 1;
		String codseriale;
		String corrid;

		try {
			System.out.println("Elenco dei macchinari disponibili per essere affidati a corrieri:");
			Statement query = con.createStatement();
			consegna_macc = "select  macchinario.cod_seriale FROM macchinario WHERE macchinario.corriere_id IS null";
			result = query.executeQuery(consegna_macc);

			while (result.next()) {
				codice_seriale = result.getString("cod_seriale");
				System.out.println(" Macchinario " + count1 + ": " + codice_seriale);

				macchinario.add(codice_seriale);
				count1++;
			}
			Scanner scan = new Scanner(System.in);
			System.out.println("Scegli il numero corrispondente al macchinario che vuoi affidare a un corriere");
			int risp = scan.nextInt();

			corriere = "SELECT  corriere.codice_id FROM corriere";
			result = query.executeQuery(corriere);
			System.out.println("Elenco dei corrieri disponibili:");
			while (result.next()) {
				corriere = result.getString("codice_id");

				System.out.println(" Corriere " + count2 + ": " + corriere);

				corrieri.add(corriere);
				count2++;

			}

			System.out.println("Scegli il numero corrispondente al corriere al quale vuoi affidare un macchinario");
			int risp2 = scan.nextInt();

			codseriale = macchinario.get(risp - 1);
			corrid = corrieri.get(risp2 - 1);

			PreparedStatement query1 = con.prepareStatement("UPDATE macchinario set corriere_id=? WHERE cod_seriale=?");
			query1.setString(1, corrid);
			query1.setString(2, codseriale);
			query1.executeUpdate();
			System.out.println("Il macchinario scelto è stato affidato correttamente ad un corriere");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void query4(Connection con) {

		ArrayList<String> macchinario = new ArrayList<>();
		ArrayList<String> clienti = new ArrayList<>();
		int count1 = 1;
		int count2 = 1;
		int acquisti = 0;
		ResultSet result;
		ResultSet result3;

		try {
			System.out.println("Elenco dei macchinari accesori disponibili per essere acquistati:");
			Statement query = con.createStatement();
			String acquisto_macc = "select  macchinario.cod_seriale FROM macchinario WHERE macchinario.cliente_cf IS null AND macchinario.base_nome IS NULL";
			result = query.executeQuery(acquisto_macc);

			while (result.next()) {
				String codice_seriale = result.getString("cod_seriale");
				System.out.println(" Macchinario " + count1 + ": " + codice_seriale);

				macchinario.add(codice_seriale);
				count1++;
			}

			Scanner scan = new Scanner(System.in);
			System.out.println("Scegli il numero corrispondente al macchinario accessorio che si vuole acquistare");
			int risp = scan.nextInt();

			System.out.println("E' il primo acquisto? Y / N");
			char risp1 = scan.next().charAt(0);
			if (risp1 == 'Y') {
				System.out.println("Inserisci il codice fiscale ");
				String cf = scan.next();
				System.out.println("Inserisci il nome ");
				String nome = scan.next();
				System.out.println("Inserisci il cognome ");
				String cognome = scan.next();
				System.out.println("Inserisci la via di residenza ");
				String via = scan.next();
				System.out.println("Inserisci il cap della residenza");
				String cap = scan.next();
				System.out.println("Inserisci il numero di telefono ");
				String num_telefono = scan.next();
				System.out.println("Inserisci l'email ");
				String email = scan.next();
				System.out.println("Inserisci il numero civico ");
				int num_civico = scan.nextInt();

				try {

					PreparedStatement queryp = con.prepareStatement("INSERT INTO cliente VALUES (?,?,?,?,?,?,?,?,?)");

					queryp.setString(1, cf);
					queryp.setString(2, nome);
					queryp.setString(3, cognome);
					queryp.setString(4, via);
					queryp.setString(5, cap);
					queryp.setInt(6, num_civico);
					queryp.setString(7, num_telefono);
					queryp.setInt(8, 1);
					queryp.setString(9, email);

					int result1 = queryp.executeUpdate();

					if (result1 == 1)
						System.out.println("Cliente registrato correttamente \n");
					else {
						System.out.println("\n Errore nella registrazione di un cliente \n");
					}
					String codseriale = macchinario.get(risp - 1);

					PreparedStatement query1 = con
							.prepareStatement("UPDATE macchinario set cliente_cf=? WHERE cod_seriale=?");
					query1.setString(1, cf);
					query1.setString(2, codseriale);
					query1.executeUpdate();

					System.out.println("L'acquisto è andato a buon fine");

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Inserisci il tuo codice fiscale per l'acquisto");
				String cf = scan.next();

				String codseriale = macchinario.get(risp - 1);

				PreparedStatement query1 = con
						.prepareStatement("UPDATE macchinario set cliente_cf=? WHERE cod_seriale=?");
				query1.setString(1, cf);
				query1.setString(2, codseriale);
				query1.executeUpdate();

				System.out.println("L'acquisto è andato a buon fine");

				PreparedStatement query2 = con.prepareStatement(
						"UPDATE cliente set acquisti_effettuati=acquisti_effettuati+1 WHERE codice_fiscale=?");
				query2.setString(1, cf);
				query2.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void query5(Connection con) {

		int count1 = 1;
		String codice_seriale = null;
		ArrayList<String> macchinari = new ArrayList<>();
		Random rnd = new Random();
		int n = 1000 + rnd.nextInt((9999 - 1000) + 1);
		long milliseconds = System.currentTimeMillis();
		Date data = new Date(milliseconds);

		try {
			System.out.println("Elenco dei macchinari disponibili per un intervento di manutenzione:");
			Statement query = con.createStatement();
			String macchinario = "select  macchinario.cod_seriale FROM macchinario WHERE macchinario.corriere_id IS NULL AND macchinario.cliente_cf IS NOT NULL AND (macchinario.cod_seriale) NOT IN (SELECT intervento.macchinario_codice_seriale FROM intervento)";
			ResultSet result = query.executeQuery(macchinario);

			while (result.next()) {
				codice_seriale = result.getString("cod_seriale");
				macchinari.add(codice_seriale);
				System.out.println(" Macchinario " + count1 + ": " + codice_seriale);
				count1++;
			}
			Scanner scan = new Scanner(System.in);
			System.out.println(
					"Scegli il numero corrispondente al macchinario su cui vuoi effettuare un intervento di manutenzione");
			int risp = scan.nextInt();

			String codseriale = macchinari.get(risp - 1);
			PreparedStatement query1 = con.prepareStatement(
					"INSERT INTO intervento (num_progressivo,macchinario_codice_seriale,in_lavorazione,data_arrivo,tipo)VALUES (?,?,'X',?,'manutenzione')");
			query1.setInt(1, n);
			query1.setString(2, codseriale);
			query1.setDate(3, data);
			query1.executeUpdate();
			System.out.println("L'intervento è stato avviato");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void query6(Connection con) {
		ArrayList<String> operai = new ArrayList<>();
		int count = 1;

		try {
			System.out.println("Elenco dei codici fiscali degli operai :");
			Statement query = con.createStatement();
			String operaio = "select operaio.cf_dipendente FROM operaio";
			ResultSet result = query.executeQuery(operaio);

			while (result.next()) {
				operaio = result.getString("cf_dipendente");
				operai.add(operaio);
				System.out.println(" Operaio " + count + ": " + operaio);
				count++;
			}
			Scanner scan = new Scanner(System.in);
			System.out.println(
					"Scegli il numero corrispondente all'operaio sul quale vuoi effettuare una verifica sulla possibilità di assegnargli un intervento di manutenzione ");

			int risp = scan.nextInt();

			String codfiscdipendente = operai.get(risp - 1);
			int num_interventi;

			Statement query1 = con.createStatement();
			String op1 = "SELECT operaio.interventi_manutenzione FROM operaio WHERE cf_dipendente='" + codfiscdipendente
					+ "'";
			ResultSet result1 = query1.executeQuery(op1);

			while (result1.next()) {
				num_interventi = result1.getInt("interventi_manutenzione");

				if (num_interventi < 3) {
					System.out.println("Operaio scelto disponibile");
				} else {
					System.out.println("Operaio scelto non disponibile, ha già 3 interventi in carico");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void query7(Connection con) {

		ArrayList<String> dipendente = new ArrayList<>();
		ArrayList<String> macchinariobase = new ArrayList<>();
		ArrayList<String> macchinarioaccessorio = new ArrayList<>();
		ArrayList<String> ingegneri = new ArrayList<>();
		int count1 = 1;
		int count2 = 1;
		int count3 = 1;
		ResultSet result;
		ResultSet result1;
		String codseriale = null;
		Random rnd = new Random();
		int n = 1000 + rnd.nextInt((9999 - 1000) + 1);
		long milliseconds = System.currentTimeMillis();
		Date data = new Date(milliseconds);

		try {
			Scanner scan = new Scanner(System.in);
			Statement query = con.createStatement();
			System.out.println(
					"Il macchinario sulla quale vuoi effettuare un intervento, è un macchinario BASE o ACCESSORIA?\n 'B'= base / 'A'= accessoria");
			char risp2 = scan.next().charAt(0);
			if (risp2 == 'B') {
				String macchinari = "select  macchinario.cod_seriale FROM macchinario WHERE macchinario.corriere_id IS null AND macchinario.accessoria_nome IS null AND (macchinario.cod_seriale NOT IN (SELECT intervento.macchinario_codice_seriale FROM intervento))";
				;
				result = query.executeQuery(macchinari);
				System.out.println("Elenco dei macchinari base disponibili sul quale puoi effettuare un intervento  :");
				while (result.next()) {
					String codice_seriale = result.getString("cod_seriale");
					System.out.println(" Macchinario " + count1 + ": " + codice_seriale);

					macchinariobase.add(codice_seriale);
					count1++;
				}
				System.out.println(
						"Scegli il numero corrispondente al macchinario basesul quale vuoi effettuare un intervento");
				int risp = scan.nextInt();
				codseriale = macchinariobase.get(risp - 1);
			} else {
				String macchinari = "select  macchinario.cod_seriale FROM macchinario WHERE macchinario.corriere_id IS null AND macchinario.base_nome IS null AND (macchinario.cod_seriale NOT IN (SELECT intervento.macchinario_codice_seriale FROM intervento))";
				result = query.executeQuery(macchinari);
				System.out.println(
						"Elenco dei macchinari accessori disponibili sul quale puoi effettuare un intervento  :");
				while (result.next()) {
					String codice_seriale = result.getString("cod_seriale");
					System.out.println(" Macchinario" + count2 + ": " + codice_seriale);

					macchinarioaccessorio.add(codice_seriale);
					count2++;
				}
				System.out.println(
						"Scegli il numero corrispondente al macchinario accessorio sul quale vuoi effettuare un intervento");
				int risp = scan.nextInt();
				codseriale = macchinarioaccessorio.get(risp - 1);
			}
			System.out.println(
					"L'intervento che si vuole effettuare è di SOSTITUZIONE o di MANUTENZIONE?\n 'S'= sostituzione / M = manutenzione");
			char risp3 = scan.next().charAt(0);
			Statement query1 = con.createStatement();
			if (risp3 == 'S' && risp2 == 'A') {
				String cfing;
				String ingegnere = "select  ingegnere.dip_codice_fiscale FROM ingegnere ";
				result = query1.executeQuery(ingegnere);

				while (result.next()) {
					String cf = result.getString("dip_codice_fiscale");
					System.out.println(" Ingegnere " + count3 + " disponibile: " + cf);

					ingegneri.add(cf);
					count3++;
				}
				System.out
						.println("Scegli il numero che corrisponde all'ingegnere al quale vuoi affidare l'intervento");
				int risp1 = scan.nextInt();
				cfing = ingegneri.get(risp1 - 1);
				PreparedStatement query2 = con.prepareStatement(
						"INSERT INTO intervento (num_progressivo,macchinario_codice_seriale,richiesto,data_arrivo,tipo)VALUES (?,?,'X',?,'sostituzione')");
				query2.setInt(1, n);
				query2.setString(2, codseriale);
				query2.setDate(3, data);
				query2.executeUpdate();
				System.out.println("L'intervento è stato richiesto");

				PreparedStatement query3 = con.prepareStatement(
						"INSERT INTO coinvolgimento (dipendente_cf,intervento_progressivo,data_inizio,macchinario)VALUES (?,?,?,?)");
				query3.setString(1, cfing);
				query3.setInt(2, n);
				query3.setDate(3, data);
				query3.setString(4, codseriale);
				query3.executeUpdate();
				System.out.println("L'ingegnere è stato coinvolto");

			} else if (risp3 == 'S' && risp2 == 'B') {
				String cfdip;
				String dipendenti = "select  dipendente.codice_fiscale FROM dipendente ";
				result1 = query.executeQuery(dipendenti);

				while (result1.next()) {
					cfdip = result1.getString("codice_fiscale");
					System.out.println(" Dipendente" + count3 + " diponibile: " + cfdip);

					dipendente.add(cfdip);
					count3++;
				}
				System.out
						.println("Scegli il numero che corrisponde al dipendente al quale vuoi affidare l'intervento");
				int risp1 = scan.nextInt();
				cfdip = dipendente.get(risp1 - 1);
				PreparedStatement query2 = con.prepareStatement(
						"INSERT INTO intervento (num_progressivo,macchinario_codice_seriale,richiesto,data_arrivo,tipo)VALUES (?,?,'X',?,'sostituzione')");
				query2.setInt(1, n);
				query2.setString(2, codseriale);
				query2.setDate(3, data);
				query2.executeUpdate();
				System.out.println("L'intervento è stato richiesto");

				PreparedStatement query3 = con.prepareStatement(
						"INSERT INTO coinvolgimento (dipendente_cf,intervento_progressivo,data_inizio,macchinario)VALUES (?,?,?,?)");
				query3.setString(1, cfdip);
				query3.setInt(2, n);
				query3.setDate(3, data);
				query3.setString(4, codseriale);
				query3.executeUpdate();
				System.out.println("Il dipendente è stato coinvolto");

			} else if (risp3 == 'M' && risp2 == 'B') {
				String cfdip;
				String dipendenti = "select  dipendente.codice_fiscale FROM dipendente ";
				result1 = query.executeQuery(dipendenti);

				while (result1.next()) {
					cfdip = result1.getString("codice_fiscale");
					System.out.println(" Dipendente" + count3 + "diponibile: " + cfdip);

					dipendente.add(cfdip);
					count3++;
				}
				System.out
						.println("Scegli il numero che corrisponde al dipendente al quale vuoi affidare l'intervento");
				int risp1 = scan.nextInt();
				cfdip = dipendente.get(risp1 - 1);
				PreparedStatement query2 = con.prepareStatement(
						"INSERT INTO intervento (num_progressivo,macchinario_codice_seriale,richiesto,data_arrivo,tipo)VALUES (?,?,'X',?,'manutenzione')");
				query2.setInt(1, n);
				query2.setString(2, codseriale);
				query2.setDate(3, data);
				query2.executeUpdate();
				System.out.println("L'intervento è stato richiesto");

				PreparedStatement query3 = con.prepareStatement(
						"INSERT INTO coinvolgimento (dipendente_cf,intervento_progressivo,data_inizio,macchinario)VALUES (?,?,?,?)");
				query3.setString(1, cfdip);
				query3.setInt(2, n);
				query3.setDate(3, data);
				query3.setString(4, codseriale);
				query3.executeUpdate();
				System.out.println("Il dipendente è stato coinvolto");

			} else if (risp3 == 'M' && risp2 == 'A') {
				String cfdip;
				String dipendenti = "select  dipendente.codice_fiscale FROM dipendente ";
				result1 = query.executeQuery(dipendenti);

				while (result1.next()) {
					cfdip = result1.getString("codice_fiscale");
					System.out.println(" Dipendente" + count3 + "diponibile: " + cfdip);

					dipendente.add(cfdip);
					count3++;
				}
				System.out
						.println("Scegli il numero che corrisponde al dipendente al quale vuoi affidare l'intervento");
				int risp1 = scan.nextInt();
				cfdip = dipendente.get(risp1 - 1);
				PreparedStatement query2 = con.prepareStatement(
						"INSERT INTO intervento (num_progressivo,macchinario_codice_seriale,richiesto,data_arrivo,tipo)VALUES (?,?,'X',?,'manutenzione')");
				query2.setInt(1, n);
				query2.setString(2, codseriale);
				query2.setDate(3, data);
				query2.executeUpdate();
				System.out.println("L'intervento è stato richiesto");

				PreparedStatement query3 = con.prepareStatement(
						"INSERT INTO coinvolgimento (dipendente_cf,intervento_progressivo,data_inizio,macchinario)VALUES (?,?,?,?)");
				query3.setString(1, cfdip);
				query3.setInt(2, n);
				query3.setDate(3, data);
				query3.setString(4, codseriale);
				query3.executeUpdate();
				System.out.println("Il dipendente è stato coinvolto");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void query8(Connection con) {

		List<String> dipendenti = new ArrayList<>();
		List<String> dipendenti_a = new ArrayList<>();

		try {

			Statement query2 = con.createStatement();
			ResultSet result2 = query2.executeQuery("select * from dipendente");

			while (result2.next()) {
				dipendenti_a.add(result2.getString("codice_fiscale"));
			}

			Statement query1 = con.createStatement();
			ResultSet result = query1.executeQuery("select * from coinvolgimento where data_fine is not null");

			while (result.next()) {
				dipendenti.add(result.getString("dipendente_cf"));
			}

			Map<String, Integer> map = dipendenti_a.stream()
					.collect(Collectors.toMap(Function.identity(), v -> Collections.frequency(dipendenti, v)));

			Iterator<String> it = dipendenti_a.iterator();
			String s;
			Integer o = 0;

			System.out.println("Riparazioni per ogni dipendente:\n");

			while (it.hasNext()) {
				s = it.next();
				o = map.get(s);
				if (o == null) {
					o = 0;
				}
				System.out.println(s + ": " + o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void query9(Connection con) {
		ArrayList<String> macchinario = new ArrayList<>();
		System.out.println(
				"Ecco i macchinari che sono stati affidati ad un corriere oppure per i quali è in atto un intervento di manutenzione con stato 'in lavorazione':");
		try {

			Statement query = con.createStatement();
			String macchinari = "select macchinario.cod_seriale FROM macchinario WHERE macchinario.corriere_id IS NOT NULL ";
			ResultSet result = query.executeQuery(macchinari);
			while (result.next()) {
				String codice_seriale = result.getString("cod_seriale");
				macchinario.add(codice_seriale);
			}
			Statement query1 = con.createStatement();
			String interv = "select intervento.macchinario_codice_seriale FROM intervento WHERE intervento.in_lavorazione IS NOT NULL AND intervento.tipo='manutenzione' ";
			ResultSet result1 = query1.executeQuery(interv);
			while (result1.next()) {
				String codseriale = result1.getString("macchinario_codice_seriale");
				macchinario.add(codseriale);
			}
			for (int i = 0; i < macchinario.size(); i++) {
				System.out.println(macchinario.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void query10(Connection con) {

		ArrayList<String> macchinario = new ArrayList<>();
		try {
			System.out
					.println("Ecco i macchinari per i quali non è stato mai richiesto un intervento di manutenzione: ");
			String query = "SELECT macchinario.cod_seriale FROM macchinario INNER JOIN intervento ON (macchinario.cod_seriale=intervento.macchinario_codice_seriale) WHERE intervento.richiesto IS NULL AND intervento.tipo='manutenzione'";

			Statement pquery = con.createStatement();
			ResultSet result = pquery.executeQuery(query);
			while (result.next()) {
				String codseriale = result.getString("cod_seriale");
				macchinario.add(codseriale);

			}
			for (int i = 0; i < macchinario.size(); i++) {
				System.out.println(macchinario.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void query11(Connection con) {

		List<String> ingegneri = new ArrayList<>();
		List<String> coinvolgimento = new ArrayList<>();

		try {

			Statement query2 = con.createStatement();
			ResultSet result2 = query2.executeQuery("select * from ingegnere");

			while (result2.next()) {
				ingegneri.add(result2.getString("dip_codice_fiscale"));
			}

			Statement query1 = con.createStatement();
			ResultSet result = query1.executeQuery("select * from coinvolgimento");

			while (result.next()) {
				coinvolgimento.add(result.getString("dipendente_cf"));
			}

			Map<String, Integer> map = ingegneri.stream()
					.collect(Collectors.toMap(Function.identity(), v -> Collections.frequency(coinvolgimento, v)));

			Iterator<String> it = ingegneri.iterator();

			String s;
			Integer a;

			System.out.println("Dati ingegneri:\n");

			while (it.hasNext()) {
				s = it.next();
				PreparedStatement q2 = con.prepareStatement("select * from dipendente where codice_fiscale=?");
				q2.setString(1, s);
				ResultSet r2 = q2.executeQuery();
				r2.next();
				System.out.print("codice fiscale: " + r2.getString("codice_fiscale") + " nome: " + r2.getString("nome")
						+ " cognome: " + r2.getString("cognome") + " tipo contratto: " + r2.getString("tipo_contratto")
						+ " numero di telefono: " + r2.getString("numero_telefono"));

				PreparedStatement q1 = con.prepareStatement("select * from ingegnere where dip_codice_fiscale=?");
				q1.setString(1, s);
				ResultSet r1 = q1.executeQuery();
				r1.next();
				System.out.print("specializzazione: " + r1.getString("specializzazione") + " albo: "
						+ r1.getString("albo") + " numero richieste definite: ");
				a = map.get(s);
				if (a == null) {
					System.out.println("0");
				} else {
					System.out.println(a);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void query12(Connection con) {

		List<String> ing = new ArrayList<>();
		List<String> pg = new ArrayList<>();
		List<String> controllocf = new ArrayList<>();
		try {
			System.out
					.println("Ecco i dati degli ingegneri che non hanno mai progettato macchinari di tipo accessorio:");
			Statement query1 = con.createStatement();
			ResultSet result3 = query1.executeQuery("select * from ingegnere");

			while (result3.next()) {

				ing.add(result3.getString("dip_codice_fiscale"));
			}

			String query = "SELECT assegnazione.dipendente FROM assegnazione INNER JOIN macchinario ON assegnazione.progetto=macchinario.progetto_id WHERE macchinario.accessoria_nome IS NULL";

			Statement pquery = con.createStatement();
			ResultSet result = pquery.executeQuery(query);
			while (result.next()) {
				String pg1 = result.getString("dipendente");
				pg.add(pg1);

			}
			for (int i = 0; i < ing.size(); i++) {
				for (int j = 0; j < pg.size(); j++) {
					if (ing.get(i).equals(pg.get(j))) {
						controllocf.add(ing.get(i));
					}

				}
			}
			for (int i = 0; i < controllocf.size(); i++) {

				PreparedStatement query3 = con
						.prepareStatement("SELECT * FROM dipendente WHERE dipendente.codice_fiscale=?");
				query3.setString(1, controllocf.get(i));
				ResultSet result4 = query3.executeQuery();

				while (result4.next()) {
					String cf = result4.getString("codice_fiscale");
					String nome = result4.getString("nome");
					String cognome = result4.getString("cognome");
					String contr = result4.getString("tipo_contratto");
					String tel = result4.getString("numero_telefono");

					System.out.println("codice fiscale dell'ingegnere: " + cf + " Nome: " + nome + " Cognome: "
							+ cognome + " contratto: " + contr + " Numero di telefono: " + tel + " \t");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void query13(Connection con) {

		List<String> macchinariacc = new ArrayList<>();
		List<String> macchinaribs = new ArrayList<>();
		try {
			String query = "SELECT  * FROM macchinario WHERE base_nome IS NULL";

			Statement pquery = con.createStatement();
			ResultSet result = pquery.executeQuery(query);
			System.out.println("Ecco un report che mostra i dati delle categorie di tipo accessorio:\n");
			while (result.next()) {
				String cod_ser = result.getString("cod_seriale");
				String lott = result.getString("num_lotto");
				String des = result.getString("descrizione");
				double prezzo = result.getDouble("prezzo");
				String cor = result.getString("corriere_id");
				String pgid = result.getString("progetto_id");
				String nom = result.getString("accessoria_nome");
				String cf = result.getString("cliente_cf");

				System.out.println("codice seriale del macchinario: " + cod_ser + " numero lotto: " + lott
						+ " descrizione: " + des + " prezzo: " + prezzo + " corriere id: " + cor + " progetto id: "
						+ pgid + " nome del macchinario: " + nom + " cf del cliente: " + cf + " \t");
			}
			Statement query2 = con.createStatement();
			ResultSet result2 = query2
					.executeQuery("select * from macchinario WHERE macchinario.accessoria_nome IS NOT NULL");

			while (result2.next()) {
				macchinariacc.add(result2.getString("accessoria_nome"));
			}

			Statement query1 = con.createStatement();
			ResultSet result3 = query1.executeQuery("select * from utilizzo ");

			while (result3.next()) {

				macchinaribs.add(result3.getString("nome_accessorio"));

			}

			Map<String, Integer> map = macchinariacc.stream()
					.collect(Collectors.toMap(Function.identity(), v -> Collections.frequency(macchinaribs, v)));

			Iterator<String> it = macchinariacc.iterator();
			String s;
			Integer o = 0;

			System.out.println(
					"\nQuantità totale di macchinari di tipo 'base' a cui sono stati associati i seguenti macchinari accessori:\n");

			while (it.hasNext()) {
				s = it.next();
				o = map.get(s);
				if (o == null) {
					o = 0;
				}

				System.out.println(s + ": " + o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void query14(Connection con) {
		try {
			String query = "SELECT  operaio.cf_dipendente,operaio.num_ore_manutenzione,operaio.interventi_manutenzione FROM operaio";

			Statement pquery = con.createStatement();
			ResultSet result = pquery.executeQuery(query);
			System.out.println(
					"Ecco un report che mostra i dati degli operai, compreso il numero totale di ore dedicate agli interventi di manutenzione\n");
			while (result.next()) {
				String cf = result.getString("cf_dipendente");
				int ore = result.getInt("num_ore_manutenzione");
				int interventi = result.getInt("interventi_manutenzione");

				System.out.println("codice fiscale dell'operaio: " + cf
						+ " Numero totale di ore dedicate agli interventi di manutenzione: " + ore
						+ " Numero di interventi di manutenzione che l'operaio ha in carico : " + interventi + " \t");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void query15(Connection con) {
		try {
			String query = "SELECT  cliente.codice_fiscale,cliente.nome,cliente.cognome,cliente.via,cliente.cap,cliente.numero_civico,cliente.numero_di_telefono,cliente.acquisti_effettuati,cliente.email FROM cliente";

			Statement pquery = con.createStatement();
			ResultSet result = pquery.executeQuery(query);
			System.out.println(
					"Ecco un report che mostra i dati dei clienti, incluso il numero totale di prodotti acquistati\n ");
			while (result.next()) {
				String cf = result.getString("codice_fiscale");
				String nome = result.getString("nome");
				String cognome = result.getString("cognome");
				String via = result.getString("via");
				String cap = result.getString("cap");
				int civic = result.getInt("numero_civico");
				String tel = result.getString("numero_di_telefono");
				int acq = result.getInt("acquisti_effettuati");
				String email = result.getString("email");

				System.out.println(
						"codice fiscale cliente: " + cf + " Nome: " + nome + " Cognome: " + cognome + " Indirizzo: via "
								+ via + " Cap: " + cap + " Numero civico: " + civic + "  Numero di telefono: " + tel
								+ " NUMERO TOTALE DI ACQUISTI EFFETUATI: " + acq + " Email: " + email + " \t");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
