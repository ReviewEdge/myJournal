def view_pages(current_folio, pages):

    print(pages) #delete me

    page_counter = 1
    for p in pages:
        print("--------------------------------------------")
        print(str(page_counter) + " - " + p["name"])
        print("By " + current_folio.get_username(p["authorId"]))
        print(p["content"])
        if page_counter == len(pages):
            print("--------------------------------------------")
        page_counter += 1


def new_page(current_folio, journal_id):
    print("\n\nNEW PAGE\n")
    page_name = input("Enter a title: ")
    content = input("Enter page content:\n")

    if input("Post page? (y/n)\n").lower() == "y":
        current_folio.add_page(page_name, content, current_folio.get_session_user()["id"], journal_id)
        print("Successfully posted page " + page_name + ".\n")
