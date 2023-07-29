package reorganize

import java.util.*

data class Customer(val name: String, val cpf: String, val address: Address) {
    companion object {
        fun create(scanner: Scanner): Customer {
            println("Cadastro de Clientes.")
            println("Digite os dados do cliente.:")

            println("Nome:")
            val name = scanner.nextLine()

            println("CPF:")
            var cpf = scanner.nextLine()

            while (cpf.length != 11) {
                println("CPF inválido. Deve conter 11 dígitos.")
                println("CPF:")
                cpf = scanner.nextLine()
            }

            println("Digite o endereço do cliente:")

            println("Bairro:")
            val neighborhood = scanner.nextLine()

            println("Rua:")
            val street = scanner.nextLine()

            println("Número:")
            val number = scanner.nextInt()
            scanner.nextLine()

            val address = Address(neighborhood, street, number)

            println("Dados do cliente cadastrados com sucesso!")

            return Customer(name, cpf, address)
        }
    }
}