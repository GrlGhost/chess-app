package chess.movementStrategy.validators

import FailedOutcome
import SuccessfulOutcome
import boardGame.board.Board
import boardGame.board.Vector
import boardGame.movement.MovementValidator
import boardGame.pieceEatingRuler.PieceEatingRuler
import boardGame.player.Player
import kotlin.math.abs

object DiagonalMovement: MovementValidator {
    override fun validate(pieceEatingRuler: PieceEatingRuler, player: Player, actual: Vector,
                          destination: Vector, board: Board
    ): Boolean {
        if (!destinationAndActualExist(board, actual, destination)) return false
        if (abs(actual.x - destination.x) != abs(actual.y - destination.y)) return false

        return pathIsEmpty(actual, destination, board)
    }

    private fun pathIsEmpty(origin: Vector, destiny: Vector, board: Board): Boolean {
        val xDir = if (origin.x < destiny.x) 1 else -1
        val yDir = if (origin.y < destiny.y) 1 else -1
        for (i in 1 until abs(origin.x-destiny.x))
            when (board.getPieceInPosition(Vector(i*xDir + origin.x, i*yDir + origin.y))) {
                is SuccessfulOutcome -> return false
                is FailedOutcome -> continue
            }
        return true
    }
}