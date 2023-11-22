package boardGame.movement

import FailedOutcome
import Outcome
import SuccessfulOutcome
import boardGame.board.Board
import boardGame.board.Vector
import boardGame.piece.BasicPiece
import boardGame.piece.Piece

class PerformMovementAndPromoteIfPossible(private val movementP: MovementPerformer): MovementPerformer {
    override fun performMovement(piecePosition: Vector, too: Vector, board: Board): Outcome<MovementResult> {
        val result: MovementResult = when (val output = movementP.performMovement(piecePosition, too, board)){
            is SuccessfulOutcome -> output.data
            is FailedOutcome -> return output
        }

        val piece: Piece = when (val outcome = result.newBoard.getPieceInPosition(too)){
            is SuccessfulOutcome -> outcome.data
            is FailedOutcome -> return SuccessfulOutcome(result)
        }

        if (!isAbleToPromote(piece, result.newBoard, too)) return SuccessfulOutcome(result)

        TODO("This shit is not working")
        val promotedPiece = BasicPiece(0, piece.getPieceColor())
        val newBoard: Board = result.newBoard.addPiece(promotedPiece, too)

        val events: List<MovementEvent> = result.movementEvents + listOf<MovementEvent>(Promotion(piece, promotedPiece, too))
        return SuccessfulOutcome(MovementResult(2, newBoard, events))
    }

    private fun isAbleToPromote(piece: Piece, board: Board, too: Vector): Boolean {
        val yDir = if (piece.getPieceColor() == 0) {-1} else {1}
        return !board.positionExists(too + Vector(0, yDir))
    }
}