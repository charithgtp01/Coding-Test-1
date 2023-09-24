package lnbti.charithgtp01.codetest1.ui.editcontact

/**
 * Data validation state of the login form.
 */
data class EditContactFormState(
    var nameError: Int? = null,
    var contactError: Int? = null,
    var emailError: Int? = null,
    var addressError: Int? = null,
    val isDataValid: Boolean = false
)