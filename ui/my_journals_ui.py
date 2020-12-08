import ui.edit_journal_ui as edit_journal_ui


def main(current_folio, my_journals):
    pick_j = ""
    while pick_j != "x":

        for c in range(1, len(my_journals) + 1):
            print(str(c) + " - " + my_journals[c][0])

        pick_j = input("Enter the number of the journal you would like to access, or 'x' to exit:\n").lower()

        if pick_j.isdigit() and int(pick_j) in my_journals:
            pick_jid = my_journals[int(pick_j)][1]

            # Opens the Edit Journal UI
            edit_journal_ui.main(current_folio, pick_jid)

        else:
            print("Invalid journal number.\n")

    print("\nExiting My Journals...")