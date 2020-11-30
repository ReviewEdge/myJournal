import folio


# returns user id if login successful, else returns False
def login():
    has_acc = input("Do you have a login account (y/n)?\n").lower()

    if has_acc == "y":
        username = input("Enter your username:").lower()

        if username in folio.get_all_usernames:
            account_id = folio.get_user_id(username)
            pas_attempt = input("Enter your password:")

            if pas_attempt == account_id.get_password():
                return account_id
            else:
                print("Your password is incorrect.")

        else:
            print("Your account doesn't exist.")
    else:
        print("Create an account.")
    return False


# Creates a new account
def add_account():
    first_name = input("Enter your first name:")
    last_name = input("Enter your last name:")
    new_username = input("Enter a username:")
    new_password = input("Enter a password:")

    folio.add_account(first_name, last_name, new_username, new_password)

    print("Successfully created new user " + new_username + ".\n")


def accounts():
    while True:
        do_acc = input("Enter: \nNew Account: 'n' \nView My Account: 'm' \nView Someone Else's Account: 'v' \nExit: 'e'").lower()

        if do_acc == "n":
            add_account()

        if do_acc == "m":
            print("Your account:\n\n")
            view_account(current_user_id)

        if do_acc == 'v':
            viewing_acc_id = input("Enter an account's ID:")
            view_account(viewing_acc_id)



        def view_account(account_id):
            print("Name: " + folio.get_first_name(account_id) + " " + folio.get_last_name(account_id))
            print("Username: " + folio.get_username(account_id))






# Runs the program

# This will be false if the user doesn't sign in
current_user_id = login()

current_username = folio.get_username(current_user_id)

if current_user_id:
    print("\nYou are now logged in as " + current_username + ".\n")
else:
    print("Please make an account.")
    # directs the user to make an account
    add_account()
    print("Restart the program to use your new account.")

while True:
    do = input("Enter: \nAccounts: 'a' \nJournals: 'j' \nPages: 'p' \nFeed 'f'").lower()

    if do == "a":
        print("ACCOUNTS\n\n")

    if do == "j":
        print("JOURNALS\n\n")

    if do == "p":
        print("PAGES\n\n")

    if do == "f":
        print("FEED\n\n")
