from json import JSONDecodeError

import requests
import endpoints


class Folio:
    """
    A Folio class to interact with Folio's API.
    """

    def __init__(self, url):
        self.url = url
        self.session = requests.session()
        self.endpoints = {}
        for (name, endpoint) in endpoints.as_dict.items():
            self.endpoints[name] = self.url + endpoint
        self.authenticated = False

    def get_session_user(self):
        """

        :return:
        """
        return self.session.get(self.endpoints["account"]).json()

    def get_user_id(self, username):
        """
        Get the id of a user from their username.
        :param username: The username to look up.
        :return: The id.
        """
        return self.get_user(username)['id']

    def get_user(self, username: str):
        """

        :param username:
        :return:
        """
        request_data = {
            "username": username
        }
        return self.session.get(self.endpoints["account"], params=request_data).json()

    def get_user_journals(self, id: int):
        """
        Get the ids of all of a user's journals.
        :param id:
        """
        user = self.get_user_by_id(id)
        return [journal["id"] for journal in user["journalIds"]]

    def get_user_by_id(self, id: int):
        """

        :param id:
        :return:
        """
        request_data = {
            "id": id
        }
        return self.session.get(self.endpoints["account"], params=request_data).json()

    def get_username(self, id: int):
        """

        :param id:
        :return:
        """
        return self.get_user_by_id(id)["username"]

    def get_user_profile(self, id: int):
        return self.get_user_by_id(id)["profile"]

    def add_account(self, first_name, last_name, new_username, new_password):
        """

        :param first_name:
        :param last_name:
        :param new_username:
        :param new_password:
        :return:
        """
        request_data = {
            "firstName": first_name,
            "lastName": last_name,
            "username": new_username,
            "password": new_password,
        }
        return self.session.post(self.endpoints["account"], data=request_data).status_code == 200

    def add_account_full(self, first_name, last_name, new_username, new_password, date_of_birth, bio, living_location):
        """

        :param first_name:
        :param last_name:
        :param new_username:
        :param new_password:
        :param date_of_birth:
        :param bio:
        :param living_location:
        :return:
        """
        request_data = {
            "firstName": first_name,
            "lastName": last_name,
            "username": new_username,
            "password": new_password,
            "dateOfBirth": date_of_birth,
            "bio": bio,
            "livingLocation": living_location
        }
        return self.session.post(self.endpoints["account"], data=request_data).status_code == 200

    def add_journal(self, name, is_private, has_likes, has_followers, owners, contributers):
        """

        :param name:
        :param is_private:
        :param has_likes:
        :param has_followers:
        :param owners:
        :param contributers:
        :return:
        """
        request_data = {
            "name": name,
            "is_private": is_private,
            "has_likes": has_likes,
            "has_followers": has_followers,
            "owners": owners,
            "contributers": contributers
        }
        return self.session.post(self.endpoints["journal"], data=request_data).status_code == 200

    def add_page(self, name, content, author_id, parent_journal_id):
        """

        :param name:
        :param content:
        :param author_id:
        :param parent_journal_id:
        :return:
        """
        request_data = {
            "name": name,
            "content": content,
            "authorId": author_id,
            "parentJournalId": parent_journal_id
        }
        return self.session.post(self.endpoints["page"], data=request_data).status_code == 200

    def user_exists(self, username):
        """

        :param username:
        :return:
        """
        try:
            self.get_user(username)
            return True
        except JSONDecodeError:
            return False

    def authenticate(self, user_id, password):
        """

        :param user_id:
        :param password:
        :return:
        """
        request_data = {
            "id": user_id,
            "password": password
        }
        response = self.session.post(self.endpoints["session"], data=request_data)
        if response.status_code == 200:
            self.authenticated = True
            return True
        return False

    def get_feed(self, num_pages: int):
        """

        :return:
        """
        request_data = {
            "numPages": num_pages
        }
        response = self.session.get(self.endpoints["feed"], params=request_data)
        return response.json()

    def get_feed_next(self):
        """

        :return:
        """
        response = self.session.get(self.endpoints["feed_next"])
        if response.status_code == 200:
            return response.json()
        elif response.status_code == 400:
            return {}

    def get_page(self, page_id):
        """

        :param page_id:
        :return:
        """
        request_data = {
            "id": page_id
        }
        response = self.session.get(self.endpoints["page"], params=request_data)
        return response.json()

    def get_page_author(self, page_id):
        return self.get_user_by_id(self.get_page(page_id)["accountId"])

    def get_journal(self, journal_id):
        """

        :param journal_id:
        :return:
        """
        request_data = {
            "id": journal_id
        }
        response = self.session.get(self.endpoints["journal"], params=request_data)
        return response.json()

    def get_account_pages(self, account_id):
        """

        :param account_id:
        :return:
        """
        request_data = {
            "id": account_id
        }
        response = self.session.get(self.endpoints["account_pages"], params=request_data)
        return response.json()

    def get_journal_pages(self, journal_id):
        """

        :param journal_id:
        :return:
        """
        request_data = {
            "id": journal_id
        }
        response = self.session.get(self.endpoints["journal_pages"], params=request_data)
        return response.json()

    def delete_account(self, account_id):
        """

        :param account_id:
        :return:
        """
        request_data = {
            "id": account_id
        }
        response = self.session.delete(self.endpoints["account"], params=request_data)
        return response.status_code == 200

    def delete_journal(self, journal_id):
        """

        :param journal_id:
        :return:
        """
        request_data = {
            "id": journal_id
        }
        response = self.session.delete(self.endpoints["journal"], params=request_data)
        return response.status_code == 200

    def delete_page(self, page_id):
        """

        :param page_id:
        :return:
        """
        request_data = {
            "id": page_id
        }
        response = self.session.delete(self.endpoints["page"], params=request_data)
        return response.status_code == 200

    def edit_account(self, account_id, first_name, last_name, new_username, new_password, date_of_birth, bio,
                     living_location):
        """

        :param account_id:
        :param first_name:
        :param last_name:
        :param new_username:
        :param new_password:
        :param date_of_birth:
        :param bio:
        :param living_location:
        :return:
        """
        request_data = {
            "id": account_id,
            "firstName": first_name,
            "lastName": last_name,
            "username": new_username,
            "password": new_password,
            "dateOfBirth": date_of_birth,
            "bio": bio,
            "livingLocation": living_location
        }
        response = self.session.put(self.endpoints["account"], data=request_data)
        return response.status_code == 200

    def edit_account_name(self, account_id, first_name, last_name):
        """

        :param account_id:
        :param first_name:
        :param last_name:
        :return:
        """
        request_data = {
            "id": account_id,
            "firstName": first_name,
            "lastName": last_name
        }
        response = self.session.put(self.endpoints["account"], data=request_data)
        return response.status_code == 200

    def edit_account_username(self, account_id, username):
        """

        :param account_id:
        :param username:
        :return:
        """
        request_data = {
            "id": account_id,
            "username": username
        }
        response = self.session.put(self.endpoints["account"], data=request_data)
        return response.status_code == 200

    def edit_account_password(self, account_id, password):
        """

        :param account_id:
        :param password:
        :return:
        """
        request_data = {
            "id": account_id,
            "password": password
        }
        response = self.session.put(self.endpoints["account"], data=request_data)
        return response.status_code == 200

    def edit_account_date_of_birth(self, account_id, date_of_birth):
        """

        :param account_id:
        :param date_of_birth:
        :return:
        """
        request_data = {
            "id": account_id,
            "date_of_birth": date_of_birth
        }
        response = self.session.put(self.endpoints["account"], data=request_data)
        return response.status_code == 200

    def edit_account_bio(self, account_id, bio):
        """

        :param account_id:
        :param bio:
        :return:
        """
        request_data = {
            "id": account_id,
            "bio": bio
        }
        response = self.session.put(self.endpoints["account"], data=request_data)
        return response.status_code == 200

    def edit_account_living_location(self, account_id, living_location):
        """

        :param account_id:
        :param living_location:
        :return:
        """
        request_data = {
            "id": account_id,
            "living_location": living_location
        }
        response = self.session.put(self.endpoints["account"], data=request_data)
        return response.status_code == 200

    def edit_journal(self, journal_id, name, is_private, has_likes, has_followers, owners, contributers, viewers):
        """

        :param viewers:
        :param journal_id:
        :param name:
        :param is_private:
        :param has_likes:
        :param has_followers:
        :param owners:
        :param contributers:
        :return:
        """
        request_data = {
            "id": journal_id,
            "name": name,
            "is_private": is_private,
            "has_likes": has_likes,
            "has_followers": has_followers,
            "owners": owners,
            "contributers": contributers,
            "viewers": viewers
        }
        return self.session.put(self.endpoints["journal"], data=request_data).status_code == 200

    def edit_journal_options(self, journal_id, is_private, has_likes, has_followers, viewers):
        """

        :param viewers:
        :param journal_id:
        :param is_private:
        :param has_likes:
        :param has_followers:
        :return:
        """
        request_data = {
            "id": journal_id,
            "is_private": is_private,
            "has_likes": has_likes,
            "has_followers": has_followers,
            "viewers": viewers
        }
        response = self.session.put(self.endpoints["journal"], data=request_data)
        return response.status_code == 200

    def edit_journal_name(self, journal_id, name):
        """

        :param journal_id:
        :param name:
        :return:
        """
        request_data = {
            "id": journal_id,
            "name": name
        }
        response = self.session.put(self.endpoints["journal"], data=request_data)
        return response.status_code == 200

    def edit_journal_owners(self, journal_id, owners):
        """

        :param journal_id:
        :param owners:
        :return:
        """
        request_data = {
            "id": journal_id,
            "owners": owners
        }
        response = self.session.put(self.endpoints["journal"], data=request_data)
        return response.status_code == 200

    def edit_journal_contributers(self, journal_id, contributers):
        """

        :param journal_id:
        :param contributers:
        :return:
        """
        request_data = {
            "id": journal_id,
            "contributers": contributers
        }
        response = self.session.put(self.endpoints["journal"], data=request_data)
        return response.status_code == 200

    def edit_journal_viewers(self, journal_id, viewers):
        """

        :param journal_id:
        :param viewers:
        :return:
        """
        request_data = {
            "id": journal_id,
            "viewers": viewers
        }
        response = self.session.put(self.endpoints["journal"], data=request_data)
        return response.status_code == 200

    def subscribe_account(self, account_id):
        """

        :param account_id:
        :return:
        """
        request_data = {
            "id": account_id
        }
        response = self.session.put(self.endpoints["account_subscribe"], data=request_data)
        return response.status_code == 200

    def subscribe_journal(self, journal_id):
        """

        :param journal_id:
        :return:
        """
        request_data = {
            "id": journal_id
        }
        response = self.session.put(self.endpoints["journal_subscribe"], data=request_data)
        return response.status_code == 200


if __name__ == '__main__':
    f = Folio("http://localhost:4567")
    f.add_account("joe", "joe", "joe", "joe")
    joe_id = f.get_user_id("joe")
    f.authenticate(joe_id, "joe")
    f.add_journal("Joe's Journal", False, False, False, [joe_id], [])
    journal_ids = f.get_session_user()["journalIds"]
    first_journal = journal_ids[0]
    first_id = first_journal["id"]
    print(first_id)
    f.add_page("Joe's page", "I SURE DO LIKE TO EAT CHICKEN", author_id=joe_id, parent_journal_id=first_id)
    pages = f.get_journal_pages(first_id)
    print(pages)
    f.subscribe_journal(first_id)
    pages2 = f.get_account_pages(joe_id)
    print(f.get_user_by_id(joe_id))
    print(pages2)


