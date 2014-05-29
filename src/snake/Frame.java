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

    private JMenuBar initMenu() {
        // Napravimo liniju menija
        JMenuBar menuBar = new JMenuBar();
        // Mapravimo meni
        JMenu gameMenu = new JMenu("Game");

        // Napravimo stavku za meni
        JMenuItem help = new JMenuItem("Help");
        JMenuItem newGame = new JMenuItem("New game");
        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                board.startGame();
            }
        });
         
        // Dodamo stavku u meni
        gameMenu.add(newGame);
        gameMenu.add(help);
        // Dodamo meni u liniju menija
        menuBar.add(gameMenu);

        return menuBar;
    }
}
