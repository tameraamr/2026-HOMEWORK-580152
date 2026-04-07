# DiaDia - Programmazione Orientata agli Oggetti

Questo repository contiene il codice sorgente relativo all'**Homework A** del corso di Programmazione Orientata agli Oggetti (POO).

## Autori
Tamer Omar - Matricola: 580152


## Descrizione del Progetto
DiaDia è un semplice gioco di ruolo testuale ambientato all'interno dell'università. Il giocatore può muoversi tra diverse stanze, raccogliere attrezzi, riporli nella propria borsa e cercare di raggiungere la stanza vincente (la Biblioteca) prima di esaurire i CFU a disposizione.

## Esercizi Implementati (Homework A)
In questa versione (Release HWA) sono stati completati i seguenti esercizi:
1. **Testing:** Scrittura di test JUnit 5 per le classi principali (`Stanza`, `Partita`, `Borsa`, `Giocatore`, `Labirinto`) garantendo minimalità e indipendenza dei test-case.
2. **Refactoring (Labirinto, Giocatore, Borsa):** Riorganizzazione delle responsabilità estraendo la logica di creazione del labirinto e la gestione dell'inventario e dei CFU in classi dedicate.
3. **Nuovi Comandi:** Implementazione dei comandi `prendi <attrezzo>` e `posa <attrezzo>` per l'interazione tra l'ambiente (Stanza) e l'inventario (Borsa).
4. **Package:** Organizzazione del codice in package logici (`it.uniroma3.diadia`, `it.uniroma3.diadia.ambienti`, `it.uniroma3.diadia.attrezzi`, `it.uniroma3.diadia.giocatore`).
5. **Disaccoppiamento I/O:** Centralizzazione della gestione degli input e output (System.in / System.out) tramite l'introduzione e l'utilizzo esclusivo della classe `IOConsole`.
