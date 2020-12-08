import ui.pages_ui as pages_ui


def edit_journal_options(current_folio, journal_id):
    journal_name = current_folio.get_journal(journal_id)["name"]

    print("\nEDIT JOURNAL\n")
    if input("Would you like rename " + journal_name + "? (y/n)\n").lower() == "y":
        new_name = input("Enter the new name: ")

        if input("Are you sure you would like to rename the journal " + new_name + "? (y/n)\n").lower() == "y":
            current_folio.edit_journal_name(journal_id, new_name)

    # Permissions issues with this
    # if input("Would you like to add an owner to " + journal_name + "? (y/n)\n").lower() == "y":
    #     new_owner = input("Enter the owner's username: ")
    #
    #     if current_folio.user_exists(new_owner):
    #         if input("Are you sure you would like to make " + new_owner + " an owner? (y/n)\n").lower() == "y":
    #             new_owner_id = current_folio.get_user_id(new_owner)
    #
    #             # Adds new owner to old owners list
    #             owners = current_folio.get_journal(journal_id)["options"]["owners"]
    #             owners.append(new_owner_id)
    #
    #             current_folio.edit_journal_owners(journal_id, owners)
    #             print("\n" + new_owner + " is now an owner of " + journal_name + ".")
    #     else:
    #         print("That user does not exist.\n")


# It really messes stuff up when you delete a journal
# def delete_journal(current_folio, journal_id):
#     journal_name = current_folio.get_journal(journal_id)["name"]
#     if input("Are you sure you would like to " + journal_name + "? (y/n)\n").lower() == "y":
#         current_folio.delete_journal(journal_id)
#         print("The journal " + journal_name + " has been deleted.")


def main(current_folio, journal_id):
    do = ""
    while do != "x":
        print("\n*" + current_folio.get_journal(journal_id)["name"] + "*")

        # Prints the owners of the journal below the journal name
        print("\tOwners: ", end="")
        counter = 0
        for owner_id in current_folio.get_journal(journal_id)["options"]["owners"]:
            owner = current_folio.get_username(owner_id)
            if counter == len(current_folio.get_journal(journal_id)["options"]["owners"]) - 1:
                print(owner)
            else:
                print(owner + ", ", end="")
                counter += 1

        do = input("\nEnter: \nView Journal Pages: 'v' \nNew Page in Journal: 'n' \nEdit Journal Options: 'e' " +
                   "\nExit: 'x'\n").lower()

        if do == "v":
            pages_ui.view_pages(current_folio, current_folio.get_journal_pages(journal_id))

        elif do == "n":
            pages_ui.new_page(current_folio, journal_id)

        elif do == "e":
            edit_journal_options(current_folio, journal_id)

        # elif do == "d":
        #     delete_journal(current_folio, journal_id)

    print("\nExiting Journal...\n")
