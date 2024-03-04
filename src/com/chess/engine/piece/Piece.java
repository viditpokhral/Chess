package com.chess.engine.piece;

import com.chess.engine.Alliance;
import com.chess.engine.board.Move;
import com.chess.engine.board.Board;

import java.util.Collection;

public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;
    protected Piece(final int piecePosition, final Alliance pieceAlliance){
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        // TODO work here
        this.isFirstMove = false;
    }
    public Alliance getPieceAlliance(){
        return this.pieceAlliance;
    }
    public abstract Collection<Move> calculateLegalMove(final Board board);

    protected boolean isFirstMove() {
        return this.isFirstMove;
    }

    public Integer getPiecePosition() {
        return this.piecePosition;
    }
    public enum PieceType{

        PAWN("PAWN"),
        KNIGHT("KNIGHT"),
        BISHOP("BISHOP"),
        ROOK("ROOK"),
        QUEEN("QUEEN"),
        KING("KING");



        private final String pieceName;

        PieceType(final String pieceName){
            this.pieceName = pieceName;
        }
        @Override
        public String toString(){
            return this.pieceName;
        }
    };
}
