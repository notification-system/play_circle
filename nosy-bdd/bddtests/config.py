host = "http://localhost:8081"

api_version = "/api/v1"

create_user_url = f"{host}{api_version}/nosy/users"

get_token_url = f"{host}{api_version}/nosy/auth/token"

status_token_url = f"{host}{api_version}/nosy/auth/status"

logout_token_url = f"{host}{api_version}/nosy/auth/logout"

create_inputsystemdto_url = f"{host}{api_version}/nosy/inputsystems"

get_emailproviders_url = f"{host}{api_version}/nosy/inputsystems/emailproviders"

api_user_create = {
    "firstName": "testFirstName",
    "lastName": "testLastName",
    "email": "adasddddas@nosy.tech",
    "password": "testPassword",
}

api_user_get = {
    "firstName": "testFirstName",
    "lastName": "testLastName",
    "email": "adasddddas@nosy.tech",
}

create_inputsystemdto = {"name": "inputsystemTest"}

headers = {"Content-type": "application/json"}
