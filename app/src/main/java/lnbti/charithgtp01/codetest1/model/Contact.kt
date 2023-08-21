package lnbti.charithgtp01.codetest1.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data class for Contact Object
 */
@Parcelize
data class Contact(
    val id: Int,
    val name: String,
    val email: String,
    val contactNo:String,
    val avatar: String,
    val address:String,
) : Parcelable {


}
