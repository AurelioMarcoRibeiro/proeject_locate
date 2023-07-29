package reorganize

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

data class Movie(
    var rented: Boolean = false,
    var rentedBy: String = "",
    var rentalDate: LocalDate? = null,
    var returnDate: LocalDate? = null,
    var movie: AvailableMovie? = null
) {

    fun resetRentInfo(){
        this.rented = false
        this.rentedBy = ""
    }
    companion object {
        fun findMoviesByCustomer(
            customer: Customer,
            rented: MutableList<Movie>
        ): List<Movie> {
            println("Devolução de Filme.")

            val rentedMovies = rented.filter { it.rented && it.rentedBy == customer.cpf }

            if (rentedMovies.isEmpty()) {
                println("O cliente '${customer.name}' não possui filmes alugados.")
            }

            println("Filmes alugados pelo cliente '${customer.name}':")
            rentedMovies.forEachIndexed { index, movieInfo ->
                val movie = movieInfo.movie
                println("${index + 1} - $movie")
            }

            return rentedMovies
        }

        fun returnMovie(scanner: Scanner, rentedMovies: List<Movie>) {
            println("Digite o número do filme que deseja devolver:")
            val numberMovieReturn = scanner.nextInt()
            scanner.nextLine()

            if (numberMovieReturn <= 0 || numberMovieReturn > rentedMovies.size) {
                println("Número de filme inválido. Pressione enter para voltar ao menu principal.")
            }

            val filmReturned = rentedMovies[numberMovieReturn - 1]
            val movieDataReturned = filmReturned.movie

            if (movieDataReturned != null) {
                val rentalDate = filmReturned.rentalDate
                println("Digite a data de devolução. (DD/MM/YYYY):")
                val returnDate = scanner.nextLine()

                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                val transformDate: LocalDate = LocalDate.parse(returnDate, formatter)

                if (rentalDate != null) {
                    val daysLate = ChronoUnit.DAYS.between(rentalDate, transformDate) - 10
                    val lateFee = daysLate * movieDataReturned.price * 0.10
                    if (daysLate > 0) {
                        val sum = lateFee + movieDataReturned.price
                        println("Valor do filme: R$ ${movieDataReturned.price}")
                        println("Dias de atraso: $daysLate")
                        println("Juros a serem pagos: R$ $lateFee")
                        println("Valor total à ser pago: ${sum}")
                    } else {
                        println("Valor do filme: R$ ${movieDataReturned.price}")
                    }
                }
            }

            if (movieDataReturned != null) {
                filmReturned.resetRentInfo()
                println("O filme '${movieDataReturned.name}' foi devolvido à locadora.")
            } else {
                println("Erro: Filme não encontrado na lista de filmes alugados.")
            }
        }
    }

}