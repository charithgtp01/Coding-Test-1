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

    private fun getProfileTextColor(firstLetter: String): Int {
        var colorCode = 0
        when (firstLetter.uppercase(Locale.getDefault())) {
            "A" -> colorCode = R.color.ALetter
            "B" -> colorCode = R.color.BLetter
            "C" -> colorCode = R.color.CLetter
            "D" -> colorCode = R.color.DLetter
            "E" -> colorCode = R.color.ELetter
            "F" -> colorCode = R.color.FLetter
            "G" -> colorCode = R.color.GLetter
            "H" -> colorCode = R.color.HLetter
            "I" -> colorCode = R.color.ILetter
            "J" -> colorCode = R.color.JLetter
            "K" -> colorCode = R.color.KLetter
            "L" -> colorCode = R.color.LLetter
            "M" -> colorCode = R.color.MLetter
            "N" -> colorCode = R.color.NLetter
            "O" -> colorCode = R.color.OLetter
            "P" -> colorCode = R.color.PLetter
            "Q" -> colorCode = R.color.QLetter
            "R" -> colorCode = R.color.RLetter
            "S" -> colorCode = R.color.SLetter
            "T" -> colorCode = R.color.TLetter
            "U" -> colorCode = R.color.ULetter
            "V" -> colorCode = R.color.VLetter
            "W" -> colorCode = R.color.WLetter
            "X" -> colorCode = R.color.XLetter
            "Y" -> colorCode = R.color.YLetter
            "Z" -> colorCode = R.color.ZLetter
            else -> colorCode = R.color.CLetter
        }
        return colorCode
    }




}
