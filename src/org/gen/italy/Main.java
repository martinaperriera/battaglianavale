package org.gen.italy;

import java.util.Scanner;
import java.util.Random; 

public class Main { 

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        Random r = new Random(); // oggetto Random per generare numeri casuali
        char [][] campo = new char[8][8]; //matrice 8x8 int utilizzata per rappresentare il campo da gioco 
        boolean naveAffondata = false; // variabile per tenere traccia se la nave è stata affondata
        int[][] nave = new int[3][2]; //coordinate nave (3celle)
        
        // ciclo for x creazione campo da gioco con acqua
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                campo[i][j] = 0 ; // ogni cella è inizialmente acqua
            }
        }

        // posizionamento della nave in modo casuale
        boolean orizzontale = r.nextBoolean(); // decide casualmente se la nave è orizzontale o verticale
        int riga, colonna;

        if (orizzontale) { //nave orizzontale
            riga = r.nextInt(8); //riga casuale tra 0 e 7
            colonna = r.nextInt(6); //colonna casuale tra 0 e 5
            for (int i = 0; i < 3; i++) {
                nave[i][0] = riga; // imposta la riga per tutte e 3 le celle
                nave[i][1] = colonna + i; // imposta la colonna incrementando di 1 per ogni cella
            }
        } else { //verticale
            riga = r.nextInt(6); // riga casuale tra 0 e 5 (per garantire che la nave di lunghezza 3 non esca dalla matrice)
            colonna = r.nextInt(8); //0-7
            for (int i = 0; i < 3; i++) {
                nave[i][0] = riga + i; // imposta la riga incrementando di 1 per ogni cella
                nave[i][1] = colonna; // imposta la colonna per tutte e 3 le celle
            }
        }

        // loop del gioco usando do-while
        do {
            System.out.println("Inserisci la tua mossa (riga, colonna): "); 
            int mossaRiga = sc.nextInt();
            int mossaColonna = sc.nextInt();

            boolean colpita = false; // variabile per verificare se la nave è stata colpita
            boolean tutteColpite = true; // variabile per verificare se tutte le celle della nave sono state colpite

            //verifica se la mossa colpisce la nave
            for (int i = 0; i < 3; i++) {
                if (nave[i][0] == mossaRiga && nave[i][1] == mossaColonna) { //usiamo matrice per accedere alle coordinate della nave - nave[i][0]=riga della cella della nave e nave[i][1] è la colonna della cella della nave
                	// con questo if si controlla se la mossa dell'utente corrisponde a una delle celle della nave 
                    campo[mossaRiga][mossaColonna] = 'X'; // segna la cella colpita con 'X'
                    colpita = true;
                }
                if (campo[nave[i][0]][nave[i][1]] != 'X') { //verifica se una delle celle non è stata colpita
                    tutteColpite = false; // se almeno una cella della nave non è colpita, tutteColpite è false
                }
            }

            if (tutteColpite) {
                naveAffondata = true; // se tutte le celle della nave sono colpite, la nave è affondata
                System.out.println("Affondata!");
            } else if (colpita) {
                System.out.println("Colpita!");
            } else {
                System.out.println("Acqua!!!");
            }

            // stampa campo da gioco aggiornato
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.print(campo[i][j] + " "); // per stampare ogni cella del campo
                }
                System.out.println();
            }
        } while (!naveAffondata); // la condizione viene verificata alla fine del ciclo e il ciclo continua finché la nave non è affondata

        System.out.println("La nave è affondata!!!!!");
        sc.close();
    }

	}


