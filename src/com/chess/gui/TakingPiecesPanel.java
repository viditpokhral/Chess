package com.chess.gui;

import com.chess.engine.Alliance;
import com.chess.engine.board.Move;
import com.chess.engine.piece.Piece;
import com.chess.gui.Table.MoveLog;
import com.google.common.primitives.Ints;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TakingPiecesPanel extends JPanel {

    private final JPanel northPanel ;
    private final JPanel southPanel ;

    private static final Color PANEL_COLOR = Color.decode("#FFE5B4");
    private static final Dimension TAKEN_PIECES_DIMENSION = new Dimension(40,80);
    private static final EtchedBorder PANEL_BOARDER = new EtchedBorder(EtchedBorder.RAISED);
    
    public TakingPiecesPanel() {
        super(new BorderLayout());
        setBackground(PANEL_COLOR);
        setBorder(PANEL_BOARDER);
        this.northPanel= new JPanel(new GridLayout(8,2));
        this.southPanel= new JPanel(new GridLayout(8,2));
        this.northPanel.setBackground(PANEL_COLOR);
        this.southPanel.setBackground(PANEL_COLOR);
        this.add(this.northPanel,BorderLayout.NORTH);
        this.add(this.southPanel,BorderLayout.SOUTH);
        setPreferredSize(TAKEN_PIECES_DIMENSION);
    }

    public void redo(final MoveLog moveLog) {

        this.northPanel.removeAll();
        this.southPanel.removeAll();

        final List<Piece> whiteTakenPieces=new ArrayList<>();
        final List<Piece> blackTakenPieces=new ArrayList<>();

        for (final Move move : moveLog.getMoves()) {
            if (move.isAttacked()){
                final Piece takenPiece = move.getAttackedPiece();
                if (takenPiece.getPieceAlliance().isWhite()){
                    whiteTakenPieces.add(takenPiece);
                }
                else if (takenPiece.getPieceAlliance().isBlack()){
                    blackTakenPieces.add(takenPiece);
                }
                else {
                    throw new IllegalStateException("should not reach here");
                }
            }
        }
        Collections.sort(whiteTakenPieces, new Comparator<Piece>() {
            @Override
            public int compare(Piece o1, Piece o2) {
                return Ints.compare(o1.getPieceValue(),o2.getPieceValue());
            }
        });
        Collections.sort(blackTakenPieces, new Comparator<Piece>() {
            @Override
            public int compare(Piece o1, Piece o2) {
                return Ints.compare(o1.getPieceValue(),o2.getPieceValue());
            }
        });

        for(final Piece takenPieces : whiteTakenPieces){
            try {
                final BufferedImage image= ImageIO.read(new File("art/simple"+
                        takenPieces.getPieceAlliance().toString().charAt(0)+""+
                        takenPieces.toString()));
                final ImageIcon icon=new ImageIcon();
                final JLabel imageLabel=new JLabel();
                this.southPanel.add(imageLabel);

            }catch (final Exception e){
                e.printStackTrace();
            }
        }
        for(final Piece takenPieces : blackTakenPieces){
            try {
                final BufferedImage image= ImageIO.read(new File("art/simple"+
                        takenPieces.getPieceAlliance().toString().charAt(0)+""+
                        takenPieces.toString()));
                final ImageIcon icon=new ImageIcon();
                final JLabel imageLabel=new JLabel();
                this.northPanel.add(imageLabel);

            }catch (final Exception e){
                e.printStackTrace();
            }
        }
        validate();
    }
}
