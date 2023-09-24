package lnbti.charithgtp01.codetest1.ui.newcontact

/**
 * Data validation state of the login form.
 */
data class NewContactFormState(
    var nameError: Int? = null,
    var contactError: Int? = null,
    var emailError: Int? = null,
    var addressError: Int? = null,
    val isDataValid: Boolean = false
)