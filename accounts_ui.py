
global folio
folio = False

def new_account(current_folio=folio):
    print("\n\nNEW ACCOUNT\n")
    first_name = input("Enter your first name:")
    last_name = input("Enter your last name:")
    new_username = input("Enter a username:")
    new_password = input("Enter a password:")

    current_folio.add_account(first_name, last_name, new_username, new_password)

    print("Successfully created new user " + new_username + ".\n")

def my_account():
    print("\n\nMY ACCOUNT\n")


def view_account():
    print("\n\nVIEW ACCOUNT\n")


def main(current_folio, current_user_id):
    folio = current_folio
    global user_id
    user_id = current_user_id

    print("\n\nACCOUNTS\n")
    do = ""
    while do != "x":
        do = input("Enter: \nNew Account: 'n' \nMy Account: 'm' \nView Account \nExit: 'x'").lower()

        if do == "n":
            new_account()

        elif do == "m":
            my_account()

        elif do == "View Account":
            view_account()

    print("\nExiting Accounts UI...\n")
