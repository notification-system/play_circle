import requests
import json
import bddtests.config as c


def test_auth_user_creation():
    print("Get Text response")
    r = requests.post(
        url=c.create_user_url, data=json.dumps(c.api_user_create), headers=c.headers
    )
    json_result = r.json()
    assert json_result.get("email") == c.api_user_create.get("email")
    assert r.status_code == 201


def test_auth_conflict_user_creation():
    r = requests.post(
        url=c.create_user_url, data=json.dumps(c.api_user_create), headers=c.headers
    )
    assert r.status_code == 409


def test_auth_get_token():
    r = requests.post(
        url=c.get_token_url, data=json.dumps(c.api_user_create), headers=c.headers
    )
    assert r.status_code == 200
    assert r.json is not None


def test_get_status(auth_get_token):
    r = requests.post(
        url=c.status_token_url, data=json.dumps(auth_get_token), headers=c.headers
    )
    assert r.text == "true"


def test_auth_get_user_profile(auth_get_token):
    json_bearer = "Bearer " + auth_get_token.get("accessToken")
    headers_auth = {"Content-type": "application/json", "Authorization": json_bearer}
    r = requests.get(url=c.create_user_url, headers=headers_auth)
    user_profile = r.json()
    assert user_profile.get("firstName") == c.api_user_get.get("firstName")
    assert user_profile.get("lastName") == c.api_user_get.get("lastName")
    assert user_profile.get("email") == c.api_user_get.get("email")


def test_auth_logout(auth_get_token):
    json_bearer = "Bearer " + auth_get_token.get("accessToken")
    headers_auth = {"Content-type": "application/json", "Authorization": json_bearer}
    r = requests.get(url=c.logout_token_url, headers=headers_auth)
    assert r.status_code == 204


def test_email_admin_inputsystems_create(auth_get_token):
    json_bearer = "Bearer " + auth_get_token.get("accessToken")
    headers_auth = {"Content-type": "application/json", "Authorization": json_bearer}
    r = requests.post(
        url=c.create_inputsystemdto_url,
        data=json.dumps(c.create_inputsystemdto),
        headers=headers_auth,
    )
    created_input_system = r.json()
    assert r.status_code == 201
    assert created_input_system.get("name") == c.create_inputsystemdto.get("name")


def test_email_admin_get_emailproviders(auth_get_token):
    json_bearer = "Bearer " + auth_get_token.get("accessToken")
    headers_auth = {"Content-type": "application/json", "Authorization": json_bearer}
    r = requests.get(url=c.get_emailproviders_url, headers=headers_auth)
    assert r.status_code == 200
    assert r.text == '["DEFAULT","YANDEX","GMAIL"]'
