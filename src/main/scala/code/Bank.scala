package code

class Bank(accounts: AccountStore) {
  // Open an account with zero balance
  // Fail if the account is already open
  def open(acc: String): Either[String, Int] = {
    accounts.get(acc) match {
      case Some(acc) =>
        Left(s"Account $acc already exists")
      case None =>
        accounts.set(acc, 0)
        accounts.get(acc).toRight(s"Account $acc not created")
    }
  }

  // Deposit cash into an account
  // Fail if the account does not exist
  // Fail if the account would go overdrawn
  // If a failure occurs, the account balance should be unchanged
  def deposit(acc: String, amt: Int): Either[String, Int] = {
    accounts.get(acc) match {
      case Some(bal) =>
        val tot = bal + amt

        if(tot > 0) {
          accounts.set(acc, tot)
          accounts.get(acc).toRight(s"Account $acc disappeared!")
        } else {
          Left(s"Account $acc would go overdrawn")
        }

      case None =>
        Left(s"Account $acc does not exist")
    }
  }

  // Withdraw cash from an account
  // Fail if the account does not exist
  // Fail if the account would go overdrawn
  // If a failure occurs, the account balance should be unchanged
  def withdraw(acc: String, amt: Int): Either[String, Int] = {
    deposit(acc, -amt)
  }

  // Transfer cash from account to account
  // Return the modified balance of the source account
  // Fail if either account does not exist
  // Fail if either account would go overdrawn
  // If a failure occurs, the accounts balance should be unchanged
  def transfer(from: String, to: String, amt: Int): Either[String, Int] = {
    withdraw(from, amt) match {
      case Left(err)  => Left(err)
      case Right(bal) => deposit(to, amt).map(_ => bal)
    }
  }

  // Apply monthly banking to every account in the bank
  // If the account is overdrawn, double the charge
  def applyCharges(amt: Int): Unit = {
    accounts.accountNumbers.foreach { acc =>
      accounts.get(acc) match {
        case Some(bal) =>
          if(bal >= 0) {
            accounts.set(acc, bal - amt)
          } else {
            accounts.set(acc, bal - amt * 2)
          }

        case None =>
          throw new Exception("Account disappeared mid-update!")
      }
    }
  }

  // Apply interest to every account in the bank
  // If the account is overdrawn, leave its balance unchanged
  def applyInterest(percent: Int): Unit = {
    val multiplier = 1.0 + percent / 100.0

    accounts.accountNumbers.foreach { acc =>
      accounts.get(acc) match {
        case Some(bal) =>
          if(bal >= 0) {
            accounts.set(acc, (bal * multiplier).toInt)
          }

        case None =>
          throw new Exception("Account disappeared mid-update!")
      }
    }
  }
}