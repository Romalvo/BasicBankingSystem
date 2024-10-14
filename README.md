Basic Banking System with Checking Accounts
Project Overview:
In this project, you will develop a Basic Banking System that supports only checking accounts.
Users will be able to perform essential banking functions like registering, opening accounts,
transferring money, and managing their accounts. This system will focus on simplifying
payments and account management without involving complex features like interest accrual or
deposit contracts.
The system will include a transaction life cycle for payments, support multiple branches with
different IBAN formats and time zones, and provide comprehensive error handling for failed
transactions, including logging failure reasons.
Project Requirements:
1. Account Lifecycle (Checking Accounts Only):
- Users can only open checking accounts.
- An account can have the following states:
1. Open: Account is created but not yet activated.
2. Active: Account is activated after the first successful transfer or deposit.
3. Closed: User can request to close the account, and the account will be marked as closed
   if the balance is zero.
- Account Closure:
- Users can only request to close an account if the balance is zero. Upon closure, the
  account will be marked as inactive and no further transactions will be allowed.
2. User Registration and Authentication:
- Users should be able to register and login to the system.
- Once logged in, they should be able to open an account.
- Admins should be able to manage all accounts and view transaction details.
3.Deposit, Withdrawal, and Transfer:
- Users can deposit money into their accounts.
- Users can withdraw money from their accounts, as long as the daily and monthly limits are
  not exceeded.
- Users can transfer money between accounts, including accounts in different branches, with
  different IBAN formats.
- Account Activation:
- When users perform their first successful transfer or deposit, the account is automatically
  marked as Active.
4. Transaction Processing:
Each payment goes through the following states:
1. Pending: The payment is initiated but not yet processed.
2. Sent: The payment has been sent from the sender’s account.
3. Received: The payment has been processed and received by the recipient’s bank.
4. Failed: The payment fails, and the reason for failure is logged and displayed to the user.
- Failure Scenarios: Transactions can fail for the following reasons:
- Account not active: If the sender’s account is still open but not yet activated.
- Insufficient funds: If the account balance is lower than the transfer amount.
- Invalid destination account: If the destination account does not exist.
- Account closed: If the recipient account is closed or inactive.
- Daily/Monthly limit exceeded: If the transaction exceeds the predefined limits.
- Failure Logging: The system should store the reason for each failed transaction, allowing
  users to see why their transaction did not succeed.
5. Balance and Account Statements:
- Users should be able to view their current balance.
- Users should also have access to their account statement, showing:
- A list of all deposits, withdrawals, and transfers.
- The status of each transaction (Pending, Sent, Received, Failed).
- Any reasons for failed transactions.
6. Payment Lifecycle:
- When a payment is initiated, it starts with a Pending status.
- Once processed from the sender’s side, it moves to Sent.
- When received by the recipient’s bank, it is marked as Received.
- If any part of the transaction fails, it is marked as Failed, and a specific reason for failure
  must be recorded and displayed.
7. Multi-Branch and Multi-Currency Support:
- The system should support multiple branches across different countries, with each branch
  having its own IBAN format and time zone.
- Transfers between accounts in different branches should be possible, and transactions
  should be time-stamped based on the time zone of the originating branch.
8. Admin Functionality:
- Admins can view all accounts and transactions.
- Admins should be able to deactivate or close accounts when necessary.
- Admins can view transaction failure logs and reasons.