package chess.movementStrategy.movementStrategyFactory

import boardGame.movement.MovementValidator
import boardGame.movement.MovementStrategyFactory
import chess.movementStrategy.validators.DistanceLimitMovement
import boardGame.movement.unionMovement.AndUnionMovementValidator

object KingMovementStrategy: MovementStrategyFactory {
    private val strategy: MovementValidator by lazy {
        AndUnionMovementValidator(listOf(DistanceLimitMovement(1), QueenMovementStrategy.getMovementStrategy()))
    }
    override fun getMovementStrategy(): MovementValidator {
        return strategy
    }
}