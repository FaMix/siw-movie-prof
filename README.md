# siw-movie-prof
progetto assegnato dal prof

CASO D'USO UC1: Inserimento Film<br>
Attore primario: Amministratore<br>
Pre-condizioni: L'amministratore è autenticato<br>
Post-condizioni: Il film viene registrato, il database viene aggiornato.<br>
Scenario principale di successo:
1. L'amministratore inizia l'inserimento di un nuovo film.
2. Il sistema mostra la pagina per aggiungere un nuovo film.
3. L'amministratore inserisce nome, anno ed un'immagine per il film.
4. Il sistema registra il film e mostra la pagina del nuovo film.<br>

Estensioni:<br>
3a. Il film non viene inserito correttamente, il sistema ritorna alla pagina di inserimento.
<br>
<br>
<br>
CASO D'USO UC2: Aggiornamento film<br>
Attore primario: Amministratore<br>
Pre-condizioni: L'amministratore è autenticato<br>
Post-condizioni: Il film viene aggiornato, il database viene aggiornato.<br>
Scenario principale di successo:
1. L'amministratore inizia l'aggiornamento di un film.
2. Il sistema mostra la pagina di aggiornamento di un film.
3. L'amministratore aggiorna nome o anno, inoltre può aggiungere una nuova immagine.
4. Il sistema aggiorna il film e mostra la pagina del film aggiornata.<br>

Estensioni:<br>
3a. Il film non viene aggiornato correttamente, il sistema ritorna alla pagina di aggiornamento.
<br>
<br>
<br>
CASO D'USO UC3: Inserimento Recensione<br>
Attore primario: Utende Registrato<br>
Pre-condizioni: L'utente è autenticato.<br>
Post-condizioni: La recensione viene registrata ed aggiunta al film, il database viene aggiornato.<br>
Scenario principale di successo:
1. L'utente inizia l'inserimento di una recenesione per un film.
2. Il sistema mostra la pagina di inserimento recensione per un film.
3. L'utente inserisce titolo, testo e voto della recensione.
4. Il sistema registra la recenesion, la aggiunge alla lista di recensioni del film e mostra la pagine del film con la nuova recensione.
   
Estensioni:<br>
3a. La recensione non viene inserita correttamente, il sistema ritorna alla pagina di inserimento.
<br>
<br>
<br>
CASO D'USO UC4: Aggiornamento recensione<br>
Attore primario: Utente Registrato<br>
Pre-condizioni: L'utente è autenticato.<br>
Post-condizioni: La recensione viene aggiornata, il database viene aggiornato.<br>
Scenario principale di successo:
1. L'utente inizia l'aggiornamento di una recensione per un film.
2. Il sistema mostra la pagina di aggiornamento di una recensione per un film.
3. L'utente aggiorna nome, anno oppure voto della recensione.
4. Il sistema aggiorna la recensione e mostra la pagina del film aggiornata.

Estensioni:<br>
3a. La recensione non viene aggiornata correttamente, il sistema ritorna alla pagina di aggiornamento.
<br>
<br>
<br>
CASO D'USO UC5: Consulta Recensione<br>
Attore primario: Utente non registrato<br>
Pre-condizioni: Nessuna<br>
Post-condizioni: Nessuna<br>
Scenario principale di successo:
1. L'utente accede alla pagina principale del sistema.
2. Il sistema mostra la pagina principale.
3. L'utente preme il pulsante per consultare la lista di film.
4. Il sistema mostra l'archivio dei film presenti.
5. L'utente apre il film di interesse.
6. Il sistema mostra la pagina con il film scelto e le sue recensioni.
<br>
<br>
<br>
CASO D'USO UC6: Consulta Film Diretti da Regista<br>
Attore primario: Utente non registrato<br>
Pre-condizioni: Nessuna<br>
Post-condizioni: Nessuna<br>
Scenario principale di successo:<br>
1. L'utente accede alla pagina principale del sistema.
2. Il sistema mostra la pagina principale.
3. L'utente preme il pulsante per consultare la lista di artisti.
4. Il sistema mostra l'archivio degli artisti presenti.
5. L'utente apre la pagina dell'artista di interesse.
6. Il sistema mostra la pagina con l'artista scelto con la lista dei film che ha diretto.
