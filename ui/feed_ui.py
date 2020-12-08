import ui.pages_ui as pages_ui


def view_feed(current_folio):
    print("\n\nFEED\n")

    feed = current_folio.get_feed(10)

    # Views pages in feed if there are new pages
    if len(feed) != 0:
        pages_ui.view_pages(current_folio, feed)
    else:
        print("You have no new pages.")
