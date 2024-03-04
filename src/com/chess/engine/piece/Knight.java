package com.chess.engine.piece;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.board.Tiles;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece
{
    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17,-15,-10,-6,6,10,15,17}; // this are the coordinates where knight can move to
    public Knight(final Alliance pieceAlliance, final int piecePosition) {
        super(piecePosition, pieceAlliance);
    }
    @Override
    public Collection<Move> calculateLegalMove(final Board board) {
        List<Move> legalMove = new ArrayList<>();
        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES){
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                if(isFirstColumn(this.piecePosition, currentCandidateOffset) ||
                    isSecondColumn(this.piecePosition, currentCandidateOffset)||
                    isSeventhColumn(this.piecePosition, currentCandidateOffset)||
                    isEighthColumn(this.piecePosition, currentCandidateOffset)) continue;
                final Tiles candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if(!candidateDestinationTile.isTileOccupied()){
                    legalMove.add(new MajorMove(board,this,candidateDestinationCoordinate));
                }
                else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if(this.pieceAlliance !=pieceAlliance)
                        legalMove.add(new AttackMove(board,this,candidateDestinationCoordinate, pieceAtDestination));
                }
            }
        }
         return ImmutableList.copyOf(legalMove);
    }
    @Override
    public String toString(){
        return PieceType.KNIGHT.toString();
    }

    private static boolean isFirstColumn(final int currentPosition,final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] &&
                (candidateOffset == -17 || candidateOffset == -10 || candidateOffset == 6 || candidateOffset == 15);
    }
    private static boolean isSecondColumn(final int currentPosition,final int candidateOffset){
        return BoardUtils.SECOND_COLUMN[currentPosition] &&
                (candidateOffset == -10 || candidateOffset == 6);
    }
    private static boolean isSeventhColumn(final int currentPosition,final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] &&
                (candidateOffset == 10 || candidateOffset == -6);
    }
    private static boolean isEighthColumn(final int currentPosition,final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] &&
                (candidateOffset == 17 || candidateOffset == 10 || candidateOffset == -6 || candidateOffset == -15);
    }
}
