package com.chess.gui;
// can also use javafx
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table {
    private final JFrame gameFrame;
    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600,600);
    public Table(){
        this.gameFrame = new JFrame("JChess");  // Title of the frame
        final JMenuBar tableMenuBar = createTableMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);   // associate tableMenuBar
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);  // Give dimensions to the frame
        this.gameFrame.setVisible(true);    // Make the frame visible
    }

    private JMenuBar createTableMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
         tableMenuBar.add(createFileMenu());
         return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("Load PGN File");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open up that pgn file!");
            }
        });
        fileMenu.add(openPGN);
        return fileMenu;
    }
}
