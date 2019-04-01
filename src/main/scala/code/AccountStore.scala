package code

trait AccountStore {
  def accountNumbers: List[String]
  def get(accountNumber: String): Option[Int]
  def set(accountNumber: String, value: Int): Unit
  def del(accountNumber: String): Unit

  def stringify: String =
    accountNumbers
      .map(acc => s"$acc -> ${get(acc).fold("???")("£" + _)}")
      .mkString("\n")
}

case class InMemoryAccountStore(var data: Map[String, Int] = Map.empty) extends AccountStore {
  def accountNumbers: List[String] = {
    data.keySet.toList.sorted
  }

  def get(accountNumber: String): Option[Int] = {
    data.get(accountNumber)
  }

  def set(accountNumber: String, value: Int): Unit = {
    data = data + ((accountNumber, value))
  }

  def del(accountNumber: String): Unit = {
    data = data - accountNumber
  }
}