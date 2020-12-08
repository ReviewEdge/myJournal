from folio import Folio


if __name__ == '__main__':
    f = Folio("http://134.122.116.97:80")
    f.add_account("joe", "joe", "joe", "joe")
    joe_id = f.get_user_id("joe")
    f.authenticate(joe_id, "joe")
    f.add_journal(name="Joe's Journal", is_private=True, has_likes=False, has_followers=False, owners=[joe_id], contributers=[])
    journal_ids = f.get_session_user()["journalIds"]
    first_journal = journal_ids[0]
    first_id = first_journal["id"]
    f.add_page("Joe's page", "I SURE DO LIKE TO EAT CHICKEN", author_id=joe_id, parent_journal_id=first_id)

    g = Folio("http://134.122.116.97:80")
    g.add_account("jim","jim","jim","jim")
    jim_id = g.get_user_id("jim")
    g.authenticate(jim_id, "jim")
    pages2 = g.get_account_pages(joe_id)
    print(pages2)
