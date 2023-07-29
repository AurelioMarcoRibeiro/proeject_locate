package reorganize

import java.time.LocalDate
import java.util.*
import java.time.format.DateTimeFormatter

fun callRentedMovies(movie: Movie, client: String, movieData: AvailableMovie) {
    movie.rented = true
    movie.rentedBy = client
    movie.rentalDate = LocalDate.now()
    movie.movie = movieData
}

fun rentMovie(scanner: Scanner, movies: MutableList<AvailableMovie>, clients: MutableList<Customer>,
              cust: MutableList<Movie>) {

    if (clients.isEmpty()) {
        println("Não existem clientes cadastrados. Cadastre um antes de alugar um filme. Pressione enter para voltar ao menu principal.")
        return
    } else {
        println("Aluguel de Filme.")

        println("Digite o CPF do cliente:")
        val cpfClient = scanner.nextLine()

        val clientFound = clients.find { it.cpf.equals(cpfClient, ignoreCase = true) }
        if (clientFound == null) {
            println("Cliente com o CPF '$cpfClient' não encontrado. Cadastre o cliente antes de alugar um filme. Pressione enter para voltar ao menu principal.")
            return
        } else {
            println("Cliente: '${clientFound.name}'")
        }

        listMovies(movies, cust)

        val rentedBy = scanner.nextInt()
        scanner.nextLine()

        if (rentedBy <= 0 || rentedBy > movies.size) {
            println("Número de filme inválido.")
        }

        val selectedMovieInfo = cust[rentedBy - 1]
        val selectedMovieData = movies[rentedBy - 1]

        if (selectedMovieInfo.rented) {
            println("O filme selecionado ja está alugado pelo cliente: ${selectedMovieInfo.rentedBy}.")
            return
        }

        selectedMovieInfo.rented = true
        selectedMovieInfo.rentedBy = cpfClient
        selectedMovieInfo.movie = selectedMovieData
        selectedMovieInfo.rentalDate = LocalDate.now()

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val currentDateFormatted = "29/07/2023"
        val currentDate = LocalDate.parse(currentDateFormatted, formatter)
        val dateAdded = currentDate.plusDays(10)

        println("Filme '${selectedMovieData.name}' alugado para '${clientFound.name}'," +
                " na data de ${selectedMovieInfo.rentalDate?.format(formatter)}. O cliente tem" +
                " ate a data de ${dateAdded.format(formatter)} para entregar o filme sem juros.\nPressione enter para voltar ao menu principal.")
    }
}