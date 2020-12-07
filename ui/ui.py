from folio import Folio
import journals_ui
import accounts_ui
import feed_ui

folio = Folio("http://localhost:4567")


# returns user id if login successful, else returns False
def login():
    has_acc = input("Do you have a login account? (y/n)\n").lower()

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

    return False


# Runs the program

# This will be false if the user doesn't sign in
user_id = login()

if user_id is not False:
    current_username = folio.get_username(user_id)
    print("\nYou are now logged in as " + current_username + ".\n")

# Prompts user to make an account
else:
    if input("Would you like to make a new account? (y/n)\n").lower() == "y":
        # directs the user to make an account
        accounts_ui.new_account(folio)
        print("Restart the UI to use your new account.")


# Runs main loop if user is logged in
if user_id is not False:
    do = ""
    while do != "x":
        print("\n\nFOLIO\n")
        do = input("Enter: \nAccounts: 'a' \nJournals: 'j' \nFeed: 'f' \nExit: 'x'\n").lower()

        if do == "a":
            accounts_ui.main(folio, user_id)

        elif do == "j":
            journals_ui.main(folio, user_id)

        elif do == "f":
            feed_ui.view_feed(folio)

print("\nExiting UI...")
