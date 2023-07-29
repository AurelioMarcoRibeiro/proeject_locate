package reorganize

import java.util.*

data class AvailableMovies(val name: String, val year: Int, val price: Double) {
    override fun toString(): String {
        return "Nome: $name, Ano: $year, Preço: R$%.2f".format(price)
    }
}

fun listMovies(movies: List<AvailableMovies>, cust: List<MovieInfo>) {

    if (movies.isEmpty()) {
        println("Não há filmes disponíveis. Pressione enter para voltar ao menu principal.")
    return
    } else {
        println("Lista de filmes.")
        movies.forEachIndexed { index, movie ->
            val price = movies[index].price
            println("${index + 1} - $movie -")
        }
    }
}

fun registerMovie(scanner: Scanner, movies: MutableList<AvailableMovies>, cust: MutableList<MovieInfo>) {
    println("Cadastro de Filmes.")

    println("Digite o nome do filme:")
    val name = scanner.nextLine()

    println("Digite o ano de lançamento do filme:")
    val year = scanner.nextInt()
    scanner.nextLine()

    val featuresMovie = AvailableMovies(name, year, price = moviePriceCalculation(year, movies))
    movies.add(featuresMovie)

    val movieInformation = MovieInfo(movie = featuresMovie)
    cust.add(movieInformation)

    println("Filme cadastrado com sucesso!")

    listMovies(movies, cust)
}
fun moviePriceCalculation(year: Int, movies: MutableList<AvailableMovies>): Double {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val yearsToCurrentYear = currentYear - year

    return when {
        yearsToCurrentYear <= 1 -> 15.0
        yearsToCurrentYear <= 5 -> 10.0
        else -> 5.0
    }
}