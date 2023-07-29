package reorganize

import java.time.LocalDate

data class MovieInfo(var rented: Boolean = false, var rentedBy: String = "",
                     var rentalDate: LocalDate? = null, var returnDate: LocalDate? = null, var movie: AvailableMovies? = null)



