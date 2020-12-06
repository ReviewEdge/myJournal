

def new_journal():
    print("\n\nNEW JOURNAL\n")


def my_journals():
    print("\n\nMY JOURNALS\n")



def main(current_folio, current_user_id):
    global folio
    folio = current_folio
    global user_id
    user_id = current_user_id

    print("\n\nJOURNALS\n")
    do = ""
    while do != "x":
        do = input("Enter: \nNew Journal: 'n' \nMy Journals: 'm' \nExit: 'x'\n").lower()

        if do == "n":
            new_journal()

        elif do == "m":
            my_journals()

    print("\nExiting Journal...\n")

