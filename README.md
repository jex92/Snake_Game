Snake_Game
==========
Snake game je jednostavna igrica. Zmija se kreće po boardu i skuplja poene tako što jede hranu. Takođe nakon svake pojedene hrane zmija se povecava.
Hrana (jabuka) se nalazi na boardu na random mestu.
Zmija može da prolazi kroz zidove, tj. ako udari o jednu stranu boarda, pojavi se na drugoj strani. Ukoliko zmija udari u svoj rep igra je gotova.

Igra sadrži sledeće klase:
Snake
BodySnake
Board
Food
Frame
HelpFrame
GameObject

Klasa Snake sadrži metodu main.

Klasa BodySnake nasleđuje GameObject.
Karakteriše je niz pravougaonika koji predstavljaju telo zmije koje se povećava kada zmija pojede hranu.
Zbog problema u kretanju zmije, klasa ima atribute realMovingState i usersLastMovingState.
U metodi public void move() ispitujemo kada može biti realMovingState = usersLastMovingState, 
i kada je to ispunjeno ispituje se kakav je status realMovingState i u zavisnosti od tog stanja implementira se kretanje zmije.

Klasa Board nasleduje klasu JPanel. JPanel definiše nešto poput fizickog panoa, koji koristimo kao kontejner za grupisanje skupa komponenata.
Klasa sadrži atribute kao što je širina, dužina boarda, atribut random klase Random, atribut runner klase Thread (nit), boolean inGame, kao i mnoge druge korištene atribute u ovoj klasi poput atributa foodEaten, speed...
U konstruktoru klase pravimo objekte koji čine našu igru: bodysnake, food, nit; koristimo već navedene atribute za širinu i dužinu boarda kako bi napravili naš board, pokrećemo nit: runner.start().
Metoda public void paint(Graphics g) služi za iscrtavanje i iscrtava jedno ukoliko je inGame = true, a drugo ako je 
inGame = false.
Ukoliko je inGame = true iscrtava se teran, pozadina, objekti u igri, a ukoliko je inGame = false iscrtava se pozadi i poruka koja nam govori koliko smo bodova osvojili tokom igre.
Metoda private void detectCollision() ispituje da li je zmija pojela hranu, tj.da li je doslo do preklapanja zmije i hrane. Ukoliko jeste, zmija raste, broj pojedene hrane se povećava, a samim tim i skor.
Takođe, sve dok hrana preseca zmiju traži se novo random mesto za hranu. U ovoj metodi se vrši ubrzavanje zmije. 
Na svaku petu pojedenu jabuku zmija se ubrzava, tj. speed se smanjuje za 20. Metoda poziva metodu hitItself() klase BodySnake.
U klasi Board imamo jos i klasu GameKeyAdapter u kojoj se upravljackim tasterima na tataturi ( gore, dole, levo i desno) govori šta zmija da radi.

Klasu Food karakteriše gornja leva tacka na ekranu, izražena kroz x i y vrijednosti, i stranica.

Klasa Frame nasledjuje klasu JFrame. Klasa ima jednu metodu private JMenuBar initMenu() kojom pravi meni igrice.

Klasa HelpFrame nasleduje klasu JFrame kao i klasa Frame.  
HelpFrame ima jedan textArea u koji se ispisuje tekst pritiskom na jedan od 4 dugmica tj.labele. U kodu ispisujemo tekst koji želimo da nam piše u TextArea pritiskom na odredeno dugme.
Frame ima i dugme koje je zaduženo da zatvori prozor HelpFrame.
