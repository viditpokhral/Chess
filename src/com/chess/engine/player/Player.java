package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.piece.King;
import com.chess.engine.piece.Piece;

import java.util.Collection;

public abstract class Player {
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMove;

    Player(final Board board,
           final Collection<Move> legalMove,
           final Collection<Move> opponentMoves){

        this.board = board;
        this.playerKing = establishKing();
        this.legalMove = legalMove;
    }

    private King establishKing() {
        for (final Piece piece : getActivePieces()){
            if (piece.getPieceType().isKing()){
                return (King)piece;
            }
        }
        throw new RuntimeException("It should not go there! Not a valid board");
    }
    public abstract Collection<Piece> getActivePieces();
}
