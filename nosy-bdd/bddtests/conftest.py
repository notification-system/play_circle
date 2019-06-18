import pytest
import requests
import json
import bddtests.config as c


@pytest.fixture(scope="module")
def auth_get_token():
    r = requests.post(
        url=c.get_token_url, data=json.dumps(c.api_user_create), headers=c.headers
    )
    yield r.json()
