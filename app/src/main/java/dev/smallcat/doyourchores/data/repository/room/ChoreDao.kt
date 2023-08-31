package dev.smallcat.doyourchores.data.repository.room


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.smallcat.doyourchores.data.repository.room.Constants.Companion.CHORE_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface ChoreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addChore(chore: ChoreEntity)

    @Query("SELECT * FROM $CHORE_TABLE")
    fun getAllChores(): Flow<List<ChoreEntity>>

    @Update
    suspend fun updateChoreDetails(chore: ChoreEntity)

    @Delete
    suspend fun deleteChore(chore: ChoreEntity)
}