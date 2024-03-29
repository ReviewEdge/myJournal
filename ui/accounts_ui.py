import ui.my_account_ui as my_account_ui
import ui.pages_ui as pages_ui


def new_account(current_folio):
    print("\n\nNEW ACCOUNT\n")
    first_name = input("Enter your first name: ")
    last_name = input("Enter your last name: ")
    new_username = input("Enter a username: ")
    new_password = input("Enter a password: ")

    current_folio.add_account(first_name, last_name, new_username, new_password)

    print("Successfully created new user " + new_username + ".\n")


def my_account(current_folio):
    print("\n\nMY ACCOUNT")
    view_account(current_folio, True)

    my_account_ui.main(current_folio, user_id)


def view_account(current_folio, checking_my_account=False):
    print("\n\nVIEW ACCOUNT\n")

    # Gets account username from user
    if not checking_my_account:
        account_username = input("Enter a username: ")
        if current_folio.user_exists(account_username):
            account_id = current_folio.get_user_id(account_username)
        else:
            print("User " + account_username + " does not exist.")
            return

    # Gets user's id if checking user's account
    else:
        account_id = user_id

    # Prints account information
    viewing_user = current_folio.get_user_by_id(account_id)
    print("User: " + viewing_user["username"])
    print("Name: " + viewing_user["profile"]["firstName"] + " " + viewing_user["profile"]["lastName"])
    account_creation = viewing_user["profile"]["accountCreation"]
    print("User Since: " + account_creation[5:10] + "-" + account_creation[:4])
    print("Followers: " + str(len(viewing_user["stats"]["followers"])))

    # Allows user to subscribe to user if not already following
    if not checking_my_account:
        if current_folio.get_session_user()["id"] in viewing_user["stats"]["followers"]:
            print("Following.")

        elif input("Would you like to follow " + viewing_user["username"] + "? (y/n)\n").lower() == "y":
            current_folio.subscribe_account(account_id)

    # Allows user to see user's pages
    if input("Would you like to view " + viewing_user["username"] + "'s pages? (y/n)\n").lower() == "y":
        pages_ui.view_pages(current_folio, current_folio.get_account_pages(account_id))

    print("\n")


def main(current_folio, current_user_id):
    global user_id
    user_id = current_user_id

    print("\n\nACCOUNTS\n")
    do = ""
    while do != "x":
        do = input("Enter: \nNew Account: 'n' \nMy Account: 'm' \nView Account: 'v' \nExit: 'x'\n").lower()

        if do == "n":
            new_account(current_folio)

        elif do == "m":
            my_account(current_folio)

        elif do == "v":
            view_account(current_folio)

    print("\nExiting Accounts...\n")
