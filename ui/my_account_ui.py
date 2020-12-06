import sys





def my_pages(current_folio):

    # prints all of user's pages, uses pages methods

    print("under construction")







def edit_account(current_folio):
    print("\nEDIT ACCOUNT\n")
    if input("Would you like to change your username? (y/n)\n").lower() == "y":
        new_username = input("Enter your new username: ")

        if input("Are you sure you would like to change your username to " + new_username + "? (y/n)\n").lower() == "y":
            current_folio.edit_account_username(user_id, new_username)

    if input("Would you like to change your name? (y/n)\n").lower() == "y":
        new_first_name = input("Enter your new first name: ")
        new_last_name = input("Enter your new last name: ")

        if input("Are you sure you would like to change your name to " + new_first_name + " " + new_last_name
                 + "? (y/n)\n").lower() == "y":
            current_folio.edit_account_name(user_id, new_first_name, new_last_name)

    if input("Would you like to change your password? (y/n)\n").lower() == "y":
        new_password = input("Enter your new password: ")

        if input("Are you sure you would like to change your password to " + new_password + "? (y/n)\n").lower() == "y":
            current_folio.edit_account_password(user_id, new_username)


def delete_account(current_folio):
    username = current_folio.get_username(user_id)
    if input("Are you sure you would like to delete your account, " + username + "?  " +
             "The UI will shut down after deletion. (y/n)\n").lower() == "y":
        current_folio.delete_account(user_id)
        print("Your account " + username + " has been deleted.")
        print("\nExiting UI...")
        sys.exit()


def main(current_folio, current_user_id):
    global user_id
    user_id = current_user_id

    print("MY ACCOUNT\n")
    do = ""
    while do != "x":
        do = input("Enter: \nMy Pages: 'p' \nEdit Account: 'e' \nDelete Account: 'd' \nExit: 'x'\n").lower()

        if do == "p":
            my_pages(current_folio)

        elif do == "e":
            edit_account(current_folio)

        elif do == "d":
            delete_account(current_folio)

    print("\nExiting My Account...\n")
