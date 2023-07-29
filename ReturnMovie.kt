package reorganize

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Scanner
import java.time.format.DateTimeFormatter

fun returnMovie(
    scanner: Scanner,
    movies: List<AvailableMovies>,
    clients: List<Customer>,
    movieAvailability: MutableList<MovieInfo>) {

    if (movieAvailability.isEmpty()){
        println("Não existem filmes alugados. Alugue um antes de devolver.")
   return } else {
    println("Devolução de Filme.")

    println("Digite o CPF do cliente:")
    val clientCPF = scanner.nextLine()

    val rentedMovies = movieAvailability.filter { it.rented && it.rentedBy == clientCPF }

    val clientFound = clients.find { it.cpf == clientCPF }
    if (clientFound == null) {
        println("Cliente com CPF '$clientCPF' não encontrado.")
        return
    }

    if (rentedMovies.isEmpty()) {
        println("O cliente '${clientFound.name}' não possui filmes alugados.")
        return
    }

    println("Filmes alugados pelo cliente '${clientFound.name}':")
    rentedMovies.forEachIndexed { index, movieInfo ->
        val movie = movieInfo.movie
        println("${index + 1} - $movie")
    }

    println("Digite o número do filme que deseja devolver:")
    val numberMovieReturn = scanner.nextInt()
    scanner.nextLine()

    if (numberMovieReturn <= 0 || numberMovieReturn > rentedMovies.size) {
        println("Número de filme inválido. Pressione enter para voltar ao menu principal.")
        return
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
        val loucadora = Loucadora()
        loucadora.returnLocator(filmReturned)
        println("O filme '${movieDataReturned.name}' foi devolvido à locadora.")
    } else {
        println("Erro: Filme não encontrado na lista de filmes alugados.")
        }
    }
}