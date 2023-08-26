package lnbti.charithgtp01.codetest1.model

import lnbti.charithgtp01.codetest1.R
import java.util.Locale

/**
 * Data class for Contact Object
 */
data class ContactItem(
    val id: Int = 0,
    val name: String = "",
    val email: String?,
    val contactNo: String?,
    val address: String?,
    var isExpanded: Boolean = false
) {
    val initialLetter: String = name.take(1).uppercase()
}
