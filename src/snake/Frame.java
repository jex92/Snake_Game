package snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Frame extends JFrame {

    Board board = new Board();

    public Frame() {
        add(board);
        setJMenuBar(initMenu());
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Snake");

        setVisible(true);
    }

    /**
     * Metoda koja pravi meni u igri sa stavkama New Game i Help.
     */
    private JMenuBar initMenu() {
        // Napravimo liniju menija
        JMenuBar menuBar = new JMenuBar();
        // Mapravimo meni
        JMenu gameMenu = new JMenu("Game");

        // Napravimo stavku za meni
        JMenuItem newGame = new JMenuItem("New game");
        JMenuItem Help = new JMenuItem("Help");
        JMenuItem Scores = new JMenuItem("Scores");

        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                board.startGame();
            }
        });

        Help.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                board.help.setVisible(true);
            }
        });
        
        Scores.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                board.readTextFileLineByLine();
            }
        });

        // Dodamo stavku u meni
        gameMenu.add(newGame);
        gameMenu.add(Scores);
        gameMenu.add(Help);

        // Dodamo meni u liniju menija
        menuBar.add(gameMenu);

        return menuBar;
    }
}
