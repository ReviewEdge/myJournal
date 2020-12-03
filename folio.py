from json import JSONDecodeError

import requests
import endpoints
import hashlib


class Folio:
    def __init__(self, url):
        self.url = url
        self.session = requests.session()
        self.endpoints = {}
        for (name, endpoint) in endpoints.as_dict.items():
            self.endpoints[name] = self.url + endpoint
        self.authenticated = False

    def get_user_id(self, username):
        return self.get_user(username)['id']

    def get_user(self, username: str):
        request_data = {
            "username": username
        }
        return self.session.get(self.endpoints["account"], params=request_data).json()

    def get_user_by_id(self, id: int):
        request_data = {
            "id": id
        }
        return self.session.get(self.endpoints["account"], params=request_data).json()

    def get_username(self, id: int):
        return self.get_user_by_id(id)["profile"]["username"]

    def add_account(self, first_name, last_name, new_username, new_password):
        request_data = {
            "firstName": first_name,
            "lastName": last_name,
            "username": new_username,
            "password": new_password,
        }
        return self.session.post(self.endpoints["account"], data=request_data).status_code == 200

    def add_account_full(self, first_name, last_name, new_username, new_password, date_of_birth, bio, living_location):
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
        request_data = {
            "name": name,
            "content": content,
            "authorId": author_id,
            "parentJournalId": parent_journal_id
        }
        return self.session.post(self.endpoints["page"], data=request_data).status_code == 200

    def user_exists(self, username):
        try:
            self.get_user(username)
            return True
        except JSONDecodeError:
            return False

    def authenticate(self, user_id, password):
        request_data = {
            "id": user_id,
            "password": password
        }
        response = self.session.post(self.endpoints["session"], data=request_data)
        if response.status_code == 200:
            self.authenticated = True
            return True
        return False

    def get_feed(self):
        response = self.session.get(self.endpoints["feed"])
        return response.json()

    def get_feed_next(self):
        response = self.session.get(self.endpoints["feed_next"])
        return response.json()

    def get_page(self, page_id):
        request_data = {
            "id": page_id
        }
        response = self.session.get(self.endpoints["page"], params=request_data)
        return response.json()

    def get_journal(self, journal_id):
        request_data = {
            "id": journal_id
        }
        response = self.session.get(self.endpoints["journal"], params=request_data)
        return response.json()

    def get_account_pages(self, account_id):
        request_data = {
            "id": account_id
        }
        response = self.session.get(self.endpoints["account_pages"], params=request_data)
        return response.json()

    def get_journal_pages(self, journal_id):
        request_data = {
            "id": journal_id
        }
        response = self.session.get(self.endpoints["journal_pages"], params=request_data)
        return response.json()

    def delete_account(self, account_id):
        request_data = {
            "id": account_id
        }
        response = self.session.delete(self.endpoints["account"], params=request_data)
        return response.status_code == 200

    def delete_journal(self, journal_id):
        request_data = {
            "id": journal_id
        }
        response = self.session.delete(self.endpoints["journal"], params=request_data)
        return response.status_code == 200

    def delete_page(self, page_id):
        request_data = {
            "id": page_id
        }
        response = self.session.delete(self.endpoints["page"], params=request_data)
        return response.status_code == 200


if __name__ == '__main__':
    f = Folio("http://localhost:4567")
    f.add_account("joe","joe","joe","joe")
    print(f.get_user_id("lo"))
