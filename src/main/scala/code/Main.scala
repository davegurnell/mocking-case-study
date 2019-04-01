package code

object Main extends App {
  val accounts = InMemoryAccountStore()
  val bank = new Bank(accounts)

  println("\n---")

  println("open")
  println(bank.open("account1"))
  println(bank.open("account2"))
  println(bank.open("account3"))
  println(accounts.stringify)

  println("\n---")

  println("deposit")
  println(bank.deposit("account1", 100))
  println(bank.deposit("account2", 200))
  println(accounts.stringify)

  println("\n---")

  println("withdraw")
  println(bank.withdraw("account1", 200))
  println(bank.withdraw("account2", 100))
  println(accounts.stringify)

  println("\n---")

  println("transfer")
  println(bank.transfer("account1", "account2", 75))
  println(accounts.stringify)

  println("\n---")

  println("applyCharges")
  bank.applyCharges(50)
  println(accounts.stringify)

  println("\n---")

  println("applyInterest")
  bank.applyInterest(10)
  println(accounts.stringify)
}