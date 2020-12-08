from folio import Folio

folio = Folio("http://localhost:4567")


# returns user id if login successful, else returns False
def login():
    has_acc = input("Do you have a login account (y/n)?\n").lower()

    if has_acc == "y":
        username = input("Enter your username:").lower()

        if folio.user_exists(username):
            account_id = folio.get_user_id(username)
            pas_attempt = input("Enter your password:")

            if folio.authenticate(account_id, pas_attempt):
                return account_id
            else:
                print("Your password is incorrect.")

        else:
            print("Your account doesn't exist.")
    else:
        print("Please make an account.")
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
        do_acc = input("Enter: \nNew Account: 'n' \nView My Account: 'm' \nView Someone Else's Account: 'v' \nExit: 'e'\n").lower()

        if do_acc == "n":
            add_account()

        elif do_acc == "m":
            print("Your account:\n\n")
            view_account(current_user_id)

        elif do_acc == 'v':
            viewing_acc_id = input("Enter an account's ID:")
            view_account(viewing_acc_id)



        def view_account(account_id):
            print("Name: " + folio.get_first_name(account_id) + " " + folio.get_last_name(account_id))
            print("Username: " + folio.get_username(account_id))





def journals():
    while True:
        do_journ = input("Enter: \nNew Journal: 'n' \nView Journal: 'v' \nEdit Journal: 'e'\nDelete Journal: 'd' \nSubscribe to Journal: 's' \nExit: 'x'\n").lower()
        if do_journ == "n":


        elif do_journ == "v":


        elif do_journ == 'd':


        elif do_journ == 'x':
            break


# Runs the program

# This will be false if the user doesn't sign in
current_user_id = login()

if current_user_id is not False:
    global current_username
    current_username = folio.get_username(current_user_id)
    print("\nYou are now logged in as " + current_username + ".\n")
else:
    if input("Would you like to make a new account (y/n)?\n").lower() == "y":
        # directs the user to make an account
        add_account()
        print("Restart the program to use your new account.")

if current_user_id is not False:
    while True:
        do = input("Enter: \nAccounts: 'a' \nJournals: 'j' \nPages: 'p' \nFeed 'f'\n").lower()
    
        if do == "a":
            print("ACCOUNTS\n\n")
            accounts()
    
        elif do == "j":
            print("JOURNALS\n\n")
            journals()
    
        elif do == "p":
            print("PAGES\n\n")
    
        elif do == "f":
            print("FEED\n\n")
