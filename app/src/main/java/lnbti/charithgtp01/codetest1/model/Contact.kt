package lnbti.charithgtp01.codetest1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class for Contact Object
 */
@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Default value is 0, and Room will automatically assign a unique value
    val name: String?,
    val email: String?,
    val contactNo: String?,
    val address: String?,
    var isExpanded: Boolean = false
)
