package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Board extends JPanel implements Runnable {

    /**
     * Širina table
     */
    public final int PANEL_WIDTH = 600;
    /**
     * Visina table
     */
    public final int PANEL_HEIGHT = 600;

    final Color BACKGROUND_COLOR = Color.BLACK;

    private final ImageIcon grass;
    private final ImageIcon background;

    final Thread runner;

    int foodEaten;

    Random random;

    Boolean inGame;

    BodySnake bodysnake;
    Food food;
    HelpFrame help;

    private String playerName;
    int speed;
    int gameScore = 0;

    /**
     * Podrazumjevani konstruktor. Postavlja veličinu table, boju pozadine i
     * font, inicijalizuje početni rezultat, te objekte u igri. Inicijalizuje i
     * pokreće radnu nit.
     */
    public Board() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        setFocusable(true);

        setFont(getFont().deriveFont(Font.BOLD, 18f));
        setDoubleBuffered(true);

        inGame = false;
        speed = 150;

        grass = new ImageIcon(getClass().getResource("grass.jpg"));
        background = new ImageIcon(getClass().getResource("background.png"));

        random = new Random();
        bodysnake = new BodySnake(this);
        food = new Food(random30(), random30());
        help = new HelpFrame(this);

        addKeyListener(new GameKeyAdapter());

        runner = new Thread(this);
        runner.start();
    }

    /**
     * Metoda koja vraća skor koji smo osvojili. Skor se nakon svake pojedene
     * hrane uvećava za 10.
     */
    int getGameScore() {
        return gameScore;
    }

    /**
     * Metoda u kojoj postavljamo broj pojedene hrane na 0, zatim
     * inicijalizujemo inGame = true i pravimo novi objekat klase BodySnake.
     */
    public void startGame() {
        foodEaten = 0;
        bodysnake = new BodySnake(this);
        inGame = true;

    }

    /**
     * Metoda u kojoj postavljamo inGame = false. Nakon završetka igre unosimo
     * svoje ime u JPanelOption message dialog box. Ime zajedno sa postignutim
     * reziltatom se pamti u listu skoreva u dokumentu results.txt.
     */
    public void stopGame() {

        playerName = JOptionPane.showInputDialog(null, "Please, enter your name:", "You scored " + getGameScore() + " points", JOptionPane.INFORMATION_MESSAGE);
        try {

            List<String> scores = load("src/snake/results.txt");
            scores.add(playerName + " - " + getGameScore());
            save_file("src/snake/results.txt", scores); //igra je sacuvana

        } catch (IOException ex) { //ako ne moze da bude prikazano, objavi gresku
            System.out.println("Error : " + ex);
        }
        inGame = false;

    }

    /**
     * Metoda koja služi za iscrtavanje i iscrtava jedno ukoliko je inGame
     * =true, a drugo ako je inGame = false. Ukoliko je inGame = true iscrtava
     * se teran, pozadina, objekti u igri,tekst koji prati skor, a ukoliko je
     * inGame = false iscrtava se samo pozadina.
     */

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        if (inGame) {
            // Savjeti pri iscrtavanju

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // Iscrtaj teren
            g2.drawRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
            g2.setColor(Color.BLACK);

            g2.drawImage(grass.getImage(), 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);
            // Iscrtaj sve objekte
            bodysnake.draw(g2);
            food.draw(g2);

            Font f = new Font("Helvetica", Font.PLAIN, 30);
            g2.setFont(f);
            g2.setColor(Color.WHITE);
            g2.drawString("Score: " + getGameScore(), 10, 30);

            Toolkit.getDefaultToolkit().sync();

            // Optimizacija upotrebe RAM-a, 
            g.dispose();
        } else {
            g2.drawImage(background.getImage(), 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);
            Font f = new Font("Helvetica", Font.PLAIN, 30);
            g2.setFont(f);
        }
    }

    /**
     * Metoda koja vraća novi slučajan broj.
     */
    private int random30() {
        return random.nextInt(30);
    }

    /**
     * Metoda u kojoj se poziva metoda move() klase BodySnake.
     */
    public void update() {
        bodysnake.move();

    }

    /**
     * Metoda u kojoj ispitujemo da li je zmija pojela hranu, tj.da li je došlo
     * do preklapanja zmije i hrane. Ukoliko jeste, zmija raste, broj pojedene
     * hrane se povećava, kao i skor. Takode, sve dok hrana preseca zmiju traži
     * se novo random mesto za hranu. U ovoj metodi se vrši ubrzavanje zmije. Na
     * svaku petu pojedenu jabuku zmija se ubrzava, tj. speed se smanjuje za 20.
     * Metoda poziva metodu hitItself() klase BodySnake.
     */
    private void detectCollision() {
        if (bodysnake.getBoundsHead().intersects(food.getBounds())) {
            bodysnake.grow();

            while (foodIntersectsSnake()) {
                food = new Food(random30(), random30());
            }
            foodEaten++;
            gameScore += 10;
            if (foodEaten % 5 == 0 && speed > 50) {
                speed = speed - 20;
            }
        }
        bodysnake.hitItself();

    }

    /**
     * Metoda poziva metodu update(), detectCollision() koje smo maločas
     * opisali, zatim vrši ponovno iscrtavanje.
     */
    @Override
    public void run() {

        while (true) {

            if (inGame) {
                update();
                detectCollision();
                repaint();
            }

            try {

                Thread.sleep(speed);

            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }

        }

    }

    /**
     * Metoda koja provera da li se zmija preseca sa hranom.
     */
    private boolean foodIntersectsSnake() {
        for (int i = 0; i < bodysnake.snake.size(); i++) {
            if (bodysnake.snake.get(i).intersects(food.getBounds())) {
                return true;
            }
        }
        return false;
    }

    //Čuvanje razultata u datoteci
    private void save_file(String name_fale, List<String> scores) throws IOException {

        File file = new File(name_fale);
        if (!file.exists()) { //Ako ne postoji datoteka, kreirati je
            file.createNewFile();
        }
        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            for (String score : scores) {
                writer.println(score);
            }
        }
    }

    // postaviti u listu rezultate(punjenje liste)
    private List<String> load(String file_name) throws FileNotFoundException {
        File file = new File(file_name);

        if (!file.exists()) {
            //ako ne postoji datotetka, izbaci izuzetak
            throw new FileNotFoundException();
        }

        List<String> scores = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) { //kreiranje citaoca koji unosi podatke
            while (scanner.hasNextLine()) {
                scores.add(scanner.nextLine()); //postavljanje rezultata u listu

            }
        }

        return scores;
    }

    public static void readTextFileLineByLine() {
        FileReader in = null;
        //BufferedReader dozvoljava čitanje većeg "komada" datoteke odjednom.
        BufferedReader bin = null;

        try {

            File file = new File("src/snake/results.txt");

            in = new FileReader(file);
            // Za inicijalizaciju, BufferedReader zahtjeva otvoren FileReader tok
            bin = new BufferedReader(in);

            String data;
            ArrayList<String> rijeci = new ArrayList<>();

            /*
             * Metoda readLine klase BufferedReader učitava jedan red teksta iz
             * datoteke. Vraća null ukoliko dođe do kraja datoteke.
             */
            while ((data = bin.readLine()) != null) {
                rijeci.add(data);
            }

            int d = rijeci.size();

            String strLine = "";

            for (int i = 0; i < d; i++) {
                strLine += (i + 1) + ". " + rijeci.get(i) + "\n";
            }
            JOptionPane.showMessageDialog(null, strLine, "Scores", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } finally {
            if (bin != null) {
                try {
                    bin.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        }
    }

    class GameKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_LEFT) {
                bodysnake.moveLeft();
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                bodysnake.moveRight();
            } else if (keyCode == KeyEvent.VK_UP) {
                bodysnake.moveUp();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                bodysnake.moveDown();
            }
        }

    }
}
