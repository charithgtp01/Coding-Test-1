package lnbti.charithgtp01.codetest1.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Data class for Contact Object
 */
@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey
    val id: Int,
    val name: String,
    val email: String,
    val contactNo: String,
    val avatar: String,
    val address: String,
    var isExpanded: Boolean = false
) {


}
