import pages_ui


def view_feed(current_folio):
    print("\n\nFEED\n")
    feed = current_folio.get_feed_next()



    print(feed) #delete me

    pages_ui.view_pages(current_folio, feed)
