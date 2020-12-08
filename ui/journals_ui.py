import ui.my_journals_ui as my_journals_ui


def new_journal(current_folio):
    print("\n\nNEW JOURNAL\n")
    journal_name = input("Enter the journal's name: ")

    if input("Would you like the journal to be private? (y/n)\n").lower() == "y":
        is_private = True
    else:
        is_private = False

    if input("Would you like the journal to have likes? (y/n)\n").lower() == "y":
        has_likes = True
    else:
        has_likes = False

    if not is_private:
        if input("Would you like the journal to be followable? (y/n)\n").lower() == "y":
            has_followers = True
        else:
            has_followers = False
    else:
        has_followers = False

    if input("Create journal? (y/n)\n").lower() == "y":
        current_folio.add_journal(journal_name, is_private, has_likes, has_followers, [user_id], [])
        print("Successfully created new journal " + journal_name + ".\n")


def my_journals(current_folio):
    print("\n\nMY JOURNALS:\n")

    jids = []
    # gets id of all of user's journals
    for raw_id in current_folio.get_session_user()["journalIds"]:
        jids.append(raw_id["id"])

    my_journals_info = {}
    counter = 1
    for jid in jids:
        my_journals_info[counter] = (current_folio.get_journal(jid)["name"], jid)
        counter += 1

    # Opens the My Journals UI
    my_journals_ui.main(current_folio, my_journals_info)


def main(current_folio, current_user_id):
    global user_id
    user_id = current_user_id

    do = ""
    while do != "x":
        print("\n\nJOURNALS\n")
        do = input("Enter: \nNew Journal: 'n' \nMy Journals: 'm' \nExit: 'x'\n").lower()

        if do == "n":
            new_journal(current_folio)

        elif do == "m":
            my_journals(current_folio)

    print("\nExiting Journals...\n")

