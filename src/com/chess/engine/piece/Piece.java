package com.chess.engine.piece;

import com.chess.engine.Alliance;
import com.chess.engine.board.Move;
import com.chess.engine.board.Board;

import java.util.Collection;

public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected Piece(final int piecePosition, final Alliance pieceAlliance){
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
    }
    public Alliance getPieceAlliance(){
        return this.pieceAlliance;
    }
    public abstract Collection<Move> calculateLegalMove(final Board board);
}
