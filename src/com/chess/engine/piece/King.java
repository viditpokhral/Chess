package com.chess.engine.piece;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tiles;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece{
    private final static int[] CANDIDATE_MOVE_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};
    public King(final Alliance pieceAlliance, final int piecePosition) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMove(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){
            final int candidateDestinationCoordinates = this.piecePosition + currentCandidateOffset;

            if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                isEightColumnExclusion(this.piecePosition, currentCandidateOffset)) continue;

            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinates)){
                final Tiles candidateDestinationTile = board.getTile(candidateDestinationCoordinates);
                if (!candidateDestinationTile.isTileOccupied()){
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinates));
                }
                else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if(this.pieceAlliance !=pieceAlliance)
                        legalMoves.add(new Move.AttackMove(board,this,candidateDestinationCoordinates, pieceAtDestination));
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
    @Override
    public String toString(){
        return PieceType.KING.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition,final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] &&
                (candidateOffset == -9 || candidateOffset == -1 || candidateOffset == 7);
    }
    private static boolean isEightColumnExclusion(final int currentPosition,final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] &&
                (candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9);
    }

}
